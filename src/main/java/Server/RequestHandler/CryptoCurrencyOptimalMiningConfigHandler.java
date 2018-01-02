package Server.RequestHandler;

import OptimalMining.ClientConfig.ClientConfiguration;
import OptimalMining.MiningConfig.MiningConfiguration;
import OptimalMining.OptimalMiningConfigCalculation;
import Server.dataExchangeAnalyser.DataExchangeMedium;

public class CryptoCurrencyOptimalMiningConfigHandler implements Handler {

    //TODO: implement this
    @Override
    public MiningConfiguration handle(DataExchangeMedium requestData) {
        // TODO: add dashboard interface (electricity cost and consumption)
//        ClientDashBoardConfiguration clientDashBoardConfiguration = new ClientDashBoardConfiguration();
        ClientConfiguration clientConfig = new ClientConfiguration(requestData);

        return OptimalMiningConfigCalculation.calculateOptimalConfig(clientConfig);
    }
}
