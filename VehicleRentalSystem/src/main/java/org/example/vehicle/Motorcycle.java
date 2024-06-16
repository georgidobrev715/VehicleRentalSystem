package org.example.vehicle;

import org.example.Customer;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Motorcycle extends Vehicle {
    private static final double INSURANCE_PERCENT = 0.0002;
    private static final double SAFETY_DISCOUNT_INSURANCE_PERCENT = 0.20;
    private Customer customer;

    // Instance variables to store calculated values
    private long daysRented;
    private long actualDaysRented;
    private double dailyRentalCost;
    private double insuranceRate;
    private double additionalInsuranceRate;
    private double insurance;
    private double totalCost;
    private static final DecimalFormat df = new DecimalFormat("0.00");

    public Motorcycle(String brand, String model, double price, LocalDate startRent, LocalDate endRent, LocalDate actualEndRent, Customer customer) {
        super(brand, model, price, startRent, endRent, actualEndRent);
        this.customer = customer;
    }

    @Override
    public void rent() {
        daysRented = ChronoUnit.DAYS.between(getStartRent(), getEndRent());
        actualDaysRented = ChronoUnit.DAYS.between(getStartRent(), getActualEndRent());

        dailyRentalCost = (daysRented <= 7) ? 15 : 10;
        insuranceRate = getPrice() * INSURANCE_PERCENT;
        additionalInsuranceRate = insuranceRate * SAFETY_DISCOUNT_INSURANCE_PERCENT;
        double notSafetyDiscountInsuranceRate = insuranceRate + additionalInsuranceRate;
        insurance = (customer.getAge() > 25) ? insuranceRate : notSafetyDiscountInsuranceRate;
        totalCost = (daysRented == actualDaysRented)
                ? (dailyRentalCost * daysRented) + (insurance * daysRented)
                : (dailyRentalCost * actualDaysRented + (dailyRentalCost / 2 * (daysRented - actualDaysRented))) + (insurance * actualDaysRented);

        printingRentalDetails();
    }

    @Override
    public void printingRentalDetails() {
        System.out.println("A motorcycle that is valued at $" + df.format(getPrice()) + " and the driver is " + customer.getAge() + " years old");
        System.out.println("-----------------------------------------------------------");
        System.out.println("Date: " + getEndRent());
        System.out.println("Customer Name: " + customer.getName());
        System.out.println("Rented vehicle: " + getBrand() + " " + getModel());
        System.out.println("Reservation start date: " + getStartRent());
        System.out.println("Reservation end date: " + getEndRent());
        System.out.println("Days Rented: " + daysRented);
        System.out.println("Actual End Rent: " + getActualEndRent());
        System.out.println("Actual Days Rented: " + actualDaysRented);
        System.out.println("Rental cost per day: $" + df.format(dailyRentalCost));
        System.out.println("Insurance per Day: $" + df.format(insurance));
        if (daysRented > actualDaysRented) {
            System.out.println("Early return discount for rent: $" + df.format(dailyRentalCost / 2 * (daysRented - actualDaysRented)));
            System.out.println("Early return discount for insurance: $" + df.format(insurance * daysRented - insurance * actualDaysRented));
        }
        System.out.println("Total Rental Cost without insurance: $" + df.format(dailyRentalCost * actualDaysRented + (dailyRentalCost / 2 * (daysRented - actualDaysRented))));
        System.out.println("Total Insurance Cost: $" + df.format(insurance * daysRented));
        System.out.println("Total: $" + df.format(totalCost));
        System.out.println("--------------------------------------------------------------------");
    }
}