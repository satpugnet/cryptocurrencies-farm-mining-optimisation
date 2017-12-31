package Server.AccessSecurity;

import static Server.AccessSecurity.AuthorisedRequests.OptimalCryptoMining;

public class RequestAuthorisation {

    public static boolean hasAuthorisedId(String userId, AuthorisedRequests requestType) {
        if(userId.equals("test") && requestType == OptimalCryptoMining){
            return true;
        }
        return false;
    }
}
