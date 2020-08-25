package first;


import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Objects;

public class PriceList {
    private HashMap<Integer, Product> priceMap = new HashMap<>();
    
    
    public Product addProduct(int code, Product product) throws IOException {
        if (product.priceIsWrong(product.getPrice())) throw new IOException("Wrong price!");
        return priceMap.put(code, product);
    }

    public Product removeProduct(int code) {
        return priceMap.remove(code);
    }

    public void changePrice(int code, BigDecimal newPrice) throws IOException {
        if (!priceMap.containsKey(code)) throw new IOException("No such code!");
        priceMap.get(code).changePrice(newPrice);
    }

    public void changeName(int code, String newName) throws IOException {
        if (!priceMap.containsKey(code)) throw new IOException("No such code!");
        priceMap.get(code).changeName(newName);
    }

    public BigDecimal countCost(int[] codes, int[] amount) throws IOException {
        if (codes.length != amount.length) throw new IOException("Wrong data!");
        BigDecimal cost = BigDecimal.ZERO;
        for (int i = 0; i < codes.length; i++) {
            if (priceMap.containsKey(codes[i]) && amount[i] > 0)
                cost = cost.add(priceMap.get(codes[i]).getPrice().multiply(BigDecimal.valueOf(amount[i])));
            else throw new IOException("Wrong data!");
        }
        return cost;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof PriceList) {
            PriceList other = (PriceList) obj;
            return priceMap.equals(other.priceMap);
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        priceMap.forEach((Integer, Product) -> result.append("code: ").append(Integer).append("   ").
                append("item: ").append(Product).append(System.lineSeparator()));
        return result.toString();
    }

    @Override
    public int hashCode() {
        return (Objects.hash(priceMap));
    }

}

