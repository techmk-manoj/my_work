package com.m3bi.admin.reservation;

import com.m3bi.common.reservation.Reservation;
import com.m3bi.common.reservation.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin/reservation")
@RequiredArgsConstructor
public class ReservationDashboardController {

    private final ReservationService reservationService;

    @GetMapping
    public String index(Model model) {
        final Iterable<Reservation> reservations = reservationService.findAll();
        model.addAttribute("reservations", reservations);
        return "admin/reservation/index";
    }
}
