package Buscaminas_v2;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author kevin
 */
public class BuscaminasV2 {

    public static Scanner teclat = new Scanner(System.in);
    public static Random rd = new Random();
    public static final byte WIDTH = 0, HEIGHT = 1;

    public static class Tabla {

        public static final char BOMBA = '¤', ESPACIO = '*', ESTRELLA = '§';
        public static final char[] items = {0, BOMBA - ESPACIO, ESTRELLA - ESPACIO};
        public static int cont_bomba = 0, cont_estrella = 0, cont_buit = 0;
        public static int bombav = 0, estrellav = 0, buitv = 0;
        public static int[] valor = {-5, +2, +1};
        public static char[][] tabla;
        public static boolean[][] mask;
        private static int[] dimensiones = new int[2];
        private static int puntuacio;
        private static boolean pirata = false;

        public static int getPunt() {
            puntuacio = bombav + estrellav + buitv;
            return puntuacio;
        }

        public static int[] medida_Tabla() {

            boolean ok = false;
            System.out.println("Introdueix un numero de files: ");

            while (!ok) {
                try {
                    ok = true;
                    dimensiones[0] = teclat.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("ERROR! Caràcter no vàlid!");
                    teclat.nextLine();
                    ok = false;

                }

                System.out.println("Introdueix un numero de columnes: ");

                try {
                    ok = true;
                    dimensiones[1] = teclat.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("ERROR! Caràcter no vàlid!");
                    teclat.nextLine();
                    ok = false;

                }

            }

            return dimensiones;
        }

        public static char[][] init_Tabla(int fila, int col) {
            char[][] tabla = new char[fila][col];
            int bomba = (fila * col) / 4;
            int estrella = (fila * col) / 4;
            int random = 0;

            do {

                for (int i = 0; i < tabla.length; i++) {
                    for (int j = 0; j < tabla[i].length; j++) {

                        random = (int) (rd.nextDouble() * 10 + 0);

                        if (tabla[i][j] == items[0]) {
                            if (cont_bomba < bomba && random >= 9) {
                                tabla[i][j] = items[1];
                                bomba--;

                            } else if (cont_estrella < estrella && random >= 9) {
                                tabla[i][j] = items[2];
                                estrella--;

                            }
                        }
                    }//fi for[j]
                }//fi for[i]
            } while (cont_bomba < bomba || cont_estrella < estrella);
            return tabla;
        }

        public static void show_Tabla(char[][] tabla, boolean[][] mask) {
            
            System.out.print("   ");
            for (int i = 0; i < tabla[0].length; i++) {
                System.out.print(i + " ");
            }
            System.out.print("\n  ");
            for (int i = 0; i < tabla[0].length; i++) {
                System.out.print("--");
            }
            System.out.println("");

            for (int i = 0; i < tabla.length; i++) {
                System.out.print(i + " |");
                for (int j = 0; j < tabla[i].length; j++) {
                    if (mask[i][j] == true || !pirata) {
                        System.out.print((char) (tabla[i][j] + ESPACIO) + " ");
                    } else {
                        System.out.print("o ");
                    }

                }

                System.out.println();
            }

        }

