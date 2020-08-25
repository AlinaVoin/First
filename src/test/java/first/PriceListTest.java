package first;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import java.math.BigDecimal;

class PriceListTest {

    private PriceList testPList = new PriceList();

    private Product bonk = new Product("name", BigDecimal.valueOf(69.69));
    private Product sandwich = new Product("name", BigDecimal.valueOf(228.42));
    private Product evilMilk = new Product("name", BigDecimal.valueOf(-34.99));
    private Product kindCheetos = new Product("name", BigDecimal.valueOf(55.987));

    @BeforeEach
    public void setUp() throws IOException {
        testPList.addProduct(1,bonk);
    }


    @Test
    void addProduct() throws IOException {
        Product res = testPList.addProduct(1, sandwich);
        assertEquals(res, bonk);

        try {
            testPList.addProduct(2, evilMilk);
        } catch (IOException thrown) {
            assertEquals("Wrong price!", thrown.getMessage());
        }

        try {
            testPList.addProduct(2, kindCheetos);
        } catch (IOException thrown) {
            assertEquals("Wrong price!", thrown.getMessage());
        }



        assertThrows(IOException.class, () -> testPList.addProduct(2, evilMilk)); // нашла этот метод проверки исключений в интернете. не знаю, насколько он акуален

    }

    @Test
    void removeProduct() {
        Product res = testPList.removeProduct(1);
        assertEquals(res, bonk);

        assertNull(testPList.removeProduct(2));

    }

    @Test
    void changePrice() throws IOException {

        PriceList actual = new PriceList();
        actual.addProduct(1, new Product("name", BigDecimal.valueOf(1.10)));
        testPList.changePrice(1, BigDecimal.valueOf(1.10));
        assertEquals(testPList, actual);

        try {
            testPList.changePrice(0, BigDecimal.valueOf(1.10));
        } catch (IOException thrown) {
            assertEquals("No such code!", thrown.getMessage());
        }

        try {
            testPList.changePrice(1, BigDecimal.valueOf(-1.0));
        } catch (IOException thrown) {
            assertEquals("Wrong price!", thrown.getMessage());
        }

        try {
            testPList.changePrice(1, BigDecimal.valueOf(1.111));
        } catch (IOException thrown) {
            assertEquals("Wrong price!", thrown.getMessage());
        }
    }

    @Test
    void changeName() throws IOException {
        PriceList actual = new PriceList();
        actual.addProduct(1, new Product("Normal", bonk.getPrice()));
        testPList.changeName(1, "Normal");
        assertEquals(testPList, actual);


        try {
            testPList.changeName(0, "NoSuchCode");
        } catch (IOException thrown) {
            assertEquals("No such code!", thrown.getMessage());
        }

    }

    @Test
    void countCost() throws IOException {
        int[] codes = {1,2};
        int[] amount = {10,10};
        int[] wrongCodes = {0,1};
        int[] wrongAmount = {10,10,5};

        testPList.addProduct(1,bonk);
        testPList.addProduct(2,sandwich);

        assertEquals(BigDecimal.valueOf(2981.10).setScale(2), testPList.countCost(codes, amount));

        try {
            testPList.countCost(codes, wrongAmount);
        } catch (IOException thrown) {
            assertEquals("Wrong data!", thrown.getMessage());
        }

        try {
            testPList.countCost(wrongCodes, amount);
        } catch (IOException thrown) {
            assertEquals("Wrong data!", thrown.getMessage());
        }
    }
}

