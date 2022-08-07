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
public class ShopeeCartResponseDto {

    @JsonProperty("error")
    private Integer errorCode;
    @JsonProperty("error_message")
    private String errorMessage;
}
