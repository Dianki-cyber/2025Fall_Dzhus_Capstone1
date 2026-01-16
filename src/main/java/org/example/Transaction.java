package org.example;

import java.time.LocalDate;
import java.time.LocalTime;

public class Transaction {

    private LocalDate date ;
    private LocalTime time;
    private String  description;
    private String  vendor;
    private double amount;



    public Transaction(){

    }

    public Transaction(LocalDate date, LocalTime time, String description, String vendor, double amount) {
        this.date = date;
        this.time = time;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;

    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }


    public void displayOnScreen() {
        System.out.printf("""
            ------------------------------
            Date:        %s
            Time:        %s
            Description: %s
            Vendor:      %s
            Amount:      %.2f
            """,
                date,
                time,
                description,
                vendor,
                amount
        );
    }


       /* System.out.println(date);
        System.out.println(time);
        System.out.println(description);
        System.out.println(vendor);
        System.out.println(amount);
        System.out.println("\n");
*/

    }







