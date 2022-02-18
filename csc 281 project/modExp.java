


package project281;
import java.math.BigInteger;
import java.util.*; 

public class modExp {


    public static void main(String[] args) {
    
       
       
  Scanner input = new Scanner(System.in); 
  System.out.println("Please enter the value of n: ");
   int n= input.nextInt(); 
  System.out.println("Please enter a value of b: "); 
   int b= input.nextInt(); 
  System.out.println("Please enter a value of e: ");
   int e= input.nextInt(); 
  
   if( ! (n>=1) ) { 
       do {
   System.out.println("Please enter another value of n (n>=1): ");
   n= input.nextInt(); 

       } while (! (n>=1) );
        
   }
   
   if( ! (b>=0)) {
   do{
   System.out.println("Please enter another value of b (b>=0): "); 
   b= input.nextInt(); 
   } while( ! (b>=0) );
   

   }
   
   if( ! (e>=0) ) {
       do{
   System.out.println("Please enter another value of e (e>=0): ");
   e= input.nextInt(); } while(! (e>=0));
   }
   
 System.out.println(b +"^"+ e +" mod "+ n +" = "+ modExp(b,e,n));

  

    }
    

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

     

    
   // The second version of modExp computes b^e then take the result mod n
   static BigInteger modExp2(int b, int e, int n) {
 BigInteger result = BigInteger.ONE; 

 for (int i = 0; i < e; e--) {
      result =  result.multiply(BigInteger.valueOf(b));
            
    }
    return result.remainder(BigInteger.valueOf(n)) ; 
   }
   

}


    
    
