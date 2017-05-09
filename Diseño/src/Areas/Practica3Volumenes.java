package Areas;

import java.util.Random;
import java.util.Scanner;

public class Practica3Volumenes {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random r = new Random();
        System.out.println("Escribe el número de repeticiones");
        int repeticiones = sc.nextInt();
        String hola="holi";
        
        int i = 0;
        double valores=0;
        double dentro, fuera;
        dentro = fuera = 0;
        while(i <= repeticiones){
                double x = r.nextDouble();
                double y = r.nextDouble();
                double z=1-(Math.pow(x, 2) + Math.pow(y, 2));
                if (z>0)
                valores+=Math.sqrt(z);dentro++;//base por altura
                i++;
        }
        //resultado es igual a puntos dentro de la figura / puntos totales = volumen de la figura / volumen del cubo
        double resultado= valores/repeticiones;
        System.out.println("Volumen de la funcion x^2 + y^2 es: " + resultado);
        sc.close();
    }
    
}