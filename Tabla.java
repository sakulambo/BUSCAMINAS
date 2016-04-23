package buscaminas;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import buscaminas.Jugador;
import java.util.InputMismatchException;

/**
 * @author kevin
 *
 * @see init(Scanner teclado) -> Metode per inicialitzar la taula amb les
 * mesures que indiqui el jugador.
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
public class Tabla {

    /*public static final char BOMBA = '¤', ESPACIO = '*', ESTRELLA = '§';
    public static final char[] items = {0, BOMBA - ESPACIO, ESTRELLA - ESPACIO};
    public static int cont_bomba = 0, cont_estrella = 0, cont_buit = 0;
    public static int bombav = 0, estrellav = 0, buitv = 0;
    public static int[] valor = {-5, +2, +1};
    public static char[][] tabla;
    public static boolean[][] mask;

    private static int[] dimensiones = new int[2];
    private static int puntuacio;

    

    /**
     * \\\\-medida_Tabla-//// Metode per inicialitzar la taula amb les mesures
     * que indiqui el jugador.
     *
     * @return dimensiones -> retorna les mesures en una array de 2 posiciones
     * on fila[0] i col[1].
     *
     */
    public static final char BOMBA = '¤';
    public static final char ESPACIO = '*';
    public static final char ESTRELLA = '§';
    public static int VALORBOMBA = -5;
    public static int VALORESPACIO = +1;
    public static int VALORESTRELLA = +2;

    private char[][] tabla;
    private boolean[][] mask;

    private int bombas = 0;
    private int estrellas = 0;

    private int puntuacionBomba = 0;
    private int puntuacionEspacio = 0;
    private int puntuacionEstrella = 0;

    // private Tiempo t;
    //private double tempsFinal = 80;
    public void init(Scanner teclado) {
        int filas = 0;
        boolean incorrecta = false;
        do {
            System.out.println("Introdueix un número de files: ");
            try {
                filas = teclado.nextInt();
                incorrecta = filas < 0;
                if (incorrecta) {
                    System.out.println("El valor introduït és incorrecte");
                }
            } catch (NumberFormatException e) {
                System.out.println("El valor introduït no és númeric");
                incorrecta = true;
            } catch (InputMismatchException a){
                System.out.println("Ups! Valor invàlid!");
                filas = teclado.nextInt();
            }
        } while (incorrecta);

        int columnas = 0;
        incorrecta = false;
        do {
            System.out.println("Introdueix un número de columnes: ");
            try {
                columnas = teclado.nextInt();
                incorrecta = columnas < 0;
                if (incorrecta) {
                    System.out.println("El valor introduït és incorrecte");
                }
            } catch (NumberFormatException e) {
                System.out.println("El valor introduït no és númeric");
                incorrecta = true;
            } catch (InputMismatchException a){
                System.out.println("Ups! Valor invàlid!");
            }
        } while (incorrecta);

        tabla = new char[filas][columnas];
        mask = new boolean[filas][columnas];
        for (int i = 0; i < tabla.length; ++i) {
            for (int j = 0; j < tabla[0].length; ++j) {
                tabla[i][j] = ESPACIO;
                mask[i][j] = false;
            }
        }

        int maxBomba = (filas * columnas) / 4;
        int maxEstrella = (filas * columnas) / 4;
        Random rd = new Random();

        do {
            for (int i = 0; i < tabla.length; i++) {
                for (int j = 0; j < tabla[i].length; j++) {

                    int random = rd.nextInt(4 - 1) + 1;

                    if (tabla[i][j] == ESPACIO) {
                        if (bombas < maxBomba && random == 2) {
                            tabla[i][j] = BOMBA;
                            ++bombas;
                        } else if (estrellas < maxEstrella && random == 3) {
                            tabla[i][j] = ESTRELLA;
                            ++estrellas;
                        }
                    }
                }//fi for[j]
            }//fi for[i]
        } while (bombas < maxBomba || estrellas < maxEstrella);

        //t.inicializa();
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
    public void show(boolean pirata) {

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
                if (mask[i][j] || pirata) {
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
    public int[] coords(Scanner teclado, Jugador p) {
        String input;
        int fila = 0;
        boolean incorrecta = false;
        do {
            System.out.print("\nIntrodueix la fila (X) o prem 'p' "
                    + "per activar el mode pirata:  ");
            input = teclado.next().toLowerCase();

            try {
                if (input.equals("p")) {
                    modoPirata(teclado, p);
                } else if (input.equals("h")) {
                    Menu.printAjuda();
                } else {
                    fila = Integer.parseInt(input);
                    incorrecta = fila >= tabla.length || fila < 0;
                    if (incorrecta) {
                        System.out.print("Aquest valor es incorrecte!");
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Caràcter no vàlid!");
                incorrecta = true;
            } catch (ArrayIndexOutOfBoundsException a) {
                System.out.println("Ups! Has escollit una coordenada equivocada.");
                incorrecta = true;
            } catch (Exception e) {
                System.out.println("Something wrong! " + e);
                incorrecta = true;
            }
        } while (incorrecta);

        int columna = 0;
        incorrecta = false;
        if (input.equals("p")) {
            incorrecta = true;
        } else {

            do {
                System.out.print("\nIntrodueix la columna (Y) o prem 'p' "
                        + "per activar el mode pirata:  ");
                input = teclado.next().toLowerCase();

                try {
                    if (input.equals("p")) {
                        modoPirata(teclado, p);
                    } else if (input.equals("h")) {
                        Menu.printAjuda();
                    } else {
                        columna = Integer.parseInt(input);
                        incorrecta = columna >= tabla[0].length || columna < 0;
                        if (incorrecta) {
                            System.out.print("Aquest valor es incorrecte!");
                        }
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Caràcter no vàlid!");
                    incorrecta = true;
                } catch (ArrayIndexOutOfBoundsException a) {
                    System.out.println("Ups! Has escollit una coordenada equivocada.");
                    incorrecta = true;
                } catch (Exception e) {
                    System.out.println("Something wrong! " + e);
                    incorrecta = true;
                }
            } while (incorrecta);
        }

        int[] coords = {fila, columna};
        return coords;
    }

    //PEDIR COORD PIRATA!
    public void coordsPirata(Scanner teclado, Jugador p) {
        boolean incorrecta = false;

        do {
            System.out.print("\nIntrodueix una opció disponible: ");
            String input = teclado.next().toLowerCase();

            try {
                if (input.equals("m")) {
                    modoManual(teclado, p);
                } else if (input.equals("h")) {
                    Menu.printAjuda();
                } else if (input.equals("b")) {
                    int fila = 0;
                    boolean incorrecta2 = false;
                    do {
                        System.out.print("\nIntrodueix la fila (X) on vols inserir la Bomba:  ");

                        try {
                            fila = teclado.nextInt();

                            incorrecta2 = fila >= tabla.length || fila < 0;
                            if (incorrecta2) {
                                System.out.print("Aquest valor es incorrecte!");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Caràcter no vàlid!");
                            incorrecta2 = true;
                        } catch (ArrayIndexOutOfBoundsException a) {
                            System.out.println("Ups! Has escollit una coordenada equivocada.");
                            incorrecta2 = true;
                        } catch (Exception e) {
                            System.out.println("Something wrong! " + e);
                            incorrecta2 = true;
                        }
                    } while (incorrecta2);

                    int columna = 0;
                    incorrecta2 = false;
                    do {
                        System.out.print("\nIntrodueix la columna (Y) on vols inserir la Bomba:  ");
                        try {
                            columna = teclado.nextInt();
                            incorrecta2 = columna >= tabla[0].length || columna < 0;
                            if (incorrecta2) {
                                System.out.print("Aquest valor es incorrecte!");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Caràcter no vàlid!");
                            incorrecta2 = true;
                        } catch (ArrayIndexOutOfBoundsException a) {
                            System.out.println("Ups! Has escollit una coordenada equivocada.");
                            incorrecta2 = true;
                        } catch (Exception e) {
                            System.out.println("Something wrong! " + e);
                            incorrecta2 = true;
                        }
                    } while (incorrecta2);

                    anadirBomba(fila, columna);
                } else if (input.equals("e")) {
                    int fila = 0;
                    boolean incorrecta2 = false;
                    do {
                        System.out.print("\nIntrodueix la fila (X) on vols inserir l'Estel:  ");
                        try {
                            fila = teclado.nextInt();
                            incorrecta2 = fila >= tabla.length || fila < 0;
                            if (incorrecta2) {
                                System.out.print("Aquest valor es incorrecte!");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Caràcter no vàlid!");
                            incorrecta2 = true;
                        } catch (ArrayIndexOutOfBoundsException a) {
                            System.out.println("Ups! Has escollit una coordenada equivocada.");
                            incorrecta2 = true;
                        } catch (Exception e) {
                            System.out.println("Something wrong! " + e);
                            incorrecta2 = true;
                        }
                    } while (incorrecta2);

                    int columna = 0;
                    incorrecta2 = false;
                    do {
                        System.out.print("\nIntrodueix la columna (Y) on vols inserir l'Estel:  ");
                        try {
                            columna = teclado.nextInt();
                            incorrecta2 = columna >= tabla[0].length || columna < 0;
                            if (incorrecta2) {
                                System.out.print("Aquest valor es incorrecte!");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Caràcter no vàlid!");
                            incorrecta2 = true;
                        } catch (ArrayIndexOutOfBoundsException a) {
                            System.out.println("Ups! Has escollit una coordenada equivocada.");
                            incorrecta2 = true;
                        } catch (Exception e) {
                            System.out.println("Something wrong! " + e);
                            incorrecta2 = true;
                        }
                    } while (incorrecta2);

                    anadirEstrella(fila, columna);
                } else {
                    incorrecta = true;
                    if (incorrecta) {
                        System.out.print("Aquest valor es incorrecte!");
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Caràcter no vàlid!");
                incorrecta = true;
            } catch (ArrayIndexOutOfBoundsException a) {
                System.out.println("Ups! Has escollit una coordenada equivocada.");
                incorrecta = true;
            } catch (Exception e) {
                System.out.println("Something wrong! " + e);
                incorrecta = true;
            }
        } while (incorrecta);
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
    public void modoManual(Scanner teclado, Jugador p) throws Exception {
        show(false);
        int[] c = coords(teclado, p);
        int fila = c[0];
        int columna = c[1];

        while (mask[fila][columna]) {
            System.out.println("Aquestes coordenades ja estan destapades!");
            c = coords(teclado, p);
            fila = c[0];
            columna = c[1];
        }
        mask[fila][columna] = true;

        if (tabla[fila][columna] == BOMBA) {
            puntuacionBomba += VALORBOMBA;
            p.sumaBomba();
            --bombas;
        } else if (tabla[fila][columna] == ESTRELLA) {
            puntuacionEstrella += VALORESTRELLA;
            p.sumaEstrella();
            --estrellas;
        } else if (tabla[fila][columna] == ESPACIO) {
            puntuacionEspacio += VALORESPACIO;
            p.sumaEspacio();
        } else {
            throw new Exception("Somethin wrong!");
        }

        System.out.println("\nBOMBA: " + p.getBombas() + " ESTEL: " + p.getEstrellas() + " ESPAI: " + p.getEspacios());
        System.out.println("Puntuació BOMBA: " + VALORBOMBA + " Puntuació d'ESTEL: " + VALORESTRELLA + " Puntuació d'ESPAI: " + VALORESPACIO);
        System.out.println("Puntuacio: " + puntuacion());
        System.out.println("\n");
        

    }

    public int puntuacion() {
        return puntuacionBomba + puntuacionEstrella + puntuacionEspacio;
    }

    public void modoPirata(Scanner teclado, Jugador p) {
        System.out.println("\nMODE PIRATA ACTIVAT");
        show(true);
        Menu.printPirata();
        coordsPirata(teclado, p);

        System.out.println("\n");
    }

    //REVISAR EN CASA!
    public void anadirBomba(int fila, int col) {
        if (!mask[fila][col] && tabla[fila][col] != ESTRELLA) {
            tabla[fila][col] = BOMBA;
            ++bombas;
            
            
        }
    }

    public void anadirEstrella(int fila, int col) {
        if (!mask[fila][col] && tabla[fila][col] != BOMBA) {
            tabla[fila][col] = ESTRELLA;
            ++estrellas;
            
        }
    }

    public boolean fi() {
        if (bombas == 0) {
            show(false);
            System.out.println("\nGAME OVER! " + "Has perdut \n"
                    + "La teva puntuació és: " + puntuacion());

            return true;
        }
        if (estrellas == 0) {
            show(false);
            System.out.println("YOU WIN! " + "Has guanyat \n"
                    + "La teva puntuació és: " + puntuacion());
            return true;
        }
        /*if (t.getTime() == FINALTIME) {
            return true;
        }*/
        return false;
    }
}
