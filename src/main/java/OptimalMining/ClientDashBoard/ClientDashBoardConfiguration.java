package OptimalMining.ClientDashBoard;

import Database.DatabaseAccess;
import lombok.Data;

@Data
public class ClientDashBoardConfiguration {

    private boolean activateMining;

    public ClientDashBoardConfiguration(String userEmail) {
        activateMining = convertActivateMiningValue(DatabaseAccess.getConfigField(userEmail, "activate_mining"));
    }

    private boolean convertActivateMiningValue(String value) {
        if(value.equals("t")) {
            return true;
        } else if(value.equals("f")) {
            return false;
        }
        return false;
    }
}
