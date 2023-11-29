package com.dam.Wordle_Proyecto.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dam.Wordle_Proyecto.repositorio.entidades.Palabra;

public interface  PalabraRepository extends JpaRepository<Palabra, Long> {
	

}
