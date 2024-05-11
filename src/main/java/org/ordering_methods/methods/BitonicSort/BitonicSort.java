package org.ordering_methods.methods.BitonicSort;

public class BitonicSort {

    /**
     * Compara y realiza un intercambio entre los elementos del arreglo según la
     * dirección especificada.
     *
     * @param a   El arreglo en el que se realiza la comparación e intercambio.
     * @param i   El índice del primer elemento a comparar.
     * @param j   El índice del segundo elemento a comparar.
     * @param dir La dirección de la comparación: 1 para ascendente y 0 para
     *            descendente.
     */
    void compAndSwap(int[] a, int i, int j, int dir) {
        // Compara e intercambia los elementos según la dirección
        if ((a[i] > a[j] && dir == 1) || (a[i] < a[j] && dir == 0)) {
            // Intercambio de elementos
            int temp = a[i];
            a[i] = a[j];
            a[j] = temp;
        }
    }

    /**
     * Realiza la fusión bitónica de una sección del arreglo según la dirección
     * especificada.
     *
     * @param a   El arreglo en el que se realiza la fusión.
     * @param low El índice de inicio de la sección a fusionar.
     * @param cnt La cantidad de elementos en la sección a fusionar.
     * @param dir La dirección de la fusión: 1 para ascendente y 0 para descendente.
     */
    void bitonicMerge(int[] a, int low, int cnt, int dir) {
        if (cnt > 1) {
            int k = cnt / 2;
            for (int i = low; i < low + k; i++) {
                compAndSwap(a, i, i + k, dir);
            }
            bitonicMerge(a, low, k, dir);
            bitonicMerge(a, low + k, k, dir);
        }
    }

    /**
     * Realiza la clasificación bitónica de una sección del arreglo según la
     * dirección especificada.
     *
     * @param a   El arreglo que se va a clasificar.
     * @param low El índice de inicio de la sección a clasificar.
     * @param cnt La cantidad de elementos en la sección a clasificar.
     * @param dir La dirección de la clasificación: 1 para ascendente y 0 para
     *            descendente.
     */
    void bitonicSort(int[] a, int low, int cnt, int dir) {
        if (cnt > 1) {
            int k = cnt / 2;
            bitonicSort(a, low, k, 1);
            bitonicSort(a, low + k, k, 0);
            bitonicMerge(a, low, cnt, dir);
        }
    }

    /**
     * Inicia el proceso de clasificación bitónica de un arreglo.
     *
     * @param a  El arreglo que se va a clasificar.
     * @param N  El tamaño del arreglo.
     * @param up La dirección de clasificación: 1 para ascendente y 0 para
     *           descendente.
     */
    public void sort(int[] a, int N, int up) {
        bitonicSort(a, 0, N, up);
    }
}