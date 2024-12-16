package com.hoteles.hoteles.models;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

@Entity
@Table(name = "rating")
@Data
public class RatingModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Min(value = 0, message = "La calificación mínima es 0")
    @Max(value = 5, message = "La calificación máxima es 5")
    private int value;

    @ManyToOne
    @JoinColumn(name = "hotel_id", nullable = false)
    @JsonBackReference
    private HotelModel hotel; // Relación muchos a uno

    @Column(length = 500)
    @Size(max = 500, message = "El comentario no puede exceder los 500 caracteres")
    private String comment;
}
