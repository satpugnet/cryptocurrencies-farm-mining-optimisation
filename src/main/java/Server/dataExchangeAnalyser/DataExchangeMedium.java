package Server.dataExchangeAnalyser;

import java.util.List;
import java.util.Map;

public interface DataExchangeMedium {
    public String getUserId();
    public String getOS();
    public List<Map<String,String>> getCurrencyBenchMarking();
}
