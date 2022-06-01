package com.example.carcompare.controller;

import com.example.carcompare.enums.Category;
import com.example.carcompare.model.ModelVariant;
import com.example.carcompare.services.ModelVariantService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/modelVariant")
public class ModelVariantController {

    @Autowired
    ModelVariantService modelVariantService;

    @PostMapping
    @ApiOperation(value = "Add a model variant into the system")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful Response", response = ModelVariant.class)
    })
    public ResponseEntity<?> addModelVariant(@ApiParam(value = "Model Varient Object", required = true) @RequestBody ModelVariant modelVariant) {
        return ResponseEntity.ok(modelVariantService.saveModelVariant(modelVariant));
    }

    @GetMapping
    @ApiOperation(value = "Get All model variants present in the system")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful Response", response = List.class)
    })
    public ResponseEntity<?> getAllModelVariant(){
        return ResponseEntity.ok(modelVariantService.getAllModelVariants());
    }

    @GetMapping("/{modelVariantId}")
    @ApiOperation(value = "Get model variant with given variant id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful Response", response = ModelVariant.class)
    })
    public ResponseEntity<?> getModelVariant(@ApiParam(value = "Model Variant Id",required = true) @PathVariable("modelVariantId") int modelVariantId){
            return ResponseEntity.ok(modelVariantService.getModelVariantById(modelVariantId));
    }

    @GetMapping("/brand/{brand}")
    @ApiOperation(value = "Get model variant with given brand")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful Response", response = List.class)
    })
    public ResponseEntity<?> getModelVariantByBrand(@ApiParam(value = "Brand name", required = true) @PathVariable("brand") String brandName) {
        return ResponseEntity.ok(modelVariantService.getAllModelVariantsByBrand(brandName));
    }

    @GetMapping("/model/{modelName}")
    @ApiOperation(value = "Get model variant with given model")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful Response", response = List.class)
    })
    public ResponseEntity<?> getModelVariantByModelName(@ApiParam(value = "model name", required = true) @PathVariable("modelName") String modelName) {
        return ResponseEntity.ok(modelVariantService.getAllModelVariantsByModelName(modelName));
    }

    @GetMapping("/category/{category}")
    @ApiOperation(value = "Get model variant with given category")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful Response", response = List.class)
    })
    public ResponseEntity<?> getModelVariantByModelName(@ApiParam(value = "model category", required = true) @PathVariable("category") Category category) {
        return ResponseEntity.ok(modelVariantService.getAllModelVariantByCategory(category));
    }
}
