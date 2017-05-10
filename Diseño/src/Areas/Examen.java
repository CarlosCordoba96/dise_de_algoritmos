package Areas;

import java.util.Random;
import java.util.Scanner;

public class Examen {
	
	//CARLOS CORDOBA RUIZ
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
        Random r = new Random();
        System.out.println("Escribe el número de repeticiones");
        int repeticiones = sc.nextInt();
        System.out.println("El área entre las funciones f(x)=raiz(x) y g(x)=x^2 es: "+areaNumerico(repeticiones));//intervalo 0,1 h=1
		
	}
	public static double areaNumerico(int k){//area probabilista según el teorema del valor medio
		double sumaf=0;
		double sumag=0;
		for(int n=0;n<k;n++){
		double x=Math.random();
		double fy=f(x);
		double gy=g(x);
			sumaf+=fy;
			sumag+=gy;
		}//CALCULO ÁREA DE F Y CALCULO AREA DE G Y RESTO AREA DE F A AREA DE G
		return Math.abs((sumaf/k-sumag/k));//Con el abs controlamos que una funcion pudiera estar por encima de otra
	}
	//f(x)=raiz(x)
		public static double f(double x){
			double sol=Math.sqrt(x);
			return sol;
		}
		//g(x)=x^2
		public static double g(double x){
			double sol=Math.pow(x, 2);
			return sol;
		}
}
