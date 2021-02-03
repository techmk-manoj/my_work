package com.m3bi.api.reservation;

import com.m3bi.common.jpa.converter.ZonedDateTimeConverter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Convert;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReservationRequest {

    @NotNull
    private Integer roomTypeId;

    @NotNull
    private Integer customerId;

    @NotNull
    private Integer quantity;

    @Convert(converter = ZonedDateTimeConverter.class)
    private ZonedDateTime startDate;

    @Convert(converter = ZonedDateTimeConverter.class)
    private ZonedDateTime endDate;
    
    private String status;
    
    private Double totalAmount;
    
    
}
