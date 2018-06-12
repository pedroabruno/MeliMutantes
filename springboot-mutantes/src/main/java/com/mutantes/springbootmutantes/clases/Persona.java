package com.mutantes.springbootmutantes.clases;

public class Persona {
	long id;
	String adn;
	boolean esMutante;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getAdn() {
		return adn;
	}
	public void setAdn(String adn) {
		this.adn = adn;
	}
	public boolean isEsMutante() {
		return esMutante;
	}
	public void setEsMutante(boolean esMutante) {
		this.esMutante = esMutante;
	}
	
}
