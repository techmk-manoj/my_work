package com.m3bi.common.reservation.exception;

import com.m3bi.common.exception.ShbsException;

public class AvailableRoomsNotEnoughException extends ShbsException {
    public AvailableRoomsNotEnoughException() {
        super("Quantity requested is bigger than the available rooms for " +
                "the specified type ");
    }

    @Override
    public String getErrorCode() {
        return "available_rooms_not_enough";
    }
}
