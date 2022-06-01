package com.example.carcompare.controller;

import com.example.carcompare.enums.CarCompareResultCode;
import com.example.carcompare.model.ComparisonReport;
import com.example.carcompare.request.ComparisonRequest;
import com.example.carcompare.response.GenericResponseBody;
import com.example.carcompare.response.ResultInfo;
import com.example.carcompare.services.CompareService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/compare")
public class ComparisonController {

    Logger logger = LoggerFactory.getLogger(ComparisonController.class);

    @Autowired
    CompareService compareService;

    @PostMapping
    @ApiOperation(value = "Compare all the model with given model ids")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful Response", response = GenericResponseBody.class)
    })
    public GenericResponseBody addModelVariant(@ApiParam(value = "Model Varient Object", required = true) @RequestBody ComparisonRequest comparisonRequest) {
        GenericResponseBody<ComparisonReport> response = new GenericResponseBody<>();
        ResultInfo resultInfo;
        ComparisonReport comparisonReport = null;
        try {
            comparisonReport = compareService.compareModels(comparisonRequest);
            resultInfo = new ResultInfo(CarCompareResultCode.SUCCESS);
        } catch (Exception e) {
            logger.error("Error Occured while comparing Models {} ",comparisonRequest);
            resultInfo = new ResultInfo(CarCompareResultCode.FAIL);
        }
        response.setResponse(comparisonReport);
        response.setResultInfo(resultInfo);
        return response;
    }
}
