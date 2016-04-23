/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buscaminas;

import java.util.Scanner;

/**
 *
 * @author kevin
 */
public class Menu {
    public static void print(Scanner teclado, Jugador p1, Jugador p2) throws Exception {
        System.out.println("||||||||||||||||||||||||||||" + "\n" +
                           "||BUSCAMINAS VALLBONA 2016||" + "\n" +
                           "||     by Kevin Garcia    ||" + "\n" +
                           "||||||||||||||||||||||||||||" + "\n");
        System.out.println();

        System.out.println("1. Mode un Jugador" + "\n" +
                           "2. Mode Multijugador" + " \n" +
                           "3. Menu d'ajuda" + "\n");
        
        int opcio = 0;
        boolean incorrecta = false;
        do {
            System.out.println("Introdueix una opcio [1-3]: ");
            try {
                opcio = teclado.nextInt();
                incorrecta = opcio != 1 && opcio != 2 && opcio != 3;
                if (incorrecta) {
                    System.out.println("El valor introduït és incorrecte");
                }
            } catch (NumberFormatException e) {
                System.out.println("El valor introduït no és númeric");
                incorrecta = true;
            }
        } while(incorrecta);

        
        if (opcio == 1) {
            System.out.println("Mode un jugador seleccionat");
            init(p1, teclado);
            p2 = p1;
        }
        else if (opcio == 2) {
            System.out.println("Mode multijudaor seleccionat"); 
            init(p1, p2, teclado);
        }
        else if (opcio == 3) {
            System.out.println("Mode ajuda seleccionat");
            printAjuda();
            print(teclado, p1, p2);
        }
        else {
            throw new Exception ("Something wrong!");
        }
    }

    public static void printAjuda() {
        System.out.println("\n");
        System.out.println("Joc BuscaMinas v1 (2016) – By VallbonaSoft Cooperativa\n"
                + "Divertit joc interactiu en el que el jugador ha d'anar mostrant caselles amb l'objectiu\n"
                + "de trobar tots els estels “§” abans de que esclatin totes les bombes “¤”.\n"
                + "Les opcions que permet el joc són:\n"
                + "H (o h): Ajuda \n"
                + "A (o a): Mostrar el taulell amb ubicació de les bombes/estrelles  (Mode “pirata”)\n"
                + "B (o b): Permetre inserir manualment una bomba en el taulell     (Mode “pirata”)\n"
                + "E (o e): Permetre inserir manualment una estrella en el taulell    (Mode “pirata”)\n"
                + "  Símbols: \n"
                + "o: casella sense destapar  ¤: Bomba (­5)  #: Casella destapada (+1)  §: estel (+2)");
        System.out.println("\n");
    }
    
     public static void printPirata() {
        System.out.println("\n");
        System.out.println("Les opcions que permet el joc són:\n"
                + "B (o b): Permetre inserir manualment una bomba en el taulell     (Mode “pirata”)\n"
                + "E (o e): Permetre inserir manualment una estrella en el taulell    (Mode “pirata”)\n"
                + "M (o m): Permet tornar al mode de joc manual (Mode “manual”)\n"
                + "  Símbols: \n"
                + "o: casella sense destapar  ¤: Bomba (­5)  #: Casella destapada (+1)  §: estel (+2)");
    }
     
    private static void init(Jugador p, Scanner teclado) {
        System.out.println("Introdueix el nom del jugador");
        String nombre = teclado.next();
        p = new Jugador(nombre);
    }
    
    private static void init(Jugador p1, Jugador p2, Scanner teclado) { 
        System.out.println("Introdueix el nom del jugador 1: ");
        String nombre = teclado.next();
        p1 = new Jugador(nombre);
        System.out.println("Introdueix el nom del jugador 2: ");
        String nombre2 = teclado.next();
        p2 = new Jugador(nombre);
        System.out.println("\n");
    }

}