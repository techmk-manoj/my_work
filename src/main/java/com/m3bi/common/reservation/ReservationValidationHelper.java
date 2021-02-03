package com.m3bi.common.reservation;

import com.m3bi.common.reservation.exception.DateSpecifiedAlreadyPassedException;
import com.m3bi.common.reservation.exception.StartDateAfterEndDateException;

import java.time.ZonedDateTime;

public final class ReservationValidationHelper {

    private ReservationValidationHelper() {
    }

    public static void validateReservationTime(ZonedDateTime start, ZonedDateTime end) {
        if (start.isAfter(end)) {
            throw new StartDateAfterEndDateException();
        }

        if (ZonedDateTime.now().isAfter(start)) {
            throw new DateSpecifiedAlreadyPassedException();
        }
    }
}