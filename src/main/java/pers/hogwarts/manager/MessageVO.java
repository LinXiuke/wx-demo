package pers.hogwarts.manager;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
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
public class MessageVO implements Serializable {

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "ToUserName")
    private String toUserName;

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "FromUserName")
    private String fromUserName;

    @JacksonXmlProperty(localName = "CreateTime")
    private Long createTime;

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "MsgType")
    private String msgType = "text";

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "Content")
    private String content;

}
