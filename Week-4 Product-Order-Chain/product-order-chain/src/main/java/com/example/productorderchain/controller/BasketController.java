package com.example.productorderchain.controller;

import com.example.productorderchain.core.utilities.Result;
import com.example.productorderchain.core.utilities.SuccessDataResult;
import com.example.productorderchain.dto.process.create.CreateBasketRequestDTO;
import com.example.productorderchain.dto.process.get.GetBasketResponseDTO;
import com.example.productorderchain.model.Basket;
import com.example.productorderchain.service.abstracts.BasketService;
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
    public ResponseEntity<?> createBasket(@RequestBody CreateBasketRequestDTO createBasketRequestDTO) {
        createBasketValidator.validate(createBasketRequestDTO);
        Result result= basketService.addBasket(createBasketRequestDTO);
        return ResponseEntity.ok().body(result.getMessage());
    }

    @GetMapping
    public ResponseEntity<SuccessDataResult<Collection<GetBasketResponseDTO>>> getAllBaskets() {
        return ResponseEntity.ok(basketService.getAllBaskets());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SuccessDataResult<Basket>> getBasket(@PathVariable Long id) {
        basketIdValidator.validate(id);
        return ResponseEntity.ok(new SuccessDataResult<>(basketService.getBasket(id),"Basket is listed"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBasket(@PathVariable Long id,
                                           @RequestParam(name = "hardDelete", required = false) boolean hardDelete) {
        basketIdValidator.validate(id);
        Result result = basketService.deleteBasket(id,hardDelete);
        return ResponseEntity.ok().body(result.getMessage());
    }










}
