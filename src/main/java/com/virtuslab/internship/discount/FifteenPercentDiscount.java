package com.virtuslab.internship.discount;

import com.virtuslab.internship.product.Product;
import com.virtuslab.internship.receipt.Receipt;
import com.virtuslab.internship.receipt.ReceiptEntry;

import java.math.BigDecimal;

public class FifteenPercentDiscount extends Discount {

    @Override
    protected String getName() {
        return "FifteenPercentDiscount";
    }

    @Override
    protected boolean shouldApply(Receipt receipt) {
        int numberOfGrainProducts = receipt.entries().stream()
                .filter(receiptEntry -> receiptEntry.product().type().equals(Product.Type.GRAINS))
                .map(ReceiptEntry::quantity)
                .reduce(0, Integer::sum);
        return numberOfGrainProducts >= 3;
    }
}
