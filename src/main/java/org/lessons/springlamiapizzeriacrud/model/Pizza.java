package org.lessons.springlamiapizzeriacrud.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.URL;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity // dà significato alla classe, hibernate saprà che deve creare questa entità a database
@Table(name = "pizzas") // diamo il nome alla tabella a database
public class Pizza {

    @Id // indica la chiave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // per indicare l'AUTO-INCREMENT
    private Integer id;

    @NotBlank(message = "Il nome è obbligatorio, il campo non può essere vuoto")
    @Size(min = 1, max = 100, message = "Il nome deve avere un numero di caratteri compreso tra 1 e 100")
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "La descrizione è obbligatoria, il campo non può essere vuoto")
    @Size(min = 1, max = 500, message = "La descrizione deve avere un numero di caratteri compreso tra 1 e 500")
    @Column(columnDefinition = "TEXT")
    private String description;

    @NotBlank(message = "URL immagine è obbligatorio, il campo non può essere vuoto")
    @Size(min = 1, max = 500, message = "URL deve avere un numero massimo di 500 caratteri")
    @URL(message = "Il campo URL deve essere un indirizzo valido")
    private String photoUrl;

    @NotNull(message = "Il prezzo è obbligatorio, non può essere nullo")
    @DecimalMin(value = "0.00", message = "Il prezzo deve essere maggiore o uguale a zero")
    @DecimalMax(value = "99.99", message = "Il prezzo deve essere minore o uguale a 99.99")
    private BigDecimal price;

    private LocalDateTime createdAt;

    // In Spring GETTERS & SETTERS sono obbligatori per ogni campo
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
