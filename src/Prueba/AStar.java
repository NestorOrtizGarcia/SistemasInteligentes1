/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Prueba;

import java.util.*;

public class AStar {

    public static void astar(int[][] graph, int start, int end) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        Map<Integer, Integer> fScore = new HashMap<>();
        Map<Integer, Integer> gScore = new HashMap<>();
        Map<Integer, Integer> cameFrom = new HashMap<>();

        gScore.put(start, 0);
        fScore.put(start, heuristic(start, end));
        queue.add(start);

        while (!queue.isEmpty()) {
            int current = queue.poll();

            if (current == end) {
                List<Integer> path = new ArrayList<>();
                path.add(current);
                while (cameFrom.containsKey(current)) {
                    current = cameFrom.get(current);
                    path.add(current);
                }
                Collections.reverse(path);
                System.out.println(path);
                return;
            }

            for (int i = 0; i < graph.length; i++) {
                if (graph[current][i] != 0) {
                    int tentativeGScore = gScore.getOrDefault(current, Integer.MAX_VALUE) + graph[current][i];
                    if (tentativeGScore < gScore.getOrDefault(i, Integer.MAX_VALUE)) {
                        cameFrom.put(i, current);
                        gScore.put(i, tentativeGScore);
                        fScore.put(i, tentativeGScore + heuristic(i, end));
                        if (!queue.contains(i)) {
                            queue.add(i);
                        }
                    }
                }
            }
        }
    }

    public static int heuristic(int node, int end) {
        // Implementar una función heurística adecuada para el problema
        return Math.abs(node - end);
    }

}

