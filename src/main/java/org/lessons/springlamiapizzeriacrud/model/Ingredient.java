package org.lessons.springlamiapizzeriacrud.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "ingredients")
public class Ingredient {

    // FIELDS
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Il nome è obbligatorio, il campo non può essere vuoto")
    @Size(min = 3, max = 50, message = "Il nome deve avere minimo 3 caratteri e non superare i 50 caratteri")
    @Column(nullable = false)
    private String name;

    @Lob
    @Size(max = 500, message = "La descrizione non può superare i 500 caratteri")
    @Column(columnDefinition = "TEXT")
    private String description;

    // GETTERS & SETTERS

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
