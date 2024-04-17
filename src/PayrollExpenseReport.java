/**
 * @(#)PayrollExpenseReport.java
 * @author Anthony Choi
 * @version 2.00 2024/04/17 01:49 AM
 *
 * PROGRAM PURPOSE: Create a program that generates a payroll expense report.
 *
 * CHANGELOG
 * v1: Academic assignment that fulfilled the relevant grading criteria.
 * v2: Added methods to streamline code.
 * v3: Edits to make the program more suitable for publishing.
 */

import java.util.Scanner;    //Input Library
import java.util.Calendar;    //Date and Time Library

public class PayrollExpenseReport
{
    private static Scanner input = new Scanner(System.in);    //Scanner Variable
    private static int firstNmLength = 0;    //Stores no of bytes in first name.
    private static Calendar dateTime = Calendar.getInstance();    //Stores the date and time.
    private static String payrollExpense = String.format("WEEKLY HOURLY EMPLOYEE PAYROLL"    //Collected final output.
                                                       + "%n%nDate: %tD"
                                                       + "%nTime: %tr"
                                                       + "%n%n%56S %23S", dateTime, dateTime, "GROSS PAY", "401K");

    /**
     * main method that calls generateReport to start the program and then exits it.
     */
    public static void main(String[] args)
    {
        generateReport();
        System.exit(0);  //Exits the program.
    }//END main()

    /**
     * Method that initializes the variables, calls all other methods for the program to function, prompts the
     * user for another employee and whether to continue, and prints the report.
     */
    public static void generateReport()
    {
        String employeeName = "";    //Stores the employee's full name.
        double payRate = 0.0,    //Employee's hourly pay rate.
               grossPay = 0.0,    //Pay for a single employee.
               retire401K = 0.0,    //401K contribution for a single employee.
               percent401K = 0.0,    //Employee's 401K contribution as a percent.
               grossPayTotal = 0.0,    //Total pay for all employees.
               total401K = 0.0;    //Total 401K contribution for all employees.
        int trigger = 1,    //Determines the first employee.
            hoursWorked = 0;    //Employee's number of hours worked.
        char cont = 'y';    //Continuation

        System.out.printf("%nWEEKLY HOURLY PAYROLL SYSTEM"
                        + "%n%nContinue?  \'Y\' or \'N\':  ");
        cont = input.nextLine().toUpperCase().charAt(0);

        //if statement that shuts off the program when the answer to 'cont' is 'N'.
        if(cont == 'N')
        {
            System.out.printf("%nExiting Weekly Hourly Payroll System.%n");
        }//END if cont is N.

        //while loop that starts when the answer to cont is 'Y' and continues the rest of the program.
        while(cont == 'Y')
        {
            employeeName = setEmployeeName();
            hoursWorked = setHoursWorked(employeeName.substring(0, firstNmLength));

            /*
               while loop that tests if hoursWorked is less than 40 or greater than 5. If true, a new input is
               requested through the method testHoursWorked.
             */
            while(hoursWorked > 40 || hoursWorked < 5)
            {
                hoursWorked = testHoursWorked(hoursWorked);
            }//END while hoursWorked is greater th an 40 or less than 5

            payRate = setPayRate();

            /*
               while loop that tests if payRate is less than 7.25 or greater than 26. If true, a new input is
               requested through the method testPayRate.
             */
            while(payRate < 7.25 || payRate > 26.00)
            {
                payRate = testPayRate(payRate);
            }//END while payRate is less than 7.25 OR greater than 26.00.

            grossPay = hoursWorked * payRate;
            grossPayTotal += grossPay;

            percent401K = set401K();

            /*
               while loop that tests if percent401K is greater than 10.00. If true, a new input is requested
               through the method test401K.
             */
            while(percent401K > 10.00)
            {
                percent401K = test401K();
            }//END while percent401K is greater than 10.00.

            retire401K = (percent401K / 100) * grossPay;
            total401K += retire401K;

            //if-else statement that determines when to put the dollar sign in the output.
            if(trigger == 1)
            {
                formatDollarSign(employeeName, grossPay, retire401K);
                trigger = 0;
            }
            else
            {
                woFormatDollarSign(employeeName, grossPay, retire401K);
            }//END if trigger is 1 else trigger is not 1.

            input.nextLine();  //Clears Buffer

            System.out.printf("%nEnter 'Y' to add another employee or 'N' to exit:  ");
            cont = input.nextLine().toUpperCase().charAt(0);  //Read

            //if statement that stops further inputs if cont is 'N' and prints the report.
            if(cont == 'N')
            {
                payrollExpense += String.format("%n%n%25s TOTALS %6s $%,15.2f %8s $%,13.2f",
                        " ", " ", grossPayTotal, " ", total401K);

                System.out.printf("%n%n%s", payrollExpense);
            }//END if cont is N.
        }//END while continue payroll system is Y.
    }//END generateReport():  static void

    /**
     * Method that takes a first name and last name from the user as an input, tests if they're
     * alphabetic, and returns them concatenated.
     */
    public static String setEmployeeName()
    {
        String first = "",    //Employee's first name.
                last = "";    //Employee's last name.

        System.out.printf("%nEnter the employee's first name press enter then the last name press enter:  ");
        first = input.nextLine();
        last = input.nextLine();

        //while loop that checks if first is not a valid name.
        while(!isAlpha(first))
        {
            System.out.printf("%nEnter valid first name:  ");
            first = input.nextLine();
        }//END while first name is NOT alphabetic

        firstNmLength = first.length();

        //while loop that checks if last is not a valid name.
        while(!isAlpha(last))
        {
            System.out.printf("%nEnter valid last name:  ");
            first = input.nextLine();
        }//END while last name is NOT alphabetic

        return first + " " + last;
    }//END setEmployeeName():  static String

