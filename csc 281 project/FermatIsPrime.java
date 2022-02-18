
package fermatisprime;

import static java.lang.StrictMath.sqrt;
import java.util.Scanner;


public class FermatIsPrime {



     
     
       // Fermat primality test.
     public static String IsPrime(int n, int k) {
        int count = 0;
        if (n <= 1 || n == 4 || n%2 == 0)
            return "composite";
        if (n <= 3)
            return "prime";

          while(count<k) {
              // pick random number in (1, n-1)
             int a = 2 + (int)(Math.random() % (n - 4)); 
            
                         
                int x = modExp(a, n-1, n);
             // n is not a prime number, which means it is a composite integer.
             if(x != 1) return "composite"; 
             count++; 
    
}
//  n may be a prime number
       return "prime"; 
       
    
       
     }
     
     
    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in); 
      System.out.println("Please a number: "); 
      int n = input.nextInt(); 
     
      
        int k = 2 + (int) (Math.random() * ( ((sqrt(n)-1) - 2)  + 1) ); 
       if((IsPrime(n, k)).equals("prime"))
            System.out.printf("The number %d is a prime number.\n", n);
       else      System.out.printf("The number %d is a composite number.\n", n);
           

  

        

    }
    
    
        // -------------------------> Compute: x = pow(b, e) % n 
        static int modExp(int b, int e, int n) { 
    
        int result;
        if (e==0) return 1; 
        else if (e%2 == 0) {
            result = modExp(b, e/2, n); 
            return (result*result) % n; 
        }
        else {
            result = modExp(b, e-1,n);  
        }
            
            
        return (b*result) %n;
       
      
    } 
    
}
