package org.ordering_methods.methods.TreeSort;

public class Node {

    /**
     * Clave del nodo. Puede representar un valor de dato que se almacena en el nodo.
     */
    int key;

    /**
     * Referencia al nodo izquierdo del árbol.
     */
    Node left;

    /**
     * Referencia al nodo derecho del árbol.
     */
    Node right;

    /**
     * Constructor de la clase Node.
     * Inicializa un nodo con la clave proporcionada y sin hijos.
     *
     * @param item El valor de clave para el nodo.
     */
    public Node(int item) {
        key = item;
        left = right = null;
    }
}
