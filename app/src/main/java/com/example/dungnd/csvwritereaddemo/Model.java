package com.example.dungnd.csvwritereaddemo;

public class Model {
    private String commodityCode;
    private String category;
    private int number;
    private float price;
    private float money;

    public Model(String commodityCode, String category, int number, float price, float money) {
        this.commodityCode = commodityCode;
        this.category = category;
        this.number = number;
        this.price = price;
        this.money = money;
    }

    public String getCommodityCode() {
        return commodityCode;
    }

    public void setCommodityCode(String commodityCode) {
        this.commodityCode = commodityCode;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "Model{" + "commodityCode='" + commodityCode + '\'' + ", category='" + category + '\'' + ", number=" + number + ", price=" + price + ", money=" + money + '}';
    }
}
