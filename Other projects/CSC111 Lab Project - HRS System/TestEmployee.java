import java.util.*;        /* This project is by:
                              Aljwhra Almakhdoub 441201225
                              Raghad Aljuhaimi   441201212
                              Reef Althunian     441201390   */
public class TestEmployee
{

// This program is to manage hiring drivers and cleaning workers processes   

   static Scanner input=new Scanner(System.in);
   public static void main (String [] args)
   {
   
   //   Create an array of objects
     
      Employee[] hiringEmployees= new Employee[100];
      
   //  Create actual employee objects
   
      for (int ind=0 ; ind < hiringEmployees.length ; ind++){
         hiringEmployees[ind]= new Employee();
      }
   
   //   Variables declaration
      String option="";
      int maxEmpIndex = 9;
      boolean exit= false;
   
    //assign data to 10 employees 
   
      AddExistingEmployees(hiringEmployees);
      
      
        /* The following is to display the maximum frequency and minimum duration information about 
         some specific employees */
    
                           // to print the frequency and duration header
      System.out.println();
      System.out.println("                  Frequency and Duration for specific employees                          ");
      System.out.println("                 _______________________________________________ ");
   
    
      
      System.out.println();
      System.out.println();
      
    // Maximum frequency between employee(9876543211) and employee(1234567899)
      String MaxFrequency = maximumFrequency(hiringEmployees[1], hiringEmployees[2]);
   
      if (MaxFrequency.equals("-1")) 
         System.out.println("Employees have different job titles");
      else
         System.out.println("Employee with the id number ( "+ MaxFrequency +" ) can take a break during the weekend");
      System.out.println();
   
   // Minimum duration between employee(9876543222) and employee(9876543211)
      String MinDuration =  minimumDuration(hiringEmployees[0], hiringEmployees[1]);
      
      if( MinDuration.equals( "Dates are Mismatched") )
         System.out.println("Employees worked in a different date");
         
      else
         System.out.println("Employee with the id number ( " + MinDuration+ " ) worked less on " + hiringEmployees[0].getContractDate());
      System.out.println();
   
   // Maximum frequency between employee(9876543211) and employee(9876543222)
      MaxFrequency = maximumFrequency(hiringEmployees[0],hiringEmployees[1]);
   
      if(MaxFrequency.equals( "-1") )
         System.out.println("Employees have different job titles");
      else
         System.out.println("Employee with the id number ( " + MaxFrequency + " ) can take a break during the weekend ");  
      System.out.println();
   
   // Minimum duration between employee(9876543211) and employee(2234567891) 
      MinDuration = minimumDuration(hiringEmployees[1],hiringEmployees[3]);
   
      if(MinDuration.equals( "Dates are Mismatched"))
         System.out.println("Employees worked in a different date");
      else
         System.out.println("Employee with the id number ( " + MinDuration + " ) worked less on ( " + hiringEmployees[1].getContractDate() + " )"); 
      System.out.println("_________________________________________________________________________________________");
   
   
     
      
      //   The menu
   
      do{   // loop to repeat menu display until the user selects exit
      
            // To display menu and to receive user selection
            
         option= displayMenu();
         switch(option){
            case "1":
            // To add a new employee
               maxEmpIndex= AddNewEmployee(hiringEmployees, maxEmpIndex);
               break;
            case "2":
            // To start hiring contract 
               HireEmployee(hiringEmployees, maxEmpIndex);
               break;
            case "3":
            // To end hiring contract 
               EndEmpContract(hiringEmployees, maxEmpIndex);
               break;
            case "4":
            // To display an employee info 
               DisplayAnEmpInfo(hiringEmployees, maxEmpIndex);
               break;
            case "5": 
            // To display HRS system report
               DisplayHrsSysStat(hiringEmployees, maxEmpIndex);
               break;
            case "6": 
            // To verify exit
               exit=verifyExit();
               break;
            default: 
            // to display invalid selection
               System.out.println(optainFunctionalErrorMessage(16));
         }
      }while(!exit);
      
   }
   
//------------------------------------------------------------------------------------------------
       
