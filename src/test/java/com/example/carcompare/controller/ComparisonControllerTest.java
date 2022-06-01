package com.example.carcompare.controller;

import com.example.carcompare.enums.Category;
import com.example.carcompare.enums.DriveType;
import com.example.carcompare.enums.FuelType;
import com.example.carcompare.enums.TransmissionType;
import com.example.carcompare.model.ComparisonReport;
import com.example.carcompare.model.Dimension;
import com.example.carcompare.model.Engine;
import com.example.carcompare.model.ModelVariant;
import com.example.carcompare.repos.ModelVariantRepository;
import com.example.carcompare.services.CompareService;
import com.example.carcompare.services.SuggestionService;
import com.example.carcompare.util.ComparisonReportUtil;
import com.example.carcompare.util.ReflectionUtil;
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

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@WebMvcTest(value = ComparisonController.class)
public class ComparisonControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CompareService compareService;

    @MockBean
    private ModelVariantRepository modelVariantRepository;

    ObjectMapper objectMapper = new ObjectMapper();

    Engine engine1 = new Engine("SMARTSTREAM G1.2",1497, FuelType.DIESEL, TransmissionType.MANUAL,
            "113.43bhp@6300rpm","144nm@4500rpm",4, DriveType.FWD,16.8);
    Dimension dimension1 = new Dimension(4315,1800,1645,2610,560,2550,5);
    ModelVariant modelVariant1 = new ModelVariant(1, "Seltos","Kia", Category.COMPACT_SUV,"Seltos GTX Plus", 15.25F,"image1.png",engine1,dimension1);

    Engine engine2 = new Engine("Turbo 1.2",1200, FuelType.DIESEL, TransmissionType.AUTOMATIC,
            "123.43bhp@6300rpm","154nm@4500rpm",4, DriveType.FWD,16.8);
    Dimension dimension2 = new Dimension(4400,1878,1650,2500,402,2550,5);
    ModelVariant modelVariant2 = new ModelVariant(2, "Creta","Hyundai", Category.COMPACT_SUV,"Creta GTX Plus", 14.37F,"image2.png",engine2,dimension2);

    @Test
    public void getComparisionReportHideSimilarFeatures() throws Exception {
        ComparisonReport report = new ComparisonReport();
        List<Object> modelVariants = Arrays.asList(modelVariant1, modelVariant2);
        ComparisonReportUtil.compareObjects(modelVariants,report,false);

        Integer[] modelVariantIds = new Integer[] {1,2};
        Mockito.when(compareService.compareModels(modelVariantIds,false)).thenReturn(report);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/compare").accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(modelVariantIds));

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        ComparisonReport actualReport = objectMapper.readValue(result.getResponse().getContentAsString(), ComparisonReport.class);
        Assert.assertEquals(report,actualReport);
    }
}
