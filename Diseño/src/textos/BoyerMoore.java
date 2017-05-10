package textos;

import java.io.*;

import java.util.*;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class BoyerMoore {

	public static void main(String[]args) throws FileNotFoundException, IOException{
		int porcentaje = 0;
		int lineas_totales;
		int lineas_espacio;
		int linea = 0;
		Scanner s = new Scanner (System.in);
		lineas_totales = numerodelineas("quijoteCap1.txt");
		System.out.println("Que patrón desea buscar en el texto: ");
		String patron=s.next();
		System.out.println("Introduzca el porcentaje de texto que desea");
		porcentaje = s.nextInt();
		lineas_espacio = (lineas_totales * porcentaje)/100;

		int  n_segmentos = 100/porcentaje;
		//int lineas_intervalo = lineas_totales/lineas_espacio;
		int repeticiones = lineas_espacio/n_segmentos;

		LinkedList<Integer> lista = lineasaleatorias(lineas_totales, n_segmentos, repeticiones);
		Collections.sort(lista);
		if (n_segmentos >= 2) 
			System.out.println("Se leen un total de " + lista.size() + " líneas de texto, se leen " + lista.size()/n_segmentos + " cada " + lineas_espacio);

		String texto = muestraContenido("quijoteCap1.txt",lista);
		System.out.println("METODO BOYER-MOORE:");
		ArrayList<Integer> ocurrencias = BoyerMoore(patron,texto);
		System.out.println("Se estiman: "+(ocurrencias.size()*100)/porcentaje+" ocurrencias del patron: '"+patron+"'");


	}

	static String muestraContenido(String archivo, LinkedList<Integer> lista) throws FileNotFoundException, IOException {
		String cadena;
		String texto = "";
		FileReader f = new FileReader(archivo);
		BufferedReader b = new BufferedReader(f);
		int i=0;
		int count=0;
		while((cadena = b.readLine())!=null) {
			if(count<lista.size()&&i==lista.get(count)){
				texto+="\n"+cadena;
				count++;			
			}
			i++;			
		}
		b.close();
		return texto;
	}

	static LinkedList lineasaleatorias(int lineas,int n_segmentos,int repeticiones){
		int aleatorio=0;
		LinkedList<Integer> list = new LinkedList();
		int segmento = (int)lineas/n_segmentos;
		for(int i=0; i < n_segmentos; i++){
			int inicio = i * segmento;
			int fin = i * segmento + segmento;
			for(int j=0; j < repeticiones; j++){
				do {
					aleatorio = generaNumeroAleatorio(inicio,fin);
				}while(buscarlinea(list,aleatorio));
				list.add(aleatorio);
			}
		}

		return list;
	}

	public static int generaNumeroAleatorio(int minimo,int maximo) {
		return ((int)Math.floor(Math.random()*(maximo-minimo+1)+(minimo)));
	}

	public static boolean buscarlinea(LinkedList<Integer> lista,int linea) {
		boolean encontrado=false;
		for (int i=0;i<lista.size() && !encontrado;i++)  {
			if (lista.get(i)==linea)
				encontrado=true;
		}
		return encontrado;
	}



	static int numerodelineas(String archivo) throws IOException{
		int acum=0;
		String cadena;
		FileReader f = new FileReader(archivo);
		BufferedReader b = new BufferedReader(f);
		while((cadena = b.readLine())!=null) {
			acum++;
		}

		b.close();
		return acum;
	}

	public static ArrayList<Integer> BoyerMoore(String patron,String texto){
		ArrayList<Integer> ocurrencias=new ArrayList<Integer>();
		if(patron.length()>0 && texto.length()>=patron.length()){
			ArrayList<Character> occChar=new ArrayList<Character>();
			ArrayList<Integer> occPos=new ArrayList<Integer>();
			iniciaOcc(occChar,occPos,patron);////Badcharacter
			//good-suffixheuristics
			int[] s=new int[patron.length()+1];
			int[] f=new int[patron.length()+1];
			preproceso1(s,f,patron);
			preproceso2(s,f,patron);
			boyerMoore(patron,texto,s,ocurrencias,occChar,occPos);
		}
		return ocurrencias;
	}

	private static void boyerMoore(String patron,String texto,int[] s,ArrayList<Integer> ocurrencias,ArrayList<Character> occChar,ArrayList<Integer> occPos){
		int i=0, j;
		while(i<=texto.length()-patron.length()) {
			j=patron.length()-1;
			while(j>=0 && patron.charAt(j)==texto.charAt(i+j)) j--;
			if(j<0) {
				ocurrencias.add(i);
				i+=s[0];
			}
			else{
				int aux=-1;
				if(occChar.contains(texto.charAt(i+j))) aux=occPos.get(occChar.indexOf(texto.charAt(i+j)));
				i+=Math.max(s[j+1], j-aux);
			}
		}
	}

	private static void preproceso2(int[] s,int[] f,String patron){
		//S�lo una parte de la coincidencia del sufijo se produce en el comienzo del patr�n
		int i, j;
		j=f[0];
		for(i=0; i<=patron.length(); i++) {
			if(s[i]==0) s[i]=j;
			if(i==j) j=f[j];
		}
	}

	private static void preproceso1(int[] s,int[] f,String patron){
		//El sufijo coincidente se produce en alg�n lugar dentro del patr�n
		int i=patron.length(), j=i+1;
		f[i]=j;
		while(i>0){
			while(j<=patron.length() && patron.charAt(i-1)!=patron.charAt(j-1)){
				if(s[j]==0) s[j]=j-i;
				j=f[j];
			}
			i--; j--;
			f[i]=j;
		}
	}

	private static void iniciaOcc(ArrayList<Character> occChar,ArrayList<Integer>occPos,String patron){
		//Badcharacter
		//hace la funcionoccocc("patrona",a)=6 occ("patrona",n)=5 la �a� de pos 1 no se cuenta //solo la mas lejana
		for(int n=patron.length()-1;n>=0;n--)
			if(!occChar.contains(patron.charAt(n))){
				occChar.add(patron.charAt(n));
				occPos.add(n);
			}
	}


}