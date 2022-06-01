package com.example.carcompare.controller;

import com.example.carcompare.services.SuggestionService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/suggest")
public class SuggestionController {

    @Autowired
    SuggestionService suggestionService;

    @GetMapping("/{modelVariantId}")
    @ApiOperation(value = "Get All models variant similar to given variant id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful Response", response = List.class)
    })
    public ResponseEntity<?> getSuggestionForModelId(@ApiParam(value = "Model Variant Id",required = true) @PathVariable("modelVariantId") int modelVariantId){
        return ResponseEntity.ok(suggestionService.getSuggestionForModelId(modelVariantId));
    }
}
