/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Prueba;

import java.util.*;

public class BFS {

    public static void bfs(int[][] graph, int start) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[graph.length];
        int[] order = new int[graph.length];

        Arrays.fill(order, -1); // Inicializar con valores negativos

        visited[start] = true;
        order[start] = 0;
        queue.offer(start);

        while (!queue.isEmpty()) {
            int current = queue.poll();
            //System.out.print(current + " ");

            for (int i = 0; i < graph[current].length; i++) {
                if (graph[current][i] != 0 && !visited[i]) {
                    visited[i] = true;
                    order[i] = order[current] + 1; // Actualizar el orden de visita
                    queue.offer(i);
                }
            }
        }

        System.out.println("\nOrden de visita BFS: " + Arrays.toString(order));
    }

//    public static void main(String[] args) {
//        int[][] graph = { {0, 1, 1, 0, 0},
//                          {1, 0, 0, 1, 1},
//                          {1, 0, 0, 1, 0},
//                          {0, 1, 1, 0, 1},
//                          {0, 1, 0, 1, 0} };
//        bfs(graph, 0);
//    }

}
