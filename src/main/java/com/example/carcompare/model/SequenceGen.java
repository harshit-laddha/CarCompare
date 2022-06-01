package com.example.carcompare.model;

import org.springframework.data.annotation.Transient;

public interface SequenceGen {
    @Transient
    public String getSequenceName();
}
