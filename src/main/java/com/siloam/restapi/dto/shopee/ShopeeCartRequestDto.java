package com.siloam.restapi.dto.shopee;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShopeeCartRequestDto {

    @JsonProperty("pre_selected_item_list")
    private List<String> preSelectedItemList;
    @JsonProperty("updated_time_filter")
    private ShopeeTimeFilter shopeeTimeFilter;
    private Integer version;
}
