package org.example.vehicle;

import org.example.Customer;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class CargoVan extends Vehicle {
    private static final double INSURANCE_PERCENT = 0.0003;
    private static final double SAFETY_DISCOUNT_INSURANCE_PERCENT = 0.15;
    private Customer customer;

    // Instance variables to store calculated values
    private long daysRented;
    private long actualDaysRented;
    private double dailyRentalCost;
    private double insurance;
    private double totalCost;
    private static final DecimalFormat df = new DecimalFormat("0.00");

    public CargoVan(String brand, String model, double price, LocalDate startRent, LocalDate endRent, LocalDate actualEndRent, Customer customer) {
        super(brand, model, price, startRent, endRent, actualEndRent);
        this.customer = customer;
    }

    @Override
    public void rent() {
        daysRented = ChronoUnit.DAYS.between(getStartRent(), getEndRent());
        actualDaysRented = ChronoUnit.DAYS.between(getStartRent(), getActualEndRent());

        double insuranceRate = getPrice() * INSURANCE_PERCENT;
        double safetyDiscountInsuranceRate = insuranceRate - (insuranceRate * SAFETY_DISCOUNT_INSURANCE_PERCENT);
        dailyRentalCost = (daysRented <= 7) ? 50 : 40;
        insurance = (customer.getDriverExperiences() < 5) ? insuranceRate : safetyDiscountInsuranceRate;
        totalCost = (daysRented == actualDaysRented)
                ? (dailyRentalCost * daysRented) + (insurance * daysRented)
                : (dailyRentalCost * actualDaysRented + (dailyRentalCost / 2 * (daysRented - actualDaysRented))) + (insurance * actualDaysRented);

        printingRentalDetails();
    }


    @Override
    public void printingRentalDetails() {
        System.out.println("A cargoVan that is valued at $" + df.format(getPrice()) + " and the driver has " + customer.getDriverExperiences() + " years of experience");
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
        System.out.println("Total Insurance Cost: $" + df.format(insurance * actualDaysRented));
        System.out.println("Total: $" + df.format(totalCost));
        System.out.println("--------------------------------------------------------------------");
    }
}
