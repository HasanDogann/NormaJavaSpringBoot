package com.example.productorderchain.controller;

import com.example.productorderchain.core.utilities.Result;
import com.example.productorderchain.core.utilities.SuccessDataResult;
import com.example.productorderchain.dto.process.CreateBasketItemRequestDTO;
import com.example.productorderchain.dto.process.CreateBasketRequestDTO;
import com.example.productorderchain.dto.process.GetBasketItemResponseDTO;
import com.example.productorderchain.dto.process.GetBasketResponseDTO;
import com.example.productorderchain.service.BasketItemService;
import com.example.productorderchain.service.BasketService;
import com.example.productorderchain.validator.Validator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("api/baskets")
public class BasketController {

    private final Validator<CreateBasketRequestDTO> createBasketValidator;

    @Qualifier("basketIDQ")
    private final Validator<Long> basketIdValidator;

    private final BasketService basketService;

    public BasketController(Validator<CreateBasketRequestDTO> createBasketValidator, @Qualifier("basketIDQ") Validator<Long> basketIdValidator, BasketService basketService) {
        this.createBasketValidator = createBasketValidator;
        this.basketIdValidator = basketIdValidator;
        this.basketService = basketService;
    }


    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody CreateBasketRequestDTO createBasketRequestDTO) {
        createBasketValidator.validate(createBasketRequestDTO);
        Result result= basketService.addBasketItem(createBasketRequestDTO);
        return ResponseEntity.ok().body(result.getMessage());
    }

    @GetMapping
    public ResponseEntity<SuccessDataResult<Collection<GetBasketResponseDTO>>> getAllProducts() {
        return ResponseEntity.ok(basketService.getAllBasketItems());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SuccessDataResult<GetBasketResponseDTO>> getProduct(@PathVariable Long id) {
        basketIdValidator.validate(id);
        return ResponseEntity.ok(new SuccessDataResult<>(basketService.getBasketItem(id),"Basket is listed"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id,
                                           @RequestParam(name = "hardDelete", required = false) boolean hardDelete) {
        basketIdValidator.validate(id);
        Result result = basketService.deleteBasketItem(id,hardDelete);
        return ResponseEntity.ok().body(result.getMessage());
    }










}
