package pers.hogwarts.model.result;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @Description:
 * @Author: Hogwarts
 * @Date: 2018/10/31
 */

@Data
public class AccessTokenResult {

    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("expires_in")
    private Integer expiresIn;
}
