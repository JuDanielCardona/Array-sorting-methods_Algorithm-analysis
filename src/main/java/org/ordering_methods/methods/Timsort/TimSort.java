package org.ordering_methods.methods.Timsort;

public class TimSort {

    /**
     * Tamaño mínimo para dividir el arreglo en runnings durante Tim Sort.
     */
    static final int MIN_MERGE = 32;

    /**
     * Ordena un arreglo de enteros utilizando el algoritmo Tim Sort.
     *
     * @param arr El arreglo de enteros que se va a ordenar.
     * @param n El tamaño del arreglo.
     */
    public void sort(int[] arr, int n) {
        // Determinar la longitud mínima de una secuencia (run)
        int minRun = minRunLength(n);

        // Realizar la ordenación por inserción en subarreglos de tamaño minRun
        for (int i = 0; i < n; i += minRun) {
            insertionSort(arr, i, Math.min(i + minRun - 1, n - 1));
        }

        // Realizar la fusión de los subarreglos ordenados utilizando el algoritmo merge sort
        for (int size = minRun; size < n; size = 2 * size) {
            for (int left = 0; left < n; left += 2 * size) {
                int mid = Math.min(left + size - 1, n - 1);
                int right = Math.min(left + 2 * size - 1, n - 1);

                if (mid < right) {
                    merge(arr, left, mid, right);
                }
            }
        }
    }

    /**
     * Calcula la longitud mínima de una secuencia (run) para el algoritmo Tim Sort.
     *
     * @param n El tamaño del arreglo.
     * @return La longitud mínima de una secuencia (run).
     */
    public int minRunLength(int n) {
        int r = 0;
        while (n >= MIN_MERGE) {
            r |= (n & 1);
            n >>= 1;
        }
        return n + r;
    }

    /**
     * Ordena una parte del arreglo utilizando el algoritmo de inserción.
     *
     * @param arr El arreglo de enteros.
     * @param left El índice de inicio de la sección a ordenar.
     * @param right El índice final de la sección a ordenar.
     */
    public void insertionSort(int[] arr, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            int temp = arr[i];
            int j = i - 1;
            while (j >= left && arr[j] > temp) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = temp;
        }
    }

    /**
     * Fusiona dos subarreglos de un arreglo.
     *
     * @param arr El arreglo de enteros.
     * @param l El índice de inicio del primer subarreglo.
     * @param m El índice final del primer subarreglo.
     * @param r El índice final del segundo subarreglo.
     * @throws IllegalArgumentException si los índices no son válidos.
     */
    public void merge(int[] arr, int l, int m, int r) {
        int len1 = m - l + 1;
        int len2 = r - m;

        if (len1 < 0 || len2 < 0) {
            throw new IllegalArgumentException("Los índices son inválidos.");
        }

        // Crear subarreglos temporales para fusionar
        int[] left = new int[len1];
        int[] right = new int[len2];

        // Copiar datos de arr a subarreglos temporales
        for (int i = 0; i < len1; i++) {
            left[i] = arr[l + i];
        }
        for (int j = 0; j < len2; j++) {
            right[j] = arr[m + 1 + j];
        }

        // Variables para mantener el estado de las fusiones
        int i = 0, j = 0, k = l;

        // Fusionar los subarreglos temporales de izquierda y derecha en el arreglo principal
        while (i < len1 && j < len2) {
            if (left[i] <= right[j]) {
                arr[k] = left[i];
                i++;
            } else {
                arr[k] = right[j];
                j++;
            }
            k++;
        }

        // Copiar los elementos restantes de left si hay alguno
        while (i < len1) {
            arr[k] = left[i];
            i++;
            k++;
        }

        // Copiar los elementos restantes de right si hay alguno
        while (j < len2) {
            arr[k] = right[j];
            j++;
            k++;
        }
    }
}
