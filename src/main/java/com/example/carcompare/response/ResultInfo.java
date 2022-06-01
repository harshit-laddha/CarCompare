package com.example.carcompare.response;

import com.example.carcompare.enums.CarCompareResultCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultInfo implements Serializable {
    private String resultCode;
    private String resultStatus;
    private String messaage;

    public ResultInfo(CarCompareResultCode carCompareResultCode) {
        this.resultCode = carCompareResultCode.getResultCode();
        this.resultStatus = carCompareResultCode.getStatus();
        this.messaage = carCompareResultCode.getMessage();
    }
}
