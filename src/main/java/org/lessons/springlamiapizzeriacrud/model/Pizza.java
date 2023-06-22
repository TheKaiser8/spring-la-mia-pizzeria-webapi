package org.lessons.springlamiapizzeriacrud.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity // dà significato alla classe, hibernate saprà che deve creare questa entità a database
@Table(name = "pizzas") // diamo il nome alla tabella a database
public class Pizza {

    @Id // indica la chiave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // per indicare l'AUTO-INCREMENT
    private Integer id;

    @Column(nullable = false)
    private String name;

    private String description;

    private String photoUrl;

    private BigDecimal price;

}
