//ChangeMClient.java
//Author: Prasanna Kunwar
//Date: 10/10/2025
//Purpose: Client class to manage ChangeM objects.

import java.util.Scanner;

public class ChangeMClient{

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        Studentinfo();
        System.out.println("----------------------------------------------------");
        System.out.println("Welcome to the Coin Change Program!");
        System.out.println("Please enter at least 10 records to test the program.");
        System.out.println("-----------------------------------------------------\n");

        ChangeM[] list=new ChangeM[20];
        int count=0;
        char choice='Y'; //initializing choice to Yes so that it can enter the loop.

        //Inputting the loop
        do{
            System.out.print("Enter name: ");
            String name=sc.next();

            //Checking duplicate names
            if(findindexbyname(list,count,name)!=-1){
                System.out.println("Name already exists. Try again.\n");
                continue;
            }
            System.out.print("Enter coin amount (in cents): ");
            int amount=sc.nextInt();
            list[count]=new ChangeM(name,amount);
            count++;
            // Validate Y/N input
            boolean validChoice=false;
            do{
                System.out.print("Do you have more person to enter (Y/N)? ");
                choice=sc.next().charAt(0);
                if(choice=='Y'||choice=='y'||choice=='N'||choice=='n'){
                    validChoice=true; // valid input, exit inner loop
                }else{
                    System.out.println("Invalid input. Please enter Y or N.");
                }
            }while(!validChoice);
        }while((choice=='Y'||choice=='y')&&count< list.length);

        //Remove "//" from the method call below to test hardcoded data.
        //hardcodedata(list);

        //Menu
        int menu;
        do {
            System.out.println("\n------------------------------------------------- MENU ------------------------------------------------------");
            System.out.println("1. Enter a name and display change to be given for each denomination");
            System.out.println("2. Find the name with the smallest amount and display change to be given for each denomination");
            System.out.println("3. Find the name with the largest amount and display change to be given for each denomination");
            System.out.println("4. Calculate and display the total number of coins for each denomination");
            System.out.println("5. Calculate and display the total amount (i.e. NOT the total number of  coins) for the sum of all denominations");
            System.out.println("6. Exit");
            System.out.println("---------------------------------------------------------------------------------------------------------------");
            System.out.print("Enter your choice: ");
            menu=sc.nextInt();

            switch(menu){
                case 1:
                    displaybyname(list, count, sc);
                    break;
                case 2:
                    displaysmallest(list, count);
                    break;
                case 3:
                    displaylargest(list, count);
                    break;
                case 4:
                    displaytotalcoins(list, count);
                    break;
                case 5:
                    displaytotalamount(list, count);
                    break;
                case 6:
                    System.out.println("\nThank you for using the program. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }while(menu!=6);
        sc.close();
    }

    //Helper Methods
    public static void Studentinfo() {
        System.out.println("Name: Prasanna Kunwar");
        System.out.println("Student ID: 35744248");
        System.out.println("Tutor: Rajasree Rajamohanan");
        System.out.println("Tutorial: Wednesday 10 a.m.");
    }

    //Helper: Find index of a name in the array
    public static int findindexbyname(ChangeM[] list, int count, String name) {
        for (int i=0;i<count;i++){
            if (list[i].getname().equalsIgnoreCase(name))
                return i;
        }
        return -1;
    }

    //Display change breakdown for a specific name
    public static void displaybyname(ChangeM[] list, int count, Scanner sc) {
        System.out.print("Enter name to search: ");
        String searchname=sc.next();
        int idx=findindexbyname(list,count,searchname);
        if (idx!=-1)
            list[idx].displaychange();
        else
            System.out.println("Name: "+searchname+" not found.\n");
    }

    //Display person with smallest amount
    public static void displaysmallest(ChangeM[] list, int count) {
        if(count==0)
            return;
        int minidx=0;
        for (int i=1;i<count;i++){
            if(list[i].getamount()<list[minidx].getamount())
                minidx=i;
        }
        System.out.println("\nPerson with smallest amount:");
        list[minidx].displaychange();
    }

    //Display person with largest amount
    public static void displaylargest(ChangeM[] list, int count) {
        if (count==0) 
            return;
        int maxidx=0;
        for (int i=1;i<count;i++){
            if (list[i].getamount() > list[maxidx].getamount())
                maxidx=i;
        }
        System.out.println("\nPerson with largest amount:");
        list[maxidx].displaychange();
    }

    //Display total number of coins for each denomination
    public static void displaytotalcoins(ChangeM[] list, int count) {
        int[] total=new int[5];
        for (int i=0;i<count;i++){
            int[] coins=list[i].getcoins();
            for (int j=0;j<5;j++){
                total[j]+=coins[j];}
        }

        int[] denoms = {100,50,25,10,5};
        System.out.println("\nTotal number of coins for each denomination:");
        for (int j=0;j<5;j++){
            if(denoms[j]==100)
                System.out.println("$1 : "+total[j]);
            else
                System.out.println(denoms[j]+" cent: "+total[j]);
        }
    }

    //Display total amount (sum of all persons' amounts)
    public static void displaytotalamount(ChangeM[] list, int count) {
        int totalamount=0;
        for (int i=0;i<count;i++)
            totalamount+=list[i].getamount();
        System.out.println("\nTotal amount from all persons: "+totalamount+" cents");
    }

    //Helper: Load hardcoded test data for tutor testing
    public static void hardcodedata(ChangeM[] list) {
        String[] names={"Sameer","John","Aamir","Jordan","Alex","Ben","Lily","Sara","Ron","Taha"};
        int[] amounts={30,50,94,35,100,75,80,45,60,25};
        for (int i=0;i<names.length;i++){
            list[i]=new ChangeM(names[i],amounts[i]);
        }
        System.out.println("Hardcoded test data loaded successfully!");
    }
}
