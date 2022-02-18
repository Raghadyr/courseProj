#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include <stdbool.h>


int ErrorHandle();

void printList ();
int AgetoNum(char *ch);
int searchDate(char *date);
 struct Attendee{
    int id;
    char name [20];
    int age;
    char date [20];
    char state;
    char ActivityZone[35];
    char Address[6];
    int numOfCompanion;
    struct listOfComp{
        int id;
        char name [20];
        int age;
        char gender;
        char Address[5];
    }*listOfCompanion;
    float activityFee;
    float totalFees;
    float balance;
    struct node *next;
}node;



struct Activity {
    char ActivityZone[35];
    char date[20];
    float price;
    char FageRange[3];
    char ageRestrec[3];

}ActList[100];
void addNewAttendee();
void deleterecord ();
void showattendeereport ();
void editrecord ();
void editactivity();
void showattendee();
void listAttende();

struct Attendee *head,*cur = NULL,*registered,*singleVisit;
void writeattendee (char* fileName,struct Attendee *temp);
int noAttende=0;
int main() {
    FILE *fp,*fpr,*fpv;

    if (!(fp = fopen("Activities.txt", "r"))) {
        printf("error opining the file");
        return 0;
    }

    char line[100];
    fgets(line,100,fp);
    for(int i = 0 ; i < 10 ; i++){ // read 10 Activity from file

        fread( ActList[i].ActivityZone , sizeof(char), 32, fp );
        ActList[i].ActivityZone[32] = '\0';
        fscanf(fp, "%f %s %s %s", &ActList[i].price , ActList[i].date ,ActList[i].ageRestrec  ,ActList[i].FageRange );

    }
    fclose(fp);

    if(!(fpv = fopen("Single Visit Attendee.txt", "w")) || !(fpr= fopen("Registered Attendee.txt","w"))){
        printf("error opening the file");
        return 0;
    }
    fprintf(fpv,"Single Visit Attendee:\n");
    fprintf(fpv,"%-15s %-15s %-15s %-15s %-15s %-15s %-15s\n","id","name","age","Address","activity zone","activity fee","balance");

    fprintf(fpr,"Registered Attendee:\n");
    fprintf(fpr,"%-15s %-15s %-15s %-15s %-15s %-15s %-15s\n","id","name","age","Address","activity zone","activity fee","balance");
    fclose(fpv);
    fclose(fpr);

    printf("WELCOME TO\nRiyadh Season 2021\nImagine, MORE!!\n--------------------\n");
    for (int i = 0; ActList[i].ActivityZone[0]!='\0'; ++i) {
        printf("%-30s %-30f %-30s %-30s %-30s\n",ActList[i].ActivityZone,ActList[i].price,ActList[i].date,ActList[i].ageRestrec,ActList[i].FageRange);
    }
    printf("--------------------\n");

    int choise = 0;
    do {


        printf("Enter the corresponding no\n");
        printf("1.Add new attendee record\n2.Search or edit record\n3.Edit attendee\n4.Edit activity\n5.Show report\n6.Know the records of attendees\n7.Delete the records\n8.Exit from the program\n");
        fflush(stdin);
        scanf("%d", &choise);
        switch (choise) {
            case 1:
                addNewAttendee();
                break;
            case 2:
                editrecord();
                break;
            case 3:
                deleterecord();
                break;
            case 4:
                editactivity();
                break;
            case 5:
                showattendeereport();
                break;
            case 6:
                showattendee();
                break;
            case 7:
                head=NULL;
                registered=NULL;
                singleVisit=NULL;
                break;
            case 8:
                break;

        }

    } while (choise!=8);
    printList();
    return 0;
}

