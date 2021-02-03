package com.m3bi.common.reservation.exception;

import com.m3bi.common.exception.ShbsException;

public class PointsNotAvailableException extends ShbsException {

	public PointsNotAvailableException() {
		super("Not sufficient point for room booking");
	}

	@Override
	public String getErrorCode() {
		return "Points Not Available for booking";
	}

}
