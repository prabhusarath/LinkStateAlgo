package cs542project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import static java.lang.System.exit;
import java.util.Scanner;
import java.util.*;
/**
 *
 * @author SarathKumar
 */
public class CS542Project {
    
    static int N = 50;
    static int Routers; // Numnber of Routers in the Network Topolgy Matrix
    static int source,destination; // Source and Destination values for the Network.
    static int source1,destination1; // Source and Destination Values for the Modified Network.
    static int p, q, r,index,value,value1,mindex,mindex1,pathindex=3;  // Looping Variabales.  
    static Boolean Flag1,Flag2,Flag3; // Flag Variables to Select Between Orginal Network.
    static final int d0=0,d1=1,d2=2; // Loop through the Distance Array.
    static int[] del = new int[N];
    
    static int[][] NW = new int[N][N];  // 2-D Array to store Orginal Cost Values. 
    static int[][] rm = new int[N][N];  // Backup of NW 2-D Array.
  
    /*  Function to Get the Values from the file and Store it in a 2-D Array 
    *   Arg1 - File Location
    *   Arg2 - Number of Routers in the Network.
    */
    public static void Route(String inputFile,int Routers)   
    {
                System.out.println(" Network topology Matrix :");    
                try {
                    FileReader fileReader = new FileReader(inputFile);
                    BufferedReader bufferedReader =  new BufferedReader(fileReader); 
                    try (Scanner scn = new Scanner(bufferedReader)) 
                    {for (int Router1 = 0; Router1 < Routers; Router1++)
                        {   String c = scn.nextLine(); // Values are read from file
                            String[] numbers = c.split(" ");
                            for (int Router2 = 0; Router2 < Routers; Router2++)
                            {  int temp = Integer.parseInt(numbers[Router2]);
                            if(temp == -1)
                            {   temp = 99; NW[Router1][Router2]= temp;}
                            else{NW[Router1][Router2] = Integer.parseInt(numbers[Router2]);} // values are stored in 2-D Array.
                            rm[Router1][Router2]=NW[Router1][Router2]; // Backup of 2d Array are Kept.
                            System.out.print(NW[Router1][Router2]); // 2-D Array is Printed.
                            System.out.print("\t");}
                            System.out.println("");}}}
            catch(IOException ex) { ex.printStackTrace();}} 
    
    static int[][] DISTANCE = new int[pathindex][N]; // 2-D Arrays For Storing the Index of Visited Nodes ,Storing their Cost and path for the Orginal Network.
    
    /*  Function to calculate the Shortest Path - Dijikstras Implementation.
    *   Arg1 - Router Number to which the Shortest Path has to be Calculated.
    */
        static void Dijkstras(int Node) {
        if( Flag1 == true){  // Flag to Check Whether Orginal Network.
        for(index=0;index < Routers;index++)
        {DISTANCE[d0][index] = 99; // Intializing the Minimum Path Array for Orginal Network.
         DISTANCE[d2][index] = 0; // Intializing the Visited Array for Orginal Network.
         DISTANCE[d1][index] = NW[Node][index]; // Intializing the Minimum Distance Array with Network 2D Array
        }    
        int count = 0; 
        while(count < Routers)
        {
            int intial=99;
            for(index = 0;index < Routers;index++)
            {
            if (DISTANCE[d2][index]==0)    
            {if (DISTANCE[d1][index] < intial) {intial = DISTANCE[d1][index];value = index;}
            }}DISTANCE[d2][value] = 1;count++;   
            // Logic to Find the Minimum Path in the Network and Storing the index values in Both the Arrays.
            for(value1=0;value1 < Routers;value1++)
            {// Check Whether the node is visited and its Minimal Path
            if (DISTANCE[d1][value] + NW[value][value1] < DISTANCE[d1][value1] ) 
            { if (DISTANCE[d2][value1]==0) {DISTANCE[d1][value1] = DISTANCE[d1][value] + NW[value][value1]; 
            DISTANCE[d0][value1] = value;}}}}}}
    
    /*  Function to Find the Cost from the Souce and Destination Routers
    *   Based  on the Shortest Distance Calculted By Above Dijikstras Implementation. 
    *   Arg1 - Source Router. 
    *   Arg2 - Destination Router. 
    */
    static void cost(int source, int target) {
    
    if ( Flag3 == true ) // Flag to Check whether Orginal Network.
    {  
        int[] print = new int[N];
        int chk = 0;
        int value = target;
        int x=1;
	System.out.println("\nThe shortest path from router "+(source+1)+" to router "+(target+1)+"  is ");
	print[0] = target;while (chk != 1)  
	{ for (int p = 0; p < Routers; p++)
	{ if (p == value){ print[x] = DISTANCE[d0][p]; //Path is Stored in Another Array to Print them.
          value = DISTANCE[d0][p];x++;
          if (value == 99)chk = 1;break;}}}
        int p = x-1;
        while(p >= 0)
	{if (print[p] == 99){ print[p] = source;}
	System.out.print(print[p]+1); // Path From Source to Destination.
        if (p != 0)
        {System.out.print("->");}p--;}
	System.out.println("");
        System.out.println("The cost destination is  "+ DISTANCE[d1][target]); // Total Cost of Travel.
    }
    } 

