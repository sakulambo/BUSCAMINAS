package GarciaFernandezKevin;

import java.util.InputMismatchException;

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
    private static int puntuacio;

    public static int getPuntuacio() {
        return puntuacio;
    }

    public static void sumaPuntuacio() {
        ++puntuacio;
    }

    public static void restaPuntuacio() {
        --puntuacio;
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
        int[] dimensiones = new int[2];
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
        int cont_bomba = 0, cont_estrella = 0;
        int random = 0;

        System.out.println("\n");
        System.out.println("Bomba: " + cont_bomba + " Estrella: " + cont_estrella);
        System.out.println("Puntuació: " + getPuntuacio());

        while (cont_bomba < bomba || cont_estrella < estrella) {

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
        }//fi while
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
                if (mask[i][j] == true) {
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
        boolean ok = false;

        while (!ok) {
            System.out.print("\nIntrodueix la fila: ");
            try {
                ok = true;
                coord[0] = teclat.nextInt();
            } catch (ArrayIndexOutOfBoundsException a) {
                System.out.println("Ups! Has escollit una coordenada equivocada."
                        + "Torna a introduir-ne una de nou!");

                teclat.nextLine();
                ok = false;
            } catch (InputMismatchException e) {
                System.out.println("ERROR! Caràcter no vàlid!");

                teclat.nextLine();
                ok = false;

            }
        }

        while (!ok) {
            System.out.print("\nIntrodueix la columna: ");

            try {
                ok = true;
                coord[1] = teclat.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("ERROR! Caràcter no vàlid!");
                teclat.nextLine();
                ok = false;

            } catch (ArrayIndexOutOfBoundsException a) {
                System.out.println("Ups! Has escollit una coordenada equivocada."
                        + "Torna a introduir-ne una de nou!");

                teclat.nextLine();
                ok = false;
            }
        }
        return coord;

    }

    /**
     * \\\\-comp_Tabla-//// "Metode per comparar la taula actual amb una taula
     * booleana i mostrar la taula amagada menys la posicio que hagi introduït
     * el jugador. Quan es completa l'operació, crida al metode @show_Tabla per
     * mostrar la taula actual."
     *
     * @param tabla-> paràmetre que utilitza la taula creada anteriorment.
     *
     * @param mask -> paràmetre que utilitza una taula booleana per comparar les
     * caselles en las que s'ha introduit una jugada.
     *
     */
    public static void comp_Tabla(boolean[][] mask, char[][] tabla) {
        int cont_bomba = 0, cont_estrella = 0, cont_buit = 0;
        int fila = 0;
        int col = 0;

        int[] c = pedir_coor();
        fila = c[0];
        col = c[1];
        if (c[0] == BOMBA) {
            cont_bomba++;
        }

        if (fila > c[0]+1) {
            System.out.println("Error! torna a introduir una fila!");
            fila = c[0];
        } else if (col > c[1]+1) {
            System.out.println("Error! Torna a introduir una columna!");
            col = c[1];
        } else {

            while (mask[fila][col] == true) {
                System.out.println("ERROR Vuelve a introducir la fila:");
                c = pedir_coor();
                fila = c[0];
                col = c[1];

            }
        }
        mask[fila][col] = true;

        show_Tabla(tabla, mask);

    }

}
