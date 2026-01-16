package org.example;

import java.sql.SQLOutput;
import java.util.InputMismatchException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        boolean running = true;
        // Create the menu in while loop
        while (running) {
            System.out.println(" === Home Menu === ");
            System.out.println(" D) Add Deposit");
            System.out.println(" P) Make Payment (Debit) ");
            System.out.println(" L) Ledger ");
            System.out.println(" X) Exit ");
            System.out.println(" Choose the option: ");

            // Create a switch case to handle what they type
            String choice = scanner.nextLine().trim().toUpperCase();

            switch (choice) {
                case "D":
                    try {
                        System.out.println("Type description:");
                        String description = scanner.nextLine();

                        System.out.println("Enter amount:");
                        double amount = scanner.nextDouble();
                        scanner.nextLine();

                        //vendor
                        System.out.println("Type vendor:");
                        String vendor = scanner.nextLine();

                        //create transaction (deposit) positive
                        Transaction transaction1 = new Transaction(LocalDate.now(), LocalTime.now(), description, vendor, amount);
                        FileManager.writeTransaction(transaction1);
                        System.out.println("Good Job!");

                    } catch (InputMismatchException exception) {
                        System.out.println("Try again");
                    }
                    break;

                case "P":
                    try {
                        System.out.println("=== Make Payment (Debit)===");

                        System.out.println("Enter description:");
                        String description1 = scanner.nextLine();

                        System.out.println("Enter vendor:");
                        String vendor1 = scanner.nextLine();

                        //create new transaction but negative
                        System.out.println("Enter amount:");
                        double amount1 = Double.parseDouble(scanner.nextLine());
                        amount1 = amount1 * -1;
                        Transaction transaction2 = new Transaction(LocalDate.now(), LocalTime.now(), description1, vendor1, amount1);
                        FileManager.writeTransaction(transaction2);

                        System.out.println("Successful payment!");
                    } catch (InputMismatchException exception) {
                        System.out.println("Try one more time");
                    }
                    break;


                case "L":
                    boolean ledgerPage = true;

                    while (ledgerPage) {
                        try {
                            System.out.println("=== Ledger ===");
                            System.out.println("A) Display all entries");
                            System.out.println("D) Display deposits");
                            System.out.println("P) Display payments ");
                            System.out.println("R) Reports");
                            System.out.println("H) Home");
                            String choiceLedger = scanner.nextLine().trim().toUpperCase();
                            switch (choiceLedger) {
                                case "A":
                                    // call method that shows all transaction
                                    FileManager.showAllTransactions();
                                    break;
                                case "D":
                                    FileManager.showAllDeposits();
                                    break;
                                case "P":
                                    FileManager.showAllPayments();
                                    break;
                                case "R":
                                    boolean reportPage = true;
                                    while (reportPage) {
                                        System.out.println("=== Reports ===");
                                        System.out.println("1) Month To Date");
                                        System.out.println("2) Previous Month");
                                        System.out.println("3) Year To Date");
                                        System.out.println("0) Back");
                                        String reports = scanner.nextLine().trim();
                                        switch (reports) {
                                            case "1":
                                                System.out.println("Enter month index:");
                                                int monthValue = Integer.parseInt(scanner.nextLine());

                                                //call method that shows all transaction by month
                                                FileManager.showTransactionsBYMonth(monthValue);
                                                break;
                                            case "2":
                                                FileManager.showTransactionPreviousMonth();
                                                break;
                                            case "3":
                                                FileManager.showTransactionsByYearToDate();
                                                break;
                                            case "0":
                                                reportPage = false;
                                                break;
                                            default:
                                                System.out.println("Try again....");
                                                break;
                                        }
                                    }
                                    break;
                                case "H":
                                    ledgerPage = false;
                                    break;

                                default:
                                    System.out.println("invalid option. Choose A, D, P, R, H");
                            }
                        } catch (InputMismatchException exception) {
                            System.out.println("Try again");

                        }

                    }
                    break;

                case "X":
                    System.out.println("Thank you for your time");
                    running = false;
                    break;

            }
        }
    }
}