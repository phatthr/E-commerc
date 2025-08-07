package Lib;
import java.util.ArrayList;

public class ShoppingCart {
    private final ArrayList<CartItem> items = new ArrayList<>();
    private final PricingService pricingService;
    private final ProductCatalog catalog;

    public ShoppingCart(PricingService pricingService, ProductCatalog catalog) {
        this.pricingService = pricingService;
        this.catalog = catalog;
        checkRep();
    }

    public void addItem(String productId, int quantity) {
        if (quantity <= 0) return;

        Product product = catalog.findById(productId);
        if (product == null) return;

        for (CartItem item : items) {
            if (item.getProduct().getProductId().equals(productId)) {
                item.increaseQuantity(quantity);
                checkRep();
                return;
            }
        }

        CartItem newItem = new CartItem(product, quantity);
        items.add(newItem);
        checkRep();
    }

    public void removeItem(String productId) {
        CartItem toRemove = null;
        for (CartItem item : items) {
            if (item.getProduct().getProductId().equals(productId)) {
                toRemove = item;
                break;
            }
        }
        if (toRemove != null) {
            items.remove(toRemove);
        }
        checkRep();
    }

    public double getTotalPrice() {
        double total = 0.0;
        for (CartItem item : items) {
            total += pricingService.calculatePrice(item);
        }
        return total;
    }

    public int getItemCount() {
        return items.size();
    }

    public void clearCart() {
        items.clear();
        checkRep();
    }

    private void checkRep() {
        if (items == null) {
            throw new RuntimeException("RI violated: items must not be null.");
        }

        for (int i = 0; i < items.size(); i++) {
            for (int j = i + 1; j < items.size(); j++) {
                if (items.get(i).getProduct().equals(items.get(j).getProduct())) {
                    throw new RuntimeException("RI violated: duplicate products found in cart.");
                }
            }
        }
    }
}