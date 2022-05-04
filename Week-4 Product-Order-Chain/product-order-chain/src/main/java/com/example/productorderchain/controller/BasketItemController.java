package com.example.productorderchain.controller;


import com.example.productorderchain.core.utilities.Result;
import com.example.productorderchain.core.utilities.SuccessDataResult;
import com.example.productorderchain.dto.process.create.CreateBasketItemRequestDTO;
import com.example.productorderchain.dto.process.get.GetBasketItemResponseDTO;
import com.example.productorderchain.service.abstracts.BasketItemService;
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
    public ResponseEntity<?> createBasketItem(@RequestBody CreateBasketItemRequestDTO createBasketItemRequestDTO) {
        createBasketItemValidator.validate(createBasketItemRequestDTO);
        Result result= basketItemService.createBasketItem(createBasketItemRequestDTO);
        return ResponseEntity.ok().body(result.getMessage());
    }

    @GetMapping
    public ResponseEntity<SuccessDataResult<Collection<GetBasketItemResponseDTO>>> getAllBasketItem() {
        return ResponseEntity.ok(new SuccessDataResult<>(basketItemService.getAllBasketItems(),"All Basket items listed succesfully"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SuccessDataResult<GetBasketItemResponseDTO>> getBasketItem(@PathVariable Long id) {
        basketItemIdValidator.validate(id);
        return ResponseEntity.ok(basketItemService.getBasketItem(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBasketItem(@PathVariable Long id,
                                           @RequestParam(name = "hardDelete", required = false) boolean hardDelete) {
        basketItemIdValidator.validate(id);
        basketItemService.deleteBasketItem(id,hardDelete);
        return ResponseEntity.ok().body(new Result(true,"BasketItem Deleted Successfully"));
    }



}
