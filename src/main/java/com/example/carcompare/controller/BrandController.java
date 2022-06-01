package com.example.carcompare.controller;

import com.example.carcompare.services.ModelVariantService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/brands")
public class BrandController {

    @Autowired
    ModelVariantService modelVariantService;

    @GetMapping
    @ApiOperation(value = "Get All brands present in the system")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful Response", response = List.class)
    })
    public ResponseEntity<?> getAllBrands(){
        return ResponseEntity.ok(modelVariantService.getAllBrands());
    }
}
