package com.tekfyx.hotelly.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tekfyx.hotelly.entity.Hotel;
import java.util.List;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
    List<Hotel> findByNameContainingIgnoreCase(String name);
    List<Hotel> findByLocationContainingIgnoreCase(String location);
}
