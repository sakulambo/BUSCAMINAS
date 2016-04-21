package GarciaFernandezKevin;

import java.util.InputMismatchException;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author kevin
 *
 * @see Buscaminas_Tabla#medida_Tabla() -> Metode per inicialitzar la taula amb
 * les mesures que indiqui el jugador.
 *
 * @see Buscaminas_Tabla#init_Tabla -> Metode per inicialitzar la taula amb les
 * mesures que indiqui el jugador.
 *
 * @see Buscaminas_Tabla#show_Tabla -> Metode per visualitzar la taula amb les
 * mesures que ha indicat a @init_Tabla.
 *
 * @see Buscaminas_Tabla#pedir_coor -> Metode per demanar coordenades per fila i
 * columna que posteriorment es mostren a @show_tabla.
 *
 */
public class Buscaminas_Tabla extends Buscaminas_MAIN {

    public static final char BOMBA = '¤', ESPACIO = '*', ESTRELLA = '§';
    public static final char[] items = {0, BOMBA - ESPACIO, ESTRELLA - ESPACIO};
    public static int cont_bomba = 0, cont_estrella = 0, cont_buit = 0;
    public static int bombav = 0, estrellav = 0, buitv = 0;
    public static int[] valor = {-5, +2, +1};
    public static char[][] tabla;
    public static boolean[][] mask;

    private static int[] dimensiones = new int[2];
    private static int puntuacio;

    public static int getPunt() {
        puntuacio = bombav + estrellav + buitv;
        return puntuacio;
    }

    /**
     * \\\\-medida_Tabla-//// Metode per inicialitzar la taula amb les mesures
     * que indiqui el jugador.
     *
     * @return dimensiones -> retorna les mesures en una array de 2 posiciones
     * on fila[0] i col[1].
     *
     */
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

    /**
     * \\\\-init_Tabla-//// Metode per inicialitzar la taula amb les mesures que
     * indiqui el jugador.
     *
     * @param fila -> paràmetre per establir el numero de files totals.
     * @param col -> paràmetre per establir el numero de columnes totals.
     * @return tabla -> retorna la taula amb totes les bombes, estrelles i
     * espais buits.
     *
     */
    //METODE PER INICIALITZAR LA TAULA AMB BOMBES, ESTRELLES I ESPAIS BLANCS.
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

    /**
     * \\\\-show_Tabla-//// "Metode per visualitzar la taula amb les mesures que
     * ha indicat a @init_Tabla."
     *
     * @param tabla-> paràmetre que utilitza la taula creada anteriorment.
     * @param mask -> paràmetre que utilitza una taula booleana per comparar les
     * caselles en las que s'ha introduit una jugada.
     *
     */
    //MOSTRAR TABLA
    public static void show_Tabla(char[][] tabla, boolean[][] mask, boolean pirata) {

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
                if (mask[i][j] == true || pirata) {
                    System.out.print((char) (tabla[i][j] + ESPACIO) + " ");
                } else {
                    System.out.print("o ");
                }

            }

            System.out.println();
        }

    }

    /**
     * \\\\-pedir_coor-//// "Metode per demanar coordenades per fila i columna
     * que posteriorment es mostren a @show_tabla."
     *
     * @return coord -> retorna una array de 2 posicions on es troben les
     * posicions de fila[0] i col[1].
     *
     */
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
                    modoPirata();
                } else if (input.equals("b")) {
                    añadirBomba(tabla, dimensiones[0], dimensiones[1], items);
                } else if (input.equals("e")) {
                    añadirEstrella(tabla, dimensiones[0], dimensiones[1], items);
                } else if (input.equals("h")) {
                    Buscaminas_Menu.print_Menu();
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
                teclat.next();

            } catch (ArrayIndexOutOfBoundsException a) {
                System.out.println("Ups! Has escollit una coordenada equivocada."
                        + "Torna a introduir-ne una de nou!");
                teclat.next();

            } catch (Exception e) {
                System.out.println("ERROR! " + e);
            }
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
                    Buscaminas_Menu.print_Menu();
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

            } catch (Exception e) {
                System.out.println("ERROR! " + e);
            }
            okcol = true;
        }
        return coord;
    }

    //PEDIR COORD PIRATA!
    public static int[] pedir_coorP(String input) {
        int[] coord = new int[2];
        boolean okfila = false;
        boolean okcol = false;

        while (!okfila) {
            System.out.print("\nIntrodueix la fila (X) o prem 'm' "
                    + "per activar el mode manual:  ");
            input = teclat.next().toLowerCase();

            try {
                if (input.equals("m")) {
                    pedir_coor();
                } else if (input.equals("b")) {
                    añadirBomba(tabla, dimensiones[0], dimensiones[1], items);
                } else if (input.equals("e")) {
                    añadirEstrella(tabla, dimensiones[0], dimensiones[1], items);
                } else if (input.equals("h")) {
                    Buscaminas_Menu.print_Menu();
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

            try {
                if (input.equals("m")) {
                    pedir_coor();

                } else if (input.equals("b")) {
                    añadirBomba(tabla, dimensiones[0], dimensiones[1], items);

                } else if (input.equals("e")) {

                } else if (input.equals("h")) {
                    Buscaminas_Menu.print_Menu();
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

            } catch (Exception e) {
                System.out.println("ERROR! " + e);
            }
            okcol = true;
        }
        return coord;
    }

    /**
     * \\\\-comp_Tabla-//// "Metode per comparar la taula actual amb una taula
     * booleana i mostrar la taula amagada menys la posicio que hagi introduït
     * el jugador. Quan es completa l'operació, crida al metode
     *
     * @show_Tabla per mostrar la taula actual."
     *
     * @param tabla-> paràmetre que utilitza la taula creada anteriorment.
     *
     * @param mask -> paràmetre que utilitza una taula booleana per comparar les
     * caselles en las que s'ha introduit una jugada.
     *
     */
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

    public static boolean modoPirata() {
        boolean pirata = true;
        String input = "";

        if (pirata) {

            System.out.println("MODE PIRATA ACTIVAT");
            show_Tabla(tabla, mask, pirata);
            Buscaminas_Menu.print_MenuP();
            System.out.println("\n");
            pedir_coorP(input);

        } else {

            System.out.println("\nMODE MANUAL ACTIVAT");
            pedir_coor();
            pirata = false;

        }

        return pirata;
    }

    //REVISAR EN CASA!
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

    /*private static boolean comprobarEmpat(char[][] taula, boolean[][] mask) {
     boolean b = false;
     for (int i = 1; i < taula.length && !b; i++) {
     for (int j = 0; j < taula[i].length; j++) {
     }
     b = taula[0][i] == ' ';
     }
     return !b;
     }*/
}
