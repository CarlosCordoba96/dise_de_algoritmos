package practica1;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class biseccion {

	public static void main(String [] args) {
		double result;
		double error=10e-4;
		System.out.println(2>=error);
		String eq="(X*X*X)+10*X - 5";
		System.out.println(recurbisec(0,1,error));
	}

	static double recurbisec(double a,double b,double error) {
		double valormedio=(a+b)/2;
		double sol=0;
		double fa = eval(a);
		double fb = eval(b);
		double fc=eval(valormedio);
		if( Math.abs(fc) <= error){//solución encontrada
			sol=valormedio;
		}else{

			if(fc * fa > 0){//es mayor que 0
				sol=recurbisec(valormedio,b,error);

			}else {//es menor que 0
				sol=recurbisec(a,valormedio,error);
			}	
		}
		return sol;		
	}

	



	static double eval(double x){

		return Math.pow(x, 3)+x-1;//x^3+x-1

	}

}
