package Server.RequestHandler;

import OptimalMining.MiningConfig.MiningConfiguration;
import Server.dataExchangeAnalyser.DataExchangeMedium;

public interface Handler {
    public MiningConfiguration handle(DataExchangeMedium requestData);
}
