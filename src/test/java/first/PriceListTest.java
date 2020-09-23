package first;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PriceListTest {

    private PriceList priceList = new PriceList();

    private Product bonk = new Product("Bonk", 69.69);
    private Product sandwich = new Product("Sandwich", 228.42);
    private Product productNegativePrice = new Product("EvilMilk", -34.99);
    private Product productInvalidPenny = new Product("EvilMilk", -34.995);

    @BeforeEach
    public void setUp() {
        priceList.addProduct(1,bonk);
    }



    @Test
    void addProduct() {
        priceList.addProduct(2, sandwich);
        Product product = priceList.getProduct(2);
        assertEquals(sandwich, product);

    }
    @Test
    void addProduct_invalidNegativePrice() {
        priceList.addProduct(2, productNegativePrice);
        Product product = priceList.getProduct(2);
        assertEquals(null, product);
    }

    @Test
    void addProduct_invalidPenny() {
        priceList.addProduct(2, productInvalidPenny);
        Product product = priceList.getProduct(2);
        assertEquals(null, product);
    }


    @Test
    void removeProduct() {
        Product res = priceList.removeProduct(1);
        assertEquals(bonk, res);
    }

    @Test
    void changePrice() {
        priceList.changePrice(1, 100.4);
        assertEquals(100.4, priceList.getProduct(1).getPrice());
    }

    @Test
    void changePrice_invalidNegativePrice() {
        double sourcePrice = priceList.getProduct(1).getPrice();
        priceList.changePrice(1, -100.4);
        assertEquals(sourcePrice, priceList.getProduct(1).getPrice());
    }


    @Test
    void changePrice_invalidPenny() {
        double sourcePrice = priceList.getProduct(1).getPrice();
        priceList.changePrice(1, 100.423);
        assertEquals(sourcePrice, priceList.getProduct(1).getPrice());
    }





    @Test
    void changeName() {
        String newName = "Snickers";
        priceList.changeName(1, newName);
        assertEquals(priceList.getProduct(1).getName(), newName);
    }


    @Test
    void invalidCode(){
        Product product = priceList.getProduct(0);
        assertEquals(product, null);
    }


    @Test
    void countCost() {
        int[] codes = {1, 2};
        int[] amount = {10, 10};

        priceList.addProduct(2, sandwich);
        double totalSum = bonk.getPrice() * amount[0] + sandwich.getPrice() * amount[1];
        assertEquals(priceList.countCost(codes, amount), totalSum);
    }

    @Test
    void countCost_invalidOneCode() {
        int[] codes = {1, -2};
        int[] amount = {10, 10};

        priceList.addProduct(2, sandwich);
        double totalSum = bonk.getPrice() * amount[0];
        assertEquals(priceList.countCost(codes, amount), totalSum);
    }

    @Test
    void countCost_invalidAllCode() {
        int[] codes = {-1, -2};
        int[] amount = {10, 10};

        priceList.addProduct(2, sandwich);
        assertEquals(priceList.countCost(codes, amount), 0);
    }


}

