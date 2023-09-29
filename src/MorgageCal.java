import java.text.NumberFormat;
import java.util.*;

public class MorgageCal {

    public static void main(String[] args) {
        int prcplAmnt=(int)readNumber("Principal: ",1_000,1_000_000);
        float annualInterest=(float)readNumber("Annual Interest: ", 1,30);
        byte yrs=(byte)readNumber("Period (Years): ",1,30);

        printMorgage(prcplAmnt, annualInterest, yrs);
        printPaymentSchedule(prcplAmnt, annualInterest, yrs);
    }

    private static void printMorgage(int prcplAmnt, float annualInterest, byte yrs) {
        String morgageFormated = NumberFormat.getCurrencyInstance().format(calculateMorgage(prcplAmnt, annualInterest, yrs));
        System.out.println("MORGAGE");
        System.out.println("-------");
        System.out.println("Monthly Payment: " + morgageFormated );
    }

    private static void printPaymentSchedule(int prcplAmnt, float annualInterest, byte yrs) {
        System.out.println("Payment Schedule");
        System.out.println("----------------");
        for(short month = 1; month<=(yrs * 12); month++){
            double balance = calculateBalance(prcplAmnt, annualInterest, yrs,month);
            System.out.println(NumberFormat.getCurrencyInstance().format(balance));
        }
    }

    public static double calculateMorgage(int principal,double annualInterest, byte yrs){
       double monthlyRate = (annualInterest/12)/100;
       int numbPayments = yrs * 12;
        double numerator, denominator,morgage;
        numerator = monthlyRate * Math.pow((1+monthlyRate),numbPayments);
        denominator = Math.pow((1+monthlyRate),numbPayments) - 1;
        morgage = (principal) * (numerator/denominator);
        //String morgageFormated = NumberFormat.getCurrencyInstance().format(morgage);

        return morgage;
    }

    public static double calculateBalance(int principal,float annualInterest,byte years, short numberPaymentsMade){
        float monthlyRate = (annualInterest/12)/100;
        int numbPayments = years * 12;

        double balance = principal
                * (Math.pow(1+monthlyRate,numbPayments) - Math.pow(1+monthlyRate,numberPaymentsMade))
                / (Math.pow(1+monthlyRate,numbPayments) - 1);

        return balance;
    }

    public static double readNumber(String prompt,double min,double max){
        Scanner scanner = new Scanner(System.in);
        double value;
        while(true) {
            System.out.print(prompt);
            value = scanner.nextFloat();
            if (value >= min && value <= max)
                break;
            else
                System.out.println("Enter a value between " + min + " and " + max);
        }
        return value;
    }
}
