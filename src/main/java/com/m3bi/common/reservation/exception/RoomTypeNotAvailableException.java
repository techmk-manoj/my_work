package com.m3bi.common.reservation.exception;

import com.m3bi.common.exception.ShbsException;

public class RoomTypeNotAvailableException extends ShbsException {
    public RoomTypeNotAvailableException() {
        super("All the rooms with type you specified have been occupied");
    }

    @Override
    public String getErrorCode() {
        return "room_type_not_available";
    }
}
