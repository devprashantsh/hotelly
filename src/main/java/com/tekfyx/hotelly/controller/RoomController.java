package com.tekfyx.hotelly.controller;

import com.tekfyx.hotelly.entity.Room;
import com.tekfyx.hotelly.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/rooms")
@CrossOrigin(origins = "*")
public class RoomController {
    @Autowired
    private RoomService roomService;

    @GetMapping
    public List<Room> getAllRooms() {
        return roomService.getAllRooms();
    }

    @GetMapping("/{id}")
    public Room getRoomById(@PathVariable Long id) {
        return roomService.getRoomById(id);
    }

    @GetMapping("/hotel/{hotelId}")
    public List<Room> getRoomsByHotel(@PathVariable Long hotelId) {
        return roomService.getRoomsByHotel(hotelId);
    }

    @GetMapping("/hotel/{hotelId}/available")
    public List<Room> getAvailableRooms(
            @PathVariable Long hotelId,
            @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") Date checkIn,
            @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") Date checkOut) {
        return roomService.getAvailableRooms(hotelId, checkIn, checkOut);
    }

    @PostMapping("/hotel/{hotelId}")
    public Room createRoom(@PathVariable Long hotelId, @RequestBody Room room) {
        return roomService.createRoom(hotelId, room);
    }

    @PutMapping("/{id}")
    public Room updateRoom(@PathVariable Long id, @RequestBody Room room) {
        return roomService.updateRoom(id, room);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRoom(@PathVariable Long id) {
        roomService.deleteRoom(id);
        return ResponseEntity.ok().build();
    }
}
