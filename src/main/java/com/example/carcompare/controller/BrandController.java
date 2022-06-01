package com.example.carcompare.controller;

import com.example.carcompare.enums.CarCompareResultCode;
import com.example.carcompare.response.GenericResponseBody;
import com.example.carcompare.response.ResultInfo;
import com.example.carcompare.services.ModelVariantService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/brands")
public class BrandController {

    Logger logger = LoggerFactory.getLogger(BrandController.class);

    @Autowired
    ModelVariantService modelVariantService;

    @GetMapping
    @ApiOperation(value = "Get All brands present in the system")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful Response", response = GenericResponseBody.class)
    })
    public GenericResponseBody getAllBrands(){
        GenericResponseBody<List> response = new GenericResponseBody<>();
        ResultInfo resultInfo;
        List<String> brands = null;
        try {
            brands = modelVariantService.getAllBrands();
            resultInfo = new ResultInfo(CarCompareResultCode.SUCCESS);
        } catch (Exception e) {
            logger.error("Error Occured while getting all brands");
            resultInfo = new ResultInfo(CarCompareResultCode.FAIL);
        }
        response.setResponse(brands);
        response.setResultInfo(resultInfo);
        return response;
    }
}