   public static void AddExistingEmployees(Employee[] hiringEmployees){
   
   /* This method is to fill the 10 employees by invoking setValidNewDate method in Employee Class 
      that validate the attributes then set their values     */
      
      hiringEmployees[0].setValidNewData("9876543222","Driver","15/12/2020","16:00","22:00",true,1); 
      hiringEmployees[1].setValidNewData("9876543211","Cleaning Worker","15/12/2020","08:00","16:00",true,4) ; 
      hiringEmployees[2].setValidNewData("1234567899","Cleaning Worker","N/A","N/A","N/A",false,0); 
      hiringEmployees[3].setValidNewData("2234567891","Cleaning Worker","17/12/2020","08:00","13:00",true,2); 
      hiringEmployees[4].setValidNewData("1334567892","Driver","11/12/2020","10:00","15:00",true,1); 
      hiringEmployees[5].setValidNewData("4412356789","Cleaning Worker","05/12/2020","12:00","16:00",true,10); 
      hiringEmployees[6].setValidNewData("3114567894","Driver","N/A","N/A","N/A",false,2); 
      hiringEmployees[7].setValidNewData("8876543221","Driver","19/12/2020","08:00","14:00",true,1); 
      hiringEmployees[8].setValidNewData("7776543267","Cleaning Worker","N/A","N/A","N/A",false,3); 
      hiringEmployees[9].setValidNewData("1176543266","Cleaning Worker","02/12/2020","17:00","21:00",true,12);
   }
   
//------------------------------------------------------------------------------------------------
  
   public static String maximumFrequency(Employee one, Employee two){
   // to get maximum frequency between two employees  
      if(one.getJobTitle().equalsIgnoreCase(two.getJobTitle())) {
         if( one.getFrequency() > two.getFrequency())
            return one.getId(); 
         else
            if (two.getFrequency() > one.getFrequency() ) 
               return two.getId(); 
            else 
               return one.getId() + " - " + two.getId();
      }
      else
         return "-1" ; 
   }
   
//------------------------------------------------------------------------------------------------
  
   public static String minimumDuration(Employee one, Employee two){
   // to get minimum duration between two employees
      if(one.getContractDate().equalsIgnoreCase(two.getContractDate())){
         if( one.calculateDuration() < two.calculateDuration())
            return  one.getId() ; 
         else
            if( two.calculateDuration() < one.calculateDuration())
               return  two.getId() ; 
            
            else
               return one.getId() + " - " + two.getId();
      }
      else
         return "Dates are Mismatched"; 
   } 

//------------------------------------------------------------------------------------------------
    
   public static String displayMenu(){
   
   // This method is to display the menu and receive user selection
   
      String option;
      System.out.println("___________________________");
      System.out.println();
      System.out.println();
      System.out.println("1. Add a new employee");
      System.out.println("2. Start a hiring contract");
      System.out.println("3. End a hiring contract");
      System.out.println("4. Display employee info");
      System.out.println("5. Display HRS system status");
      System.out.println("6. Exit");
      System.out.println();
      System.out.println("___________________________");
      option = inputNextLine("Enter your option:"); // to receive user selection 
   
      return option;
   }
   
//------------------------------------------------------------------------------------------------   
    
   public static int AddNewEmployee(Employee[] hiringEmployees, int maxEmpIndex){
   
    // This method is to Add new employee after validating user input
   
      String id, jobTitle="";
      int msgNo=0;
      boolean goBack, validId, validJobTitle;
      do{
         validId= true;
         goBack= false;
      
         id= inputNextLine("Enter Employee Id or enter back to return to the menu");
         if (!(id.equalsIgnoreCase("back"))){
         //when the user wants to continue
         
            if (!(IsEmpNew(hiringEmployees, id, maxEmpIndex))){ //when the employee exists
                                                                //to receive the error message and then print it
               System.out.println(optainFunctionalErrorMessage(11)); 
               return maxEmpIndex;
            }                       //check employee Id validity by invoking validate id method in Employee Class
            msgNo= hiringEmployees[maxEmpIndex +1].validateId(id); 
            if (msgNo != 0){ //when the id is not valid
               validId= false;
               System.out.println(hiringEmployees[maxEmpIndex+1].retrieveAttributeErrorMessage(msgNo));
            }
            else 
               validId= true;
         }
         
         
         else 
            goBack=true;
            
                   //to repeat the loop if id is not valid or to quit when the user wants to go back to the menu
      }while(!goBack && !validId); 
            
      validJobTitle = false; //to enter the while loop
      
      while(!goBack && !validJobTitle){
         jobTitle= inputNextLine("Enter Employee job title or enter back to return to the menu");
         if (!(jobTitle.equalsIgnoreCase("back"))){          //when the user wants to continue
            msgNo= hiringEmployees[maxEmpIndex +1].validateJobTitle(jobTitle); //check employee job title validity  
            if (msgNo != 0){ //when the job title is not valid
               validJobTitle= false;
               System.out.println(hiringEmployees[maxEmpIndex+1].retrieveAttributeErrorMessage(msgNo));
            }
            else 
               validJobTitle= true;
         
         
         
         }
         
         else 
            goBack = true;
      
      }
      
      if (!goBack){      
                      // add the employee by invoking setValidNewData method in Employee Class
         msgNo= hiringEmployees[maxEmpIndex+1].setValidNewData(id,jobTitle,"N/A","N/A","N/A",false,0);
                      /* the following checking is to suit setValidNewData method logic in Employee
                         Class since the attributes are validated in here before invoking the method */
         if (msgNo != 0){  
            validJobTitle= false;
            System.out.println(hiringEmployees[maxEmpIndex+1].retrieveAttributeErrorMessage(msgNo));
            return maxEmpIndex;
         }
         else
            return maxEmpIndex+1;
      }
      else
      
         return maxEmpIndex;
   }
   
//------------------------------------------------------------------------------------------------
        
