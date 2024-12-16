package com.hoteles.hoteles.repositories;

import com.hoteles.hoteles.models.RatingModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<RatingModel, Long> {
}
