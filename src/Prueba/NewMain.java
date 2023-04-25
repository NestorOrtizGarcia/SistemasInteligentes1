/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Prueba;

import static Prueba.AStar.astar;
import static Prueba.BFS.bfs;
import static Prueba.StringToMatrix.stringToMatrix;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author kael-
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        FileReader archivo;
        BufferedReader lector;
        String cadena ="";
        String valores = null;
        int[][] matrix = null; 
        try {
            archivo = new FileReader("C:\\Users\\kael-\\Desktop\\SemestreFinal\\TESIS.txt");
            if(archivo.ready()){
                lector = new BufferedReader(archivo);
                while((cadena = lector.readLine()) != null)
                {
                    //System.out.println(cadena);
                    valores += cadena + "\n"; 
                }
            }else{
                System.out.println("Archivo no disponible");
            }
        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }
        valores = valores.replaceAll("null", "");
        //System.out.println(valores);
        
        matrix = stringToMatrix(valores);
        bfs(matrix,0);
        DFS dfs = new DFS();
        dfs.search(matrix, 0);
        astar(matrix,0,5);

    }
}
