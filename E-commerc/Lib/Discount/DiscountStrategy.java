package Lib.Discount;
import Lib.*;
/*
 *  Interface  สำหรับกลยุทธ์การคำนวณราคาสินค้า
 */

public interface DiscountStrategy {
    /*
     * 
     */

    double calculatePrice (CartItem item);
} 