void addNewAttendee() {

    struct Attendee *temp=NULL;
    temp = (struct Attendee*) malloc(sizeof(struct Attendee));
    int flag = 1;
    int n=0,k=0;
    char c;
    do {
        printf("enter your ID: \n");
        fflush(stdin);
        scanf("%d", &temp->id);
        printf("enter your name: \n");
        fflush(stdin);
        scanf("%[^\n]", temp->name);
        printf("enter your age: \n");
        scanf("%d", &temp->age);



        printf("enter the date format [yyyy/mm/dd] of the attending: \n");
        fflush(stdin);
        scanf("%s", temp->date);
        for (int i = 0; temp->date[i]!='\0'; i++) {
            if (!isdigit(temp->date[i]) && temp->date[4] != '/' && temp->date[7] != '/') {
                flag=ErrorHandle();
                if(flag==2)
                    continue;
                else
                    return;
            }
        }
        if(searchDate(temp->date)>50) {
            printf("the date have the reached the limt of attendee ");
            return;
        }
        fflush(stdin);
        printf("enter your Address: \n");
        scanf("%s", temp->Address);
        for (int i = 0; temp->Address[i]!='\0'; i++) {//if the user enters more than the specific digits it will be handled with the error code
            if (temp->Address[i] == '\0')
                flag = 0;
            else if (isdigit(temp->Address[i]) && i < 3)
                flag = 0;
            else if (isalpha(temp->Address[i]) && i > 3)
                flag = 0;
        }
        if(flag==0) {
            flag = ErrorHandle();
            if (flag == 2)
                continue;
            else
                return;
        }
        printf("enter your state \'R\' for registered \'V\' Single visitor: \n");
        fflush(stdin);
        scanf("%c", &temp->state);
        if (temp->state != 'R' && temp->state != 'V') {
            flag=ErrorHandle();
            if(flag==2)
                continue;
            else
                return;
            }

        fflush(stdin);
        printf("enter your activity zone: \n");
        gets(temp->ActivityZone);
        temp->ActivityZone[32]='\0';
        int aflag=1;
        int v=0;

        for (v = 0; ActList[v].ActivityZone[0]!='\0'; v++) {// v to save the index for the total fee
            if(strstr(ActList[v].ActivityZone,temp->ActivityZone) != NULL){
                temp->activityFee=ActList[v].price;
                aflag=0;
                break;
            }
        }
        /*       OR
         * for (v = 0; ActList[v].ActivityZone[0]!='\0'; v++) {
            if(strncasecmp(ActList[v].ActivityZone,temp->ActivityZone,a) == 0){
                aflag=0;
                break;
            }
        }
         but its only work for the first activity
         */
        if(aflag==1){
        printf("the activity dose not exist! \n");
            flag = ErrorHandle();
            if (flag == 2)
                continue;
            else
                return;
        }
        printf("enter your balance: \n");
        fflush(stdin);
        scanf("%f", &temp->balance);

        if(temp->state=='R') {
            printf("enter number of companions: \n");
            fflush(stdin);
            scanf("%d", &temp->numOfCompanion);
        }  else {
            temp->numOfCompanion=0;
            temp->totalFees=ActList[v].price;
            if (temp->totalFees > temp->balance)
                printf("Sorry the your balance is not enough for the operation\n");
        }
            for (int i = 0; ActList[i].ActivityZone[0]!='\0'; ++i) {
                if (ActList[i].ActivityZone == temp->ActivityZone) {
                    temp->activityFee = ActList[i].price;
                    k = i;
                    if (temp->age < AgetoNum(ActList[i].ageRestrec) && temp->age != 0) {
                        printf("the age you entered is not allowed for the activity\n");
                        flag = ErrorHandle();
                        if (flag == 2)
                            continue;
                        else
                            return;
                    } else if (temp->age < AgetoNum(ActList[i].FageRange) && temp->age != 0)
                        printf("there is no fee for the attend\n");
                }
            }
            n = temp->numOfCompanion + 1;

            temp->listOfCompanion=(struct listOfComp*) calloc((unsigned int) temp->listOfCompanion, sizeof(struct listOfComp));
            if (temp->numOfCompanion > 0) {
                for (int i = 0; i < temp->numOfCompanion; i++) {
                    printf("enter the Attendee  id: \n");
                    fflush(stdin);
                    scanf("%d", &(temp->listOfCompanion + i)->id);
                    printf("enter the Attendee  name: \n");
                    fflush(stdin);
                    scanf("%s", (temp->listOfCompanion + i)->name);
                    printf("enter the Attendee  age: \n");
                    fflush(stdin);
                    scanf("%d", &(temp->listOfCompanion + i)->age);
                    if ((temp->listOfCompanion + i)->age < AgetoNum(ActList[k].ageRestrec) && AgetoNum(ActList[k].ageRestrec) != 0) {
                        printf("the age you entered is not allowed for the activity\n");
                        i=i-1;
                        printf("do you have another companion Y or N\n");
                        fflush(stdin);
                        scanf("%c", &c);
                        if (c == 'Y')
                            continue;
                        else if (c == 'N')
                            break;
                        else {
                            printf("invalid input\n");
                            flag = 0;
                        }
                    } else if ((temp->listOfCompanion + i)->age < AgetoNum(ActList[k].FageRange) &&  AgetoNum(ActList[k].FageRange) != 0) {
                        printf("the Attendee will dosent have a fee\n");
                        n--;
                    }
                    printf("enter the Attendee gender F or M: \n");// make error handle
                    fflush(stdin);
                    scanf("%c", &(temp->listOfCompanion + i)->gender);
                    printf("enter the Attendee address:\n");
                    scanf("%s", (temp->listOfCompanion + i)->Address);
                    for (int j = 0; ((temp->listOfCompanion + i)->Address[j]) != '\0' ; ++j) {//if the user enters more than the specific digits it will be handled with the error code
                        if(((temp->listOfCompanion+i)->Address[j])=='\0')
                            flag=0;
                        else if (isdigit((temp->listOfCompanion + i)->Address[j]) && j < 3)
                             flag = 0;
                         else if (isalpha((temp->listOfCompanion + i)->Address[j]) && j > 3){
                            flag = 0;
                        }
                    }

                    if (flag == 0) {
                        flag = ErrorHandle();
                        if (flag == 2) {
                            i=i-1;
                            continue;
                        } else
                            return;
                    }

                }

                temp->totalFees = temp->activityFee * (float) n;
                if (temp->totalFees > temp->balance && temp->balance>0)
                    printf("Sorry the your balance is not enough for the operation\n");
            }

            flag=0;
        }while (flag != 0);

    cur = (struct Attendee*) malloc(sizeof(struct Attendee));
    if (head == NULL) {
        head = (struct Attendee*) malloc(sizeof(struct Attendee));
        head=temp;
        cur= head;
        noAttende++;
    } else{
        cur=head;
        while (cur!=NULL){
        cur= (struct Attendee *) cur->next;
        }
        cur=temp;
        noAttende++;
        }
    //adding the linked list for the registered and the single value=
    if(temp->state=='R'){
        if(registered==NULL){
            registered=temp;
        } else {
            cur = registered;
            while (cur != NULL){
            cur = (struct Attendee *) cur->next;
            }
            cur = temp;
        }
    }
    if(temp->state=='V'){
         cur = NULL;
        if(singleVisit==NULL){
            singleVisit=temp;
        } else {
            cur = singleVisit;
            while (cur != NULL){
            cur = (struct Attendee *) cur->next;}
            cur = temp;
        }
    }
    //inserting the node
    if(temp->state=='R')
    writeattendee("Registered Attendee.txt",temp);
    else
    writeattendee("Single Visit Attendee.txt",temp);


    }

