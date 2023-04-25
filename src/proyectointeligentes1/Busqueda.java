/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectointeligentes1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class Busqueda {

    public static void main(String[] args) {
        int[][] mapa = {{1, 2, 3, 4, 5},
                        {6, 7, 8, 9, 10},
                        {11, 12, 13, 14, 15},
                        {16, 17, 18, 19, 20},
                        {21, 22, 23, 24, 25}};

        Nodo inicio = new Nodo(1, 1);
        Nodo fin = new Nodo(3, 3);

        System.out.println("BFS:");
        List<Nodo> caminoBFS = buscarBFS(mapa, inicio, fin);
        imprimirCamino(caminoBFS);

        System.out.println("DFS:");
        List<Nodo> caminoDFS = buscarDFS(mapa, inicio, fin);
        imprimirCamino(caminoDFS);

        System.out.println("A*:");
        List<Nodo> caminoAStar = buscarAStar(mapa, inicio, fin);
        imprimirCamino(caminoAStar);
    }

    public static List<Nodo> buscarBFS(int[][] mapa, Nodo inicio, Nodo fin) {
        List<Nodo> camino = new ArrayList<>();
        Queue<Nodo> cola = new LinkedList<>();
        boolean[][] visitado = new boolean[mapa.length][mapa[0].length];

        cola.add(inicio);

        while(!cola.isEmpty()) {
            Nodo nodoActual = cola.poll();

            if(nodoActual.equals(fin)) {
                camino = reconstruirCamino(nodoActual);
                break;
            }

            for(Nodo vecino : obtenerVecinos(mapa, nodoActual)) {
                if(!visitado[vecino.getX()][vecino.getY()]) {
                    visitado[vecino.getX()][vecino.getY()] = true;
                    cola.add(vecino);
                }
            }
        }

        return camino;
    }

    public static List<Nodo> buscarDFS(int[][] mapa, Nodo inicio, Nodo fin) {
        List<Nodo> camino = new ArrayList<>();
        Stack<Nodo> pila = new Stack<>();
        boolean[][] visitado = new boolean[mapa.length][mapa[0].length];

        pila.push(inicio);

        while(!pila.isEmpty()) {
            Nodo nodoActual = pila.pop();

            if(nodoActual.equals(fin)) {
                camino = reconstruirCamino(nodoActual);
                break;
            }

            for(Nodo vecino : obtenerVecinos(mapa, nodoActual)) {
                if(!visitado[vecino.getX()][vecino.getY()]) {
                    visitado[vecino.getX()][vecino.getY()] = true;
                    pila.push(vecino);
                }
            }
        }

        return camino;
    }

        public static List<Nodo> buscarAStar(int[][] mapa, Nodo inicio, Nodo fin) {
        List<Nodo> camino = new ArrayList<>();
        List<Nodo> abiertos = new ArrayList<>();
        List<Nodo> cerrados = new ArrayList<>();
        double[][] gScore = new double[mapa.length][mapa[0].length];
        double[][] fScore = new double[mapa.length][mapa[0].length];

        for(int i = 0; i < mapa.length; i++) {
            for(int j = 0; j < mapa[0].length; j++) {
                gScore[i][j] = Double.POSITIVE_INFINITY;
                fScore[i][j] = Double.POSITIVE_INFINITY;
            }
        }

        gScore[inicio.getX()][inicio.getY()] = 0;
        fScore[inicio.getX()][inicio.getY()] = distanciaHeuristica(inicio, fin);

        abiertos.add(inicio);

        while(!abiertos.isEmpty()) {
            Nodo nodoActual = obtenerMenorFScore(abiertos, fScore);

            if(nodoActual.equals(fin)) {
                camino = reconstruirCamino(nodoActual);
                break;
            }

            abiertos.remove(nodoActual);
            cerrados.add(nodoActual);

            for(Nodo vecino : obtenerVecinos(mapa, nodoActual)) {
                if(cerrados.contains(vecino)) {
                    continue;
                }

                double tentativeGScore = gScore[nodoActual.getX()][nodoActual.getY()] + distanciaEuclidiana(nodoActual, vecino);

                if(!abiertos.contains(vecino)) {
                    abiertos.add(vecino);
                } else if(tentativeGScore >= gScore[vecino.getX()][vecino.getY()]) {
                    continue;
                }

                gScore[vecino.getX()][vecino.getY()] = tentativeGScore;
                fScore[vecino.getX()][vecino.getY()] = tentativeGScore + distanciaHeuristica(vecino, fin);
                vecino.setPadre(nodoActual);
            }
        }

        return camino;
    }

    public static double distanciaEuclidiana(Nodo n1, Nodo n2) {
        int dx = n1.getX() - n2.getX();
        int dy = n1.getY() - n2.getY();
        return Math.sqrt(dx*dx + dy*dy);
    }

    public static double distanciaHeuristica(Nodo n1, Nodo n2) {
        return distanciaEuclidiana(n1, n2);
    }

    public static Nodo obtenerMenorFScore(List<Nodo> nodos, double[][] fScore) {
        Nodo nodoMenor = nodos.get(0);
        for(Nodo nodo : nodos) {
            if(fScore[nodo.getX()][nodo.getY()] < fScore[nodoMenor.getX()][nodoMenor.getY()]) {
                nodoMenor = nodo;
            }
        }
        return nodoMenor;
    }
    public static void imprimirCamino(List<Nodo> camino) {
    if (camino == null || camino.isEmpty()) {
        System.out.println("No se encontró un camino válido.");
        return;
    }

    System.out.print("Camino encontrado: ");
    for (int i = 0; i < camino.size(); i++) {
        Nodo nodo = camino.get(i);
        System.out.print("(" + nodo.getX() + ", " + nodo.getY() + ")");
        if (i < camino.size() - 1) {
            System.out.print(" -> ");
        }
    }
    System.out.println();
}
//    public static void imprimirCamino(List<Nodo> nodos) {
//    Nodo destino = nodos.get(nodos.size() - 1);
//    if (destino.getPadre() == null) {
//        System.out.println("No hay camino para llegar al destino.");
//        return;
//    }
//    List<Nodo> camino = new ArrayList<>();
//    Nodo actual = destino;
//    while (actual != null) {
//        camino.add(actual);
//        actual = actual.getPadre();
//    }
//    System.out.println("El camino más corto desde el inicio hasta el destino es:");
//    for (int i = camino.size() - 1; i >= 0; i--) {
//        Nodo nodo = camino.get(i);
//        System.out.printf("(%d, %d) ", nodo.getX(), nodo.getY());
//    }
//    System.out.println();
//}
    
    public static List<Nodo> reconstruirCamino(Nodo nodoDestino) {
        List<Nodo> camino = new ArrayList<>();
        Nodo nodoActual = nodoDestino;
        while (nodoActual.getPadre() != null) {
            camino.add(nodoActual);
            nodoActual = nodoActual.getPadre();
        }
        camino.add(nodoActual);
        Collections.reverse(camino);
        return camino;
    }
    
//    public static List<Nodo> obtenerVecinos(Nodo nodo, int[][] mapa) {
//    List<Nodo> vecinos = new ArrayList<>();
//    int x = nodo.getX();
//    int y = nodo.getY();
//    int filas = mapa.length;
//    int columnas = mapa[0].length;
//
//    if (x > 0 && mapa[x-1][y] == 0) {
//        vecinos.add(new Nodo(x-1, y));
//    }
//
//    if (x < filas-1 && mapa[x+1][y] == 0) {
//        vecinos.add(new Nodo(x+1, y));
//    }
//
//    if (y > 0 && mapa[x][y-1] == 0) {
//        vecinos.add(new Nodo(x, y-1));
//    }
//
//    if (y < columnas-1 && mapa[x][y+1] == 0) {
//        vecinos.add(new Nodo(x, y+1));
//    }
//
//    return vecinos;
//}
    
    public static List<Nodo> obtenerVecinos(int[][] mapa, Nodo nodoActual) {
    List<Nodo> vecinos = new ArrayList<>();

    int x = nodoActual.getX();
    int y = nodoActual.getY();

    // arriba
    if (x > 0 && mapa[x - 1][y] == 0) {
        vecinos.add(new Nodo(x - 1, y));
    }

    // abajo
    if (x < mapa.length - 1 && mapa[x + 1][y] == 0) {
        vecinos.add(new Nodo(x + 1, y));
    }

    // izquierda
    if (y > 0 && mapa[x][y - 1] == 0) {
        vecinos.add(new Nodo(x, y - 1));
    }

    // derecha
    if (y < mapa[0].length - 1 && mapa[x][y + 1] == 0) {
        vecinos.add(new Nodo(x, y + 1));
    }

    return vecinos;
}


}




