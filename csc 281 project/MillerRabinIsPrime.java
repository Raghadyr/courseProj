
package millerrabinisprime;

import static java.lang.StrictMath.sqrt;
import java.util.*;


public class MillerRabinIsPrime {
    
    
    public static void PrimeGeneration(int nMIN, int  nMAX) {
         
        for(int i= nMIN; i<= nMAX; i++) {

        int RandomNum =  nMIN + (int)(Math.random() * ((nMAX - nMIN) + 1));
        
        int k = 2 + (int) (Math.random() * ( ((sqrt(RandomNum)-1) - 2)  + 1) ); 
            
            if((IsPrime(RandomNum, k)).equals("prime"))
            System.out.printf("The number %d is a prime number.\n", RandomNum);
            else
            System.out.printf("The number %d is a composite number.\n",  RandomNum); }
        }
    

    // Miller-Rabin primality test. 
    public static String IsPrime(int n, int k) {
        int count = 0;

        if (n <= 1 || n == 4 || n%2 == 0)
            return "composite"; 
        if (n <= 3)
            return "prime";
        // find d, such that d*2^r = n-1
        int d = n-1; 
          while (d % 2 == 0)
            d /= 2;
          while(count<k) {
            // pick random number in (1, n-1) 
            // NOTE: the first two cases make sure that n>4
             int a = 2 + (int)(Math.random() % (n - 4)); 
             
             a = a % n;  // Update a if it is more than or equal to n.
         
          int x = modExp(a, d, n);
        // test for pow(a, d) % n 
             if(x==1 || x== n-1) return "prime"; 
             
             // test for ( pow(a,2d), pow(a, 4d) , ... , pow(a, (2^r-1)*d) ) % n -> "while d doesn't become n-1".
             while (d != n-1) {
                 x = (x*x) % n; 
                 d *=2;  
                 
                 if (x == 1) return "composite"; 
                 if(x == n-1) return "prime"; 
                     
                 
             } 
             

               count++;   
             }
                  
          return "composite"; 
    }
    
    

    
    // Fermat primality test.
     public static String FermatIsPrime(int n, int k) {
        int count = 0;
        if (n <= 1 || n == 4 || n%2 == 0)
            return "composite";
        if (n <= 3)
            return "prime";

          while(count<k) {
              // pick random number in (1, n-1)
             int a = 2 + (int)(Math.random() % (n - 4)); 
            
             a = a % n;  // Update a if it is more than or equal to n.
             
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
      System.out.println("Please enter the minimum number you want to start with: "); 
      int nMIN = input.nextInt(); 
      System.out.println("Enter the maximum number: "); 
      int nMAX = input.nextInt(); 
    
      
  
      if(nMAX<nMIN){
          int temp = nMIN; 
          nMIN = nMAX; 
          nMAX = temp; 
      }
            
       PrimeGeneration(nMIN, nMAX);  
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