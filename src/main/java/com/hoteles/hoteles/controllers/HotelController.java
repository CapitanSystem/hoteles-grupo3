package com.hoteles.hoteles.controllers;
import com.hoteles.hoteles.models.HotelModel;
import com.hoteles.hoteles.models.RatingModel;
import com.hoteles.hoteles.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/hotels")
public class HotelController {

    @Autowired
    HotelService hotelService;

    @GetMapping
    public List<HotelModel> getAllHotels() {
        return hotelService.getAllHotels();
    }

    @PostMapping
    public ResponseEntity<?> createHotel(@RequestBody HotelModel hotelModel) {
        try {
            HotelModel createdHotel = hotelService.createHotel(hotelModel);
            return ResponseEntity.ok(createdHotel);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public HotelModel detailHotel(@PathVariable Long id) {
        return hotelService.detailHotel(id);
    }

    @PatchMapping("/{id}")
    public HotelModel updateHotel(@PathVariable Long id, @RequestBody HotelModel hotelModel) {
        return hotelService.updateHotel(id, hotelModel);
    }

    @PostMapping("/{hotelId}/ratings")
    public RatingModel addRating(@PathVariable Long hotelId, @RequestBody RatingModel ratingModel) {
        return hotelService.addRatingToHotel(hotelId, ratingModel);
    }

    @PatchMapping("/{hotelId}/{ratingId}/ratings")
    public RatingModel editRating(@PathVariable Long hotelId, @PathVariable Long ratingId, @RequestBody RatingModel ratingModel) {
        return hotelService.updateRating(hotelId, ratingId, ratingModel);
    }

}
