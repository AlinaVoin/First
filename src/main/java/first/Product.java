package first;


import java.util.Objects;

public class Product {
    private String name;
    private double price;

    Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public void changePrice(double newPrice) {
        this.price = newPrice;
    }

    public void changeName(String newName){
        this.name = newName;
    }

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof Product) {
            Product other = (Product) obj;
            return name.equals(other.name) && price == price;
        }
        return false;
    }

    @Override
    public String toString() {
        return ("name: " + this.name +
                "   price: " + this.price);
    }
}