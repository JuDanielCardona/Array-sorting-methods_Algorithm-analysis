package org.ordering_methods.methods.CombSort;

public class CombSort {

    /**
     * Ordena un arreglo de enteros utilizando el algoritmo Comb Sort.
     *
     * @param arr El arreglo de enteros que se va a ordenar.
     */
    public void sort(int[] arr) {
        int n = arr.length;
        int gap = n;
        boolean swapped = true;

        // Mientras el gap sea mayor a 1 o hubo intercambios en la iteración anterior
        while (gap != 1 || swapped) {
            // Calcula el siguiente gap
            gap = getNextGap(gap);
            swapped = false;

            // Realiza la ordenación utilizando el gap calculado
            for (int i = 0; i < n - gap; i++) {
                // Si el elemento actual es mayor que el elemento en el índice del gap,  intercambiarlos
                if (arr[i] > arr[i + gap]) {
                    int temp = arr[i];
                    arr[i] = arr[i + gap];
                    arr[i + gap] = temp;
                    swapped = true;
                }
            }
        }
    }

    /**
     * Calcula el siguiente valor de gap para el algoritmo Comb Sort.
     *
     * @param gap El gap actual.
     * @return El próximo gap a utilizar en el algoritmo.
     */
    public int getNextGap(int gap) {
        // Reduce el gap actual utilizando un factor de contracción de 1.3
        gap = (gap * 10) / 13;
        // Asegura que el gap sea al menos 1
        if (gap < 1) {
            return 1;
        }
        return gap;
    }
}
