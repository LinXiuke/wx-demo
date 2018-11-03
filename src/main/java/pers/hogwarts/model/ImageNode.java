package pers.hogwarts.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

/**
 * @Description:
 * @Author: Hogwarts
 * @Date: 2018/11/1
 */
@Data
public class ImageNode {

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "MediaId")
    private String mediaId;
}
