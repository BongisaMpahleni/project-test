package com.caniksea.adp3.collection;

import java.math.BigDecimal;

public class App implements Comparable<App> {

    private int id;
    private String name;
    private BigDecimal balance;

    public App(int id, String name, BigDecimal balance) {
        this.id = id;
        this.name = name;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return "App{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", balance=" + balance +
                '}';
    }

    @Override
    public int compareTo(App app) {
        return Integer.compare(this.id, app.id);
    }
}
