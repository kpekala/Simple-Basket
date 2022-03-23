package com.virtuslab.internship.domain.discount;

import com.virtuslab.internship.domain.product.Product;
import com.virtuslab.internship.domain.receipt.Receipt;
import com.virtuslab.internship.domain.receipt.ReceiptEntry;

import java.math.BigDecimal;

public class FifteenPercentDiscount extends Discount {

    public FifteenPercentDiscount() {
        super(BigDecimal.valueOf(0.85));
    }

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
