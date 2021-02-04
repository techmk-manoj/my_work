package com.m3bi.common.reservation;

import com.m3bi.api.reservation.ReservationRequest;
import com.m3bi.common.customer.Customer;
import com.m3bi.common.customer.CustomerRepository;
import com.m3bi.common.exception.NotFoundException;
import com.m3bi.common.reservation.exception.AvailableRoomsNotEnoughException;
import com.m3bi.common.reservation.exception.ReservationStartDateHasPassedException;
import com.m3bi.common.roomtype.RoomType;
import com.m3bi.common.roomtype.RoomTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReservationService {

	private final ReservationRepository reservationRepository;
	private final RoomTypeRepository roomTypeRepository;
	private final CustomerRepository customerRepository;

	@Transactional
	public Reservation create(ReservationRequest request) {
		ReservationValidationHelper.validateReservationTime(request.getStartDate(), request.getEndDate());

		validateRoomAvailability(request);

		final Reservation reservation = new Reservation();
		reservation.setRoomTypeId(request.getRoomTypeId());
		reservation.setCustomerId(request.getCustomerId());
		reservation.setQuantity(request.getQuantity());
		reservation.setStartDate(request.getStartDate());
		reservation.setEndDate(request.getEndDate());
		reservation.setCancelled(Boolean.FALSE);
		reservation.setStatus(request.getStatus());
		reservation.setAmountPaid(request.getTotalAmount());
		reservation.setRoomAmount(request.getRoomAmount());

		return reservationRepository.save(reservation);
	}

	public Iterable<Reservation> findAll() {
		return reservationRepository.findAll();
	}

	public Reservation find(Integer id) {
		return reservationRepository.findByIdAndAndCancelled(id, Boolean.FALSE).orElseThrow(() -> new NotFoundException(
				String.format("Reservation with id %d is not found or it has been cancelled", id)));
	}

	public Reservation findForUpdate(Integer id) {
		return reservationRepository.findByIdAndAndCancelledForUpdate(id, Boolean.FALSE)
				.orElseThrow(() -> new NotFoundException(
						String.format("Reservation with id %d is not found or it has been cancelled", id)));
	}

	@Transactional
	public Reservation update(Integer id, ReservationRequest request) {
		ReservationValidationHelper.validateReservationTime(request.getStartDate(), request.getEndDate());

		final Reservation reservation = findForUpdate(id);

		if (ZonedDateTime.now().isAfter(reservation.getStartDate())) {
			throw new ReservationStartDateHasPassedException();
		}

		final RoomType currentRoomType = getRoomType(reservation.getRoomTypeId());

		if (!request.getRoomTypeId().equals(currentRoomType)) {
			validateRoomAvailability(request);
			reservation.setRoomTypeId(request.getRoomTypeId());
		}

		reservation.setCustomerId(request.getCustomerId());
		reservation.setQuantity(request.getQuantity());
		reservation.setStartDate(request.getStartDate());
		reservation.setEndDate(request.getEndDate());

		return reservationRepository.save(reservation);
	}

	@Transactional
	public Reservation cancel(Integer id) {
		final Reservation reservation = findForUpdate(id);

		return reservationRepository.save(reservation);
	}

	private void validateRoomAvailability(ReservationRequest request) {
		final RoomType roomType = getRoomType(request.getRoomTypeId());

		final List<Reservation> reservations = reservationRepository.find(roomType.getId(), request.getStartDate(),
				request.getEndDate());

		final Integer reservedQuantity = reservations.stream().mapToInt(reservation -> reservation.getQuantity()).sum();

		if (request.getQuantity() > (roomType.getQuantity() - reservedQuantity)) {
			throw new AvailableRoomsNotEnoughException();
		}
	}

	private RoomType getRoomType(Integer id) {
		return roomTypeRepository.findOne(id).orElseThrow(() -> new NotFoundException(RoomType.class, id.toString()));
	}
	
	public List<Reservation> getAllBookingByCustId(List<Integer> id, String status) {	
		return reservationRepository.findByCustomerIdInAndStatus(id, status);
	}

	@Transactional
	public Iterable<Reservation> update(List<Reservation> rs) {
		return reservationRepository.save(rs);
	}

}
