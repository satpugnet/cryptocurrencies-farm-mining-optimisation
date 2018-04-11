package com.company.Server.dataExchangeAnalyser;

import java.util.List;
import java.util.Map;

public interface DataExchangeMedium {
    public String getUserEmail();
    public String getOS();
    public List<Map<String,String>> getCurrencyBenchMarking();
}
