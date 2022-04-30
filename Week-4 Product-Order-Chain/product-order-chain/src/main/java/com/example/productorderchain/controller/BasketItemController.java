package com.example.productorderchain.controller;


import com.example.productorderchain.core.utilities.Result;
import com.example.productorderchain.core.utilities.SuccessDataResult;
import com.example.productorderchain.dto.process.CreateBasketItemRequestDTO;
import com.example.productorderchain.dto.process.CreateProductRequestDTO;
import com.example.productorderchain.dto.process.GetBasketItemResponseDTO;
import com.example.productorderchain.dto.process.GetProductsResponseDTO;
import com.example.productorderchain.service.BasketItemService;
import com.example.productorderchain.validator.Validator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("api/basketitems")
public class BasketItemController {

    private final Validator<CreateBasketItemRequestDTO> createBasketItemValidator;

    @Qualifier("basketItemIDQ")
    private final Validator<Long> basketItemIdValidator;

    private final BasketItemService basketItemService;

    public BasketItemController(Validator<CreateBasketItemRequestDTO> createBasketItemValidator, @Qualifier("basketItemIDQ") Validator<Long> basketItemIdValidator, BasketItemService basketItemService) {
        this.createBasketItemValidator = createBasketItemValidator;
        this.basketItemIdValidator = basketItemIdValidator;
        this.basketItemService = basketItemService;
    }


    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody CreateBasketItemRequestDTO createBasketItemRequestDTO) {
        createBasketItemValidator.validate(createBasketItemRequestDTO);
        Result result= basketItemService.createBasketItem(createBasketItemRequestDTO);
        return ResponseEntity.ok().body(result.getMessage());
    }

    @GetMapping
    public ResponseEntity<SuccessDataResult<Collection<GetBasketItemResponseDTO>>> getAllProducts() {
        return ResponseEntity.ok(basketItemService.getAllBasketItems());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SuccessDataResult<GetBasketItemResponseDTO>> getProduct(@PathVariable Long id) {
        basketItemIdValidator.validate(id);
        return ResponseEntity.ok(basketItemService.getBasketItem(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id,
                                           @RequestParam(name = "hardDelete", required = false) boolean hardDelete) {
        basketItemIdValidator.validate(id);
        Result result = basketItemService.deleteBasketItem(id,hardDelete);
        return ResponseEntity.ok().body(result.getMessage());
    }



}
