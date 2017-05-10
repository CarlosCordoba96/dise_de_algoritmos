package Areas;

import java.util.Random;
import java.util.Scanner;

public class Pi {
	 public static void main(String[] args) {
	        Scanner sc = new Scanner(System.in);
	        Random r = new Random();
	        System.out.println("Escribe el número de repeticiones");
	        int repeticiones = sc.nextInt();
	        int i;                                                               
	      	 int nThrows = 0;                                             
	      	 int nSuccess = 0;                                            
	      									
	      	 double x, y;                                                 
	      									
	      	 for (i = 0; i < repeticiones ; i++)                         
	      	 {                                                            
	      	    x = Math.random();      // Throw a dart                   
	      	    y = Math.random();                                                
	      									
	      	    nThrows++;                                                        
	      									
	      	    if ( x*x + y*y <= 1 )             
	      	       nSuccess++;                                               
	      	 }                                                            
	      									
	      	 System.out.println("Pi/4 = " + (double)nSuccess/(double)nThrows );
	      	 System.out.println("Pi = " + 4*(double)nSuccess/(double)nThrows );
	        sc.close();
	    }
}
