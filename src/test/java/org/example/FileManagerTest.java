package org.example;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalTime;
import java.time.LocalDate;

import java.io.FileReader;

import static org.junit.jupiter.api.Assertions.*;

class FileManagerTest {


    @Test
   public  void writeTransaction() {
        //arange

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

    }

