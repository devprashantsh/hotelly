package com.tekfyx.hotelly.service;

import com.tekfyx.hotelly.entity.Hotel;
import com.tekfyx.hotelly.entity.Room;
import com.tekfyx.hotelly.repository.HotelRepository;
import com.tekfyx.hotelly.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

@Service
public class HotelService {
    @Autowired
    private HotelRepository hotelRepository;
    
    @Autowired
    private RoomRepository roomRepository;

    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    public Hotel getHotelById(Long id) {
        return hotelRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Hotel not found"));
    }

    public List<Hotel> searchHotels(String query) {
        List<Hotel> byName = hotelRepository.findByNameContainingIgnoreCase(query);
        List<Hotel> byLocation = hotelRepository.findByLocationContainingIgnoreCase(query);
        byName.addAll(byLocation);
        return byName.stream().distinct().toList();
    }

    public List<Room> getAvailableRooms(Long hotelId, Date checkIn, Date checkOut) {
        return roomRepository.findAvailableRooms(hotelId, checkIn, checkOut);
    }

    public Hotel createHotel(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    public Hotel updateHotel(Long id, Hotel hotelDetails) {
        Hotel hotel = getHotelById(id);
        hotel.setName(hotelDetails.getName());
        hotel.setLocation(hotelDetails.getLocation());
        hotel.setDescription(hotelDetails.getDescription());
        return hotelRepository.save(hotel);
    }

    public void deleteHotel(Long id) {
        hotelRepository.deleteById(id);
    }
}
