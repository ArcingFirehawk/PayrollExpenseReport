/**
 * @(#)PayrollExpenseReport.java
 * @author Anthony Choi
 * @version 2.00 2024/04/15 03:31 PM
 *
 * PROGRAM PURPOSE: Create a program that produces a payroll expense report.
 *
 * CHANGELOG
 * v1: Academic assignment that fulfilled the relevant grading criteria.
 * v2: Edits to minimize personally identifiable information and fix formatting.
 */

import java.util.Scanner;  //Input Library
import java.util.Calendar;  //Date and Time Library

public class PayrollExpenseReport
{
    public static void main(String[] args)
    {
        //Initialized Variables
        Scanner input = new Scanner(System.in);    //Scanner
        Calendar dateTime = Calendar.getInstance();    //Date and Time
        double grossPay = 0.0,    //Pay for a single employee.
                grossPayTotal = 0.0,    //Total pay for all employees.
                retire401K = 0.0,    //401K contribution for a single employee.
                total401K = 0.0;    //Total 401K contribution for all employees.
        String payrollExpense = String.format("WEEKLY HOURLY EMPLOYEE PAYROLL"    //Collected final output.
                + "%n%nDate: %tD"
                + "%nTime: %tr"
                + "%n%n%56S %23S", dateTime, dateTime, "GROSS PAY", "401K");
        int trigger = 1;

        //Input Variables
        String first = "",    //Employee's first name.
                last = "";    //Employee's last name.
        double payRate = 0.0,    //Employee's hourly pay rate.
                percent401K = 0.0;    //Employee's 401K contribution as a percent.
        int hoursWorked = 0;    //Employee's number of hours worked.
        char cont = 'y';    //Continuation variable.

        System.out.printf("%nWEEKLY HOURLY PAYROLL SYSTEM"
                + "%n%nContinue?  'Y' or 'N':  ");
        cont = input.nextLine().toUpperCase().charAt(0);

        //if statement that shuts off the program when the answer to 'cont' is 'N'.
        if(cont == 'N')
        {
            System.out.printf("%nExiting Weekly Hourly Payroll System.%n");
        }//END if cont is N.

        //while loop that starts when the answer to 'cont' is 'Y' and continues the rest of the program.
        while(cont == 'Y')
        {
            System.out.printf("%nEnter the employee's first name press enter "
                    + "then the last name press enter:  ");
            first = input.nextLine();
            last = input.nextLine();


            System.out.printf("%nEnter the number of hours worked for " + first + ":  ");
            hoursWorked = input.nextInt();

            //while loop that forces a replacement input if 'hoursWorked' is greater than 40 or less than 5.
            while(hoursWorked > 40 || hoursWorked < 5)
            {
                //if statement that forces a replacement input when 'hoursWorked' is greater than 40.
                if(hoursWorked > 40)
                {
                    System.out.printf("%nHours worked cannot EXCEED 40. Please re-enter:  ");
                }//END if hoursWorked is greater than 40.

                //if statement that forces a replacement input when 'hoursWorked' is less than 5.
                if(hoursWorked < 5)
                {
                    System.out.printf("%nHours worked cannot be LESS than 5.  "
                            + "Please re-enter:  ");
                }//END if hoursWorked is less than 5.

                hoursWorked = input.nextInt();  //Read
            }//END while hoursWorked is greater than 40 OR less than 5.


            System.out.printf("%nEnter the employee's hourly pay rate:  ");
            payRate = input.nextDouble();

            //while loop that forces a replacement input if 'payRate' is greater than 26.00 or less than 7.25.
            while(payRate < 7.25 || payRate > 26.00)
            {
                //if statement that forces a replacement input when 'payRate' is less than 7.25.
                if(payRate < 7.25)
                {
                    System.out.printf("%nHourly pay rate cannot be LESS than $7.25.  "
                            + "Please re-enter:  ");
                }//END if payRate is less than 7.25.

                //if statement that forces a replacement input when 'payRate' is greater than 26.00.
                if(payRate > 26.00)
                {
                    System.out.printf("%nHourly pay rate cannot EXCEED $26.00.  "
                            + "Please re-enter:  ");
                }//END if payRate is greater than 26.00.

                payRate = input.nextDouble();
            }//END while payRate is less than 7.25 OR greater than 26.00.


            grossPay = hoursWorked * payRate;
            grossPayTotal += grossPay;


            System.out.printf("%nEnter the employee�s 401K contribution as "
                    + "a percentage of salary (not to exceed 10%%):  ");
            percent401K = input.nextDouble();

            //while loop that forces a replacement input if 'percent401K' is greater than 10.
            while(percent401K > 10)
            {
                System.out.printf("%nContributions cannot EXCEED 10%%.  "
                        + "Please re-enter:  ");

                percent401K = input.nextDouble();  //Read
            }//END while percent401K is greater than 10.


            retire401K = (percent401K / 100) * grossPay;
            total401K = total401K + retire401K;


            //if-else statement that determines to put the dollar in when 'trigger' is 1.
            if(trigger == 1)
            {
                payrollExpense += String.format("%n%-30s %8s $%,15.2f %8s $%, 13.2f",
                        first + " " + last, " ", grossPay, " ", retire401K);
                trigger = 0;
            }
            else
            {
                payrollExpense += String.format("%n%-30s %9s %,15.2f %9s %, 13.2f",
                        first + " " + last, " ", grossPay,
                        " ", retire401K);
            }//END if trigger is 1 else trigger is not 1.


            input.nextLine();  //Clears Buffer


            System.out.printf("%nEnter 'Y' to add another employee or 'N' to exit:  ");
            cont = input.nextLine().toUpperCase().charAt(0);

            //if statement that stops further inputs if 'cont' is 'N'.
            if(cont == 'N')
            {
                payrollExpense += String.format("%n%n%25s TOTALS %6s $%,15.2f %8s $%,13.2f",
                        " ", " ", grossPayTotal, " ", total401K);

                System.out.printf("%n%n%s", payrollExpense);
            }//END if cont is N.
        }//END while continue payroll system is Y.
        //END New Employee Prompt

        System.exit(0);
    }//END main()
}//END APPLICATION CLASS PayrollExpenseReport