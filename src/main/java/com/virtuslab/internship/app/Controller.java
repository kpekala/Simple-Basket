package com.virtuslab.internship.app;

import com.virtuslab.internship.domain.basket.Basket;
import com.virtuslab.internship.domain.receipt.Receipt;
import com.virtuslab.internship.domain.receipt.ReceiptGenerator;
import com.virtuslab.internship.model.BasketRequestModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @PostMapping("/basket")
    public ResponseEntity<Receipt> index(@RequestBody BasketRequestModel basketModel) {
        if (basketModel == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Basket basket = basketModel.transform();
        Receipt receipt = new ReceiptGenerator().generate(basket);

        return new ResponseEntity<>(receipt, HttpStatus.OK);
    }

}