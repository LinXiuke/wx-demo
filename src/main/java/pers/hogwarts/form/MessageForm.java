package pers.hogwarts.form;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description:
 * @Author: Hogwarts
 * @Date: 2018/10/31
 */

@Data
@JacksonXmlRootElement(localName = "xml")
public class MessageForm implements Serializable {

    @JacksonXmlProperty(localName = "ToUserName")
    private String toUserName;

    @JacksonXmlProperty(localName = "FromUserName")
    private String fromUserName;

    @JacksonXmlProperty(localName = "CreateTime")
    private Long createTime;

    @JacksonXmlProperty(localName = "MsgType")
    private String msgType;

    @JacksonXmlProperty(localName = "Content")
    private String content;

    @JacksonXmlProperty(localName = "MsgId")
    private Long msgId;

}
