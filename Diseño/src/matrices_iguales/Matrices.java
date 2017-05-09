package matrices_iguales;
import java.util.*;

public class Matrices {
	static Scanner scan = new Scanner(System.in);
	public static void main(String[]args){
		System.out.println("Numero de filas y columnas");
		int n = scan.nextInt();
		//int[][]m1=creaMatriz(n);
		//int[][]m2=creaMatriz(n);
		int[][]m1=creaMatriz22(n);
		int[][]m2=creaMatriz22(n);
		imprimeMatriz(m1);
		imprimeMatriz(m2);
		System.out.println("Antes y despues");
		int[]vAleatorio=generaVectorAleatorio(n);
		int[]resultado1=multiplicar(m1,vAleatorio);
		int[]resultado2=multiplicar(m2,vAleatorio);
		boolean sonIguales=compararResultados(resultado1,resultado2);
		imprimeVector(resultado1);
		imprimeVector(resultado2);
		//imprimeMatriz(m1);
		//imprimeMatriz(m2);
		Algoritmo(m1,m2,resultado1,resultado2,sonIguales);
	}
	public static int[][] creaMatriz(int n){
		int[][]matriz=new int[n][n];
		int i,j;
		for(i=0;i<matriz.length;i++){
			for(j=0;j<matriz[i].length;j++){
				matriz[i][j]=generaNumeroAleatorio(1,25);
			}
		}
		return matriz;
	}
	public static void imprimeVector(int[]v){
		for(int i=0;i<v.length;i++){
			System.out.println(v[i]);
		}
	}
	public static int[][] creaMatriz22(int n){
		int[][]matriz=new int[n][n];
		matriz[0][0]=1;
		matriz[0][1]=2;
		matriz[1][0]=3;
		matriz[1][1]=3;
		return matriz;
	}
	
	public static void imprimeMatriz(int[][]m){
		for(int i=0;i<m.length;i++){
			for(int j=0;j<m[i].length;j++){
				System.out.print(" "+m[i][j]+" ");
			}
			System.out.println("");
		}
	}
	public static int[] generaVectorAleatorio(int n){
		int[]v=new int[n];
		for(int i=0;i<n;i++){
			v[i]=generaNumeroAleatorio(1,25);
		}
		return v;
	}
	public static int[] multiplicar(int[][]m,int[]v){
		int [] resultado=new int[v.length];
		
		for(int k=0;k<m.length;k++){
			for(int j=0;j<m[k].length;j++){
				resultado[k]+=m[k][j]*v[k];
			}
		}
		return resultado;
	}
	
	public static void Algoritmo(int[][]m1,int[][]m2,int[]v1,int[]v2,boolean sonIguales){
		if(sonIguales==true){
			System.out.println("Son iguales");
			for(int i=0;i<v1.length;i++){
				System.out.println("vectores comparados:"+v1[i]+" VS "+v2[i]);
			}
		}else{
			System.out.println("No son iguales");
		}
	}
	
	public static boolean compararResultados(int[]v,int[]v2){
		boolean iguales=true;
		for(int i=0;i<v.length;i++){
			if(v[i]!=v2[i]){
				iguales=false;
			}
		}
		return iguales;
	}
	public static int generaNumeroAleatorio(int minimo,int maximo) {
		return ((int)Math.floor(Math.random()*(maximo-minimo+1)+(minimo)));
	}
}