    /**
     * Method that takes an integer from the user as an input, tests if it's an integer, and returns it.
     */
    public static int setHoursWorked(String first)
    {
        System.out.printf("%nEnter the number of hours worked for %s:  ", first);

        //while loop that checks if input is not an integer.
        while(!input.hasNextInt())
        {
            input.next();
            System.out.printf("%nInvalid type!  Re-enter the number of hours worked for %s:  ", first);
        }//END while NOT an integer

        return input.nextInt();
    }//END setHoursWorked(String):  static int

    /**
     * Method that tests if hoursWorked is greater than 40 or less than 5. If true, then a new input is
     * requested, retested if it's an integer, and returned.
     */
    public static int testHoursWorked(int hoursWorked)
    {
        //if statement that tests if hoursWorked is greater than 40. If true, a new input is requested.
        if(hoursWorked > 40)
        {
            System.out.printf("%nHours worked cannot EXCEED 40.  Please re-enter:  ");
        }//END if hoursWorked is greater than 40.

        //if statement that tests if hoursWorked is less than 5. If true, a new input is requested.
        if(hoursWorked < 5)
        {
            System.out.printf("%nHours worked cannot be LESS than 5.  Please re-enter:  ");
        }//END if hoursWorked is less than 5.

        //while loop that checks if first is not an integer.
        while(!input.hasNextInt())
        {
            input.next();
            System.out.printf("%nInvalid type!  Re-enter the number of hours worked not less than 5 or greater "
                            + "than 40:  ");
        }//END while NOT an integer

        return input.nextInt();
    }//END testHoursWorked(int):  static int

    /**
     * Method that takes a double from the user as an input, tests if it's a double, and returns it.
     */
    public static double setPayRate()
    {
        System.out.printf("%nEnter the employee's hourly pay rate:  ");

        //while loop that checks if input is not a double.
        while(!input.hasNextDouble())
        {
            input.next();
            System.out.printf("%nInvalid type!  Re-enter the hourly pay rate:  ");
        }//END while NOT a double

        return input.nextDouble();
    }//END setPayRate():  static double

    /**
     * Method that tests if payRate is greater than 26.00 or less than 7.25. If true, then a new input is
     * requested, retested if it's a double, and returned.
     */
    public static double testPayRate(double payRate)
    {
        //if statement that tests if payRate is less than 7.25. If true, a new input is requested.
        if(payRate < 7.25)
        {
            System.out.printf("%nHourly pay rate cannot be LESS than $7.25.  Please re-enter:  ");
        }//END if payRate is less than 7.25.

        //if statement that tests if payRate is greater than 26.00. If true, a new input is requested.
        if(payRate > 26.00)
        {
            System.out.printf("%nHourly pay rate cannot EXCEED $26.00.  Please re-enter:  ");
        }//END if payRate is greater than 26.00.

        //while loop that checks if input is not a double.
        while(!input.hasNextDouble())
        {
            input.next();
            System.out.printf("%nInvalid type!  Re-enter the "
                    + "hourly pay rate:  ");
        }//END while NOT a double

        return input.nextDouble();
    }//END testPayRate(double):  static double

    /**
     * Method that takes a double from user as an input and stores it in percent401K, it then checks to
     * see if the input value is a double and returns value if true.
     */
    public static double set401K()
    {
        System.out.printf("%nEnter the employees 401k contribution as a percentage "
                        + "of salary (not to exceed 10%%): ");

        /*
           This while loop is used to check to see if the input value is of double type, if true
           then the value will be returned.
         */
        while(!input.hasNextDouble())
        {
            input.next();
            System.out.printf("%nInvalid type! Re-enter the 401K contribution not to exceed 10%% of salary:  ");

        }//END while: data type is NOT a double

        return input.nextDouble();
    }//End set401K: static double

    /**
     * Method that tests if the 401K is feasible, if it is not, print Error message and asks user
     * to enter another percentage.
     */
    public static double test401K()
    {
        System.out.printf("%nContribution cannot EXCEED 10%%. Please re-enter: ");

        /*
           while loop is used to check if the data type of the input is a double. If it is not, error
           message is printed and user is prompted to re-enter another number.
         */
        while(!input.hasNextDouble())
        {
            input.next();
            System.out.printf("%nInvalid type! Re-enter the 401K contribution not to exceed 10%% of salary:  ");
        }//END while: data type is NOT a double

        return input.nextDouble();
    }//END test401K: static double

    /**
     * Method that formats the information with a dollar sign.
     */
    public static String formatDollarSign(String employeeName,
                                          double grossPay, double retire401K)
    {
        return payrollExpense += String.format("%n%-30s %8s $%,15.2f %8s $%, 13.2f",
                employeeName, "", grossPay,"", retire401K);

    }//END formatDollarSign: static String

    /**
     * Method that formats the information without a dollar sign.
     */
    public static String woFormatDollarSign(String employeeName,
                                            double grossPay, double retire401K)
    {
        return payrollExpense += String.format("%n%-30s %9s %,15.2f %9s %, 13.2f",
                employeeName, "", grossPay, "", retire401K);
    }//END woFormatDollarSign: static String

    /**
     * Using lambda expression to check for names that are alphabetic. The :: tells the compiler to call
     * the isLetter method from the Character class. The chars() is a Java 9 String class method.
     */
    public static boolean isAlpha(String name)
    {
        return name != null && name.chars().allMatch(Character::isLetter);

    }//END  isAlpha(String): static boolean
}//END APPLICATION CLASS PayrollExpenseReport