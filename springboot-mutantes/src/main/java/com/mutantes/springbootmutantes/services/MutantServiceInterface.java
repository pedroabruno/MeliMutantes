package com.mutantes.springbootmutantes.services;

import exceptions.BusinessException;

public interface MutantServiceInterface {

	/**
	 * Verifica si un array de String contiene 
	 * al menos 2 secuencias de caracteres iguales
	 *
	 * @param cadenaAdn array de String
	 * @return true si cumple la condicion, false en caso opuesto
	 */
	boolean isMutant(String cadenaAdn[]) throws BusinessException;
	
	/**
	 * Obtiene el porcentaje de mutantes en base al total de personas
	 *
	 * @param 
	 * @return String de porcentaje
	 */
	String getMutantStats();
	
	/**
	 * Obtiene una matriz de NxN en base a un array de Strings
	 *
	 * @param cadenaAdn array de String
	 * @return Matriz de nxn de char
	 */
	char[][] getMatrizAdn(String cadenaAdn[]);
	
	/**
	 * Obtiene el numero de repeticiones horizontales 
	 * en una Matriz de nxn
	 *
	 * @param matrizAdn Matriz de caracteres
	 * @return cantidad de repeticiones horizontales
	 */
	int getTotalHorizontales(char[][] matrizAdn);
	
	/**
	 * Obtiene el numero de repeticiones verticales 
	 * en una Matriz de nxn
	 *
	 * @param matrizAdn Matriz de caracteres
	 * @return cantidad de repeticiones verticales
	 */
	int getTotalVerticales(char[][] matrizAdn);
	
	/**
	 * Obtiene el numero de repeticiones oblicuas 
	 * en una Matriz de nxn
	 *
	 * @param matrizAdn Matriz de caracteres
	 * @return cantidad de repeticiones oblicuas
	 */
	int getTotalOblicuas(char[][] matrizAdn);
	
	/**
	 * Obtiene el numero de repeticiones en 
	 * un array de caracteres
	 *
	 * @param filaAdn array de caracteres
	 * @return cantidad de repeticiones en un array
	 */
	int getCantRepeticionesPorFila(char filaAdn[]);
	
	/**
	 * Valida si una cadena de Adn es valida
	 *
	 * @param cadenaAdn array de String
	 * @return si no valida tira exception
	 */
	void validarCadenaAdn(String cadenaAdn[]) throws BusinessException; 
	
}
