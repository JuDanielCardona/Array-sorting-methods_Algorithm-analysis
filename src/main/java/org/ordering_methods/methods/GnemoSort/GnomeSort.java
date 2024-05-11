package org.ordering_methods.methods.GnemoSort;

public class GnomeSort {

    /**
     * Ordena un arreglo de enteros utilizando el algoritmo de ordenamiento Gnome.
     * Este algoritmo recorre el arreglo y realiza intercambios de elementos adyacentes
     * hasta que el arreglo esté completamente ordenado.
     *
     * @param arr el arreglo de enteros a ordenar
     * @param length la longitud del subarreglo que se desea ordenar
     */
    public void sort(int[] arr, int length) {
        int index = 0;
        while (index < length) {
            if (index == 0 || arr[index] >= arr[index - 1]) {
                index++;
            } else {
                swap(arr, index, index - 1);
                index--;
            }
        }
    }

    /**
     * Intercambia dos elementos en un arreglo dado.
     *
     * @param arr el arreglo en el que se realizará el intercambio
     * @param i el índice del primer elemento a intercambiar
     * @param j el índice del segundo elemento a intercambiar
     */
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
