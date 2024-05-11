package org.ordering_methods.methods.TreeSort;

public class TreeSort {

    /**
     * Raíz del árbol binario de búsqueda utilizado para Tree Sort.
     */
    Node root;

    /**
     * Constructor de la clase TreeSort.
     * Inicializa un árbol binario de búsqueda vacío.
     */
    public TreeSort() {
        root = null;
    }

    /**
     * Inserta una clave en el árbol binario de búsqueda.
     *
     * @param key La clave a insertar en el árbol.
     */
    void insert(int key) {
        root = insertRec(root, key);
    }

    /**
     * Inserta una clave en el árbol binario de búsqueda de forma recursiva.
     *
     * @param root La raíz del árbol donde se realizará la inserción.
     * @param key La clave a insertar.
     * @return La raíz actualizada del árbol.
     */
    Node insertRec(Node root, int key) {
        // Si el árbol está vacío, crear un nuevo nodo con la clave
        if (root == null) {
            root = new Node(key);
            return root;
        }

        // Si la clave es menor que la raíz, insertarla en el subárbol izquierdo
        if (key < root.key) {
            root.left = insertRec(root.left, key);
        } else if (key > root.key) { // Si la clave es mayor que la raíz, insertarla en el subárbol derecho
            root.right = insertRec(root.right, key);
        }
        return root;
    }

    /**
     * Realiza un recorrido en orden (in-order) en el árbol binario de búsqueda.
     * Esta función se utiliza para ordenar el arreglo.
     *
     * @param root La raíz del árbol desde donde comenzar el recorrido.
     */
    void inorderRec(Node root) {
        if (root != null) {
            inorderRec(root.left);
            // La clave del nodo actual está visitada durante el recorrido en orden
            inorderRec(root.right);
        }
    }

    /**
     * Ordena un arreglo de enteros utilizando el algoritmo Tree Sort.
     *
     * @param arr El arreglo de enteros que se va a ordenar.
     */
    public void sort(int[] arr) {
        // Insertar cada elemento del arreglo en el árbol binario de búsqueda
        for (int i = 0; i < arr.length; i++) {
            insert(arr[i]);
        }
        // Realizar un recorrido en orden en el árbol para ordenar el arreglo
        inorderRec(root);
    }
}
