package org.ordering_methods;

//Presentado por:
//Juan Daniel Cardona Urbano
//Juan Esteban Mosquera Zapata
//Juan Alejandro Brito

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.ordering_methods.methods.BitonicSort.BitonicSort;
import org.ordering_methods.methods.CombSort.CombSort;
import org.ordering_methods.methods.GnemoSort.GnomeSort;
import org.ordering_methods.methods.HeapSort.HeapSort;
import org.ordering_methods.methods.PigeonholeSort.PigeonholeSort;
import org.ordering_methods.methods.Timsort.TimSort;
import org.ordering_methods.methods.TreeSort.TreeSort;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

public class Main {
    /*
     * Genera un arreglo de números aleatorios, lo ordena con varios algoritmos
     * de clasificación y mide el tiempo de ejecución de cada algoritmo.
     *
     * @param args Argumentos de la línea de comandos (no se usan)
     */
    public static <CategoryPlot> void main(String[] args){

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        TreeMap<Long, String> sortedTimes = new TreeMap<>();

        // Variables para medir el tiempo de ejecución
        long start_time;
        long end_time;
        long time;

        // Arreglo de entrada y su tamaño
        int[] arrayInput;
        int n;

        // Generar el arreglo de números aleatorios
        generateArray();

        // Leer el arreglo desde el archivo
        arrayInput = readArray();
        n = arrayInput.length;

        // Inicializar y ejecutar Tim Sort
        System.out.println("\n\n------Tim Sort Init------");
        TimSort timSort = new TimSort();
        start_time = System.nanoTime();
        timSort.sort(arrayInput, n);
        end_time = System.nanoTime();
        time = executionTime(end_time, start_time);
        sortedTimes.put(time, "Tim Sort");

        // Leer el arreglo nuevamente desde el archivo
        arrayInput = readArray();

        // Inicializar y ejecutar Comb Sort
        System.out.println("\n\n------Comb Sort Init------");
        CombSort combSort = new CombSort();
        start_time = System.nanoTime();
        combSort.sort(arrayInput);
        end_time = System.nanoTime();
        time = executionTime(end_time, start_time);
        sortedTimes.put(time, "Comb Sort");

        // Leer el arreglo nuevamente desde el archivo
        arrayInput = readArray();

        // Inicializar y ejecutar Tree Sort
        System.out.println("\n\n------Tree Sort Init------");
        TreeSort treeSort = new TreeSort();
        start_time = System.nanoTime();
        treeSort.sort(arrayInput);
        end_time = System.nanoTime();
        time = executionTime(end_time, start_time);
        sortedTimes.put(time, "Tree Sort");

        // Leer el arreglo nuevamente desde el archivo
        arrayInput = readArray();

        // Inicializar y ejecutar Pigeonhole Sort
        System.out.println("\n\n------Pigeonhole Sort Init------");
        PigeonholeSort pigeonholeSort = new PigeonholeSort();
        start_time = System.nanoTime();
        pigeonholeSort.sort(arrayInput, n);
        end_time = System.nanoTime();
        time = executionTime(end_time, start_time);
        sortedTimes.put(time, "Pigeonhole Sort");

        // Leer el arreglo nuevamente desde el archivo
        arrayInput = readArray();

        // Inicializar y ejecutar Heap Sort
        System.out.println("\n\n------Heap Sort Init------");
        HeapSort heapSort = new HeapSort();
        start_time = System.nanoTime();
        heapSort.sort(arrayInput);
        end_time = System.nanoTime();
        time = executionTime(end_time, start_time);
        sortedTimes.put(time, "Heap Sort");

        // Leer el arreglo nuevamente desde el archivo
        arrayInput = readArray();

        // Inicializar y ejecutar Bitonic Sort
        System.out.println("\n\n------Bitonic Sort Init------");
        BitonicSort bitonizSort = new BitonicSort();
        start_time = System.nanoTime();
        bitonizSort.sort(arrayInput, n, 1);
        end_time = System.nanoTime();
        time = executionTime(end_time, start_time);
        sortedTimes.put(time, "Bitonic Sort");

        // Leer el arreglo nuevamente desde el archivo
        arrayInput = readArray();

        // Inicializar y ejecutar Gnome Sort
        System.out.println("\n\n------Gnome Sort Init------");
        GnomeSort gnomeSort = new GnomeSort();
        start_time = System.nanoTime();
        gnomeSort.sort(arrayInput, n);
        end_time = System.nanoTime();
        time = executionTime(end_time, start_time);
        sortedTimes.put(time, "Gnome Sort");

        for (Long t : sortedTimes.descendingKeySet()) {
            dataset.addValue(t, "Tiempo (ns)", sortedTimes.get(t));
        }

        JTable table = new JTable(createTableModel(sortedTimes));
        JScrollPane scrollPane = new JScrollPane(table);
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);