   public static void HireEmployee(Employee[] hiringEmployees, int maxEmpIndex){
   
           /* This method is to hire employee after validating user input except the assumed 
              valid attributes(generous exception in the assignment) */
   
      int msgNo=0;
      int empIndex= 0;
      String id, contractDate="", startTime="", endTime="";
      boolean proceed =true;
      id= inputNextLine("Enter Employee Id that you want to hire or enter back to return to the menu");
      if (!(id.equalsIgnoreCase("back"))){
                                    //when the user wants to continue
         empIndex= retrieveEmpIndex(hiringEmployees, id, maxEmpIndex); //to retrieve the entered employee index if found
        
         if (isIndexFound(empIndex)){ //when the employee exists
            if (isEmpHired(hiringEmployees, empIndex)){
                                    //when the employee is hired
               System.out.println(optainFunctionalErrorMessage(12));
               proceed = false;
            }
            
         }else {
           
            System.out.println(optainFunctionalErrorMessage(13));
            proceed = false;;
         }
      }else
         proceed = false;
   
      if(proceed){   // when the employee is found and the user wants to continue 
         contractDate= inputNextLine("Enter contract date or enter back to return to the menu");
         if (contractDate.equalsIgnoreCase("back")){
         //when the user wants to quit
            proceed = false;
         }
      }
      
      if (proceed){ // when the employee found as long as the user wants to continue 
         startTime= inputNextLine("Enter start time or enter back to return to the menu");
         if (startTime.equalsIgnoreCase("back")){
         //when the user wants to quit
            proceed = false;
         }
      }
      if (proceed){ // when the employee found as long as the user wants to continue
         endTime= inputNextLine("Enter end time or enter back to return to the menu");
         if (endTime.equalsIgnoreCase("back")){
         //when the user wants to quit
            proceed = false;
         }
      }
      
      if (proceed){ 
                    // Hire the employee by invoking setValidContractData method in Employee Class
         msgNo= hiringEmployees[empIndex].setValidContractData(contractDate, startTime, endTime, true);
         if (msgNo != 0){
            System.out.println(hiringEmployees[empIndex].retrieveAttributeErrorMessage(msgNo));
         
         }
         else {
            printHeader();    // to print the header
            hiringEmployees[empIndex].displayInfo();
            printTrailer();   // to print the trailer
            System.out.println();
            System.out.println("The employee is ready for starting the assigned job");
         }
      }  
     
   
   
   }
   
//------------------------------------------------------------------------------------------------
           
   public static void EndEmpContract(Employee[] hiringEmployees, int maxEmpIndex){
   
    //        This method is to end employee contract  
    
      int msgNo=0;
      int empIndex= 0;
      String id;
      boolean proceed =true;
      id= inputNextLine("Enter Employee Id that you want to end their contract or enter back to return to the menu");
      if (!(id.equalsIgnoreCase("back"))){
         //when the user wants to continue
         empIndex= retrieveEmpIndex(hiringEmployees, id, maxEmpIndex); //to retrieve the entered employee index if found
         
         if (isIndexFound(empIndex)){ //when the employee exists
            if (!(isEmpHired(hiringEmployees, empIndex))){
            //when the employee is not hired
               System.out.println(optainFunctionalErrorMessage(14));
               proceed = false;
            }
            
         }else {    //when the employee does not exist
         
            System.out.println(optainFunctionalErrorMessage(13));
            proceed = false;
         }
      }else
         proceed = false;  // when the user wants to go back to the menu
   
           
      if (proceed){        // when the emp found and is hired and as long as the user wants to continue
         printHeader();    // to print the header
         hiringEmployees[empIndex].displayInfo(); // display employee information before ending the contract 
      
         printTrailer();   // to print the trailer
                           // display employee duration before ending the contract 
         System.out.println("The duration is: "+ (hiringEmployees[empIndex].addHourWordToDuration(hiringEmployees[empIndex].calculateDuration())));
                           // End employee contract by involking setValidContractData method in Employee Class
         msgNo= hiringEmployees[empIndex].setValidContractData("N/A", "N/A", "N/A", false);
         if (msgNo != 0){
            System.out.println(hiringEmployees[empIndex].retrieveAttributeErrorMessage(msgNo));
         
         }
      }  
   }
   
//------------------------------------------------------------------------------------------------  
   
