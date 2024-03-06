package com.samangkar.Samangkar.dto;

import com.samangkar.Samangkar.model.Shop;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShopDto {

    private Long id;
    private String name;
    private String description;
    private String shopImageUrl;
    private Long ownerId;
    private Boolean trending;
    private Boolean activate;
    private Long create_by;


    public Shop toEntity() {
        Shop shop = new Shop();
        shop.setName(name);
        shop.setDescription(description);
        shop.setShopImageUrl(shopImageUrl);
        shop.setTrending(trending);
        shop.setActivated(activate);
        return shop;
    }
}
