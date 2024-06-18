package com.example.demo.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "genero")
public class Genero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", length = 45)
    private String nome;

    // Getters and Setters
    // Constructors (default and parameterized)
    // Equals and HashCode (optional, based on your needs)
}
