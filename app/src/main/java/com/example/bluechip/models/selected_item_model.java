package com.example.bluechip.models;

public class selected_item_model {
    String name;
    String quantity;
    String rate;
    String total;

    public selected_item_model(String name, String quantity, String rate, String total) {
        this.name = name;
        this.quantity = quantity;
        this.rate = rate;
        this.total = total;
    }


    public String toString() {
        return "Name-" + name +  ',' +
                "Quantity-" + quantity + ',' +
                "Rate-" + rate + ',' +
                "Total-" + total ;
    }



//    public String toString() {
//        return "selected_item_model{" +
//                "name='" + name + '\'' +
//                ", quantity='" + quantity + '\'' +
//                ", rate='" + rate + '\'' +
//                ", total='" + total + '\'' +
//                '}';
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
