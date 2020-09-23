package first;

import java.util.HashMap;
import java.util.Map;

public class PriceList {
    private Map<Integer, Product> priceMap = new HashMap<>();


    public void addProduct(int code, Product product)  {
        if (isPriceWrong(String.valueOf(product.getPrice()))) {
            System.out.println("Цена продукта "+product.getName()+" указана некорректно, продукт не может быть добавлен");
        }else{
            priceMap.put(code, product);
            System.out.println("Продукт добавлен");
        }
    }

    public Product getProduct(int code){
        return priceMap.get(code);
    }


    private boolean isPriceWrong(String price){
        return !price.matches("\\d+\\.?\\d{0,2}");
    }

    public Product removeProduct(int code) {
        return priceMap.remove(code);
    }

    public void changePrice(int code, double newPrice) {
        if (!priceMap.containsKey(code)) {
            System.out.println("No such code!");
        }else {
            if(!isPriceWrong(String.valueOf(newPrice))){
                priceMap.get(code).changePrice(newPrice);
            }
        }
    }

    public void changeName(int code, String newName) {
        if (!priceMap.containsKey(code)) {
            System.out.println("No such code!");
        }else {
            priceMap.get(code).changeName(newName);
        }
    }

    public double countCost(int[] codes, int[] amount) {
        double cost = 0;
        if (codes.length != amount.length)
        {
            System.out.println("Wrong data!");
        }
        else {
            for (int i = 0; i < codes.length; i++) {
                if (priceMap.containsKey(codes[i]) && amount[i] > 0)
                    cost += priceMap.get(codes[i]).getPrice() * amount[i];
            }
        }
        return cost;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        priceMap.forEach((code, product) -> result.append("code: ").append(code).append("   ").
                append("item: ").append(product).append("\n"));
        return result.toString();
    }


}