   public static boolean IsEmpNew(Employee[] hiringEmployees, String id, int maxEmpIndex){
   
   // This method is to check if the employee does not exist therefore She/He is a new employee
   
      for (int i=0 ; i<= maxEmpIndex ; i++){
         if (id.equals(hiringEmployees[i].getId())){
            return false;   // when the employee exists
         }
      }
      return true;       // when the employee does not exist
   }

   public static boolean isEmpHired(Employee[] hiringEmployees, int empIndex){
   
                 // This method is to check if the employee is hired
   
      return (hiringEmployees[empIndex].getHired());  // return true or false
   }
   
//------------------------------------------------------------------------------------------------

   public static int retrieveEmpIndex(Employee[] hiringEmployees, String id, int maxEmpIndex){
   
             /*  This method is to retrieve employee index depending on employee id and return 
                 the index when found or return -1 when it is not */
   
      int empIndex= -1;
          
      for (int i = 0; i <= maxEmpIndex ; i++){
         if (id.equals(hiringEmployees[i].getId())){
            empIndex = i;
            break;
         }
      }
      return empIndex;   // return index or -1
   }
   
//------------------------------------------------------------------------------------------------

   public static boolean isIndexFound(int empIndex){
   
                 /* This method interpret the index if it is -1 then the method return false to indicate that
                    the employee is not found but if it is not -1 the method return true to indicate that 
                    the employee is found */   
                    
      if (empIndex == -1){
         return false;
      }
      else
         return true;
   }

//------------------------------------------------------------------------------------------------

   public static void DisplayAnEmpInfo(Employee[] hiringEmployees, int maxEmpIndex){
   
                /* This method is to display a specific employee info by invoking displayInfo method 
                   in Employee Class */
                   
      int msgNo=0;
      int empIndex= 0;
      String id;
      
      id= inputNextLine("Enter Employee Id that you want to display their information or enter back to return to the menu");
      if (!(id.equalsIgnoreCase("back"))){
         //when the user wants to continue
         empIndex= retrieveEmpIndex(hiringEmployees, id, maxEmpIndex);
         
         if (isIndexFound(empIndex)){ //when the employee exists
            
            printHeader();  // to print the header
                            // display employee information  
            hiringEmployees[empIndex].displayInfo();
         
            printTrailer();   // to print the trailer
         
         
         }else {
           
            System.out.println(optainFunctionalErrorMessage(13));
            
         }
      }
   
   
   }

//------------------------------------------------------------------------------------------------
   
