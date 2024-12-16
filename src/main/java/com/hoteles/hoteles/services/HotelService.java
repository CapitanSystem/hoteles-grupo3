package com.hoteles.hoteles.services;

import com.hoteles.hoteles.exceptions.InvalidRequestException;
import com.hoteles.hoteles.exceptions.ResourceNotFoundException;
import com.hoteles.hoteles.models.HotelModel;
import com.hoteles.hoteles.models.RatingModel;
import com.hoteles.hoteles.repositories.HotelRepository;

import com.hoteles.hoteles.repositories.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private RatingRepository ratingRepository;

    public List<HotelModel> getAllHotels() {
        return hotelRepository.findAll();
    }

    public HotelModel createHotel(HotelModel hotel) {
        if (hotel.getRatings() != null && !hotel.getRatings().isEmpty()) {
            throw new InvalidRequestException("No puedes agregar calificaciones al crear un hotel. El campo ratings debe estar vacío.");
        }
        return hotelRepository.save(hotel);
    }

    public HotelModel detailHotel(Long id) {
        return hotelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel con ID " + id + " no encontrado"));
    }

    public HotelModel updateHotel(Long id, HotelModel hotelModel) {
        HotelModel existingHotel = hotelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel con ID " + id + " no encontrado"));
        existingHotel.setName(hotelModel.getName());
        existingHotel.setDescription(hotelModel.getDescription());
        return hotelRepository.save(existingHotel);
    }


    public RatingModel addRatingToHotel(Long hotelId, RatingModel ratingModel) {
        HotelModel hotelModel = hotelRepository.findById(hotelId).orElseThrow(() -> new ResourceNotFoundException("Hotel con ID " + hotelId + " no encontrado"));
        ratingModel.setHotel(hotelModel);
        return ratingRepository.save(ratingModel);
    }

    public RatingModel updateRating(Long hotelId, Long ratingId, RatingModel ratingModel) {
        HotelModel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel con ID " + hotelId + " no encontrado"));

        RatingModel existingRating = ratingRepository.findById(ratingId)
                .orElseThrow(() -> new ResourceNotFoundException("Calificación con ID " + ratingId + " no encontrada"));

        if (!existingRating.getHotel().getId().equals(hotelId)) {
            throw new ResourceNotFoundException("La calificación no pertenece al hotel con ID " + hotelId);
        }

        existingRating.setValue(ratingModel.getValue());
        existingRating.setComment(ratingModel.getComment());

        return ratingRepository.save(existingRating);

    }

}



