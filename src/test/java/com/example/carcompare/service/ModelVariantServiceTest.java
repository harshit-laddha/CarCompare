package com.example.carcompare.service;

import com.example.carcompare.enums.Category;
import com.example.carcompare.enums.DriveType;
import com.example.carcompare.enums.FuelType;
import com.example.carcompare.enums.TransmissionType;
import com.example.carcompare.model.Dimension;
import com.example.carcompare.model.Engine;
import com.example.carcompare.model.ModelVariant;
import com.example.carcompare.repos.ModelVariantRepository;
import com.example.carcompare.services.ModelVariantService;
import com.example.carcompare.services.SequenceGeneratorService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ModelVariantServiceTest {

    @Autowired
    ModelVariantService modelVariantService;

    @MockBean
    MongoTemplate mongoTemplate;

    @MockBean
    ModelVariantRepository modelVariantRepository;

    @MockBean
    SequenceGeneratorService sequenceGeneratorService;

    @Test
    public void saveModelVariantTest() throws Exception {
        Engine engine1 = new Engine("SMARTSTREAM G1.5",1497, FuelType.PETROL, TransmissionType.MANUAL,
                "113.43bhp@6300rpm","144nm@4500rpm",4, DriveType.FWD,16.8);
        Dimension dimension1 = new Dimension(4315,1800,1645,2610,560,2550,5);
        ModelVariant modelVariant1 = new ModelVariant(1, "Seltos","Kia", Category.COMPACT_SUV,"Seltos HTK", 11.25F,"image.png",engine1,dimension1);

        Mockito.when(sequenceGeneratorService.getNextSequenceId(Mockito.any(ModelVariant.class))).thenReturn(1);
        Mockito.when(mongoTemplate.save(Mockito.any(ModelVariant.class))).thenReturn(modelVariant1);

        ModelVariant actualResult = modelVariantService.saveModelVariant(modelVariant1);

        Assert.assertEquals(modelVariant1,actualResult);
    }
}
