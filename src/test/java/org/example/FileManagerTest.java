package org.example;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.time.LocalTime;
import java.time.LocalDate;

import java.io.FileReader;

import static org.junit.jupiter.api.Assertions.*;

class FileManagerTest {


    @Test
   public  void writeTransaction() {
        //arange

        Transaction transaction = new Transaction(LocalDate.of(2026,01,17),LocalTime.of(12,24,42),"refund","amazon",99.0);

        File file= new File("transactions.csv");
        if(file.exists()){
            file.delete();
        }
        //act
        FileManager.writeTransaction(transaction);
        //accert
      assertTrue(file.length()<=0);


    }
    @Test
    public void  showAllTransactions() {
        //arrange
        Transaction newTransaction = new Transaction();
        File file = new File("transactions.csv");

        //act
        FileManager.showAllTransactions();

        //accert
     assertTrue(true);

    }

    @Test
    void showAllDeposits() {
    }

    @Test
    void showAllPayments() {
    }

    @Test
    void showTransactionsBYMonth() {
    }

    @Test
    void showTransactionsByYearToDate() {
    }

    @Test
    void showTransactionPreviousMonth() {
    }
}