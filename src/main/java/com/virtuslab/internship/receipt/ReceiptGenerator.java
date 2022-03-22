package com.virtuslab.internship.receipt;

import com.virtuslab.internship.basket.Basket;
import com.virtuslab.internship.discount.FifteenPercentDiscount;
import com.virtuslab.internship.discount.TenPercentDiscount;
import com.virtuslab.internship.product.Product;
import com.virtuslab.internship.product.ProductDb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ReceiptGenerator {

    public Receipt generate(Basket basket) {
        List<ReceiptEntry> receiptEntries = generateEntries(basket.getProducts());

        Receipt receipt = new Receipt(receiptEntries);

        applyDiscountIfNecessary(receipt);

        return new Receipt(receiptEntries);
    }

    private void applyDiscountIfNecessary(Receipt receipt) {
        var tenPercentDiscount = new TenPercentDiscount();
        var fifteenPercentDiscount = new FifteenPercentDiscount();
        fifteenPercentDiscount.apply(receipt);
        tenPercentDiscount.apply(receipt);
    }

    private List<ReceiptEntry> generateEntries(List<Product> products){
        List<ReceiptEntry> receiptEntries = new ArrayList<>();
        HashMap<String, Integer> quantities = new HashMap<>();
        for (var product: products){
            int lastQuantity = quantities.getOrDefault(product.name(),0);
            quantities.put(product.name(), lastQuantity + 1);
        }

        var productDb = new ProductDb();
        quantities.forEach((productName, quantity) ->{
            receiptEntries.add(new ReceiptEntry(productDb.getProduct(productName),quantity));
        });
        return receiptEntries;
    }
}
