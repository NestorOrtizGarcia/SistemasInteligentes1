/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Prueba;

import java.util.*;

public class UniformCostSearch {

    private static List<Node> visitedNodes; // Lista auxiliar para almacenar los nodos visitados y su costo

    private static class Node {
        public int state;
        public Node parent;
        public int cost;

        public Node(int state, Node parent, int cost) {
            this.state = state;
            this.parent = parent;
            this.cost = cost;
        }
    }

    public static void uniformCostSearch(int[][] graph, int start, int goal) {
        PriorityQueue<Node> queue = new PriorityQueue<>(new Comparator<Node>() {
            public int compare(Node n1, Node n2) {
                return n1.cost - n2.cost;
            }
        });

        boolean[] visited = new boolean[graph.length];
        visitedNodes = new ArrayList<>(); // Inicializar la lista de nodos visitados
        queue.add(new Node(start, null, 0));

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            int state = node.state;

            if (state == goal) {
                printSolution(node);
                System.out.println("\nOrden de visitas: ");

                for (Node n : visitedNodes) {
                    System.out.print(n.state + " ");
                }
                return;
            }

            if (!visited[state]) {
                visited[state] = true;
                visitedNodes.add(node); // Almacenar el nodo visitado en la lista auxiliar
                for (int i = 0; i < graph.length; i++) {
                    if (graph[state][i] != 0 && !visited[i]) {
                        int cost = node.cost + graph[state][i];
                        queue.add(new Node(i, node, cost));
                    }
                }
            }
        }

        System.out.println("No se encontró una solución.");
    }

    private static void printSolution(Node node) {
        if (node.parent != null) {
            printSolution(node.parent);
        }
        System.out.print(node.state + " ");
    }

    public static void main(String[] args) {
        int[][] graph = {
            {0, 2, 1, 0, 0},
            {2, 0, 0, 1, 0},
            {1, 0, 0, 3, 1},
            {0, 1, 3, 0, 1},
            {0, 0, 1, 1, 0}
        };
        int start = 0;
        int goal = 4;

        System.out.println("Buscando ruta más corta desde " + start + " hasta " + goal + ":");
        uniformCostSearch(graph, start, goal);
    }
}