void writeattendee (char* fileName,struct Attendee *temp){
    FILE *fpo;
    fpo= fopen(fileName,"a");
    fprintf(fpo,"%-15d %-15s %-15d %-15s %-15s %-15d %-15d\n",temp->id,temp->name,temp->age,temp->Address,temp->ActivityZone,(int)temp->totalFees,(int)temp->balance);

}
int ErrorHandle() {
    char c;
    printf("please enter the correct format\n");
    printf("do you want to re enter the data Y or N\n");
    fflush(stdin);
    scanf("%c", &c);
    if (c == 'N') {
        return 0;
    } else if (c == 'Y')
        return 2;
    else {
        printf("invalid input");
        return 0;
    }
}
int AgetoNum(char ch[]){

    char tmp[3];
    int num;
    for (int i = 0,k=0; ch[i]!='\0'; i++) {
        if(isdigit(ch[i])){
            tmp[k]=ch[i];
            k++;
        }
    }
    if(ch[0]=='-')
        num=0;
    else
        num= atoi(tmp);
    return num;
}
int searchDate(char *date){
    int num=0;
    cur=head;
    while (cur!=NULL){
        if(strcmp(cur->date,date)==0)
        num=cur->numOfCompanion+1;
        cur= (struct Attendee *) cur->next;
    }
    return num;
}
void deleterecord(){

    int ID, choice, numOfact=0;

    char actname[35];
    fflush(stdin);
    printf("Enter your ID ");
    scanf("%d", &ID);

    cur=head;

    while (cur != NULL)
    {
        if(cur->id == ID){
            numOfact++;
        }
        cur = (struct Attendee *) cur->next;
    }

    if(numOfact>1){
        printf("This attendee have more than one activities booked, which are: \n");
        cur = head;

        while (cur != NULL)
        {
            if(cur->id == ID)
                printf("%s/n", cur->ActivityZone);
            cur = (struct Attendee *) cur->next;
        }
        fflush(stdin);
        printf("Enter 1 or 2 to 1-delete all activities or \n2-single activity \n");
        scanf("%d", &choice);
        cur=head;
        switch (choice)
        {
            case 1:
                for (int i = 0; i <numOfact ; ) {
                    if(head->id == ID){
                        struct Attendee *temp = head;
                        head = (struct Attendee *) head->next;
                        free(temp);
                    i=i+1;}

                    else{
                        struct Attendee *prev = head;
                        cur= (struct Attendee *) head->next;
                        while(cur != NULL){
                            if(cur->id == ID){
                                prev->next = cur->next;
                                free(cur);
                                break; }
                            prev = cur;
                            cur = (struct Attendee *) cur->next;}
                        i=i+1;}
                }

                break;
            case 2:
                fflush(stdin);
                printf("Enter the activity name \n");
                scanf("%s", actname);

                if(head->id == ID && (!strcmp(actname, head->ActivityZone))){
                    struct Attendee *temp = head;
                    head = (struct Attendee *) head->next;
                    free(temp);}

                else{
                    struct Attendee *prev = head;
                    cur= (struct Attendee *) head->next;
                    while(cur != NULL){
                        if(cur->id == ID && (!strcmp(actname, cur->ActivityZone))){
                            prev->next = cur->next;
                            free(cur);
                            break; }
                        prev = cur;
                        cur = (struct Attendee *) cur->next;}
                }


                break;

            default:
                printf("Error, a wrong number!\n");
                break;
        }
    }

    else if(numOfact == 1){
        if(head->id == ID){
            struct Attendee *temp = head;
            head = (struct Attendee *) head->next;
            free(temp);
            }

        else{
            struct Attendee *prev = head;
            cur= (struct Attendee *) head->next;
            while(cur != NULL){
                if(cur->id == ID){
                    prev->next = cur->next;
                    free(cur);
                    break; }
                prev = cur;
                cur = (struct Attendee *) cur->next;}
            }

    }

    else   printf("The ID given is a wrong number\n");
    printf("the attend has been deleted successfully\n");
}
void showattendeereport (){

    cur = head;
    int counter;

    for(int i=0; ActList[i].ActivityZone[0]!='\0'; i++){
        counter=0;
        while(cur != NULL){
            if ( strstr(ActList[i].ActivityZone, cur->ActivityZone) !=NULL){
                counter=counter+cur->numOfCompanion+1;
            } cur = (struct Attendee *) cur->next;
        }

        printf("Total attendees numbers for %-15s is %d \n",ActList[i].ActivityZone,counter);
    }
    fflush(stdin);
    printf("Enter date: \n");
    char date[20] ;
    scanf("%s" , date);

    int count;
    int TotalAttendeByDate = 0 ;
    float TotalIncomeByDate = 0;

    cur = head;

    for(int i=0; ActList[i].ActivityZone[0]!='\0'; i++){
        count=0;
        while(cur != NULL){
            if (strcmp (date , cur->date)==0 && strstr(ActList[i].ActivityZone, cur->ActivityZone) !=NULL){
                count=count+cur->numOfCompanion+1;
                TotalAttendeByDate=TotalAttendeByDate+cur->numOfCompanion+1;
                TotalIncomeByDate=TotalIncomeByDate+cur->totalFees;
            }
            cur = (struct Attendee *) cur->next;
        }
        printf("Number of attendees in %-30s is %d on %s \n",ActList[i].ActivityZone, count,  date);
    }
    printf("Total attendees numbers for date %-30s is %d \n", date, TotalAttendeByDate);
    printf("Total income for date %-20s is %.2f \n",date, TotalIncomeByDate);
}//end mathod
void editrecord (){
    int ID,numOfCom,choice1,choice2,cID;
    float newbalance;
    bool foundR = false, foundS = false;
    fflush(stdin);
    printf("Enter 1 or 2 to 1-search 2-updated the list of companions\n");
    scanf("%d", &choice1);
    fflush(stdin);
    printf("Enter your ID ");
    scanf("%d", &ID);

    struct Attendee *curr = singleVisit;
    while (curr != NULL)
    {  if(curr->id == ID){
            foundS = true;
            break;
        }
        curr = (struct Attendee *) curr->next;
    }

    curr = registered;
    while (curr != NULL)
    {  if(curr->id == ID){
            foundR = true;
            break;
    }

        curr = (struct Attendee *) curr->next;
    }
    if(choice1 == 1){
        if(foundR || foundS)
            printf("The ID exist\n");
    }

    if((!foundR) && (!foundS)){
        printf("The given ID is a wrong number\n");
        return;}
    if(foundR){
        if(choice1 == 2){
            fflush(stdin);
            printf("Enter 1 or 2 to 1-delete companion/s\n 2-add companion/s \n");
            scanf("%d", &choice2);
            fflush(stdin);
            printf("How many do you want to add ? \n");
            scanf("%d", &numOfCom);

            if(numOfCom == 0){
                printf("No change \n");
                return;
            }

            if(numOfCom < 0){
                printf("Error, negative number \n");
                return;}

            switch (choice2)
            {
                case 1:
                    if(curr->numOfCompanion < numOfCom){
                        printf("The number you entered is more than number of companion you have \n");
                        return;
                    }

                    for(int i = 0; i < numOfCom; i++){
                        fflush(stdin);
                        printf("Enter the ID of companion you want to delete \n");
                        scanf("%d", &cID);

                        struct listOfComp temp = *(curr->listOfCompanion);


                        for(int j = 0; j < curr->numOfCompanion; j++){
                            if( cID == temp.id){
                                for( int k = j; k < curr->numOfCompanion-1; k++){
                                    *(curr->listOfCompanion + k) = *(curr->listOfCompanion + (k+1));
                                }
                                break;
                            }
                            if((curr->listOfCompanion + (j+1)) != NULL )
                                temp = *(curr->listOfCompanion + (j+1));

                        }
                    }
                    curr->numOfCompanion = numOfCom;
                    curr->listOfCompanion = (struct listOfComp *) realloc(curr->listOfCompanion, (curr->numOfCompanion) * sizeof(struct listOfComp));


                    break;

                case 2:

                    curr->totalFees = (curr->activityFee * (float )numOfCom);
                    fflush(stdin);
                    printf("How much balance do you have?\n");
                    scanf("%F", &newbalance);

                    if(newbalance < curr->totalFees){
                        printf("Sorry, the balance can not be less than total fees\n");
                        return;}

                    if(newbalance < 0){
                        printf("Sorry, no negative balance allowed\n");
                        return;}

                    curr->listOfCompanion = (struct listOfComp *) (struct Companion *) realloc(curr->listOfCompanion,(curr->numOfCompanion +numOfCom) * sizeof(struct listOfComp));

                    for(int i = 0, j = curr->numOfCompanion; i < numOfCom; i++, j++){
                        struct listOfComp temp;
                        fflush(stdin);
                        printf("Enter companion %d ID \n", (i+1));
                        scanf("%d", &temp.id);
                        fflush(stdin);
                        printf("Enter companion %d name \n", (i+1));
                        scanf("%s", temp.name);
                        fflush(stdin);
                        printf("Enter companion %d age \n", (i+1));
                        scanf("%d", &temp.age);
                        fflush(stdin);
                        printf("Enter companion %d gender \n", (i+1));
                        scanf("%c", &temp.gender);
                        fflush(stdin);
                        printf("Enter companion %d address \n", (i+1));
                        scanf("%s", temp.Address);

                        *(curr->listOfCompanion + j) = temp;
                    }
                    curr->balance = newbalance;
                    curr->numOfCompanion += numOfCom;

                    break;

                default:
                    printf("Error, a wrong number!");
                    break;
            }
        }
    }
}
void printList(){

    for(int i = 0; ActList[i].ActivityZone[0]!='\0'; i++){

        printf("%s Attendee Information\n", ActList[i].ActivityZone);

        printf("*Records of registered  attendees*\n");
        cur = registered;
        printf("| %-20s | %-20s | %-20s | %-20s | %-20s | %-20s | %-20s | %-20s |%-20s | %-20s |\n" , "id","name","age","Address","date","state" ,"activityZone" , "num of companion" , "ActivityFee" , "balance");


        while (cur != NULL){
            if(strstr(ActList[i].ActivityZone, cur->ActivityZone)!=NULL){
                printf( "| %-20d | %-20s | %-20d |%-20s | %-20c |%-20s | %-20s | %-20d | %-20.2f | %-30.2f |\n" ,cur->id, cur->name,cur->age , cur->date,cur->state , cur->Address ,cur->ActivityZone , cur->numOfCompanion ,cur->activityFee , cur->balance);
            }
            cur = (struct Attendee *) cur->next;
        }


        printf("Single Visit Attendee\n");
        cur = singleVisit;
        printf("| %-20s | %-20s | %-20s |  %-20s | %-20s | %-20s | %-20s | %-20s | %-20s |\n" , "id","name","age",	"Address","date","state" ,"activityZone" , "ActivityFee" , "balance");


        while (cur != NULL){
            if(strstr(ActList[i].ActivityZone, cur->ActivityZone)!=NULL){
                printf( "| %-20d | %-20s | %-20d |  %-20s | %-20c | %-20s | %-20s | %-20f | %-20.2f |\n" ,cur->id, cur->name,cur->age , cur->date,cur->state , cur->Address ,cur->ActivityZone , cur->activityFee , cur->balance);
            }
            cur = (struct Attendee *) cur->next;
        }


        printf("Companion Attendee\n");
        cur = registered;
        printf("| %-10s | %-20s |  %-20s |  %-20s | %-20s |\n" , "id","name","age","Address","gender");


        while (cur != NULL){
            if(strstr(ActList[i].ActivityZone, cur->ActivityZone)!=NULL){

                for(int j = 0; j < cur->numOfCompanion; j++){

                    printf("| %-20d | %-20s |  %-20d |  %-20c | %-20s |\n", (cur->listOfCompanion+j)->id,(cur->listOfCompanion+j)->name,(cur->listOfCompanion+j)->age,(cur->listOfCompanion+j)->gender,(cur->listOfCompanion+j)->Address);
                }
            }
            cur= (struct Attendee *) cur->next;
        }
    }
}
void editactivity(){
    fflush(stdin);
    printf("Enter name of Activity : ");
    char activeName[25] ;
    scanf("%s" , activeName);

    for( int i = 0 ; ActList[i].ActivityZone[0]=='\0' ; i++) {
        if (strcmp(ActList[i].ActivityZone, activeName)==0) {
        printf("name of activity is already found ");
        return;
        }
    }
        int j;
        for ( j = 0; ActList[j].ActivityZone[0]=='\0' ; ++j);



        strcpy( ActList[j].ActivityZone ,   activeName) ;
        fflush(stdin);
        printf("Enter price : ");
        scanf("%f" , ActList[j].price);
        fflush(stdin);
        printf("date : ");
        scanf( "%s" , ActList[j].date);
        fflush(stdin);
        printf("Enter freeAge : ");
        scanf("%s" , ActList[j].FageRange);
        fflush(stdin);
        printf("Enter restrictAge : ");
        scanf("%s" , ActList[j].ageRestrec);


        FILE* file;
        file = fopen("Activities.txt", "a");
        if( file == NULL){
            printf("file Activities.txt is not found\n");
            return  ;
        }
        fprintf(file , "%35s %.2f %s %s %s\n", ActList[j].ActivityZone,ActList[j].price,ActList[j].date ,ActList[j].FageRange ,ActList[j].ageRestrec);
        fclose(file);

        printf("the activity has been updated successfully\n");
    }
