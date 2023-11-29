package com.dam.Wordle_Proyecto.repositorio.entidades;


import java.util.Date;

import jakarta.persistence.*;

@Entity
@Table(name = "Palabras")
public class Palabra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "texto", nullable = false)
    private String texto;

    @Column(name = "fecha", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;

    @ManyToOne
    @JoinColumn(name = "idCategoria", nullable = false)
    private Categoria categoria;

   
}