    /** Main Function 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Integer UserChoice;
        do  // Do While Loop to Get the User Input Based on It Different Computations are Done.
        {  
            System.out.println("CS542 Link State Routing Simulator");
            System.out.println("USER MENU");
            System.out.println("(1) Create a Network Topology");  // Loads the Network Topolgy From File
            System.out.println("(2) Build a Connection Table");   // Builds Connection Table For a Router.
            System.out.println("(3) Shortest Path to Destination Router"); // Finds the Shortest Path From Source to Deestination.
            System.out.println("(4) Print All Connection Tables for the Network"); // Prints All Connection Table. 
            System.out.println("(5) Modify a topology"); // Modify's the Topolgy ( Router Down.)
            System.out.println("(6) Exit"); // Exits the Program.// Exits the Program.
            Scanner Command = new Scanner(System.in);
            System.out.println("Command:");
            UserChoice = Command.nextInt();
            switch(UserChoice)
            {   
                    //  (1) Create a Network Topology
                case 1:
                    Scanner Command1 = new Scanner(System.in);
                    System.out.println(" Input original network topology matix Data file Location :");
                    String inputFile = Command1.nextLine();
                    System.out.println(" Number of Routers in the Network topology ");
                    Routers = Command1.nextInt();
                    Route(inputFile,Routers); 
                break;   
                    //  (2) Build a Connection Table
                    case 2:
                        Scanner Command2 = new Scanner(System.in);
                        System.out.println(" Select Router ");
                        int Source = Command2.nextInt();
                        if(Source<=Routers)
                        {System.out.println(" Selected Router is  "+ Source );  
                        Flag1 = true;Flag2 = true;   
                        Dijkstras(Source - 1);    
                        System.out.println("Router"+ (Source) + "Connection Table");
                        System.out.print("Destination");
                        System.out.print("\t");
                        System.out.println("Interface");    
                        int index=0;
                        while(index < Routers)
                        {if (index == Source - 1){
                        System.out.print(index+1);
                        System.out.print("\t\t\t");
                        System.out.println((char)45);}
                        else { if (DISTANCE[d0][index] != 99){
                        System.out.print(index+1);
                        System.out.print("\t\t\t");
                        System.out.println(DISTANCE[d0][index]+1);}
                        else {System.out.print(index+1);
                        System.out.print("\t\t\t");
                        System.out.println(index+1);}}
                        index++;}}
                        else{
                        System.out.println("This Router number does not exist. Please try again! ");}
                        break;
                    // (3) Shortest Path to Destination Router
                        case 3:
                                Scanner Command3 = new Scanner(System.in);
                                System.out.println("\nEnter the Source Router : ");
                                source = Command3.nextInt();
				System.out.println("Selected Source Router is  "+source);
				System.out.println("\nEnter the Destination Router : ");
                                destination = Command3.nextInt();
                                System.out.println("Selected Source Router is  " +destination);
                                Flag1 = true;
				Dijkstras(source-1);
                                Flag3 = true;
				cost(source-1, destination-1);
				break;
                    // (4) Print All Router Tables for the Network 
                            case 4:
                            Scanner Command4 = new Scanner(System.in);
                            System.out.println(" All Routers Selected - Buliding Connection Table ");
                            for(int i=0;i<Routers;i++)
                            {
                            Flag1 = true;
                            Flag2 = true;
                            Dijkstras(i);  
                            System.out.println("Router"+ (i+1) + "Connection Table");
                            System.out.print("Destination");
                            System.out.print("\t");
                            System.out.println("Interface");
                            index=0;
                            while(index < Routers)
                            {if (index == i ){
                            System.out.print(index+1);
                            System.out.print("\t\t\t");
                            System.out.println((char)45);}
                            else {if (DISTANCE[d0][index] != 99){
                            System.out.print(index+1);
                            System.out.print("\t\t\t");
                            System.out.println(DISTANCE[d0][index]+1);   
                            }else{
                            System.out.print(index+1);
                            System.out.print("\t\t\t");
                            System.out.println(index+1);}}
                            index++;}}
                            break;    
                // (5) Modify the Network (Remove a Router).
                             case 5:
                                 Scanner Command5 = new Scanner(System.in);
                                 System.out.println(" Select Router to be Removed ");
                                 int rmrouter = Command5.nextInt();
                                 System.out.println("The Orginal Topology Matrix");
                                 int R1=0;
                                 while(R1<Routers){int R2=0;
                                 while(R2<Routers){
                                 System.out.print(rm[R1][R2]);System.out.print("\t");R2++;}
                                 System.out.println("");R1++;}
                                del[rmrouter-1] = 1;
                                int i=0;while(i<Routers)
                                {NW[i][rmrouter-1]= 99;
                                NW[rmrouter-1][i] = 99;i++;}
                                System.out.println(" Topology After Removal from the Matrix");                     
                                for (i = 0; i < Routers; i++)
                                {for (int j = 0; j < Routers ; j++)
                                {System.out.print(NW[i][j]); 
                                 System.out.print("\t");}
                                 System.out.println("");}
                                break;            
                   // (6) Exit from the program.
                                case 6:
                                System.out.println(" Exit CS542 project. Good Bye! ");
                                exit(0);
                                break; 
                   // Default Option.            
                            default:
                            System.out.println(" Please Make Proper Selection ");}}
        while(UserChoice<=6);}}
