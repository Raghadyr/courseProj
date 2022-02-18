public class Employee{
                           /* This project is by:
                              Aljwhra Almakhdoub 441201225
                              Raghad Aljuhaimi   441201212
                              Reef Althunian     441201390   */


// This class to facilate hiring Drivers and Cleaning Workers attributes

   private String id;
   private String jobTitle;
   private String contractDate;
   private String startTime;
   private String endTime;
   private boolean hired;
   private int frequency;

   private void setId(String id) {
      this.id = id; }
   private void setJobTitle(String jobTitle) {
      this.jobTitle = jobTitle; }
   private void setContractDate(String contractDate) {
      this.contractDate = contractDate; }
   private void setStartTime(String startTime) {
      this.startTime = startTime; }
   private void setEndTime(String endTime) {
      this.endTime = endTime; }
   private void setHired(boolean hired) {
      this.hired = hired; }
   private void setFrequency(int frequency) {
      this.frequency = frequency; }
    

    
   public String getId() {
      return id; }
   public String getJobTitle() {
      return jobTitle; }
     
   public String getContractDate() {
      return contractDate; }
   public String getStartTime() {
      return startTime; }
   public String getEndTime() {
      return endTime; }
   public boolean getHired() {
      return hired; }

   public int getFrequency() {
      return frequency; }


   private void updateFrequency(){
      setFrequency(getFrequency()+1);
   }
   
   public int calculateDuration(){
   
      int startHr=Integer.parseInt(startTime.substring(0,startTime.indexOf(":")));
      int endHr=Integer.parseInt((endTime.substring(0,endTime.indexOf(":"))));
      if (startHr>endHr){
         endHr=endHr+24;
      }
      return endHr - startHr;
   } 
   
        
   public String addHourWordToDuration(int totalTime){
   
   //to add hour word to duration value
      String  totalDuration;
   
      
      if (totalTime==0)
         totalDuration="";
      else if (totalTime==1)       // when duration equals one hour
         totalDuration=" One Hour ";
      else                        // when duration greater than one hour
         totalDuration=Integer.toString(totalTime)+" Hours ";
         
       
      return totalDuration;
   }
   
  
   public String convertHiredToWording(){
              // this method to convert true to yes and false to no
      return (getHired()? "Yes":"No");
   }


   public void displayInfo(){
      
      System.out.printf("|%-12s|%15s|%10s|%6s|%6s|%-4s|%-4d|%n",id,jobTitle,contractDate,startTime,endTime,convertHiredToWording(),frequency);
   }//end of third method


   public int setValidNewData(String id,String jobTitle,String contractDate,String startTime,String endTime, boolean hired, int frequency){
     
          /* This method is to set all the attributes of an employee after making sure that the data is valid.
             It calls the related method to validate each attribute.
             Note the date and time attributes assumed to be correct according to the assignment.
    
             This method and setValidContractData the only methods that allowed to set the data 
             publicly to make sure that the attributes is set validly  */
   
      int msgNo= 0;
      msgNo= validateId(id);     // check id validity
   
      if (!(msgNo== 0))
         return msgNo;
         
      msgNo= validateJobTitle(jobTitle);  // check job title validity
      if (!(msgNo== 0))
         return msgNo; 
      switch(jobTitle.toLowerCase()){     // to make sure that job title is in the formal wordings
         case "driver": jobTitle = "Driver";
            break;
         case "cleaning worker": jobTitle= "Cleaning Worker";
            break;
      }
      if (!hired){      // check contract date to be not available value when employee is not hired
         msgNo= validateUnavailableCode(contractDate, "contDate");        
         if (!(msgNo== 0))
            return msgNo; 
      }
      if (!hired){      // check start time to be not available value when employee is not hired
         msgNo= validateUnavailableCode(startTime, "startTime");
         if (!(msgNo== 0))
            return msgNo;
      }
      if (!hired){      // check end time to be not available value when employee is not hired
         msgNo= validateUnavailableCode(endTime, "endTime");
         if (!(msgNo== 0))
            return msgNo;
      }
      msgNo= validateFrequency(frequency);  // check frequency validity
      if (!(msgNo== 0))
         return msgNo;
      
                        //  set the attributes since they are proved previously to be valid
      
      setId(id);
      setJobTitle(jobTitle);
      setContractDate(contractDate);
      setStartTime(startTime);
      setEndTime(endTime);
      setHired(hired);
      setFrequency(frequency);
      return msgNo;
   }
   
//------------------------------------------------------------------------------------------------   
    
   public int setValidContractData(String contractDate,String startTime,String endTime, boolean hired){
   
   
   /* This method is to set contract attributes for both the hired employee and the terminated one according to
      hired attribute (true/false).
          
      The method should make sure that the data is valid but note:-
      the date and time attributes assumed to be correct according to the assignment.
     
      This method and setValidNewData the only methods that allowed to set the data publicly to make sure 
      that the attributes is set validly. */
   
   
   
      int msgNo=0;
      if (!hired){    // check contract date to be not available value when employees' contract to be ended
      
         msgNo= validateUnavailableCode(contractDate, "contDate");
         if (!(msgNo== 0))
            return msgNo; 
      }
      if (!hired){    // check start time to be not available value when employees' contract to be ended
         msgNo= validateUnavailableCode(startTime, "startTime");
         if (!(msgNo== 0))
            return msgNo;
      }
      if (!hired){     // check end time to be not available value when employees' contract to be ended
      
         msgNo= validateUnavailableCode(endTime, "endTime");
         if (!(msgNo== 0))
            return msgNo;
      }
                           //  set the attributes since they are assumed to be valid
   
      setContractDate(contractDate);
      setStartTime(startTime);
      setEndTime(endTime);
      setHired(hired);
      if (hired){
         updateFrequency();
      }
   
      return msgNo;
   }
   
//------------------------------------------------------------------------------------------------

   public int validateId(String id){
               // This method is to validate id
   
      int msgNo= 0;
      if (id.length() != 10)   // when id length is not ten
         msgNo=101;
   
      if (msgNo == 0){
         for (int i=0 ; i < id.length() ; i++){
            if (!(Character.isDigit(id.charAt(i)))){   // when id is not numeric
               msgNo = 201;
               break;
            }
         }
      }
      return msgNo;                     // return msgNoand, it will be 0 when the id is valid 
   }
   
//------------------------------------------------------------------------------------------------

   public int validateJobTitle(String jobTitle){
   
   // This method is to validate job title
   
      int msgNo= 0;
   
      switch(jobTitle.toLowerCase()){
         case "driver":
            break;
         case "cleaning worker":
            break;
         default: msgNo=301;     // when job title value is not driver or cleaning worker
      
      }
      return msgNo;              // return msgNoand, it will be 0 when the id is valid  
   }
   
//------------------------------------------------------------------------------------------------

   public int validateUnavailableCode(String UnavailableCode, String fieldType){
   
            /* This method is to validate contractDate, startTime and endTime to be N/A when 
               the employee is not hired or her/his contract being ended */
   
      int msgNo= 0;                      
      if (!(UnavailableCode.equals("N/A"))){     // when value is not equal to N/A
         if (fieldType.equals("contDate")){   //  when the field is contractDate
            msgNo = 102;           
         }
         else if (fieldType.equals("startTime")){  //  when the field is startTime
            msgNo = 103;
         }
         else if (fieldType.equals("endTime")){    //  when the field is endTime
            msgNo = 104;
         }
      
      }
      return msgNo;            // return msgNo, it will be 0 when the field is valid meaning it equals N/A
   }
   
//------------------------------------------------------------------------------------------------   

   public int validateFrequency(int frequency){
   
               // This method is to validate id
   
      int msgNo= 0;
      if (frequency < 0)   // when frequency less than 0
         msgNo=101;
   
      return msgNo;                     // return msgNo and it equals 0 when the id is valid 
   }

//------------------------------------------------------------------------------------------------

   public String retrieveAttributeErrorMessage(int msgNo){
   
                   /* This method is to find out the content of an error message number and return it. 
                  And it is only related to the attributes content validity */
      
      String msgContent="";
      switch(msgNo){
         case 101: msgContent= "Id must be 10 digits";
            break;
         case 102: msgContent= "Contract date must be N/A since employee is not hired yet";
            break;
         case 103: msgContent= "Start time must be N/A since employee is not hired yet";
            break;
         case 104: msgContent= "End time must be N/A since employee is not hired yet";
            break;
         case 105: msgContent= "frequency must be 0 or greater";
            break;
         case 201: msgContent= "Id must be numeric";
            break;
         case 301: msgContent= "Job title must be driver or cleaning worker";
            break;
      }
   
      return msgContent;
   }

}//end class
