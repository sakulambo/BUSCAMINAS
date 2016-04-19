package GarciaFernandezKevin;

import static GarciaFernandezKevin.Buscaminas_MAIN.teclat;
import java.util.Scanner;

public class Buscaminas_Player extends Buscaminas_Menu {

    public static class Jugador {

        //Propietats de jugador
        public String nombre;
        public int cont;

        public Jugador() {
            nombre = "";
            cont = 0;
        }

        public Jugador(String s) {
            nombre = s;
            cont = 0;
        }
        
        public String getNombre() {
            return nombre;
        }

        public int getCont() {
            return cont;
        }

        public void setNombre(String s) {
            nombre = s;
        }

        public void setCont(int n) {
            cont = n;
        }

        public void sumaCont() {
            ++cont;
        }
    }

    public static void initJugador(Jugador[] p, Scanner teclat) { 
        p = new Jugador[2];
        System.out.println("Introdueix el nom del jugador 1");
        String nombre1 = teclat.nextLine();
        p[0] = new Jugador(nombre1);
        System.out.println("Introdueix el nom del jugador 2");
        String nombre2 = teclat.nextLine();
        p[1] = new Jugador(nombre2);
        System.out.println("\n");
    }

}
