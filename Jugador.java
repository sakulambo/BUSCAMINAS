/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buscaminas;

import java.util.Scanner;

public class Jugador {
    //Propietats de jugador
    private String nombre;
    private int bombas, estrellas, espacios;
    

    public Jugador() {
        nombre = "";
        bombas = estrellas = espacios = 0;
    }

    public Jugador(String s) {
        nombre = s;
        bombas = estrellas = espacios = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public int getBombas() {
        return bombas;
    }
    
    public int getEstrellas() {
        return estrellas;
    }
    
    public int getEspacios() {
        return espacios;
    }
    
    public void setNombre(String s) {
        nombre = s;
    }

    public void setBombas(int n) {
        bombas = n;
    }
    
    public void setEstrellas(int n) {
        estrellas = n;
    }
    
    public void setEspacios(int n) {
        espacios = n;
    }

    public void sumaBomba() {
        ++bombas;
    }
    
    public void sumaEstrella() {
        ++estrellas;
    }
    
    public void sumaEspacio() {
        ++espacios;
    }
}
