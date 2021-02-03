package com.m3bi.common.reservation.exception;

import com.m3bi.common.exception.ShbsException;

public class ReservationStartDateHasPassedException extends ShbsException {
    public ReservationStartDateHasPassedException() {
        super("Reservation Start Date has passed");
    }

    @Override
    public String getErrorCode() {
        return "reservation_start_date_has_passed";
    }
}
