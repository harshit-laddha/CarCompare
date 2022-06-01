package com.example.carcompare.controller;

import com.example.carcompare.model.ModelVariant;
import com.example.carcompare.services.CompareService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.QueryParam;

@RestController
@RequestMapping("/compare")
public class ComparisonController {

    @Autowired
    CompareService compareService;

    @PostMapping
    @ApiOperation(value = "Compare all the model with given model ids")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful Response", response = ModelVariant.class)
    })
    public ResponseEntity<?> addModelVariant(@ApiParam(value = "Model Varient Object", required = true) @RequestBody Integer[] modelVariantIds,
                                             @QueryParam("hideSimilarFeature") boolean hideSimilarFeature) {

        return ResponseEntity.ok(compareService.compareModels(modelVariantIds,hideSimilarFeature));
    }
}
