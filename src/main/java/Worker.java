import config.Configurator;
import server.PSServer;

/**
 * Created by wangwei on 17-7-28.
 */
public class Worker {
    public static void main(String[] args){
        PSServer psServer = null;
        try {
            psServer = PSServer.newInstance();
            psServer.bind(Configurator.getServerHost(),Configurator.getServerPort());
            psServer.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}