package GarciaFernandezKevin;

import garciafernandezkevin.Buscaminas_Player;
import java.util.Scanner;

/**
 *
 * @author kevin
 */
public class Buscaminas_Menu extends Buscaminas_MAIN {

    public static Buscaminas_Player.Jugador[] p;

    public static void print_MenuMain() {
        boolean ok = false;
        int opcio = 0;

        System.out.println("||||||||||||||||||||||||||||"
                + "\n||BUSCAMINAS VALLBONA 2016||"
                + "\n||  by Kevin Garcia       ||"
                + "\n||||||||||||||||||||||||||||");
        System.out.println();

        System.out.println("1. Mode un Jugador"
                + "\n2. Mode Multijugador"
                + "\n3. Menu d'ajuda");

        System.out.println("Introdueix una opcio [1-3]: ");
        while (!ok) {
            String linia = teclat.nextLine();

            //COMPROBAR CON PARSE INT
            try {
                ok = true;
                opcio = Integer.parseInt(linia);
            } catch (NumberFormatException e) {
                System.out.println("El valor no es un numero");
                ok = false;
            }
            //COMPROVAR SIN PARSE INT
            /*try {
                ok = true;
                opcio = teclat.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Las lletres no funcionen!");
                teclat.nextLine();
                ok = false;

            }*/
        }

        switch (opcio) {
            case 1:
                break;
            case 2: {
                Buscaminas_Player.initJugador(p, teclat);
            }
            break;
            case 3:
                print_Menu();
                break;
        }

    }

    public static void print_Menu() {
        System.out.println("\n");
        System.out.println("Joc BuscaMinas v1 (2016) – By VallbonaSoft Cooperativa\n"
                + "Divertit joc interactiu en el que el jugador ha d'anar mostrant caselles amb l'objectiu\n"
                + "de trobar tots els estels “§” abans de que esclatin totes les bombes “¤”.\n"
                + "Les opcions que permet el joc són:\n"
                + "H (o h): Ajuda \n"
                + "A (o a): Mostrar el taulell amb ubicació de les bombes/estrelles  (Mode “pirata”)\n"
                + "B (o b): Permetre inserir manualment una bomba en el taulell     (Mode “pirata”)\n"
                + "E (o e): Permetre inserir manualment una estrella en el taulell    (Mode “pirata”)\n"
                + "  Símbols: \n"
                + "o: casella sense destapar  ¤: Bomba (­5)  #: Casella destapada (+1)  §: estel (+2)");
        System.out.println("\n");
        print_MenuMain();

    }

}