        public static int[] pedir_coor() {
            int[] coord = new int[2];
            boolean okfila = false;
            boolean okcol = false;
            String input;

            //INTRODUIR FILA
            while (!okfila) {

                System.out.print("\nIntrodueix la fila (X) o prem 'p' "
                        + "per activar el mode pirata:  ");

                input = teclat.next().toLowerCase();

                try {
                    if (input.equals("p")) {
                        pirata = true;
                        modoPirata();
                    } else if (input.equals("b")) {
                        añadirBomba(tabla, dimensiones[0], dimensiones[1], items);
                    } else if (input.equals("e")) {
                        añadirEstrella(tabla, dimensiones[0], dimensiones[1], items);
                    } else if (input.equals("h")) {
                        Menu.print_Menu();
                    } else {
                        coord[0] = Integer.parseInt(input);
                        while (coord[0] >= dimensiones[0] || coord[0] < 0) {
                            System.out.print("Aquest valor es incorrecte! ");
                            System.out.print("\nIntrodueix la fila (X) o prem 'p' "
                                    + "per activar el mode pirata:  ");
                        }
                    }

                } catch (NumberFormatException e) {
                    System.out.println("ERROR! Caràcter no vàlid! ");

                } catch (ArrayIndexOutOfBoundsException a) {
                    System.out.println("Ups! Has escollit una coordenada equivocada."
                            + "Torna a introduir-ne una de nou!");
                    input = teclat.next().toLowerCase();

                } /*catch (Exception e) {
                    System.out.println("ERROR! " + e);
                }*/
                okfila = true;
            }

            //INTORDUIR COLUMNA
            while (!okcol) {
                System.out.print("\nIntrodueix la columna (Y) o prem 'p' "
                        + "per activar el mode pirata:  ");

                input = teclat.next().toLowerCase();

                try {
                    if (input.equals("p")) {
                        modoPirata();
                    } else if (input.equals("b")) {
                        añadirBomba(tabla, dimensiones[0], dimensiones[1], items);
                    } else if (input.equals("e")) {
                        añadirEstrella(tabla, dimensiones[0], dimensiones[1], items);
                    } else if (input.equals("h")) {
                        Menu.print_Menu();
                    } else {
                        coord[1] = Integer.parseInt(input);
                        while (coord[1] >= dimensiones[1] || coord[1] < 0) {
                            System.out.println("Aquest valor es incorrecte! ");
                            System.out.print("\nIntrodueix la columna (Y) o prem 'p' "
                                    + "per activar el mode pirata:  ");

                        }
                    }

                } catch (NumberFormatException e) {
                    System.out.println("ERROR! Caràcter no vàlid!");
                    teclat.nextLine();

                } catch (ArrayIndexOutOfBoundsException a) {
                    System.out.println("Ups! Has escollit una coordenada equivocada."
                            + "Torna a introduir-ne una de nou!");
                    teclat.next();

                } /*catch (Exception e) {
                    System.out.println("ERROR! " + e);
                }*/
                okcol = true;
            }
            return coord;
        }

        public static int[] pedir_coorP(String input) {
            int[] coord = new int[2];
            boolean okfila = false;
            boolean okcol = false;

            while (!okfila) {
                System.out.print("\nIntrodueix la fila (X) o prem 'm' "
                        + "per activar el mode manual:  ");
                input = teclat.next().toLowerCase();
                String[] coord2 = input.split(" ");
                coord[0] = Integer.parseInt(coord2[0]);

                try {
                    if (input.equals("m")) {
                        pedir_coor();
                    } else if (input.equals("b")) {
                        añadirBomba(tabla, dimensiones[0], dimensiones[1], items);
                    } else if (input.equals("e")) {
                        añadirEstrella(tabla, dimensiones[0], dimensiones[1], items);
                    } else if (input.equals("h")) {
                        Menu.print_Menu();
                    } else {
                        coord[0] = Integer.parseInt(input);
                        while (coord[0] >= dimensiones[0] || coord[0] < 0) {
                            System.out.print("Aquest valor es incorrecte! ");
                            System.out.print("\nIntrodueix la fila (X) o prem 'p' "
                                    + "per activar el mode pirata:  \n");
                        }
                    }

                } catch (NumberFormatException e) {
                    System.out.println("ERROR! Caràcter no vàlid! ");
                    teclat.next();

                }
                okfila = true;
            }

            while (!okcol) {
                System.out.print("\nIntrodueix la columna (Y) o prem 'm' "
                        + "per activar el mode manual:  ");
                input = teclat.next().toLowerCase();
                String[] coord2 = input.split(" ");
                coord[1] = Integer.parseInt(coord2[1]);

                try {
                    if (input.equals("m")) {
                        pedir_coor();

                    } else if (input.equals("b")) {
                        añadirBomba(tabla, dimensiones[0], dimensiones[1], items);

                    } else if (input.equals("e")) {

                    } else if (input.equals("h")) {
                        Menu.print_Menu();
                    } else {
                        coord[1] = Integer.parseInt(input);
                        while (coord[1] >= dimensiones[1] || coord[1] < 0) {
                            System.out.println("Aquest valor es incorrecte! ");
                            System.out.print("\nIntrodueix la columna (Y) o prem 'p' "
                                    + "per activar el mode pirata:  ");
                        }
                    }

                } catch (NumberFormatException e) {
                    System.out.println("ERROR! Caràcter no vàlid!");
                    teclat.next();

                } catch (ArrayIndexOutOfBoundsException a) {
                    System.out.println("Ups! Has escollit una coordenada equivocada."
                            + "Torna a introduir-ne una de nou!");
                    teclat.next();

                } /*catch (Exception e) {
                    System.out.println("ERROR! " + e);
                }*/
                okcol = true;
            }
            return coord;
        }

