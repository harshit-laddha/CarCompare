package com.example.carcompare.controller;

import com.example.carcompare.enums.CarCompareResultCode;
import com.example.carcompare.enums.Category;
import com.example.carcompare.model.ModelVariant;
import com.example.carcompare.response.GenericResponseBody;
import com.example.carcompare.response.ResultInfo;
import com.example.carcompare.services.ModelVariantService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/modelVariant")
public class ModelVariantController {

    Logger logger = LoggerFactory.getLogger(ModelVariantController.class);

    @Autowired
    ModelVariantService modelVariantService;

    @PostMapping
    @ApiOperation(value = "Add a model variant into the system")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful Response", response = GenericResponseBody.class)
    })
    public GenericResponseBody addModelVariant(@ApiParam(value = "Model Varient Object", required = true) @RequestBody ModelVariant modelVariant) {
        GenericResponseBody<ModelVariant> response = new GenericResponseBody<>();
        ResultInfo resultInfo;
        ModelVariant result = null;
        if(modelVariant == null) {
            logger.error("Parameters Invalid..");
            response.setResultInfo(new ResultInfo(CarCompareResultCode.PARAM_ILLEGAL));
            return response;
        }
        try {
            result = modelVariantService.saveModelVariant(modelVariant);
            resultInfo = new ResultInfo(CarCompareResultCode.SUCCESS);
        } catch (Exception e) {
            logger.error("Error Occured saving model in db {}", modelVariant);
            resultInfo = new ResultInfo(CarCompareResultCode.FAIL);
        }
        response.setResponse(result);
        response.setResultInfo(resultInfo);
        return response;
    }

    @GetMapping
    @ApiOperation(value = "Get All model variants present in the system")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful Response", response = GenericResponseBody.class)
    })
    public GenericResponseBody getAllModelVariant(){
        GenericResponseBody<List> response = new GenericResponseBody<>();
        ResultInfo resultInfo;
        List<ModelVariant> models = null;
        try {
            models = modelVariantService.getAllModelVariants();
            resultInfo = new ResultInfo(CarCompareResultCode.SUCCESS);
        } catch (Exception e) {
            logger.error("Error Occured while getting all models");
            resultInfo = new ResultInfo(CarCompareResultCode.FAIL);
        }
        response.setResponse(models);
        response.setResultInfo(resultInfo);
        return response;
    }

    @GetMapping("/{modelVariantId}")
    @ApiOperation(value = "Get model variant with given variant id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful Response", response = GenericResponseBody.class)
    })
    public GenericResponseBody getModelVariant(@ApiParam(value = "Model Variant Id",required = true) @PathVariable("modelVariantId") int modelVariantId){
        GenericResponseBody<ModelVariant> response = new GenericResponseBody<>();
        ResultInfo resultInfo;
        ModelVariant model = null;
        try {
            model = modelVariantService.getModelVariantById(modelVariantId);
            resultInfo = new ResultInfo(CarCompareResultCode.SUCCESS);
        } catch (Exception e) {
            logger.error("Error Occured while getting model from id {}",modelVariantId);
            resultInfo = new ResultInfo(CarCompareResultCode.FAIL);
        }
        response.setResponse(model);
        response.setResultInfo(resultInfo);
        return response;
    }

    @GetMapping("/brand/{brand}")
    @ApiOperation(value = "Get model variant with given brand")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful Response", response = GenericResponseBody.class)
    })
    public GenericResponseBody getModelVariantByBrand(@ApiParam(value = "Brand name", required = true) @PathVariable("brand") String brandName) {
        GenericResponseBody<List> response = new GenericResponseBody<>();
        ResultInfo resultInfo;
        List<ModelVariant> models = null;
        try {
            models = modelVariantService.getAllModelVariantsByBrand(brandName);
            resultInfo = new ResultInfo(CarCompareResultCode.SUCCESS);
        } catch (Exception e) {
            logger.error("Error Occured while getting all models from brand : {}",brandName);
            resultInfo = new ResultInfo(CarCompareResultCode.FAIL);
        }
        response.setResponse(models);
        response.setResultInfo(resultInfo);
        return response;
    }

    @GetMapping("/model/{modelName}")
    @ApiOperation(value = "Get model variant with given model")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful Response", response = GenericResponseBody.class)
    })
    public GenericResponseBody getModelVariantByModelName(@ApiParam(value = "model name", required = true) @PathVariable("modelName") String modelName) {
        GenericResponseBody<List> response = new GenericResponseBody<>();
        ResultInfo resultInfo;
        List<ModelVariant> models = null;
        try {
            models = modelVariantService.getAllModelVariantsByModelName(modelName);
            resultInfo = new ResultInfo(CarCompareResultCode.SUCCESS);
        } catch (Exception e) {
            logger.error("Error Occured while getting all models of {}",modelName);
            resultInfo = new ResultInfo(CarCompareResultCode.FAIL);
        }
        response.setResponse(models);
        response.setResultInfo(resultInfo);
        return response;
    }

    @GetMapping("/category/{category}")
    @ApiOperation(value = "Get model variant with given category")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful Response", response = GenericResponseBody.class)
    })
    public GenericResponseBody getModelVariantByModelName(@ApiParam(value = "model category", required = true) @PathVariable("category") Category category) {
        GenericResponseBody<List> response = new GenericResponseBody<>();
        ResultInfo resultInfo;
        List<ModelVariant> models = null;
        try {
            models = modelVariantService.getAllModelVariantByCategory(category);
            resultInfo = new ResultInfo(CarCompareResultCode.SUCCESS);
        } catch (Exception e) {
            logger.error("Error Occured while getting all models of category : {}",category);
            resultInfo = new ResultInfo(CarCompareResultCode.FAIL);
        }
        response.setResponse(models);
        response.setResultInfo(resultInfo);
        return response;
    }
}
