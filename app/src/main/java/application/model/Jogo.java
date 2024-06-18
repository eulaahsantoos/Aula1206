package com.example.demo.model;

import javax.persistence.*;

@Entity
@Table(name = "jogo")
public class Jogo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titulo", length = 255)
    private String titulo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "genero_id", nullable = false)
    private Genero genero;

    @Column(name = "multiplayer")
    private boolean multiplayer;

    // Getters and Setters
    // Constructors (default and parameterized)
    // Equals and HashCode (optional, based on your needs)
}
