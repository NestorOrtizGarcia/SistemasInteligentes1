/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Prueba;

import java.util.Arrays;

public class StringToMatrix {

    public static int[][] stringToMatrix(String str) {
        String[] lines = str.split("\n");
        int n = lines.length;
        int[][] matrix = new int[n][n];

        for (int i = 0; i < n; i++) {
            String[] parts = lines[i].split(" ");

            for (int j = 0; j < n; j++) {
                matrix[i][j] = Integer.parseInt(parts[j]);
            }
        }

        return matrix;
    }
}

