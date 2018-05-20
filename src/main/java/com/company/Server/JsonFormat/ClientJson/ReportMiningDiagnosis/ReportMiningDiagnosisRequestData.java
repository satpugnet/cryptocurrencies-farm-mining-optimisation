package com.company.Server.JsonFormat.ClientJson.ReportMiningDiagnosis;

import com.company.Server.JsonFormat.General.GPU.GPU;
import com.company.Server.JsonFormat.General.MinedCurrencyShortName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReportMiningDiagnosisRequestData {
    private GPU gpu;
    private MinedCurrencyShortName currency;
    private float hashRate;

}
