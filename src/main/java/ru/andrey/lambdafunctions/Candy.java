package ru.andrey.lambdafunctions;

public class Candy {
    private String name;
    private float price;
    private String manufactorer;

    public Candy(String name, float price, String manufactorer) {
        this.name = name;
        this.price = price;
        this.manufactorer = manufactorer;
    }

    public float getPrice() {
        return price;
    }

    public String getManufactorer() {
        return manufactorer;
    }

    public String getName() {
        return name;
    }
}
