package org.example;

public class Customer {
    private  int age;
    private String name;
    private  int driverExperiences;

    public int getAge() {
        return age;
    }
    public String getName() {
        return name;
    }
    public int getDriverExperiences() {
        return driverExperiences;
    }

    public Customer( int age, String name, int driverExperiences) {
        this.age = age;
        this.name = name;
        this.driverExperiences = driverExperiences;
    }
}
