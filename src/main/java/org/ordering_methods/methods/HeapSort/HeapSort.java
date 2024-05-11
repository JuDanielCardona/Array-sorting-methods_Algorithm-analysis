package org.ordering_methods.methods.HeapSort;

public class HeapSort {

    /**
     * Ordena un arreglo de enteros utilizando el algoritmo Heap Sort.
     *
     * @param arr El arreglo de enteros que se va a ordenar.
     */
    public void sort(int[] arr) {
        int N = arr.length;

        // Construir un montón máximo a partir de la mitad del arreglo hacia el
        // principio
        for (int i = N / 2 - 1; i >= 0; i--) {
            heapify(arr, N, i);
        }

        // Extraer elementos del montón uno por uno y re-heapificar
        for (int i = N - 1; i > 0; i--) {
            // Intercambiar el elemento raíz del montón con el último elemento del arreglo
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            // Volver a heapify el arreglo para restablecer el montón
            heapify(arr, i, 0);
        }
    }

    /**
     * Reajusta un subárbol de heap (montón) en un arreglo para mantener la
     * propiedad de heap.
     *
     * @param arr El arreglo de enteros que contiene el heap.
     * @param N   El tamaño del heap en el arreglo.
     * @param i   El índice de la raíz del subárbol que se va a heapificar.
     */
    void heapify(int[] arr, int N, int i) {
        int largest = i;
        int l = 2 * i + 1; // Índice del hijo izquierdo
        int r = 2 * i + 2; // Índice del hijo derecho

        // Comparar el valor del hijo izquierdo con el de la raíz para encontrar el mayor
        if (l < N && arr[l] > arr[largest]) {
            largest = l;
        }

        // Comparar el valor del hijo derecho con el mayor valor encontrado para encontrar el mayor
        if (r < N && arr[r] > arr[largest]) {
            largest = r;
        }

        // Si el mayor valor no es la raíz, intercambiarlos
        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            // Llamar recursivamente para restablecer la propiedad de heap en el subárbol
            heapify(arr, N, largest);
        }
    }
}
