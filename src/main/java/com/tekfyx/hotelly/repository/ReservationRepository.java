package com.tekfyx.hotelly.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tekfyx.hotelly.entity.Reservation;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByUserId(Long userId);
    List<Reservation> findByRoomHotelId(Long hotelId);
}
