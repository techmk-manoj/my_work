package com.m3bi;

import com.m3bi.common.customer.Customer;
import com.m3bi.common.customer.CustomerRepository;
import com.m3bi.common.reservation.Reservation;
import com.m3bi.common.reservation.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;

@Component
public class TestHelper {

	@Autowired
	private ReservationRepository reservationRepository;

	@Autowired
	private CustomerRepository customerRepository;

	public void cleanUp() {
	}

	public Integer createReservation(Integer roomTypeId, Integer customerId, Integer quantity, ZonedDateTime startDate,
			ZonedDateTime endDate, Boolean cancelled, String status, Integer amountpaid) {
		final Reservation reservation = new Reservation();
		reservation.setRoomTypeId(roomTypeId);
		reservation.setCustomerId(customerId);
		reservation.setQuantity(quantity);
		reservation.setStartDate(startDate);
		reservation.setEndDate(endDate);
		reservation.setCancelled(cancelled);
		reservation.setStatus(status);
		reservation.setAmountPaid(amountpaid);
		final Reservation newReservation = reservationRepository.save(reservation);
		return newReservation.getId();
	}

	public Integer createCustomer(String username, double points) {
		final Customer customer = new Customer();
		customer.setName("name");
		customer.setPassword("pass");
		customer.setUsername(username);
		customer.setPoints(points);

		final Customer newCustomer = customerRepository.save(customer);
		return newCustomer.getId();
	}
}