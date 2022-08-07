package com.siloam.restapi.dto.shopee;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShopeeLoginResponseDto {

    @JsonProperty("bff_meta")
    private String bffMeta;
    @JsonProperty("error")
    private Integer errorCode;
    @JsonProperty("error_msg")
    private String errorMessage;
    @JsonProperty("data")
    private ShopeeLoginResponseDataDto data;
}
