package GarciaFernandezKevin;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author kevin
 */
public class Buscaminas_MAIN {

    public static Scanner teclat = new Scanner(System.in);
    public static Random rd = new Random();
    public static final byte WIDTH = 0, HEIGHT = 1;
    

    public static void main(String[] args) {
        boolean fi = false;
        boolean empat = false;
        boolean jugar = true;

        //IMRPIMIR MENU
        Buscaminas_Menu.print_MenuMain();
        //SI ESCOJE OPCION ENTRE 1 y 3 -> GENERA TABLA
        int[] dim = Buscaminas_Tabla.medida_Tabla();
        boolean[][] mask = new boolean[dim[WIDTH]][dim[HEIGHT]];
        Buscaminas_Tabla.mask=mask;
        char[][] tabla = Buscaminas_Tabla.init_Tabla(dim[WIDTH], dim[HEIGHT]);
        Buscaminas_Tabla.tabla=tabla;
        //COMIENZA JUGADA HASTA QUE FI o EMPAT
        do {
            Buscaminas_Tabla.show_Tabla(tabla, mask,false);
            Buscaminas_Tabla.comp_Tabla(mask, tabla);

        } while (jugar);

    }

}
