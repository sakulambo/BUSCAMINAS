package garciafernandezkevin;

import static GarciaFernandezKevin.Buscaminas_MAIN.teclat;

public class Buscaminas_Player extends Buscaminas_Menu {
    
      private static void initJugadors(Jugador[] p) {
            System.out.println("Introdueix el nom del jugador 1");
            String nombre1 = teclat.nextLine();
            p[0] = new Jugador(nombre1);

            System.out.println("Introdueix el nom del jugador 2");
            String nombre2 = teclat.nextLine();
            p[1] = new Jugador(nombre2);
        }

    private String nombre;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String s) {
        nombre = s;
    }

    public Buscaminas_Player(String s) {
        nombre = s;
    }

    public Buscaminas_Player() {
        nombre = "";
    }

    private static class Jugador {

        public Jugador(String nombre) {
        }

      

    }

}
