package com.m3bi.common.reservation.exception;

import com.m3bi.common.exception.ShbsException;

public class SchedularJobException extends ShbsException{
	


	public SchedularJobException() {
		super("Update user booking status Exception");
	}

	@Override
	public String getErrorCode() {
		return "No Record for update booking status";
	}



}
