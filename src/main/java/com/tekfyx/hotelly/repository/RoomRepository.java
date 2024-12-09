package com.tekfyx.hotelly.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.tekfyx.hotelly.entity.Room;
import java.util.Date;
import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {
    List<Room> findByHotelId(Long hotelId);
    
    @Query("SELECT r FROM Room r WHERE r.hotel.id = ?1 AND r.id NOT IN " +
           "(SELECT res.room.id FROM Reservation res WHERE " +
           "(?2 BETWEEN res.checkInDate AND res.checkOutDate) OR " +
           "(?3 BETWEEN res.checkInDate AND res.checkOutDate))")
    List<Room> findAvailableRooms(Long hotelId, Date checkIn, Date checkOut);
}
