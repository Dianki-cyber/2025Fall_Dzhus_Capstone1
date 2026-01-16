package org.example;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLOutput;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.io.FileWriter;


public class FileManager {

    public static List<Transaction> readFile() {
        List<Transaction> transactionList = new ArrayList<>();
        try {
            FileReader fr = new FileReader("src/main/resources/transactions.csv");
            BufferedReader reader = new BufferedReader(fr);

            String line;

            // not read first line its important
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                if (line.isBlank()) continue;
                String[] transactionData = line.split("\\|");

                //If old rows only have 5 columns, category becomes "Uncategorized"
                String category = "Uncategorized";
                if (transactionData.length >= 6) {
                    category = transactionData[5];
                    if (category == null || category.isBlank()) category = "Uncategorized";
                }

                // date= 0 |time=1|description|=2vendor=3|amount=4
                Transaction newTransaction = new Transaction();
                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                LocalDate date = LocalDate.parse(transactionData[0], dateFormatter);
                LocalTime time = LocalTime.parse(transactionData[1], timeFormatter);
                newTransaction.setDate(date);
                newTransaction.setTime(time);
                newTransaction.setDescription(transactionData[2]);
                newTransaction.setVendor(transactionData[3]);
                newTransaction.setAmount(Double.parseDouble(transactionData[4]));
                newTransaction.setCategory(category);

                transactionList.add(newTransaction);
            }
        } catch (FileNotFoundException ex) {

        } catch (IOException ex) {


        }
        return transactionList;
    }

    public static void writeTransaction(Transaction transaction) {
        try {
            // open the file
            FileWriter writer = new FileWriter("src/main/resources/transactions.csv", true);
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            // write to the file
            writer.write("\n");
            writer.write(transaction.getDate().format(dateFormatter));
            writer.write("|");
            writer.write(transaction.getTime().format(timeFormatter));
            writer.write("|");
            writer.write(transaction.getDescription());
            writer.write("|");
            writer.write(transaction.getVendor());
            writer.write("|");
            writer.write(String.valueOf(transaction.getAmount()));

            // NEW: write category (6th column)
            String category = transaction.getCategory();
            if (category == null || category.isBlank()) category = "Uncategorized";
            writer.write("|");
            writer.write(category);

            writer.close();
        } catch (IOException e) {
            System.out.println("ERROR: An unexpected error occurred");
            e.printStackTrace();
        }
    }


    public static void showAllTransactions() {
        //define an array of <Transaction >

        List<Transaction> transactionList = readFile();// function that reads all transaction from transactions.csv
        //make loop for(<NameOfClass> nameOfVariable: nameOfArray)

        /* for (Transaction transaction : transactionList) {
            transaction.displayOnScreen();
        }
        */

        if (transactionList.isEmpty()) {
            System.out.println("\nNo transactions found.");
            return;
        }

        System.out.println("\n========= TRANSACTIONS =========");
        for (Transaction transaction : transactionList) {
            transaction.displayOnScreen();
        }
        System.out.println("================================\n");
    }

    public static void showAllDeposits() {
        List<Transaction> transactionList = readFile();
        System.out.println("\n========= DEPOSITS =========");

        for (Transaction transaction : transactionList) {
            if (transaction.getAmount() > 0) {
                transaction.displayOnScreen();
            }
        }
        System.out.println("================================\n");
    }

    public static void showAllPayments() {
        List<Transaction> transactionList = readFile();
        System.out.println("\n========= PAYMENTS =========");

        for (Transaction transaction : transactionList) {
            if (transaction.getAmount() < 0) {
                transaction.displayOnScreen();

            }
        }
        System.out.println("===============================\n");
    }

    public static void showTransactionsBYMonth(int month) {
        List<Transaction> transactionList = readFile();
        //boolean found = false;

        for (Transaction transaction : transactionList) {

            int currentMonth = transaction.getDate().getMonthValue();

            if (currentMonth == month) {
                transaction.displayOnScreen();
            }
        }

    }


    public static void showTransactionsByYearToDate() {
        List<Transaction> transactionList = readFile();
        LocalDate today = LocalDate.now();

        for (Transaction t : transactionList) {

            //check that the date falls between Jan 1, the current year to today's date
            if (t.getDate().getYear() == today.getYear()) {
                t.displayOnScreen();
            }

        }
    }

    public static void showTransactionPreviousMonth() {
        List<Transaction> transactionList = readFile();
        LocalDate previousMonth = LocalDate.now().minusMonths(1);

        for (Transaction transaction : transactionList) {

            //String previousMonth1 = transaction.getDate().getMonth();
            boolean yearsMatch = transaction.getDate().getYear() == previousMonth.getYear();
            boolean isPreviousMonth = transaction.getDate().getMonth() == previousMonth.getMonth();

            if (yearsMatch && isPreviousMonth){
                transaction.displayOnScreen();
            }

        }
    }

    public static void searchByVendor(String vendorSearch) {
        List<Transaction> transactionList = readFile();

        boolean found = false;
        String search = vendorSearch.toLowerCase();

        for (Transaction transaction : transactionList) {
            if (transaction.getVendor().toLowerCase().contains(search)) {
                transaction.displayOnScreen();
                found = true;
            }
        }

        if (!found) {
            System.out.println("No transactions found for vendor: " + vendorSearch);
        }
    }
}





