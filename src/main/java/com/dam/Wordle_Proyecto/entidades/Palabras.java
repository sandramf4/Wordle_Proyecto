package com.dam.Wordle_Proyecto.entidades;

public class Palabras {
	String texto;
	int id_categoria;
	

	public Palabras(String txt){
		this.texto=txt;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}
	public int getId_categoria() {
		return id_categoria;
	}

	public void setId_categoria(int id_categoria) {
		this.id_categoria = id_categoria;
	}
	
}
