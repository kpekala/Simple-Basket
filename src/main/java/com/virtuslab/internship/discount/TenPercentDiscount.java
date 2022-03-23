package com.virtuslab.internship.discount;

import com.virtuslab.internship.receipt.Receipt;

import java.math.BigDecimal;

public class TenPercentDiscount extends Discount {

    public TenPercentDiscount() {
        super(BigDecimal.valueOf(0.9));
    }

    @Override
    protected String getName() {
        return "TenPercentDiscount";
    }

    @Override
    protected boolean shouldApply(Receipt receipt) {
        return receipt.totalPrice().compareTo(BigDecimal.valueOf(50)) > 0;
    }
}
