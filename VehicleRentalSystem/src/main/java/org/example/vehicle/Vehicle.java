package org.example.vehicle;

import java.time.LocalDate;

public abstract class Vehicle {

    private final static int x=9;
    private String brand;
    private String model;
    private  double price;
    private LocalDate startRent;
    private LocalDate endRent;

    private  LocalDate actualEndRent;


    public Vehicle(String brand, String model, double price, LocalDate startRent, LocalDate endRent, LocalDate actualEndRent) {
        this.brand = brand;
        this.model = model;
        this.price = price;
        this.startRent = startRent;
        this.endRent = endRent;
        this.actualEndRent = actualEndRent;
    }

    public  abstract void rent();
    public  abstract void printingRentalDetails();

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public double getPrice() {
        return price;
    }

    public LocalDate getStartRent() {
        return startRent;
    }

    public LocalDate getEndRent() {
        return endRent;
    }

    public LocalDate getActualEndRent() {
        return actualEndRent;
    }
}
