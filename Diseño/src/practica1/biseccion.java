package practica1;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class biseccion {

	public static void main(String [] args) {
		double error=10e-4;
		double start1=System.currentTimeMillis();
		double result=(recurbisec(-10,10,error));
		double end1=System.currentTimeMillis();
		double start2=System.currentTimeMillis();
		double result2=pilas(-10,10,error);
		double end2=System.currentTimeMillis();
		System.out.println("El tiempo del recursivo es :"+ (end1-start1)/10000);
		System.out.println("El tiempo del pilas es : "+ (end2-start2)/10000);

	}


	static double pilas(double a,double b,double error){
		Stack <Double> izq=new Stack<Double>();
		Stack <Double>der=new Stack<Double>();
		izq.push(a);
		der.push(b);
		double valormedio=(a+b)/2;
		while(Math.abs(eval(valormedio))>error){
			if(eval(valormedio)*eval(izq.peek())>0){//f(c)*f(a)>0
				izq.push(valormedio);
				der.push(der.peek());

			}else{//eval valor medio es negativo f(c)*f(a)<0
				izq.push(izq.peek());
				der.push(valormedio);
				
				
				
			}

			valormedio=(izq.peek()+der.peek())/2;
		}
		return valormedio;
	}

	static double recurbisec(double a,double b,double error) {
		double valormedio=(a+b)/2;
		double sol=0;
		double fa = eval(a);
		double fb = eval(b);
		double fc=eval(valormedio);
		if( Math.abs(fc) <= error){//solucion encontrada
			sol=valormedio;
		}else{

			if(fc * fa > 0){//es mayor que 0,
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
