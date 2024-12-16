package com.hoteles.hoteles.repositories;

import com.hoteles.hoteles.models.HotelModel;
import org.springframework.data.jpa.repository.JpaRepository;


public interface HotelRepository  extends JpaRepository<HotelModel, Long> {


}
