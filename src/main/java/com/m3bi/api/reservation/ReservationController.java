package com.m3bi.api.reservation;

import com.m3bi.common.customer.Customer;
import com.m3bi.common.customer.CustomerService;
import com.m3bi.common.reservation.Reservation;
import com.m3bi.common.reservation.ReservationService;
import com.m3bi.common.reservation.exception.PointsNotAvailableException;
import com.m3bi.common.roomtype.RoomType;
import com.m3bi.common.roomtype.RoomTypeService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("reservations")
@RequiredArgsConstructor
public class ReservationController {

	private final ReservationService reservationService;

	private final CustomerService customerService;

	private final RoomTypeService roomTypeService;

	/* Room Booking API */
	@PostMapping
	public ResponseEntity<Reservation> create(@Validated @RequestBody ReservationRequest request) throws Exception {

		/* checking Condition for customer id to fetch the data */
		if ((request.getCustomerId() != null) && (request.getCustomerId() != ' ')) {
			Customer customer = customerService.find(request.getCustomerId());
			RoomType roomType = roomTypeService.findById(request.getRoomTypeId());
			double amount = roomType.getPrice() * request.getQuantity();
			/* checking points and amount of room booking if true room booked */
			if (customer.getPoints() >= amount) {
				request.setStatus("Booked");
				request.setTotalAmount(amount);
				customer.setPoints(customer.getPoints() - amount);
				/* checking for minimum points to book the room and room quantity */
			} else if (customer.getPoints() >= 500 && request.getQuantity() < 2) {
				request.setStatus("Booking pending");
				request.setTotalAmount(customer.getPoints());
				customer.setPoints(0);
			} else {
				throw new PointsNotAvailableException();
			}

			/* update the poinst after succesful transaction */

			customerService.update(customer);

		}

		return new ResponseEntity<>(reservationService.create(request), HttpStatus.CREATED);
	}

	/* fetch booking details through booking id */
	@GetMapping("{id}")
	public Reservation find(@PathVariable Integer id) {
		return reservationService.find(id);
	}

	/* update the booking dates */
	@PatchMapping("{id}")
	public Reservation update(@PathVariable Integer id, @Validated @RequestBody ReservationRequest request) {
		return reservationService.update(id, request);
	}

	/* cancel the booking */
	@PatchMapping("{id}/cancel")
	public Reservation cancel(@PathVariable Integer id) {

		/* fetch cancel record through id */
		Reservation reservation = reservationService.cancel(id);
		/* fetch the user for giving back points */
		Customer customer = customerService.find(reservation.getCustomerId());
		customer.setPoints(reservation.getAmountPaid() + customer.getPoints());
		/* update the bonus points */
		customerService.update(customer);

		return reservationService.cancel(id);
	}
}
