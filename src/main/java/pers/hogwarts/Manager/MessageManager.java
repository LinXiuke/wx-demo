package pers.hogwarts.Manager;

import org.springframework.stereotype.Service;
import pers.hogwarts.form.MessageForm;

import java.util.Date;

/**
 * @Description:
 * @Author: Hogwarts
 * @Date: 2018/10/31
 */

@Service
public class MessageManager {


    public Object getMessage(MessageForm form) {

        String content = form.getContent();

        MessageVO messageVO = new MessageVO();
        messageVO.setToUserName(form.getFromUserName());
        messageVO.setFromUserName(form.getToUserName());
        messageVO.setCreateTime(new Date().getTime());
        messageVO.setMsgType("text");

        messageVO.setContent("hello");

        return messageVO;
    }
}
