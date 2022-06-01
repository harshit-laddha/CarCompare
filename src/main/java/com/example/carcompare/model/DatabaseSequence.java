package com.example.carcompare.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "sequence")
@Data
@AllArgsConstructor
public class DatabaseSequence {

    @Id
    private String id;

    private int seq;
}