   public static  void DisplayHrsSysStat(Employee[] hiringEmployees,int maxEmpIndex){
   
                 //   This method is to print HRS statistics report 
                 
      String highestEmpFreq="";
      int empTotalNo = 0, hiredNo = 0, avalibleEmpNo = 0; 
      int maxFreq = hiringEmployees[0].getFrequency();
      for (int i = 0; i <= maxEmpIndex; i++){   // 
         empTotalNo+=1;                         // to calculate employees total
         if (hiringEmployees[i].getHired())
            hiredNo+=1;                         // to calculate hired employees 
         
         else
            avalibleEmpNo+=1;                   // to calculate available employees 
      
         if (  hiringEmployees[i].getFrequency() > maxFreq ){   // to compare frequencies to find out the max
            maxFreq = hiringEmployees[i].getFrequency();
            highestEmpFreq = hiringEmployees[i].getId();
         }                  
      }
                  // to print the header
      System.out.println();
      System.out.println();
      System.out.println("|__________________________________________________________________________________________________________|");
   
      System.out.printf("%1s %42s %18s %41s %2s %n","|"," ","HRS SYSTEM REPORT"," ","|"); 
      System.out.printf("%1s %104s %1s %n","|"," ","|"); 
                                  // to print the statistics
      System.out.printf("| %-25s %-4d %17s %-39s %-4d %8s |%n","Total number of employees: ",empTotalNo," ","The current hired number of employees: ",hiredNo," ");
      System.out.printf("| %-33s %-4d %9s %-40s %-10s%1s |%n","The number of available employees: ",avalibleEmpNo," ","The employee with the highest frequency: ",highestEmpFreq," ");
      System.out.printf("%1s %104s %1s %n","|"," ","|"); 
      System.out.println("|__________________________________________________________________________________________________________|");
                                        // to print the columns names and header
      System.out.printf("%1s%1s%3s %1s| %10s %2s| %-10s %6s| %13s%1s| %9s %1s| %8s%1s| %5s%1s| %10s %2s %1s %n","|"," ","Seq"," ","Employee Id"," ","job title"," ","contract date"," ","start time"," ","end time"," ","hired"," ","frequency"," ","|");         
      System.out.println("|      |               |                  |               |             |          |       |               |");
                                             // to print employees information
      System.out.println("|======|===============|==================|===============|=============|==========|=======|===============|");
      for (int i = 0; i <= maxEmpIndex; i++){
         System.out.printf("%1s%3d %2s| %10s %3s| %-15s %1s| %-10s %3s| %-5s %6s| %-5s %3s| %-3s %2s| %2s %-3s %6s %1s %n","|",i+1," ",hiringEmployees[i].getId()," ",hiringEmployees[i].getJobTitle()," ",hiringEmployees[i].getContractDate()," ",hiringEmployees[i].getStartTime()," ",hiringEmployees[i].getEndTime()," ",hiringEmployees[i].convertHiredToWording()," "," ",hiringEmployees[i].getFrequency()," ","|");               
         System.out.println("|______|_______________|__________________|_______________|_____________|__________|_______|_______________|");
      
      } 
      System.out.println("|==========================================================================================================|");
   
   }

//------------------------------------------------------------------------------------------------
   
   public static boolean verifyExit(){
   
                           // This method is to verify that the user wants to exit the system
   
      boolean exit= false;
      String verifyExit= inputNextLine("All info. will be lost. Are you sure you want to exit? (Yes/No)");
      if (verifyExit.equalsIgnoreCase("Yes")){  // when the user wants to exit
         exit=true;
         System.out.println();
                                // to print thanking words
         System.out.println("_________________________________________________________________________________________");
         System.out.println();
         System.out.println("Thank you for using the HRS System."); 
         System.out.println("Goodbye visit us again.");
         System.out.println("_________________________________________________________________________________________");
      }
      else if (!(verifyExit.equalsIgnoreCase("No"))) // when the user entered a word not yes or no
         System.out.println(optainFunctionalErrorMessage(15));
   
      return exit;
   
   }
   
//------------------------------------------------------------------------------------------------
    
   public static String inputNextLine(String prompt){
    
    // This method is to print the entry prompt that asked of the user and to read his input
      String intermedInput;
      System.out.println(prompt);
      intermedInput=input.nextLine();
      return intermedInput;
   
   }
   
//------------------------------------------------------------------------------------------------
  
   public static String optainFunctionalErrorMessage(int msgNo){
               /* This method is to find out the content of an error message number and return it. 
                  And it is only for the functional messages, is not concerned with error messages
                  that relate to the content of the attributes */
   
      String msgContent="";
      switch(msgNo){
         case 11: msgContent= "The employee already exists in the system";
            break;
         case 12: msgContent= "The employee is already hired";
            break;
         case 13: msgContent= "The employee does not exist";
            break;
         case 14: msgContent= "The employee is not hired";
            break; 
         case 15: msgContent= "Only (yes/no) is accepted!";
            break;
         case 16: msgContent= "Not a valid selection!";
            break;       
      }
      return msgContent;
   
   }

//------------------------------------------------------------------------------------------------
   
   public static void printHeader(){
   
   // This method is to print a header for displayInfo method in Employee Class
   
      System.out.println(" _______________________________________________________________");
      System.out.printf("%1s%4s %2s %4s%1s%2s %10s %1s%1s %2s %4s%1s %5s%1s%1s%3s %1s%1s%4s%1s%-4s%1s%n","|"," ","Id"," ","|"," ","Job Title"," ","|","Date"," ","|","Start","|"," ","End"," ","|","Hire","|","Freq","|"); 
      System.out.println("|____________|_______________|__________|______|______|____|____|");
      System.out.println("|            |               |          |      |      |    |    |");
   
   }  

//------------------------------------------------------------------------------------------------

   public static void printTrailer(){
   
   // This method is to print a trailer for displayInfo method in Employee Class
   
      System.out.println("|            |               |          |      |      |    |    |");
      System.out.println("|____________|_______________|__________|______|______|____|____|"); 
   
   
   }
   
 
    

}
