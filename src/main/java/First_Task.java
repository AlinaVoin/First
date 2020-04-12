import java.util.ArrayList;

    class Product{
        private String name;
        private int id;
        public Product(String _name, int _id){
            name = _name;
            id = _id;
        }
        public String getName(){
            return name;
        }
        public int getId(){
            return id;
        }
        public void setName(String newName){name = newName;}
        public void setId(int newId){id = newId;}
    }
    class Price{
        private int rubles;
        private short cents;
        public Price(int r, short c){
            rubles = r;
            cents = c;
        }
        public int getRubles(){
            return rubles;
        }

        public short getCents() {
            return cents;
        }
        public void setRubles(int newRubles){
            rubles = newRubles;
        }

        public void setCents(short newCents) {
            cents = newCents;
        }

        public boolean equals(Object obj) {

            if(obj==this)return true;
            if(!(obj instanceof Price)) return false;
            Price p = (Price)obj;

            return p.rubles == this.rubles && p.cents == this.cents;
        }
    }
    class Store {
        private ArrayList<Product> allProducts = new ArrayList<>();
        private ArrayList<Price> allPrices = new ArrayList<>();

        public Store() {
        }

        public void addProduct(Product product, Price price) {
            if (product == null || price == null){
                throw new IllegalArgumentException();
            }
            boolean found = false;
            for (int i = 0; i < allProducts.size(); i++) {
                if (allProducts.get(i).getId() == product.getId()) {
                    found = true;
                    break;
                }
            }
            if (found){
                throw new IllegalArgumentException();
            }
            allProducts.add(product);
            allPrices.add(price);

        }
    public int getSize(){
        return allProducts.size();
    }

    public void changePriceForProduct(int id, Price newPrice) {
        boolean found = false;
        for (int i = 0; i < allProducts.size(); i++) {
            if (allProducts.get(i).getId() == id) {
                allPrices.set(i, newPrice);
                found = true;
                break;
            }
        }
        if (!found){
            throw new IllegalArgumentException();
        }
    }
    public Price getPrice(int id){
        for (int i = 0; i < allProducts.size(); i++) {
            if (allProducts.get(i).getId() == id) {
                return new Price(allPrices.get(i).getRubles(), allPrices.get(i).getCents());
            }
        }
        throw new IllegalArgumentException();
    }

//    public void printData() {
//        if (allPrices.size() == 0) {
//            System.out.format("Store is empty!\n");
//        } else {
//            System.out.format("Наименование    ID     Цена  \n");
//            for (int i = 0; i < allProducts.size(); i++) {
//                System.out.format("%-20s  %8d  %d.%02d\n", allProducts.get(i).getName(), allProducts.get(i).getId(), allPrices.get(i).getRubles(), allPrices.get(i).getCents());
//            }
//            System.out.format("\n");
//        }
//    }
    public void changeProductName(int id, String newName) {
        boolean found = false;
        for (int i = 0; i < allProducts.size(); i++) {
            if (allProducts.get(i).getId() == id) {
                allProducts.get(i).setName(newName);
                found = true;
                break;
            }
        }
        if (!found){
            throw new IllegalArgumentException();
        }
    }
    public String getProductName(int id){
        for (int i = 0; i < allProducts.size(); i++) {
            if (allProducts.get(i).getId() == id) {
                return allProducts.get(i).getName();
            }
        }
            throw new IllegalArgumentException();
    }

    public void deleteProduct(int id) {
        boolean found = false;
        for (int i = 0; i < allProducts.size(); i++) {
            if (allProducts.get(i).getId() == id) {
                allProducts.remove(i);
                allPrices.remove(i);
                found = true;
                break;
            }
        }
        if (!found){
            throw new IllegalArgumentException();
        }
    }

    public Price bill(int id, int total) {
        if (total < 1) throw new IllegalArgumentException();
        for (int i = 0; i < allProducts.size(); i++) {
            if (allProducts.get(i).getId() == id) {
                int totalRubles = allPrices.get(i).getRubles() * total;
                int totalCents = allPrices.get(i).getCents() * total;
                totalRubles += totalCents / 100;
                totalCents = totalCents % 100;
                return new Price(totalRubles, (short) totalCents);
            }
        }
        throw new IllegalArgumentException();
    }
}
