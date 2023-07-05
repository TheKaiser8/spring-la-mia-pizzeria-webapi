package org.lessons.springlamiapizzeriacrud.dto;

import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.URL;
import org.lessons.springlamiapizzeriacrud.model.Ingredient;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

// DTO: Data Transfer Object
public class PizzaForm {

    // FIELDS DTO
    // elimino le annotation da db perché PizzaForm non viene salvato su db
    private Integer id;

    @NotBlank(message = "Il nome è obbligatorio, il campo non può essere vuoto")
    @Size(min = 1, max = 100, message = "Il nome deve avere un numero di caratteri compreso tra 1 e 100")
    private String name;

    @NotBlank(message = "La descrizione è obbligatoria, il campo non può essere vuoto")
    @Size(min = 1, max = 500, message = "La descrizione deve avere un numero di caratteri compreso tra 1 e 500")
    private String description;

    @NotBlank(message = "L''URL immagine è obbligatorio, il campo non può essere vuoto") // per messaggio
    @Size(min = 1, max = 500, message = "L''URL deve avere un numero massimo di 500 caratteri")
    @URL(message = "Il campo URL deve essere un indirizzo valido")
    private String photoUrl;

    @NotNull(message = "Il prezzo è obbligatorio, non può essere nullo")
    @DecimalMin(value = "0.00", message = "Il prezzo deve essere maggiore o uguale a zero")
    @DecimalMax(value = "99.99", message = "Il prezzo deve essere minore o uguale a 99.99")
    private BigDecimal price;

    private MultipartFile coverFile;

    // RELATIONSHIP ATTRIBUTES DTO
    // la lista delle offerte la eliminiamo perché nel form non è richiesta
    private List<Ingredient> ingredients = new ArrayList<>(); // relazione con ingredient, inizializzo la lista vuota per non avere errori se la lista risultasse null

    // GETTERS & SETTERS DTO
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

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public MultipartFile getCoverFile() {
        return coverFile;
    }

    public void setCoverFile(MultipartFile coverFile) {
        this.coverFile = coverFile;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
}
