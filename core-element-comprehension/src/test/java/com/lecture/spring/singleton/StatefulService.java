package com.lecture.spring.singleton;

public class StatefulService {

    private int price;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void order(String name, int price) {
        System.out.println("name= " + name + " price= " + price);
        this.price = price;
    }
}
