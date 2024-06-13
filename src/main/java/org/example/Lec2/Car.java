package org.example.Lec2;

public class Car {

    public String name;
    private String price;
    private String engType;
    private String engPower;
    private int maxSpeed;

    public Car(String name) {
        this.name = name;
        this.price = "100_000";
        this.engType = "V8";
        this.engPower = "12345";
        this.maxSpeed = 100;
    }

    @Override
    public String toString() {
        return "Car{" +
                "name='" + name + '\'' +
                ", maxSpeed=" + maxSpeed +
                '}';
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }
}
