package model.logic;

import java.util.Random;

import edu.princeton.cs.introcs.StdRandom;

/**
 * Clase que organiza un arreglo de objetos Comparables que llega por parametro
 * 
 * @author andre
 *
 */
public class Sorting<T> {
	/**
	 * Arreglo auxiliar para ordenamiento
	 */
	private static Comparable[] aux;

	public Sorting() {
	}

	/**
	 * Organiza el arreglo que llega por parámetro con el método shell
	 * @param principal
	 * 					Arreglo a ordenar
	 */					
	public static void shellSort(Comparable[] principal) {
		//Tomado de geeksforgeeks
		int mitad=principal.length/2;
		while (mitad>0) {
			int j =0;
			for (int i =mitad; i<principal.length;i++) {
				Comparable elemento=principal[i];
				for ( j= i; j>=mitad && principal[j-mitad].compareTo(elemento)>0;j-=mitad )
					principal[j]=principal[j-mitad];
				principal[j]=elemento; 
			}
			mitad/=2;
		}
	}
	/**
	 * Organiza el arreglo que llega por parametro usando merge sorting, es el
	 * que llama la clase principal
	 * 
	 * @param principal
	 *            arreglo a ordenar
	 */
	public static void mergeSort(Comparable[] principal) {
		aux = new Comparable[principal.length];
		mergeSort(0, principal.length - 1, principal);
	}

	/**
	 * Organiza el arreglo que llega por parametro de manera recursiva
	 * (MergeSorting)
	 * 
	 * @param lo
	 *            indice del elemento donde inicia el ordenamiento
	 * @param hi
	 *            indice del elemento donde termina el ordenamiento
	 * @param arr
	 *            arreglo a terminar
	 */
	public static void mergeSort(int low, int hi, Comparable[] arr) {
		if (low >= hi)
			return;
		int mid = (low + hi) / 2;
		mergeSort(low, mid, arr);
		mergeSort(mid + 1, hi, arr);
		merge(arr, low, hi, mid);
	}

	/**
	 * Combina de manera ordenada dos subarreglos ordenados disyuntos
	 * 
	 * @param arr
	 *            arreglo que contiene los arreglos disyuntos
	 * @param lo
	 *            primer elemento del subarreglo 1
	 * @param hi
	 *            ultimo elemento del sub arreglo 2
	 * @param mid
	 *            donde termina el subarreglo 1
	 */
	public static void merge(Comparable[] arr, int low, int hi, int mid) {
		// Tomado de las presentaciones en sicua (Slides clase 9)
		int i = low;
		int j = mid + 1;
		for (int k = low; k <= hi; k++) {
			aux[k] = arr[k];
		}
		for (int k = low; k <= hi; k++) {
			if (i > mid)
				arr[k] = aux[j++];
			else if (j > hi)
				arr[k] = aux[i++];
			else if ((aux[j].compareTo(aux[i])) <= 0)
				arr[k] = aux[j++];
			else
				arr[k] = aux[i++];
		}
		
	}

	/**
	 * Retorna el elemento en donde termina-comienza la particion del arreglo
	 * 
	 * @param principal
	 *            el arreglo a ordenar
	 * @param el
	 *            primer elemento del subarreglo a ordenar
	 * @param hi
	 *            el ultimo elemento del subarreglo a ordenar
	 * @return elemento donde termina un particion
	 */
	private static int quickParticion(Comparable[] principal, int low, int hi) {
		int i = low;
		int j = hi + 1;
		Comparable v = principal[low];
		while (true) {

			while (principal[++i].compareTo(v) <= 0)
				if (i == hi)
					break;
			while (v.compareTo(principal[--j]) <= 0)
				if (j == low)
					break;
			if (i >= j)
				break;
			exchange(principal, i, j);
		}
		exchange(principal, low, j);
		return j;
	}

	/**
	 * Organiza el arreglo que llega por parametro usando shell sorting
	 * 
	 * @param principal
	 *            arreglo a ordenar
	 */
	public static void quickSort(Comparable[] principal) {
		// Utiliza libreria adjunta al texto
		// guia:https://introcs.cs.princeton.edu/java/stdlib/
		StdRandom.shuffle(principal);
		quickSort(principal, 0, principal.length-1);
	}

	/**
	 * Ordena los subarreglos de manera recursiva usando quick sorting
	 * 
	 * @param arreglo
	 *            el subarreglo a ordenar
	 * @param hi
	 *            fin del arreglo
	 * @param low
	 *            inicio del arreglo
	 */
	public static void quickSort(Comparable[] arreglo, int low, int hi) {
		// Tomado de "Slides clase 9" en Sicua
		if (hi <= low)
			return;
		int j = quickParticion(arreglo, low, hi);
		quickSort(arreglo, low, j - 1);
		quickSort(arreglo, j + 1, hi);
	}

	/**
	 * Intercambia la posicion en el arreglo de los dos elementos que entran por
	 * parametro
	 * 
	 * @param arr
	 *            arreglo en el que estan los elementos
	 * @param a
	 *            indice del primer elemento
	 * @param b
	 *            indice del segundo elemento
	 */
	public static void exchange(Comparable[] arr, int a, int b) {
		Comparable E = arr[a];
		arr[a] = arr[b];
		arr[b] = E;
	}
	/**
	 * Ordena por merge pero usando el codigo para comararar
	 * @param principal
	 */
	public static void mergeSortCodigo(Comparendo[] principal)
	{
		// Tomado de las presentaciones en sicua (Slides clase 9)
		aux = new Comparable[principal.length];
		mergeSortCodigo(0, principal.length - 1, principal);
	}
	/**
	 * Ordena por merge pero usando el codigo para comararar
	 * @param low
	 * @param hi
	 * @param arr
	 */
	public static void mergeSortCodigo(int low, int hi, Comparendo[] arr) {
		if (low >= hi)
			return;
		int mid = (low + hi) / 2;
		mergeSort(low, mid, arr);
		mergeSort(mid + 1, hi, arr);
		mergeporCodigo(arr, low, hi, mid);
	}
	/**
	 * Ordena por merge pero usando el codigo para compararar
	 * @param arr
	 * @param low
	 * @param hi
	 * @param mid
	 */
	public static void mergeporCodigo(Comparendo[] arr, int low, int hi, int mid) {
		// Tomado de las presentaciones en sicua (Slides clase 9)
		int i = low;
		int j = mid + 1;
		for (int k = low; k <= hi; k++) {
			aux[k] = arr[k];
		}
		for (int k = low; k <= hi; k++) {
			if (i > mid)
				arr[k] = (Comparendo) aux[j++];
			else if (j > hi)
				arr[k] = (Comparendo) aux[i++];
			else if ((((Comparendo) aux[j]).compareCodigo((Comparendo) aux[i])) <= 0)
				arr[k] = (Comparendo) aux[j++];
			else
				arr[k] = (Comparendo) aux[i++];
		}
	}
}
