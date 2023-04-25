/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectointeligentes1;

import java.util.Objects;

public class Nodo {
    private int x;
    private int y;
    private double g;
    private Nodo padre;

    public Nodo(int x, int y) {
        this.x = x;
        this.y = y;
        this.g = Double.POSITIVE_INFINITY;
        this.padre = null;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public double getG() {
        return g;
    }

    public void setG(double g) {
        this.g = g;
    }

    public Nodo getPadre() {
        return padre;
    }

    public void setPadre(Nodo padre) {
        this.padre = padre;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Nodo) {
            Nodo otroNodo = (Nodo) obj;
            return this.x == otroNodo.x && this.y == otroNodo.y;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.x, this.y);
    }
}
