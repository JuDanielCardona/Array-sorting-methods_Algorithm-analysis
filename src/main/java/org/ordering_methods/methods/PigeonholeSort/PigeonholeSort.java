package org.ordering_methods.methods.PigeonholeSort;

import java.util.Arrays;

public class PigeonholeSort {

    /**
     * Ordena un arreglo de enteros utilizando el algoritmo Pigeonhole Sort.
     *
     * @param arr El arreglo de enteros que se va a ordenar.
     * @param n El tamaño del arreglo.
     */
    public void sort(int[] arr, int n) {
        // Inicializar los valores mínimo y máximo del arreglo
        int min = arr[0];
        int max = arr[0];
        int range, i, j, index;

        // Encontrar los valores mínimo y máximo en el arreglo
        for (int a = 0; a < n; a++) {
            if (arr[a] > max) {
                max = arr[a];
            }
            if (arr[a] < min) {
                min = arr[a];
            }
        }

        // Calcular el rango de valores en el arreglo
        range = max - min + 1;

        // Crear un array de pigeonholes para almacenar las frecuencias de los valores en el arreglo
        int[] phole = new int[range];
        Arrays.fill(phole, 0);

        // Llenar los pigeonholes con las frecuencias de los valores en el arreglo
        for (i = 0; i < n; i++) {
            phole[arr[i] - min]++;
        }

        // Reconstruir el arreglo ordenado utilizando los pigeonholes
        index = 0;
        for (j = 0; j < range; j++) {
            while (phole[j]-- > 0) {
                arr[index++] = j + min;
            }
        }
    }
}
