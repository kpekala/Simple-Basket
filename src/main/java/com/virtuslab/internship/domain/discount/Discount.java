package com.virtuslab.internship.domain.discount;

import com.virtuslab.internship.domain.receipt.Receipt;

import java.math.BigDecimal;

public abstract class Discount {

    private final BigDecimal percent;

    public Discount(BigDecimal percent){
        this.percent = percent;
    }

    public Receipt apply(Receipt receipt) {
        if (shouldApply(receipt)) {
            var totalPrice = receipt.totalPrice().multiply(percent);
            var discounts = receipt.discounts();
            discounts.add(getName());
            return new Receipt(receipt.entries(), discounts, totalPrice);
        }
        return receipt;
    }

    protected abstract String getName();

    protected abstract boolean shouldApply(Receipt receipt);
}
