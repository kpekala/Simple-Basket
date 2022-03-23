package com.virtuslab.internship.discount;

import com.virtuslab.internship.domain.discount.FifteenPercentDiscount;
import com.virtuslab.internship.domain.product.ProductDb;
import com.virtuslab.internship.domain.receipt.Receipt;
import com.virtuslab.internship.domain.receipt.ReceiptEntry;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FifteenPercentDiscountTest {
    @Test
    void shouldApply15PercentDiscountWhenGrainProductNumberIsAtLeast3() {
        // Given
        var productDb = new ProductDb();
        var bread = productDb.getProduct("Bread");
        List<ReceiptEntry> receiptEntries = new ArrayList<>();
        receiptEntries.add(new ReceiptEntry(bread, 3));

        var receipt = new Receipt(receiptEntries);
        var discount = new FifteenPercentDiscount();
        var expectedTotalPrice = bread.price()
                .multiply(BigDecimal.valueOf(3))
                .multiply(BigDecimal.valueOf(0.85));

        // When
        var receiptAfterDiscount = discount.apply(receipt);

        // Then
        assertEquals(expectedTotalPrice, receiptAfterDiscount.totalPrice());
        assertEquals(1, receiptAfterDiscount.discounts().size());
    }

    @Test
    void shouldNotApply15PercentDiscountWhenGrainProductNumberIsBelow3() {
        // Given
        var productDb = new ProductDb();
        var bread = productDb.getProduct("Bread");
        List<ReceiptEntry> receiptEntries = new ArrayList<>();
        receiptEntries.add(new ReceiptEntry(bread, 1));

        var receipt = new Receipt(receiptEntries);
        var discount = new FifteenPercentDiscount();
        var expectedTotalPrice = bread.price();

        // When
        var receiptAfterDiscount = discount.apply(receipt);

        // Then
        assertEquals(expectedTotalPrice, receiptAfterDiscount.totalPrice());
        assertEquals(0, receiptAfterDiscount.discounts().size());
    }
}
