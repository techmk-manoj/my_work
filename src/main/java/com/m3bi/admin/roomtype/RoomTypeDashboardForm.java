package com.m3bi.admin.roomtype;

import com.m3bi.common.roomtype.RoomType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class RoomTypeDashboardForm {

    @NotBlank
    private String type;

    private String description;
    private String image;

    @NotNull
    private Integer quantity;

    @NotNull
    private double price;

    public RoomTypeDashboardForm(RoomType roomType) {
        this.type = roomType.getType();
        this.description = roomType.getDescription();
        this.image = roomType.getImage();
        this.quantity = roomType.getQuantity();
        this.price = roomType.getPrice();
    }
}
