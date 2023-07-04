package org.lessons.springlamiapizzeriacrud.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

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

    // per poter cancellare un ingrediente anche se è associato a delle pizze devo definire l'attributo di relazione
    // specificando che la relazione è già stata mappata nel model Pizza
    @JsonIgnore // ignoro le pizze per evitare la ricorsione infinita
    @ManyToMany(mappedBy = "ingredients")
    private List<Pizza> pizzas = new ArrayList<>(); // relazione con pizza, inizializzo la lista vuota per non avere errori se la lista risultasse null

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

    public List<Pizza> getPizzas() {
        return pizzas;
    }

    public void setPizzas(List<Pizza> pizzas) {
        this.pizzas = pizzas;
    }
}
