package com.m3bi.bookingupdate.job;

import java.util.ArrayList;
import java.util.List;

import org.quartz.CronTrigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.m3bi.common.customer.Customer;
import com.m3bi.common.customer.CustomerService;
import com.m3bi.common.reservation.Reservation;
import com.m3bi.common.reservation.ReservationService;
import com.m3bi.common.reservation.exception.SchedularJobException;
import com.m3bi.common.roomtype.RoomTypeService;

@Component
public class BookingUpdateJob {
	
	@Autowired
	ReservationService reservationService;

	@Autowired
	CustomerService customerService;

	@Autowired
	RoomTypeService roomTypeService;

	@Scheduled(cron = "0 */3 * ? * *")
	public void print() {

		List<Reservation> resv = new ArrayList<Reservation>();
		/* getting all customer for fetch Booking Record */

		List<Integer> custId = new ArrayList<Integer>();

		List<Customer> customer = customerService.getALlCustomer();
		int id = 0;
		for (Customer cs : customer) {
			id = cs.getId();
			custId.add(id);
		}

		/* fetch those record whose status is booking pending by userid */
		Iterable<Reservation> reservation = reservationService.getAllBookingByCustId(custId, "Booking pending");
		if (reservation != null) {
			/* iterate one after another record */
			for (Reservation rs : reservation) {
				/* fetch user for updated points */
				Customer cust = customerService.find(rs.getCustomerId());
				double chechpoints = cust.getPoints() + rs.getAmountPaid();
				double roomAmount = rs.getRoomAmount();
				double points = rs.getAmountPaid() + cust.getPoints() - roomAmount;
				/* check for room amount by adding update and previous */
				if (chechpoints >= rs.getRoomAmount()) {
					rs.setStatus("Booked");
					rs.setAmountPaid(roomAmount);
					cust.setPoints(points);
					/* update user points after paying */
					customerService.update(cust);
				} else {
					rs.setStatus("Booking pending");
				}

				resv.add(rs);
			}
			/* update booking status */
			reservationService.update(resv);
		} else {
			throw new SchedularJobException();
		}

	}

}
