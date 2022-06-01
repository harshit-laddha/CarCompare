package com.example.carcompare.controller;

import com.example.carcompare.enums.CarCompareResultCode;
import com.example.carcompare.model.ModelVariant;
import com.example.carcompare.response.GenericResponseBody;
import com.example.carcompare.response.ResultInfo;
import com.example.carcompare.services.SuggestionService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/suggest")
public class SuggestionController {

    Logger logger = LoggerFactory.getLogger(SuggestionController.class);

    @Autowired
    SuggestionService suggestionService;

    @GetMapping("/{modelVariantId}")
    @ApiOperation(value = "Get All models variant similar to given variant id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful Response", response = GenericResponseBody.class)
    })
    public GenericResponseBody getSuggestionForModelId(@ApiParam(value = "Model Variant Id",required = true) @PathVariable("modelVariantId") int modelVariantId){
        GenericResponseBody<List> response = new GenericResponseBody<>();
        ResultInfo resultInfo;
        List<ModelVariant> suggestions = null;
        try {
            suggestions = suggestionService.getSuggestionForModelId(modelVariantId);
            resultInfo = new ResultInfo(CarCompareResultCode.SUCCESS);
        } catch (Exception e) {
            logger.error("Error Occured while getting all suggestions of modelVariantId : {}",modelVariantId);
            resultInfo = new ResultInfo(CarCompareResultCode.FAIL);
        }
        response.setResponse(suggestions);
        response.setResultInfo(resultInfo);
        return response;
    }
}
