package com.company.Server.JsonFormat.MiningDiagnosis;

import lombok.Data;

@Data
public class MiningDiagnosisProperties {
    private String userEmail;
    private String workerName;
    private String currency;
    private float hashrate;
}
