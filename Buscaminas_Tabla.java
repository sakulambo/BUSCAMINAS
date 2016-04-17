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
    public static int cont_bomba = 0, cont_estrella = 0, cont_buit = 0;
    public static int bombav = 0, estrellav = 0, buitv = 0;
    public static int[] valor = {-5, +2, +1};

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
        int cont_bomba = 0, cont_estrella = 0;
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
        String input = teclat.next();
        input = input.toLowerCase();

        
        try {
            
            
            System.out.print("\nIntrodueix la fila: ");
            if (input.equals("a") || input.equals("b") || input.equals("e")) {
                System.out.println("MODO PIRATA");

            } else if (input.equals("h")) {
                Buscaminas_Menu.print_Menu();
            } else {
                System.out.print("\nIntrodueix la fila: ");
                coord[0] = Integer.parseInt(teclat.next());

                while (coord[0] >= dimensiones[0] || coord[0] < 0) {
                    System.out.println("Aquest valor es incorrecte! ");
                    System.out.println("\nIntrodueix una fila: ");
                    coord[0] = Integer.parseInt(teclat.next());
                }
            }

        } catch (NumberFormatException e) {
            System.out.println("ERROR! Caràcter no vàlid! ");
            teclat.nextLine();

        }

        System.out.print("\nIntrodueix la columna: ");

        
        try {
            coord[1] = Integer.parseInt(teclat.next(input));
            if (input.equals("a") || input.equals("b") || input.equals("e")) {
                System.out.println("MODO PIRATA");

            } else if (input.equals("h")) {
                Buscaminas_Menu.print_Menu();
            } else {

                coord[1] = Integer.parseInt(teclat.next());
                System.out.println("\n");

                while (coord[1] >= dimensiones[1] || coord[1] < 0) {
                    System.out.println("Aquest valor es incorrecte! ");
                    System.out.println("Introdueix una columna: ");
                    coord[1] = Integer.parseInt(teclat.next());
                }
            }

        } catch (NumberFormatException e) {
            System.out.println("ERROR! Caràcter no vàlid!");
            teclat.nextLine();

        } catch (ArrayIndexOutOfBoundsException a) {
            System.out.println("Ups! Has escollit una coordenada equivocada."
                    + "Torna a introduir-ne una de nou!");

            teclat.nextLine();

        } catch (Exception e) {
            System.out.println("ERROR! " + e);
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

        while (mask[fila][col] == true) {
            System.out.println("ERROR Vuelve a introducir la fila:");
            c = pedir_coor();
            fila = c[0];
            col = c[1];

        }

        mask[fila][col] = true;

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