        public static boolean modoPirata() {
            String input = "";

            if (pirata) {

                System.out.println("MODE PIRATA ACTIVAT");

                do {
                    show_Tabla(tabla, mask);
                    Menu.print_MenuP();
                    System.out.println("\n");
                    pedir_coorP(input);
                } while (input.equals("m"));

            } else {

                System.out.println("\nMODE MANUAL ACTIVAT");
                pedir_coor();
                pirata = false;

            }

            return pirata;
        }

        public static char[][] añadirBomba(char tabla[][], int fila, int col, char[] items) {
            String input = "";
            pedir_coorP(input);
            int b = '¤';

            for (int i = 0; i < b + 1; i++) {
                int x = ThreadLocalRandom.current().nextInt(0, fila - 1);
                int y = ThreadLocalRandom.current().nextInt(0, col - 1);

                tabla[x][y] = '¤';

            }
            return tabla;
        }

        public static char[][] añadirEstrella(char tabla[][], int fila, int col, char[] items) {
            String input = "";
            pedir_coorP(input);
            int b = '§';

            for (int i = 0; i <= b; i++) {
                int x = ThreadLocalRandom.current().nextInt(0, fila);
                int y = ThreadLocalRandom.current().nextInt(0, col);

                if (tabla[x][y] == '¤') {
                    tabla[x][y] = '¤';
                } else {
                    tabla[x][y] = '§';
                }
            }
            return tabla;
        }

        public static void comp_Tabla(boolean[][] mask, char[][] tabla) {

            int fila = 0;
            int col = 0;

            int[] c = pedir_coor();
            fila = c[0];
            col = c[1];

            if (tabla[fila][col] == items[1]) {
                bombav += valor[0];
                cont_bomba++;

            } else if (tabla[fila][col] == items[2]) {
                estrellav += valor[1];
                cont_estrella++;

            } else {
                buitv += valor[2];
                cont_buit++;

            }

            System.out.println("BOMBA " + cont_bomba + " ESTRELLA " + cont_estrella + " BUIT " + cont_buit);
            System.out.println("p.BOMBA " + bombav + " p.ESTRELLA " + estrellav + " p.BUIT " + buitv);
            System.out.println("Puntuacio: " + getPunt());
            System.out.println("\n");

            System.out.println("JAJAJJA");
            while (mask[fila][col] == true) {

                System.out.println("ERROR Vuelve a introducir la fila:");
                c = pedir_coor();
                fila = c[0];
                col = c[1];

            }

            mask[fila][col] = true;

        }

    }

    public static class Menu {

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
            print_MenuMain();

        }

        public static void print_MenuP() {
            System.out.println("\n");
            System.out.println("Les opcions que permet el joc són:\n"
                    + "A (o a): Mostrar el taulell amb ubicació de les bombes/estrelles  (Mode “pirata”)\n"
                    + "B (o b): Permetre inserir manualment una bomba en el taulell     (Mode “pirata”)\n"
                    + "E (o e): Permetre inserir manualment una estrella en el taulell    (Mode “pirata”)\n"
                    + "  Símbols: \n"
                    + "o: casella sense destapar  ¤: Bomba (­5)  #: Casella destapada (+1)  §: estel (+2)");

        }

    }

    public static class Buscaminas_Player {

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

    public static void main(String[] args) {
        boolean jugar = true;

        int[] dim = Tabla.medida_Tabla();
        boolean[][] mask = new boolean[dim[WIDTH]][dim[HEIGHT]];
        char[][] tabla = Tabla.init_Tabla(dim[WIDTH], dim[HEIGHT]);
        Tabla.init_Tabla(WIDTH, WIDTH);
        do {
            Tabla.show_Tabla(tabla, mask);
            Tabla.comp_Tabla(mask, tabla);

        } while (jugar);

    }

}
