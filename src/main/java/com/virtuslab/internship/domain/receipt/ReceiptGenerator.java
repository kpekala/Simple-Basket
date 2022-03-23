package com.virtuslab.internship.domain.receipt;

import com.virtuslab.internship.domain.basket.Basket;
import com.virtuslab.internship.domain.discount.FifteenPercentDiscount;
import com.virtuslab.internship.domain.discount.TenPercentDiscount;
import com.virtuslab.internship.domain.product.Product;
import com.virtuslab.internship.domain.product.ProductDb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ReceiptGenerator {

    public Receipt generate(Basket basket) {
        List<ReceiptEntry> receiptEntries = generateEntries(basket.getProducts());

        Receipt receipt = new Receipt(receiptEntries);

        receipt = applyDiscountIfNecessary(receipt);

        return receipt;
    }

    private Receipt applyDiscountIfNecessary(Receipt receipt) {
        var tenPercentDiscount = new TenPercentDiscount();
        var fifteenPercentDiscount = new FifteenPercentDiscount();
        receipt = fifteenPercentDiscount.apply(receipt);
        receipt = tenPercentDiscount.apply(receipt);
        return receipt;
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
