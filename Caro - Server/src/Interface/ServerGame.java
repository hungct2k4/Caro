package Interface;

import Core.ServerManager;
import Core.VoiceChatServer;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author hungk
 */
public class ServerGame implements Observer {

    ServerManager mServerManager;

    public ServerGame() {
        mServerManager = new ServerManager(this);
        mServerManager.StartServer();
        
    }

    public static void main(String[] args) {
        new ServerGame();
        new VoiceChatServer();
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println(arg.toString());
    }

}
