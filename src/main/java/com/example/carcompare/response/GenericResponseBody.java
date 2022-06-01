package com.example.carcompare.response;

import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GenericResponseBody<T> implements Serializable {
    T response;
    ResultInfo resultInfo;

}
