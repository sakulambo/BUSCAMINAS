
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
      boolean fi=false;
        
        Buscaminas_Menu.print_MenuMain();
        int[] dim = Buscaminas_Tabla.medida_Tabla();
        do{
        boolean[][] mask = new boolean[dim[WIDTH]][dim[HEIGHT]];
        char[][] tabla = Buscaminas_Tabla.init_Tabla( dim[WIDTH], dim[HEIGHT] );
        Buscaminas_Tabla.show_Tabla(tabla, mask);
        Buscaminas_Tabla.comp_Tabla(mask, tabla);
        }while(!fi);
       
       
        
        
        
        
    }
    
}
