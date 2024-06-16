package org.example;

import org.example.vehicle.Car;
import org.example.vehicle.CargoVan;
import org.example.vehicle.Motorcycle;

import java.time.LocalDate;

public  class DataLoader {

    public static void LoadData(){
        Customer customer1 = new Customer(30, "John Doe", 10);
        LocalDate startRent1 = LocalDate.of(2024, 6, 3);
        LocalDate endRent1 = LocalDate.of(2024, 6, 13);
        LocalDate actualEndRent1 = LocalDate.of(2024, 6, 13);
        Car car1 = new Car("Toyota", "Camry", 15000, startRent1, endRent1, actualEndRent1, 3, customer1);
        car1.rent(); // Should print the rental details


        Customer customer2 = new Customer(20, "Jane Smith", 10);
        // Test case: Rental period with early return
        LocalDate startRent2 = LocalDate.of(2024, 6, 3);
        LocalDate endRent2 = LocalDate.of(2024, 6, 13);
        LocalDate actualEndRent2 = LocalDate.of(2024, 6, 13);
        Motorcycle motorcycle2 = new Motorcycle("Honda", "CBR500R", 10000, startRent2, endRent2, actualEndRent2, customer2);
        motorcycle2.rent(); // Should print the rental details

        // Create another customer
        Customer customer3 = new Customer(40, "Alice Johnson", 8);// Test case: Rental period with late return
        LocalDate startRent3 = LocalDate.of(2024, 6, 3);
        LocalDate endRent3 = LocalDate.of(2024, 6, 18);
        LocalDate actualEndRent3 = LocalDate.of(2024, 6, 13);
        CargoVan cargoVan3 = new CargoVan("Chevrolet", "Express", 20000, startRent3, endRent3, actualEndRent3, customer3);
        cargoVan3.rent(); // Should print the rental details
    }

}
