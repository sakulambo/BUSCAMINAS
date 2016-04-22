/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buscaminas;

import java.util.Random;
import java.util.Scanner;


/**
 *
 * @author kevin
 */
public class Main {

    public static Scanner teclat = new Scanner(System.in);
    public static Random rd = new Random();
    public static final byte WIDTH = 0, HEIGHT = 1;
    

    public static void main(String[] args) throws Exception {
        Scanner teclado = new Scanner(System.in);
        Jugador p1 = new Jugador();
        Jugador p2 = new Jugador();
        Menu menu = new Menu();
        
        menu.print(teclado, p1, p2);
        
        Tabla tabla = new Tabla();
        tabla.init(teclado);
        
        String torn = "JUGADOR 1";
        do {
            if (torn.equals("JUGADOR 1")) {
                tabla.modoManual(teclado, p1);
                torn = "JUGADOR 2";
            }
            else {
                tabla.modoManual(teclado, p1);
                torn = "JUGADOR 1";
            }
        } while(!tabla.fi());

    }

}
