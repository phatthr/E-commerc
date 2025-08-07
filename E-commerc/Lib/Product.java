package Lib;

public final class Product {
    private final String productId;
    private final String productName;
    private final double price;

    public Product(String productId ,String productName , double price){
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        checkRep();
    }

    private void checkRep(){
        if(productId == null || productId.isBlank()){
            throw new RuntimeException("RI violated : productId cannot be blank.");
        }
        if(productId == null || productName.isBlank()){
            throw new RuntimeException("RI violated : productName cannot be blank.");
        }
        if(price < 0){
            throw new RuntimeException("RI violated : price cannot be negative.");
        }
    }

    public String getProductId(){ return productId;}
    public String getProductName(){ return productName;}
    public double getPrice(){ return price;}

    public boolean equals(Object obj){
        if (this == obj) return true;
        if (obj == null||getClass()!=obj.getClass()) return false;
        Product product = (Product) obj;
        return productId.equals(productId);
    }

}