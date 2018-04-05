package Server.AccessSecurity;

import static Server.AccessSecurity.AuthorisedRequests.OptimalCryptoMining;

public class RequestAuthorisation {

    public static boolean hasAuthorisedId(String userEmail, AuthorisedRequests requestType) {
        if(userEmail.equals("saturnin.13@hotmail.fr") && requestType == OptimalCryptoMining){
            return true;
        }
        return false;
    }
}
