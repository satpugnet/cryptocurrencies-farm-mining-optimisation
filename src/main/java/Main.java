import Server.HttpRequestHandling;
import org.apache.log4j.Logger;

import java.io.IOException;

// TODO: learn about Spring and add to the app
// TODO: add dependencies to maven
// TODO: set heroku
public class Main {

    private final static Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) throws IOException {
        String portOption = args[0];
        int portValue = 8000;
        if(portOption != null && portOption.equals("--port")) {
            portValue = Integer.parseInt(args[1]);
        } else {
            logger.warn("No port given in the command line parameters, using default of " + portValue);
        }
        HttpRequestHandling.startServer(portValue);
    }
}
