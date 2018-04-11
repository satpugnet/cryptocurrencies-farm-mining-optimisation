package com.company.Server.dataExchangeAnalyser;

import lombok.Data;

@Data
public class MiningDiagnosisProperties {

    private String userEmail;
    private String workerName;
    private String currency;
    private float hashrate;
}