// end method
struct listAttned{
    int id;
    char name [20];
    int age;
    char date [20];
    char Address[6];
    char ActivityZone[35];
    float activityFee;

}*listAttn;

void showattendee(){

    int ch ;
    do{
        printf("Enter the corresponding no \n");
        printf("1.Records of attendees in alphabetical order\n");
        printf("2.Records of single visit attendees\n");
        printf("3.Records of registered attendees\n");
        printf("4.Records in particular date\n");
        printf("5.Return to main menu\n");
        scanf("%d", &ch);
        int tmpAddnNo=0;
        switch( ch ) {
            case 1 :
                cur=head;
                while (cur!=NULL) {
                    tmpAddnNo=tmpAddnNo+cur->numOfCompanion+1;
                }



                struct listAttned temp ;
                int j ;
                for(int i = 0 ;i < tmpAddnNo ; i++)
                {
                    for( j = 0 ; j < tmpAddnNo ; j++)
                        if(strcmp( listAttn[i].name , listAttn[j].name) > 0)
                        {
                            temp = listAttn[i];
                            listAttn[i]= listAttn[j];
                            listAttn[j] = temp;
                        }
                }


                listAttende();
                printf("| %-20s | %-20s | %-20s | %-20s | %-20s | %-20s | %-20s |\n" , "id","name","age","date","Address","activityZone" , "activity fee" );
                for(int i = 0 ;i <tmpAddnNo ; i++)
                    printf( "%-20d %-20s %-20d %-20s  %-20s %-20s  %-20.2f  \n" ,(listAttn+i)->id, (listAttn+i)->name,(listAttn+i)->age , (listAttn+i)->date, (listAttn+i)->Address ,(listAttn+i)->ActivityZone  , (listAttn+i)->activityFee);
                break;

            case 2 :
                printf("*Records of single visit attendees*\n");
                printf("| %-20s | %-20s |  %-20s |  %-20s | %-20s | %-20s | %-20s | %-20s | %-20s |\n" , "id","name","age",	"Address","date","state" ,"activityZone" , "ActivityFee" , "balance");

                cur = singleVisit ;
                while( cur != NULL)
                {
                    printf( "| %-20d | %-20s | %-20d | %-20s | %-20c | %-20s | %-20s | %-20.2f | %-20.2f |\n" ,cur->id, cur->name,cur->age , cur->date,cur->state , cur->Address ,cur->ActivityZone , cur->activityFee , cur->balance);
                    cur = (struct Attendee *) cur->next;
                }

                break;

            case 3 :
                printf("*Records of registered  attendees*\n");
                printf("| %-20s | %-20s |  %-20s |  %-20s | %-20s | %-20s | %-20s | %-20s | %-20s | %-20s |\n" , "id","name","age","Address","date","state" ,"activityZone" , "num of companion" , "ActivityFee" , "balance");

                cur = registered ;
                while( cur != NULL)
                {
                    printf( "| %-20d | %-20s | %-20d | %-20s | %-20c | %-20s | %-20s | %-20d | %-20.2f | %-20.2f |\n" ,cur->id, cur->name,cur->age , cur->date,cur->state , cur->Address ,cur->ActivityZone , cur->numOfCompanion ,cur->activityFee , cur->balance);
                    cur = (struct Attendee *) cur->next;
                }
                break;

            case 4 :
                fflush(stdin);
                printf("Enter date : ");
                char date[20];
                scanf("%s" , date);

                printf("| %-20s | %-20s |  %-20s |  %-20s | %-20s | %-20s | %-20s | %-20s | %-20s | %-20s |\n", "id","name","age",	"Address","date","state" ,"activityZone" , "num of companion" , "ActivityFee" , "balance");


                cur = head ;
                while( cur != NULL)
                {
                    if ( !strcmp (cur->date ,date)){
                        if (cur->date[0] !='\0')
                            printf("| %-20d | %-20s | %-20d | %-20s | %-20c | %-20s | %-20s | %-20d | %-20.2f | %-20.2f |\n" ,cur->id, cur->name,cur->age , cur->date,cur->state , cur->Address ,cur->ActivityZone , cur->numOfCompanion ,cur->activityFee , cur->balance);
                    }
                    cur = (struct Attendee *) cur->next;
                }

                break;

            case 5 :
                printf("back to main menu\n ");
                break;
            default :
                printf("Error input \n");
        }// end switch

    }while( ch != 5 ) ;
}



void listAttende(){
    cur=head;
    int i=0;
    while (cur!=NULL){
        (listAttn+i)->id=cur->id;
        strcpy((listAttn+i)->name,cur->name);
        (listAttn+i)->age=cur->age;
        strcpy((listAttn+i)->date,cur->date);
        strcpy((listAttn+i)->Address,cur->Address);
        strcpy((listAttn+i)->ActivityZone,cur->ActivityZone);
        listAttn->activityFee=cur->activityFee;
        int j = 0;
        for (j = 0; j < cur->numOfCompanion; ++j) {
            (listAttn+j)->id=cur->id;
            strcpy((listAttn+j)->name,cur->name);
            (listAttn+j)->age=cur->age;
            strcpy((listAttn+j)->date,cur->date);
            strcpy((listAttn+j)->Address,cur->Address);
            strcpy((listAttn+j)->ActivityZone,cur->ActivityZone);
            listAttn->activityFee=cur->activityFee;
        }
        i=i+j;

    }



}