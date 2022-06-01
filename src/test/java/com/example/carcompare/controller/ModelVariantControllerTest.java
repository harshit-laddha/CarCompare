package com.example.carcompare.controller;

import com.example.carcompare.enums.Category;
import com.example.carcompare.enums.DriveType;
import com.example.carcompare.enums.FuelType;
import com.example.carcompare.enums.TransmissionType;
import com.example.carcompare.model.Dimension;
import com.example.carcompare.model.Engine;
import com.example.carcompare.model.ModelVariant;
import com.example.carcompare.repos.ModelVariantRepository;
import com.example.carcompare.response.GenericResponseBody;
import com.example.carcompare.services.ModelVariantService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;


@RunWith(SpringRunner.class)
@WebMvcTest(value = ModelVariantController.class)
public class ModelVariantControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private ModelVariantService modelVariantService;

    @MockBean
    private ModelVariantRepository modelVariantRepository;

    ObjectMapper objectMapper = new ObjectMapper();

    Engine engine1 = new Engine("SMARTSTREAM G1.5",1497, FuelType.PETROL, TransmissionType.MANUAL,
            "113.43bhp@6300rpm","144nm@4500rpm",4, DriveType.FWD,16.8);
    Dimension dimension1 = new Dimension(4315,1800,1645,2610,560,2550,5);
    ModelVariant modelVariant1 = new ModelVariant(1, "Seltos","Kia", Category.COMPACT_SUV,"Seltos HTK", 11.25F,"image.png",engine1,dimension1);

    Engine engine2 = new Engine("SMARTSTREAM G1.2",1497, FuelType.DIESEL, TransmissionType.MANUAL,
            "113.43bhp@6300rpm","144nm@4500rpm",4, DriveType.FWD,16.8);
    Dimension dimension2 = new Dimension(4315,1800,1645,2610,560,2550,5);
    ModelVariant modelVariant2 = new ModelVariant(2, "Seltos","Kia", Category.COMPACT_SUV,"Seltos GTX Plus", 15.25F,"image.png",engine2,dimension2);

    @Test
    public void getModelVariantByIdTest() throws Exception {
        Mockito.when(modelVariantService.getModelVariantById(Mockito.anyInt())).thenReturn(modelVariant1);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/modelVariant/1").accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        ModelVariant actualResult = (ModelVariant) objectMapper.readValue(result.getResponse().getContentAsString(), GenericResponseBody.class).getResponse();
        Assert.assertEquals(modelVariant1,actualResult);
    }

    @Test
    public void getModelVariantByBrandTest() throws Exception {
        List<ModelVariant> kiaModels = new ArrayList<>();
        kiaModels.add(modelVariant1);
        kiaModels.add(modelVariant2);

        Mockito.when(modelVariantService.getAllModelVariantByCategory(Category.COMPACT_SUV)).thenReturn(kiaModels);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/modelVariant/category/COMPACT_SUV").accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        List<ModelVariant> actualResult = (List<ModelVariant>) objectMapper.readValue(result.getResponse().getContentAsString(), GenericResponseBody.class).getResponse();
        Assert.assertTrue(kiaModels.size() == actualResult.size());
    }
}
