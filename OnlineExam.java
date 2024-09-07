import java.util.*;
public class OnlineExam{
    private String user_name;
    private String password;
    private boolean loggedin;
    private int timeleft;
    private int count;
    private int[] answers;
    private int[] correct_answers;
    public OnlineExam(String user_name,String password){
        this.user_name=user_name;
        this.password=password;
        System.out.println("Successfully registered!!");
        this.loggedin=false;
        this.timeleft=20;
        this.count=15;
        this.answers=new int[count];
        this.correct_answers=new int[count];
        for(int i=0;i<count;i++){
            correct_answers[i]=(int)Math.round(Math.random());
        }
    }
    public void login(){
        System.out.println("Login to continue");
        Scanner in=new Scanner(System.in);
        System.out.println("Username:");
        String input=in.next();
        System.out.println("Password:");
        String in_password=in.next();
        if(input.equals(user_name) && in_password.equals(password)){
            loggedin=true;
            System.out.println("Login successful!! All the best");
        }
        else{
            System.out.println("Login failed. Try again");
        }
    }
    public void logout(){
        loggedin=false;
        System.out.println("Logout successful");
    }
    public void StartExam(){
        if(!loggedin){
            System.out.println("Kindly login first");
            return;
        }
         Scanner in=new Scanner(System.in);
        System.out.println("You have"+timeleft+"minutes");
        for(int i=0;i<count;i++){
            System.out.println("Question"+(i+1)+":");
            System.out.println("1. Option 1");
            System.out.println("2. Option 2");
            System.out.println("Enter your choice(1 or 2):");
            int answer=in.nextInt();
            answers[i]=answer;
        }
        System.out.println("Would you like to submit?\n 1:YES \n 2:NO");
        int n=in.nextInt();
        if(n==1){
            submit();
        }
        else{
            try{
                Thread.sleep(timeleft*10*1000);
            }
            catch(InterruptedException e){
                e.printStackTrace();
                submit();
            }
        }

    }
    public void submit(){
        if(!loggedin){
            System.out.println("Kindly login first");
            return;
        }
        int score=0;
        for(int i=0;i<count;i++){
            if(answers[i]==correct_answers[i]){
                score++;
            }
        }
        System.out.println("Your score is"+score+"out of"+count+".");
        System.out.println("Good luck");
        logout();
    }
    public static void main(String[] args){
        Scanner in=new Scanner(System.in);
        System.out.println("Enter username:");
        String U=in.next();
        System.out.println("Enter password:");
        String P=in.next();
        OnlineExam e=new OnlineExam(U,P);
        e.login();
        e.StartExam();
    }
}
