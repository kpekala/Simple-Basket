package com.virtuslab.internship.discount;

import com.virtuslab.internship.receipt.Receipt;

import java.math.BigDecimal;

public abstract class Discount {

    public Receipt apply(Receipt receipt) {
        if (shouldApply(receipt)) {
            var totalPrice = receipt.totalPrice().multiply(BigDecimal.valueOf(0.9));
            var discounts = receipt.discounts();
            discounts.add(getName());
            return new Receipt(receipt.entries(), discounts, totalPrice);
        }
        return receipt;
    }

    protected abstract String getName();

    protected abstract boolean shouldApply(Receipt receipt);
}
