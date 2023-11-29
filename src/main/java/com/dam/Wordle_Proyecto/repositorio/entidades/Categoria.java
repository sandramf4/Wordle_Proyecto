package com.dam.Wordle_Proyecto.repositorio.entidades;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "Categoria")
	
public class Categoria {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "idCategoria")
	    private Long idCategoria;

	    @Column(name = "nombreCategoria", nullable = false)
	    private String nombreCategoria;

	    @OneToMany(mappedBy = "categoria")
	    private List<Palabra> palabrasList;

	    // getters and setters
	}

