package first;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Objects;

class Product {
    private String name;
    private BigDecimal price;

    Product(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    void changePrice(BigDecimal newPrice) throws IOException {
        if (priceIsWrong(newPrice)) throw new IOException("Wrong price!");
        this.price = newPrice;
    }

    boolean priceIsWrong(BigDecimal price){
        return (price.compareTo(BigDecimal.ZERO) < 0 || price.remainder(BigDecimal.valueOf(0.01d)).compareTo(BigDecimal.ZERO) != 0);
    }

    void changeName(String newName){
        this.name = newName;
    }

    BigDecimal getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof Product) {
            Product other = (Product) obj;
            return name.equals(other.name) && price.equals(other.price);
        }
        return false;
    }

    @Override
    public String toString() {
        return ("name: " + this.name +
                "   price: " + this.price);
    }

    @Override
    public int hashCode() {
        return (Objects.hash(name, price));
    }

}