package pers.hogwarts.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import pers.hogwarts.form.MessageForm;
import pers.hogwarts.model.ImageNode;
import pers.hogwarts.model.vo.ImageVO;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;

/**
 * @Description:
 * @Author: Hogwarts
 * @Date: 2018/10/31
 */

@Service
public class MessageManager {

    @Autowired
    private AccessTokenManager accessTokenManager;


    public Object getMessage(MessageForm form) {

        String content = form.getContent();

        if ("image".equals(content)) {
            ImageVO imageVO = new ImageVO();
            imageVO.setToUserName(form.getFromUserName());
            imageVO.setFromUserName(form.getToUserName());
            imageVO.setCreateTime(new Date().getTime());
            ImageNode imageNode = new ImageNode();
            imageNode.setMediaId(ImageManager.upload("classpath:image/1.jpg", accessTokenManager.getAccessToken()
                    .getAccessToken()).getString("media_id"));
            imageVO.setImage(imageNode);

            return imageVO;
        }

        MessageVO messageVO = new MessageVO();
        messageVO.setToUserName(form.getFromUserName());
        messageVO.setFromUserName(form.getToUserName());
        messageVO.setCreateTime(new Date().getTime());

        messageVO.setContent("hello");

        return messageVO;
    }


    /**
     * 获取背景图片并将用户头像绘制到背景图片
     * @param openId
     * @param imageUrl
     * @param accessToken
     * @return
     * @throws Exception
     */
    private File getImage(String openId, String imageUrl, String accessToken) throws Exception {

        ClassPathResource classPathResource = new ClassPathResource(imageUrl);
        InputStream imInputStream = classPathResource.getInputStream();
        BufferedImage posterImage = ImageIO.read(imInputStream);


        String headImageUrl = UserManager.getUserInfo(accessToken, openId).getHeadImgUrl();
        BufferedImage headImage = ImageIO.read(new URL(headImageUrl));

        Graphics2D bg = (Graphics2D) posterImage.getGraphics();
        bg.drawImage(makeRoundedCorner(headImage, 720), null, 0, 0);

        File file = new File(openId + ".png");
        if (!file.exists()) {
            file.createNewFile();
        }
        ImageIO.write(posterImage, "png", file);

        return file;
    }


    private BufferedImage makeRoundedCorner(BufferedImage image, int cornerRadius) {
        int w = image.getWidth();
        int h = image.getHeight();
        BufferedImage output = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = output.createGraphics();
        g2.setComposite(AlphaComposite.Src);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.WHITE);
        g2.fill(new RoundRectangle2D.Float(0, 0, w, h, cornerRadius, cornerRadius));
        g2.setComposite(AlphaComposite.SrcAtop);
        g2.drawImage(image, 0, 0, null);
        g2.dispose();
        return output;
    }
}
