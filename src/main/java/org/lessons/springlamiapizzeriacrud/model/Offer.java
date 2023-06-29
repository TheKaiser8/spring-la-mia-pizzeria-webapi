package org.lessons.springlamiapizzeriacrud.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@Entity
@Table(name = "offers")
public class Offer {

    // FIELDS
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private LocalDate startOfferDate;

    @NotNull
    private LocalDate endOfferDate;

    @NotBlank(message = "Il titolo è obbligatorio, il campo non può essere vuoto")
    @Size(min = 1, max = 50, message = "Il titolo deve avere almeno 1 carattere e non superare i 50 caratteri")
    @Column(nullable = false)
    private String offerTitle;

    // RELATIONSHIP ATTRIBUTE
    @ManyToOne // molte offerte speciali possono essere collegate ad una pizza
    @JoinColumn(nullable = false)
    private Pizza pizza; // attributo che contiene la relazione con Pizza (LATO PRINCIPALE)

    // GETTERS & SETTERS
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getStartOfferDate() {
        return startOfferDate;
    }

    public void setStartOfferDate(LocalDate startOfferDate) {
        this.startOfferDate = startOfferDate;
    }

    public LocalDate getEndOfferDate() {
        return endOfferDate;
    }

    public void setEndOfferDate(LocalDate endOfferDate) {
        this.endOfferDate = endOfferDate;
    }

    public String getOfferTitle() {
        return offerTitle;
    }

    public void setOfferTitle(String offerTitle) {
        this.offerTitle = offerTitle;
    }

    public Pizza getPizza() {
        return pizza;
    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }
}
