import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StoreTest {

    @Test
    void addProduct() {
        Store test = new Store();
        test.addProduct(new Product("Milk", 456789), new Price(49, (short) 99));
        test.addProduct(new Product("Coconut", 123987), new Price(139, (short) 99));
        test.addProduct(new Product("Chocolate", 666993), new Price(84, (short) 37));

        int currentSize = test.getSize();
        test.addProduct(new Product("Eggs", 771689), new Price(841, (short) 37));
        assert test.getSize() == ++currentSize;

        try {
            test.addProduct(new Product("Eggs", 771689), new Price(841, (short) 37));
        } catch (IllegalArgumentException ex){
            assert true;
            return;
        }
        assert false;
    }

    @Test
    void changePriceForProduct() {
        Store test = new Store();
        test.addProduct(new Product("Milk", 456789), new Price(49, (short) 99));
        test.addProduct(new Product("Coconut", 123987), new Price(139, (short) 99));
        test.addProduct(new Product("Chocolate", 666993), new Price(84, (short) 37));

        test.changePriceForProduct(456789, new Price(55, (short) 55));
        assert test.getPrice(456789).equals(new Price(55, (short) 55));
        try {
            test.changePriceForProduct(456851, new Price(61, (short) 23));
        } catch (IllegalArgumentException ex){
            assert true;
            return;
        }
        assert false;
    }

    @Test
    void changeProductName() {
        Store test = new Store();
        test.addProduct(new Product("Milk", 456789), new Price(49, (short) 99));
        test.addProduct(new Product("Coconut", 123987), new Price(139, (short) 99));
        test.addProduct(new Product("Chocolate", 666993), new Price(84, (short) 37));

        test.changeProductName(456789, "Bread");
        assert test.getProductName(456789).equals("Bread");
        try {
            test.changeProductName(458699, "Cheese");

        } catch (IllegalArgumentException ex){
            assert true;
            return;
        }
        assert false;
    }

    @Test
    void deleteProduct() {
        Store test = new Store();
        test.addProduct(new Product("Milk", 456789), new Price(49, (short) 99));
        test.addProduct(new Product("Coconut", 123987), new Price(139, (short) 99));
        test.addProduct(new Product("Chocolate", 666993), new Price(84, (short) 37));

        int currentSize = test.getSize();
        test.deleteProduct(123987);
        assert test.getSize() == --currentSize;
        try {
            test.deleteProduct(918400);
        } catch (IllegalArgumentException ex){
            assert true;
            return;
        }
        assert false;
    }

    @Test
    void bill() {
        Store test = new Store();
        test.addProduct(new Product("Milk", 456789), new Price(49, (short) 99));
        test.addProduct(new Product("Coconut", 123987), new Price(139, (short) 99));

        Price billPrice = test.bill(456789, 5);
        assert billPrice.getRubles() == 249;
        assert billPrice.getCents() == 95;
        try {
            Price billPrice2 = test.bill(123987, -9);

        } catch (IllegalArgumentException ex){
            assert true;
            return;
        }
        assert false;
    }
}