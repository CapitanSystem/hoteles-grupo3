package com.hoteles.hoteles.models;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.List;


@Entity
@Table(name = "hotel")
@Data
public class HotelModel
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotNull(message = "El nombre no puede ser nulo")
    @Size(min = 1, max = 100, message = "El nombre debe tener entre 1 y 100 caracteres")
    private String name;

    @Column(length = 500) // Limita el tamaño de la descripción en la base de datos
    @Size(max = 500, message = "La descripción no puede exceder los 500 caracteres")
    private String description;

    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<RatingModel> ratings; // Relación uno a muchos

}
