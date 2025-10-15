//ChangeM.java
//Author:Prasanna Kunwar
//Date: 10/10/2025
//Purpose:defines a person and their coin change details.

public class ChangeM{
    //Private instance variables
    private String name;
    private int amount;
    private int[] coins=new int[5]; //used for denomination: $1, 50c, 25c, 10c, 5c
    private final int[] DENOMS={100,50,25,10,5}; //Coin denominations in cents and constant so it doesnt change.

    //Constructor
    public ChangeM(){
        name = "";
        amount = 0;
    }

    public ChangeM(String name, int amount){
        this.name=name;
        setamount(amount);
        calculatecoins();
    }

    //Accessors (getters (returns the values))
    public String getname(){
        return name;
    }

    public int getamount(){
        return amount;
        
    }

    public int[] getcoins(){
        return coins;
    }

    //Mutators (setters(sets the values))
    public void setname(String name){
        this.name = name;
    }

    public void setamount(int amount){
        //Round to nearest multiple of 5 before setting
        int remainder=amount%5;
        if (remainder>=3)
            amount+=(5-remainder);
        else
            amount-=remainder;
        this.amount=amount;
    }

    //Helper: Calculate coin breakdown
    public void calculatecoins(){
        int remaining=amount;
        for (int i=0;i<DENOMS.length;i++){
            coins[i]=remaining/DENOMS[i];
            remaining%=DENOMS[i];
        }
    }

    //Helper: Display change breakdown (called by client)
    public void displaychange(){
        System.out.println("Customer: "+name+" "+amount+" cents");
        System.out.println("Change:");
        for (int i=0;i<DENOMS.length; i++){
            if (coins[i]>0){
                if (DENOMS[i]==100)
                    System.out.println("$1 : " + coins[i]);
                else
                    System.out.println(DENOMS[i]+" cent: "+coins[i]);
            }
        }
        System.out.println();
    }
}
