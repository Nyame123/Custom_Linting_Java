package com.ecomtrading.mycustomlintbutton.Calculator;

import java.util.InputMismatchException;
import java.util.Scanner;

public class CalculatorMain {


    private static int choiceSelected = 1;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        setMenus(scanner);
    }

    private static void setMenus(Scanner scanner) {


        System.out.println("Please, choose your operation to perform");
        System.out.println("1. Addition");
        System.out.println("2. Subtraction");
        System.out.println("3. Multiplication");
        System.out.println("4. Division");

        try {
            choiceSelected = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Please, choose a proper option from the above menu");
            choiceSelected = scanner.nextInt();
        }


        System.out.println("Enter the First Number: ");
        double firstNumber = scanner.nextDouble();
        System.out.println("Enter the Second Number: ");
        double secondNumber = scanner.nextDouble();

        perfomOperation(choiceSelected, firstNumber, secondNumber);


        System.out.println("Do you want to perform the operation again?: Y/N ");
        String feedback = scanner.nextLine();
        if (feedback.equalsIgnoreCase("Y")) {
            setMenus(scanner);
        } else {
            System.exit(1);
        }

    }

    private static void perfomOperation(int choice, double first, double second) {
        Double result = 0d;
        if (choice == 1) {
            //perform addition
            Addition addition = new Addition();
            addition.setFirstNumber(first);
            addition.setSecondNumber(second);
            result = addition.execute();

        } else if (choice == 2) {
            //perform substraction
            Substraction substraction = new Substraction();
            substraction.setFirstNumber(first);
            substraction.setSecondNumber(second);
            result = substraction.execute();
        } else if (choice == 3) {
            //perform multiplication
            Multiplication multiplication = new Multiplication();
            multiplication.setFirstNumber(first);
            multiplication.setSecondNumber(second);
            result = multiplication.execute();
        } else if (choice == 4) {
            //perform division
            Division division = new Division();
            division.setFirstNumber(first);
            division.setSecondNumber(second);
            result = division.execute();
        } else {
            //choose the proper menu
        }

        System.out.format("The result is %s \n", "" + result);

    }
}
