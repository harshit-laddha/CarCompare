package com.example.carcompare.controller;

import com.example.carcompare.enums.Category;
import com.example.carcompare.enums.DriveType;
import com.example.carcompare.enums.FuelType;
import com.example.carcompare.enums.TransmissionType;
import com.example.carcompare.model.Dimension;
import com.example.carcompare.model.Engine;
import com.example.carcompare.model.ModelVariant;
import com.example.carcompare.repos.ModelVariantRepository;
import com.example.carcompare.services.ModelVariantService;
import com.example.carcompare.services.SuggestionService;
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
@WebMvcTest(value = BrandController.class)
public class BrandControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ModelVariantService modelVariantService;

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
    public void getAllBrands() throws Exception {
        List<String> brands = new ArrayList<>();
        brands.add(modelVariant1.getBrand());

        Mockito.when(modelVariantService.getAllBrands()).thenReturn(brands);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/brands").accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        List<String> actualResult = objectMapper.readValue(result.getResponse().getContentAsString(), List.class);
        Assert.assertTrue(brands.size() == actualResult.size());
    }
}
