package com.lecture.spring.singleton;

public class StatefulService {

    // Stateful field
    private int price;

    public int getPrice() {
        return price;
    }

    public void order(String name, int price) {
        System.out.println("name= " + name + " price= " + price);
        this.price = price;     // cause problem
    }
}
