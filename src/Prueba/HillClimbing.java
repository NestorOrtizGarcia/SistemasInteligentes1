/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Prueba;

import java.util.Random;

public class HillClimbing {

    private static final int MAX_ITERATIONS = 100; // Número máximo de iteraciones
    private static final double STEP_SIZE = 0.01; // Tamaño de paso

    public static double hillClimbing(int[][] graph, int start) {
        double currentValue = start;
        double neighborValue;
        double neighborX;

        for (int i = 0; i < MAX_ITERATIONS; i++) {
            neighborX = getNeighbor(graph, currentValue); // Seleccionar un vecino aleatorio
            neighborValue = f(neighborX);
            if (neighborValue > currentValue) {
                currentValue = neighborValue; // Si el vecino es mejor, avanzar
            }
        }
        return currentValue;
    }

    private static double getNeighbor(int[][] graph, double x) {
        int n = graph.length;
        int[] neighbors = new int[n];
        int k = 0;

        for (int i = 0; i < n; i++) {
            if (graph[(int)x][i] != 0) {
                neighbors[k++] = i;
            }
        }

        if (k == 0) {
            return x; // Si no hay vecinos, retornar el valor actual
        } else {
            Random rand = new Random();
            int index = rand.nextInt(k); // Seleccionar un vecino aleatorio
            return neighbors[index];
        }
    }

    private static double f(double x) {
        return -1 * (x * x - 2 * x + 2); // Función a maximizar
    }

    public static void main(String[] args) {
        int[][] graph = {
            {0, 1, 1, 0},
            {1, 0, 1, 1},
            {1, 1, 0, 1},
            {0, 1, 1, 0}
        }; // Ejemplo de una matriz de adyacencia de un grafo

        double maximum = hillClimbing(graph, 0);
        System.out.println("El máximo local es: " + maximum);
    }
}

