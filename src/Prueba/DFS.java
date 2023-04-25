/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Prueba;

import java.util.*;

import java.util.ArrayList;
import java.util.List;

public class DFS {
    private boolean[] visited;
    private List<Integer> visitOrder;

    public void search(int[][] graph, int start) {
        int n = graph.length;
        visited = new boolean[n];
        visitOrder = new ArrayList<>();

        dfs(graph, start);
        printVisitOrder();
    }

    private void dfs(int[][] graph, int node) {
        visited[node] = true;
        visitOrder.add(node);

        for (int neighbor = 0; neighbor < graph.length; neighbor++) {
            if (graph[node][neighbor] == 1 && !visited[neighbor]) {
                dfs(graph, neighbor);
            }
        }
    }

    private void printVisitOrder() {
        System.out.println("DFS visit order: " + visitOrder);
    }
}
