package com.virtuslab.internship.model;

import com.virtuslab.internship.domain.basket.Basket;
import com.virtuslab.internship.domain.product.ProductDb;

import java.lang.reflect.Array;

public record BasketRequestModel(ReceiptEntryRequestModel[] receiptEntryModels) {
    public Basket transform(){
        Basket basket = new Basket();
        ProductDb productDb = new ProductDb();
        for (ReceiptEntryRequestModel receiptEntryModel: receiptEntryModels){
            for(int i=0; i< receiptEntryModel.quantity(); i++){
                basket.addProduct(productDb.getProduct(receiptEntryModel.name()));
            }
        }
        return basket;
    }
}
