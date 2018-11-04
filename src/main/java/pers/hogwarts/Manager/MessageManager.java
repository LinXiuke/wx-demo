package pers.hogwarts.Manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.hogwarts.form.MessageForm;
import pers.hogwarts.model.ImageNode;
import pers.hogwarts.model.ImageVO;

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
}