        // Crear el gráfico de barras
        JFreeChart chart = ChartFactory.createBarChart(
                "Tiempo de ejecución de algoritmos de ordenamiento", // Título del gráfico
                "Algoritmo", // Etiqueta del eje X
                "Tiempo (ns)", // Etiqueta del eje Y
                dataset, // Conjunto de datos
                PlotOrientation.VERTICAL, // Orientación del gráfico
                true, // Incluir leyenda
                true, // Incluir tooltips
                false // Incluir URLs
        );


        // Mostrar el gráfico y la tabla en una ventana
        JFrame frame = new JFrame("Gráfico de Barras y Tabla");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(new ChartPanel(chart), BorderLayout.NORTH);
        frame.add(panel, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
    }

    public static DefaultTableModel createTableModel(TreeMap<Long, String> sortedTimes) {

        String[] columnNames = {"Algoritmo", "Tiempo (ns)"};

        Set<Map.Entry<Long, String>> entrySet = sortedTimes.entrySet();

        Object[][] data = new Object[sortedTimes.size()][2];

        int row = 0;
        for (Map.Entry<Long, String> entry : entrySet) {
            data[row][0] = entry.getValue(); // Nombre del algoritmo
            data[row][1] = entry.getKey();   // Tiempo de ejecución
            row++;
        }

        return new DefaultTableModel(data, columnNames);
    }


    /**
     * Genera un arreglo de números aleatorios basado en un tamaño ingresado por el
     * usuario.
     * Guarda el arreglo en un archivo de texto.
     *
     * @throws IllegalArgumentException si el tamaño ingresado es menor o igual a
     *                                  cero.
     */
    public static void generateArray() {
        // Solicitar al usuario el tamaño del arreglo
        String input = JOptionPane.showInputDialog("Ingrese el tamaño del arreglo O(x100.000):");
        int size = Integer.parseInt(input);
        if (size <= 0) {
            throw new IllegalArgumentException("El tamaño del array debe ser un entero mayor que 0.");
        }

        int tamano = size * 100000;
        int[] arreglo = new int[tamano];
        Random random = new Random();

        // Generar números aleatorios y almacenarlos en el arreglo
        for (int i = 0; i < tamano; i++) {
            arreglo[i] = random.nextInt(90000000) + 10000000;
        }

        // Guardar el arreglo generado en un archivo de texto
        writeArray(arreglo);
        System.out.println("\nArrayInput [" + tamano + "] generated correctly.\n");
    }

    /**
     * Calcula y muestra el tiempo de ejecución en nanosegundos entre dos instantes
     * de tiempo.
     *
     * @param end   El tiempo final en nanosegundos.
     * @param start El tiempo inicial en nanosegundos.
     */
    public static long executionTime(long end, long start) {
        long totalTime;
        totalTime = end - start;
        long milliseconds = totalTime / 1_000_000;
        long seconds = milliseconds / 1000;
        System.out.println("Tiempo de ejecución: " + totalTime + " ns");
        System.out.println("Tiempo de ejecución: " + milliseconds + " ms");
        System.out.println("Tiempo de ejecución: " + seconds + " s");
        return totalTime;
    }

    /**
     * Escribe un arreglo de enteros en un archivo de texto, uno por línea.
     *
     * @param arreglo El arreglo de enteros que se escribirá en el archivo.
     */
    public static void writeArray(int[] arreglo) {
        String nombreArchivo = "src/main/resources/arrayInput.txt";

        try (FileWriter writer = new FileWriter(nombreArchivo)) {
            // Crear un nuevo archivo o sobrescribir el archivo existente
            File file = new File(nombreArchivo);
            if (file.exists()) {
                file.delete();
            }

            // Escribir cada número del arreglo en una línea del archivo
            for (int num : arreglo) {
                writer.write(String.valueOf(num));
                writer.write(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Lee un arreglo de enteros desde un archivo de texto y lo devuelve.
     *
     * @return El arreglo de enteros leído desde el archivo.
     */
    public static int[] readArray() {
        List<Integer> arrayList = new ArrayList<>();
        String fileName = "src/main/resources/arrayInput.txt";
        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            // Leer cada línea del archivo y agregar los valores al ArrayList
            while ((line = br.readLine()) != null) {
                arrayList.add(Integer.parseInt(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Convertir el ArrayList en un arreglo de enteros
        int[] array = new int[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++) {
            array[i] = arrayList.get(i);
        }
        return array;
    }
}