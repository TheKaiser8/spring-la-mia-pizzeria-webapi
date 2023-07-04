package org.lessons.springlamiapizzeriacrud.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.URL;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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

    @NotBlank(message = "L''URL immagine è obbligatorio, il campo non può essere vuoto") // per messaggio
    @Size(min = 1, max = 500, message = "L''URL deve avere un numero massimo di 500 caratteri")
    @URL(message = "Il campo URL deve essere un indirizzo valido")
    private String photoUrl;

    @NotNull(message = "Il prezzo è obbligatorio, non può essere nullo")
    @DecimalMin(value = "0.00", message = "Il prezzo deve essere maggiore o uguale a zero")
    @DecimalMax(value = "99.99", message = "Il prezzo deve essere minore o uguale a 99.99")
    private BigDecimal price;

    private LocalDateTime createdAt;

    // RELATIONSHIP ATTRIBUTES
    // una pizza può avere più offerte speciali
    @JsonIgnore // ignoro le offers per evitare la ricorsione infinita
    @OneToMany(mappedBy = "pizza", cascade = {CascadeType.REMOVE})
    // specifichiamo l'attributo con cui la relazione è già stata mappata per evitare che Hibernate crei una tabella ponte
    // attributo cascade: se cancello una pizza rimuovo anche tutte le offerte associate ad essa
    private List<Offer> offers = new ArrayList<>(); // relazione con offer, inizializzo la lista vuota per non avere errori se la lista risultasse null

    @ManyToMany
    @JoinTable(
            name = "pizza_ingredient",
            joinColumns = @JoinColumn(name = "pizza_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id")
    )
    private List<Ingredient> ingredients = new ArrayList<>(); // relazione con ingredient, inizializzo la lista vuota per non avere errori se la lista risultasse null

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

    // aggiungo getters & setters del nuovo attributo offers
    public List<Offer> getOffers() {
        return offers;
    }

    public void setOffers(List<Offer> offers) {
        this.offers = offers;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    @JsonIgnore
    public String getFormattedCreatedAt() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy MMMM dd 'at' HH:mm");
        return createdAt.format(formatter);
    }
}
