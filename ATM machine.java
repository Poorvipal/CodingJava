import java.util.*;
class ATM{
    private double balance;
    private double deposit;
    private double withdraw;

    public ATM() {
    }

        public double getbalance(){
            return balance;
        }
        public void setbalance(double balance){
            this.balance=balance;
        }
        public double getDeposit(){
            return deposit;
        }
        public void setDeposit(double deposit){
            this.deposit=deposit;
        }
        public double getWithdraw(){
            return withdraw;
        }
        public void setWithdraw(double withdraw){
            this.withdraw=withdraw;
        }

}
 interface Atmoperinter{
    public void viewBalance();
    public void Withdraw(double withdraw);
    public void Deposit(double deposit);
}
 class Atmoper implements Atmoperinter{
    ATM a=new ATM();
    public void viewBalance(){
        System.out.println("Your balance: "+a.getbalance());
    }@Override
    public void Withdraw(double withdraw) {
        if (withdraw % 1 == 0) {
            if (withdraw <= a.getbalance()) {
                System.out.println("Collect the cash" + withdraw);
                a.setbalance(a.getbalance() - withdraw);
                viewBalance();
            } else {
                System.out.println("Low Balance");
            }
        }else{
                System.out.println("Enter proper amount");
            }

        }

        @Override
          public void Deposit(double deposit){
        System.out.println(deposit+"Amount deposited");
        a.setbalance(a.getbalance()+deposit);
        viewBalance();
    }
}
public class Main{
    public static void main(String[] args){
        Atmoperinter b=new Atmoper();
        int number=123456;
        int pin=9112;
        Scanner in=new Scanner(System.in);
        System.out.println("Welcome");
        System.out.println("Enter your atm number");
        long Number=in.nextLong();
        System.out.println("Enter pin");
        long Pin=in.nextLong();;
        if((number==Number)&&(pin==Pin)){
            while(true){
                System.out.println("1. View balance\n 2. Withdraw amount\n 3. Deposit amount\n 4. Exit");
                System.out.println("Enter choice");
                int choice=in.nextInt();
                if(choice==1){
                    b.viewBalance();
                }
                else if(choice==2){
                    System.out.println("Enter amount to withdraw");
                    double withdraw=in.nextDouble();
                    b.Withdraw(withdraw);
                }
                else if(choice==3){
                    System.out.println("Enter amount to deposit");
                    double deposit=in.nextDouble();
                    b.Deposit(deposit);
                }
                else if(choice==4){
                    System.out.println("Thank you\n Have a nice day!");
                    System.exit(0);
                }
                else{
                    System.out.println("Enter correct choice");;
                }
            }
        }
        else{
            System.out.println("Incorrect ATM number or pin");
            System.exit(0);
        }

    }
}