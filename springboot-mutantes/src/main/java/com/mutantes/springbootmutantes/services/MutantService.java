package com.mutantes.springbootmutantes.services;

import org.springframework.stereotype.Component;

import exceptions.BusinessException;

@Component
public class MutantService implements MutantServiceInterface  {

	private final static int CANT_MAX_SECUENCIA = 4;
	
	private final static char CARACTER_INEXISTENTE = '#';
	
	/**
	 * Verifica si un array de String contiene 
	 * al menos 2 secuencias de caracteres iguales
	 *
	 * @param cadenaAdn array de String
	 * @return true si cumple la condicion, false en caso opuesto
	 */
	public boolean isMutant(String cadenaAdn[]) throws BusinessException {
		boolean resultado = false ;
		
		validarCadenaAdn(cadenaAdn);
		existeUsuarioEnBd(cadenaAdn);
		
		if(resultado) {
			return resultado;
		}else{
			char matrizadn[][] = getMatrizAdn(cadenaAdn);
			int totalesHorizontales = getTotalHorizontales(matrizadn);
			int totalesVerticales = getTotalVerticales(matrizadn);
			int totalOblicuas = getTotalOblicuas(matrizadn);

			System.out.println("total Horizontales : " + totalesHorizontales);
			System.out.println("total Verticales : " + totalesVerticales);
			System.out.println("total Oblicuas : " + totalOblicuas);

			return (totalesHorizontales + totalesVerticales + totalOblicuas) >= 2 ;
		}

	}
	
	/**
	 * Obtiene el porcentaje de mutantes en base al total de personas
	 *
	 * @param 
	 * @return String de porcentaje
	 */
	@Override
	public String getMutantStats() {
		return "100%";
	}
	
	/**
	 * Obtiene una matriz de NxN en base a un array de Strings
	 *
	 * @param cadenaAdn array de String
	 * @return Matriz de nxn de char
	 */
	@Override
	public char[][] getMatrizAdn(String cadenaAdn[]){
		char[][] matrizAdn = new char[cadenaAdn.length][cadenaAdn.length];
		for (int i = 0; i < cadenaAdn.length; i++) {
			for (int j = 0; j < cadenaAdn.length; j++) {
				matrizAdn[i][j] = cadenaAdn[i].charAt(j);
			}
		}
		return matrizAdn;
	}
	
	/**
	 * Obtiene el numero de repeticiones horizontales 
	 * en una Matriz de nxn
	 *
	 * @param matrizAdn Matriz de caracteres
	 * @return cantidad de repeticiones horizontales
	 */
	@Override
	public int getTotalHorizontales(char[][] matrizAdn) {
		int totalHorizontales = 0;
		for (int i = 0; i < matrizAdn.length; i++) {
			totalHorizontales += getCantRepeticionesPorFila(matrizAdn[i]);
		}
		return totalHorizontales;
	}
	
	/**
	 * Obtiene el numero de repeticiones verticales 
	 * en una Matriz de nxn
	 *
	 * @param matrizAdn Matriz de caracteres
	 * @return cantidad de repeticiones verticales
	 */
	@Override
	public int getTotalVerticales(char[][] matrizAdn) {
		int totalVerticales = 0;
		
		for (int i = 0; i < matrizAdn.length; i++) {
			totalVerticales += getCantRepeticionesPorFila(getColumnaAdn(matrizAdn, i));
		}
		return totalVerticales;
	}
	
	/**
	 * Obtiene el numero de repeticiones oblicuas 
	 * en una Matriz de nxn
	 *
	 * @param matrizAdn Matriz de caracteres
	 * @return cantidad de repeticiones oblicuas
	 */
	@Override
	public int getTotalOblicuas(char[][] matrizAdn) {
		int totalOblicuas = 0;
		
		for (int i = 0; i <= matrizAdn.length - CANT_MAX_SECUENCIA; i++) {
			char[] cantOblicuasA = new char[matrizAdn.length - i];
			char[] cantOblicuasB = new char[matrizAdn.length - i];
			char[] cantOblicuasC = new char[matrizAdn.length - i];
			char[] cantOblicuasD = new char[matrizAdn.length - i];
			
			for (int j = 0; j < cantOblicuasA.length; j++) {
				cantOblicuasA[j] = matrizAdn[j+i][j];
				cantOblicuasB[j] = matrizAdn[j][j+i];
				cantOblicuasC[j] = matrizAdn[matrizAdn.length-1-j][j+i];
				cantOblicuasD[j] = matrizAdn[matrizAdn.length-1-j-i][j];
			}
			
			if(i == 0) {
				totalOblicuas += getCantRepeticionesPorFila(cantOblicuasA);
				totalOblicuas += getCantRepeticionesPorFila(cantOblicuasC);
				
			}else{
				totalOblicuas += getCantRepeticionesPorFila(cantOblicuasA);
				totalOblicuas += getCantRepeticionesPorFila(cantOblicuasB);
				totalOblicuas += getCantRepeticionesPorFila(cantOblicuasA);
				totalOblicuas += getCantRepeticionesPorFila(cantOblicuasD);
			}			
		}
		return totalOblicuas ;
	}
	
	/**
	 * Obtiene el numero de repeticiones en 
	 * un array de caracteres
	 *
	 * @param filaAdn array de caracteres
	 * @return cantidad de repeticiones en un array
	 */
	@Override
	public int getCantRepeticionesPorFila(char filaAdn[]) {
		char puntero = CARACTER_INEXISTENTE;
		int cantCaractRepetidos = 0;
		int totalRepeticiones = 0 ;
		for (int i = 0; i < filaAdn.length; i++) {
			if(puntero == filaAdn[i]){
				cantCaractRepetidos++;
				
				if(cantCaractRepetidos>=CANT_MAX_SECUENCIA){
					totalRepeticiones++;
				}
			}else{
				cantCaractRepetidos=1;
				puntero = filaAdn[i];
			}
		}
		
		return totalRepeticiones;
	}
	
	/**
	 * Valida si una cadena de Adn es valida
	 *
	 * @param cadenaAdn array de String
	 * @return si no valida tira exception
	 */
	@Override
	public void validarCadenaAdn(String cadenaAdn[]) throws BusinessException  {
		for (int i = 0; i < cadenaAdn.length; i++) {
			if (cadenaAdn[i].length() != cadenaAdn.length) {
				throw new BusinessException()  ;
			}
		}
	}
	
	
	private char[] getColumnaAdn(char matrizadn[][], int i) {
		char[] columnaAdn = new char[matrizadn.length]; 
		for (int j = 0; j < matrizadn.length; j++) {
			columnaAdn[j] = matrizadn[j][i];
		}
		return columnaAdn;
	}
	
	private boolean existeUsuarioEnBd(String cadenaAdn[]) {
		return false;
	}
}
