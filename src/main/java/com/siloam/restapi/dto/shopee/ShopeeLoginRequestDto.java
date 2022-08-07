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
public class ShopeeLoginRequestDto {

    private String phone;
    private String password;
    @Builder.Default
    @JsonProperty("support_ivs")
    private Boolean supportIvs = Boolean.TRUE;
    @JsonProperty("client_identifier")
    private ShopeeClientIdentifierDto shopeeClientIdentifierDto;
}
