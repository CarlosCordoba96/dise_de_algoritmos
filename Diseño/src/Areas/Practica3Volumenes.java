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
        //System.out.println(Math.pow(4,(1.0/2.0)));
        generico();
        
        /*System.out.println("Escribe el número de repeticiones");
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
        double resultado=Math.pow(2, 3)* valores/repeticiones;
        System.out.println("Volumen de la funcion x^2 + y^2 es: " + resultado);

        double sphereRadius;
        double sphereVolume;

        System.out.println("Enter the diamater of a sphere:");
        sphereRadius = sc.nextFloat();
        sphereVolume = ( 4.0 / 3.0 ) * Math.PI * Math.pow( sphereRadius, 3 );
        System.out.println("The volume is: " + sphereVolume);
        sphere();
        sc.close();*/

    }
    
    static void potencias(int n){
        for(int i=0;i<10;i++){
                double num=Math.pow(n, i);
                System.out.println(n+" elevado a "+i +" es"+num);
        }
    }
    
    public static void generico() {
        Scanner s = new Scanner(System.in);
        Random r = new Random();
        double valor = 0;
        int i = 0;
        System.out.println("Introduce el número de repeticiones");
        double repeticiones = s.nextInt();
        System.out.println("Introduce el máximo número a comprobar");
        double maximo = s.nextDouble();
        System.out.println("Introduce el índice de potencia de x");
        double pot_x = s.nextDouble();
        System.out.println("Introduce el índice de potencia de y");
        double pot_y = s.nextDouble();
        System.out.println("Introduce el índice de potencia de z");
        double pot_z = s.nextDouble();
        
        System.out.println("¿El objetivo de la función es despejar z?(1 ó 2)");
        int option = s.nextInt();
        if (option == 1) {
            while(i <= repeticiones){
                double x = r.nextDouble();
                double y = r.nextDouble();
                double z =(Math.pow(x, pot_x) + Math.pow(y, pot_y));
                valor+=Math.pow(z, (1.0/pot_z))*maximo;
                i++;
            }
            double resultado = (maximo*maximo)*valor/repeticiones;
            System.out.println("Volumen de la funcion es: " + resultado);
            
        } else {
                System.out.println("Introduce el valor máximo de la suma de incógnitas.");
                double radio = s.nextDouble();
                double fuera, dentro;
                fuera = dentro = 0.0;
                
                while(i < repeticiones){
                        double x = Math.random() * (radio);
                        double y = Math.random() * (radio);
                        double z = Math.random() * (radio);

                        if(Math.sqrt(Math.pow(x, pot_x) + Math.pow(y, pot_y) + Math.pow(z, pot_z)) <= radio)
                                dentro++;
                        else
                                fuera++;
                        i++;
                }
                double volumePrism = Math.pow(radio, 2) * (2 * radio); //base por altura
                double resultado = (dentro/ (dentro + fuera)) * volumePrism;
               System.out.println("El volumen de esta esfera es: " + resultado);
                
               // z = Math.pow(x, pot_x) + Math.pow(y, pot_y);
       }
        
   
            //resultado es igual a puntos dentro de la figura / puntos totales = volumen de la figura / volumen del cubo
    }
    
    public static void sphere(){
         Scanner sca = new Scanner(System.in);
                System.out.println("Put a number to repeat the algorithm (Monte Carlo)");
                int repetitions = sca.nextInt();
                System.out.println("The value of the radio is");
                double r = sca.nextDouble();
                double volume_sphere = volume_sphere(r, repetitions);
                System.out.println("The value of the volume of the sphere is: " + volume_sphere);
        }

        private static double volume_sphere(double r, int repetitions){
                int count = 0;
                double inside, outside;
                inside = outside = 0.0;
                while(count < repetitions){
                        double x = Math.random() * (r);
                        double y = Math.random() * (r);
                        double z = Math.random() * (r);

                        if(Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2)) <= r)
                                inside++;
                        else
                                outside++;
                        count++;
                }
                double volumePrism = Math.pow(r, 2) * (2 * r); //base por altura
                double volumeSphere = (inside/ (inside + outside)) * volumePrism;
                return  4 * volumeSphere;
        }
}
