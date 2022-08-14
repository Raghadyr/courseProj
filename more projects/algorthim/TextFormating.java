public class TextFormating{
int MAX = Integer.MAX_VALUE;


//To print the solution
int print(int arr[], int n){
int s;
 
 if(arr[n] == 1)
   s=1;
    else 
     s=print(arr, arr[n]-1) + 1;
   
    System.out.println("Line number" + " " + s + " : " + "From word number" +" "+ arr[n] + " " + "to" + " " + n);
    return s;}
    
    
// l[] represents lengths of different words in input sequence. For example, int l[] = {6,3,6,3,8,2,10}; represents the given sentence "CSC311 The Design and Analysis of Algorithms". 
// n is size of l[], and M is line width: (maximum no. of characters that can fit in a line)
void solution(int l[], int n, int M){
int extra[][] =   new int[n+1][n+1]; // extra[i][j] will have number of extra spaces if words from i to j are put in a single line
int cost[][]  =   new int[n+1][n+1]; // cost[i][j] will have cost of a line which has words from i to j
int total[]   =   new int[n+1];      // total[i] will have total cost of optimal arrangement of words from 1 to i
int p[]       =   new int[n+1];      // p[] is used to print the solution.

//calculate extra spaces in a single line.
for (int i = 1; i <= n; i++){
extra[i][i]= M - l[i-1];
  for (int j = i+1; j <= n; j++)
  extra[i][j] = extra[i][j-1] - l[j-1] - 1;}
    for (int i = 1; i <= n; i++){
      for (int j = i; j <= n; j++){
       if (extra[i][j] < 0)
         cost[i][j] = MAX;
          else if (j == n && extra[i][j] >= 0)
             cost[i][j] = 0;
          else
          cost[i][j] = extra[i][j]*extra[i][j];}
          }
          
// Calculate minimum cost and find minimum cost arrangement.
total[0] = 0;
for (int j = 1; j <= n; j++){
 total[j] = MAX;
  for (int i = 1; i <= j; i++){
   if (total[i-1] != MAX && cost[i][j] != MAX && (total[i-1] + cost[i][j] < total[j])){
    total[j] = total[i-1] + cost[i][j];
    p[j] = i;}}}
    
    print(p, n);}


public static void main(String args[]){
TextFormating t = new TextFormating();
int l[] = {6, 3, 6,3,8,2,10};
int n = l.length;
int M = 13;
t.solution (l, n, M);}}