package org.example.vehicle;



import org.example.Customer;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Car extends Vehicle {
    private static final double INSURANCE_PERCENT = 0.0001;
    private static final double SAFETY_DISCOUNT_INSURANCE_PERCENT = 0.10;
    private int carSafetyRating;
    private Customer customer;

    private static final DecimalFormat df = new DecimalFormat("0.00");


    // Instance variables to store calculated values
    private long daysRented;
    private long actualDaysRented;
    private double dailyRentalCost;
    private double insurance;
    private double totalCost;

    public Car(String brand, String model, double price, LocalDate startRent, LocalDate endRent, LocalDate actualEndRent, int carSafetyRating, Customer customer) {
        super(brand, model, price, startRent, endRent, actualEndRent);
        this.carSafetyRating = carSafetyRating;
        this.customer = customer;
    }

    public int getCarSafetyRating() {
        return carSafetyRating;
    }

    @Override
    public void rent() {
        daysRented = ChronoUnit.DAYS.between(getStartRent(), getEndRent());
        actualDaysRented = ChronoUnit.DAYS.between(getStartRent(), getActualEndRent());

        double insuranceRate = getPrice() * INSURANCE_PERCENT; // 0.01% of the car's price
        double safetyDiscountInsuranceRate = insuranceRate - insuranceRate * SAFETY_DISCOUNT_INSURANCE_PERCENT; // Decrease 10% of the insurance
        dailyRentalCost = (daysRented <= 7) ? 20 : 15;
        insurance = (getCarSafetyRating() <= 3) ? insuranceRate : safetyDiscountInsuranceRate;
        totalCost = (daysRented == actualDaysRented)
                ? (dailyRentalCost * daysRented) + (insurance * daysRented)
                : (dailyRentalCost * actualDaysRented + (dailyRentalCost / 2 * (daysRented - actualDaysRented))) + (insurance * actualDaysRented);

        printingRentalDetails();
    }

    @Override
    public void printingRentalDetails() {
        System.out.println("A car that is valued at " + df.format(getPrice()) + " and has security rating of " + getCarSafetyRating());
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
        System.out.println("Total Rental Cost without insurance: $" + df.format(totalCost - (insurance * daysRented)));
        System.out.println("Total Insurance Cost: $" + df.format(insurance * daysRented));
        System.out.println("Total: $" + df.format(totalCost));
        System.out.println("--------------------------------------------------------------------");
    }
}