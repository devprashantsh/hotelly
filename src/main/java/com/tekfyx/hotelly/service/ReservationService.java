package com.tekfyx.hotelly.service;

import com.tekfyx.hotelly.entity.Reservation;
import com.tekfyx.hotelly.entity.Room;
import com.tekfyx.hotelly.entity.User;
import com.tekfyx.hotelly.repository.ReservationRepository;
import com.tekfyx.hotelly.repository.RoomRepository;
import com.tekfyx.hotelly.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Date;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;
    
    @Autowired
    private RoomRepository roomRepository;
    
    @Autowired
    private UserRepository userRepository;

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public Reservation getReservationById(Long id) {
        return reservationRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Reservation not found"));
    }

    public List<Reservation> getUserReservations(Long userId) {
        return reservationRepository.findByUserId(userId);
    }

    public Reservation createReservation(Long userId, Long roomId, Date checkIn, Date checkOut) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found"));
        
        Room room = roomRepository.findById(roomId)
            .orElseThrow(() -> new RuntimeException("Room not found"));
        
        List<Room> availableRooms = roomRepository.findAvailableRooms(room.getHotel().getId(), checkIn, checkOut);
        if (!availableRooms.contains(room)) {
            throw new RuntimeException("Room is not available for the selected dates");
        }

        Reservation reservation = new Reservation();
        reservation.setUser(user);
        reservation.setRoom(room);
        reservation.setCheckInDate(checkIn);
        reservation.setCheckOutDate(checkOut);
        
        return reservationRepository.save(reservation);
    }

    public void cancelReservation(Long id) {
        reservationRepository.deleteById(id);
    }
}
