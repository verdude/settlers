/**
 * 
 */
package model;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author S Jacob Powell
 *	The needUpdate method of this class should be called periodically
 *	in order to see if the server's model has changed.
 *	If it returns true, then the update method should be called
 *	so that the local ClientModel will be the same as the one on
 *	the server. It uses the IProxy to do that.
 *
 */
public class ServerPoller {

	/**
	 * To be used to thread and frequently poll the server.
	 */
	private Timer timer = new Timer();
	/**
	 * The interval at which the needUpdate function will be called.
	 */
	private final int pollInterval = 3000;
	
	private IProxy proxy;
	
	private static ServerPoller SINGLETON;
	
	public static ServerPoller getSingleton(IProxy proxy) {
		if(SINGLETON == null) {
			SINGLETON = new ServerPoller(proxy);
		}
		return SINGLETON;
	}
	
	/**
	 * Takes in a proxy in order to call the methods to check the 
	 * ServerModel's version and to retrieve it if needed in order to
	 * update.
	 * @param proxy, An IProxy to be used to query the server for needed updates
	 * @pre proxy is not null
	 * @post The proxy will be loaded and the other methods in ServerPoller will be callable after creation of a ServerPoller instance.
	 */
	private ServerPoller(IProxy proxy) {
		this.proxy = proxy;
		timer = new Timer();
		// start the poller
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
					needUpdate();
				} catch (ServerException e) {
					e.printStackTrace();
					System.out.println("[Error] - Checking for update Failed...(ServerPoller).");
				}
            }
        }, 0, pollInterval);
	}

	/**
	 * Used to check if the client's model needs an update
	 * @pre None
	 * @post The server's model number is different if true, false otherwise
	 * @return Whether the local ClientModel needs an update
	 * @throws ServerException when this function fails when it shouldn't
	 */
	private void needUpdate() throws ServerException {
		int version = ClientFacade.getSingleton(proxy).getVersion();
		String response = proxy.gamesModel(Integer.toString(version));
		if (!response.equals("true")) {
			update(response);
		}
	}
	
	/**
	 * The update method will use the proxy to get the latest ServerModel
	 * and loads it into the ClientModel locally
	 * @param jsonModel String client model as json
	 * @pre needUdate() returns true
	 * @post The local model class is replaced with a clone of the model class on the server
	 * @throws ServerException when this function fails when it shouldn't
	 */
	private void update(String jsonModel) throws ServerException{
		ClientModel newModel = (ClientModel) Converter.deserialize(jsonModel);
		ClientFacade facade = ClientFacade.getSingleton(proxy);
		if (newModel.getVersion() > facade.getVersion()) {
			// means the version one the client is old
			facade.updateModel(newModel);
		}
	}
}
