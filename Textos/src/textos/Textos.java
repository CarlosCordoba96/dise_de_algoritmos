package textos;

import java.io.*;
import java.util.ArrayList;

public class Textos {

    public static void main(String[]args) throws FileNotFoundException, IOException{
            System.out.println("Hola");
            ArrayList<Integer> ocurrencias=BoyerMoore("a",muestraContenido("quijoteCap1.txt"));
            System.out.println(ocurrencias.size()+" Ocurrencias");

    }
    static String muestraContenido(String archivo) throws FileNotFoundException, IOException {
          String cadena;
          String texto = "";
          FileReader f = new FileReader(archivo);
          BufferedReader b = new BufferedReader(f);
          while((cadena = b.readLine())!=null) {
           // System.out.println(cadena);
           
            texto=texto+"\n"+cadena;
          }
          //System.out.println(texto);

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