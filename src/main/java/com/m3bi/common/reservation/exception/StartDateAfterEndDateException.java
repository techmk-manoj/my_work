package com.m3bi.common.reservation.exception;

import com.m3bi.common.exception.ShbsException;

public class StartDateAfterEndDateException extends ShbsException {
    public StartDateAfterEndDateException() {
        super("Start date must be before end date");
    }

    @Override
    public String getErrorCode() {
        return "start_date_after_end_date";
    }
}
