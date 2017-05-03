package textos;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Textos {

    public static void main(String[]args) throws FileNotFoundException, IOException{
            int porcentaje = 0;
            int lineas_totales;
            int lineas_espacio;
            int linea = 0;
            
            System.out.println("Hola");
            Scanner s = new Scanner (System.in);
            lineas_totales = numerodelineas("quijoteCap1.txt");
            System.out.println("De un total de " + lineas_totales + " líneas.");
            System.out.println("Introduzca el porcentaje de texto que desea");
            porcentaje = s.nextInt();

            lineas_espacio = (lineas_totales * porcentaje)/100;
           //System.out.println("Res porcentaje: " + lineas_espacio);
            int lineas_intervalo = lineas_totales/lineas_espacio;
            //System.out.println("Lineas intervalo" + lineas_intervalo);

            ArrayList<Integer> ocurrencias=BoyerMoore("a",muestraContenido("quijoteCap1.txt", lineas_intervalo));
            System.out.println(ocurrencias.size()+" Ocurrencias");

    }
    static String muestraContenido(String archivo, int leer) throws FileNotFoundException, IOException {
          String cadena;
          String texto = "";
          FileReader f = new FileReader(archivo);
          BufferedReader b = new BufferedReader(f);
          
          Random r = new Random();
          int cont_linea = 0;
          int linea = r.nextInt(leer) + 1;
          int lineas_contadas = 0;
          
          while((cadena = b.readLine())!=null) {
              if (cont_linea == linea) {
                 // System.out.println(cadena);
                  texto=texto+"\n"+cadena;
                  lineas_contadas++;
              }
              if (cont_linea == leer) {
                  linea = r.nextInt(leer) + 1;
                  cont_linea = 0;              
              }
              cont_linea++;
          }
          
          System.out.println("Líneas contadas totales: " + lineas_contadas);
          System.out.println(texto);

          b.close();
          return texto;
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