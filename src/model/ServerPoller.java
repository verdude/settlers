/**
 * 
 */
package model;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
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
	 * This will be used to tell the Timer what task to perform.
	 */
	private TimerTask timerTask;
	/**
	 * The interval at which the needUpdate function will be called.
	 */
	private final int pollInterval = 3000;
	
	private IProxy proxy;
	/**
	 * Takes in a proxy in order to call the methods to check the 
	 * ServerModel's version and to retrieve it if needed in order to
	 * update.
	 * @param proxy, An IProxy to be used to query the server for needed updates
	 * @pre proxy is not null
	 * @post The proxy will be loaded and the other methods in ServerPoller will be callable after creation of a ServerPoller instance.
	 */
	public ServerPoller(IProxy proxy) {
		this.proxy = proxy;
		timer = new Timer();
		// start the poller
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
					if (needUpdate()) {
						update();
					}
				} catch (ServerException e) {
					e.printStackTrace();
					System.out.println("[Error] - Checking for update Failed...(ServerPoller).");
				}
            }
        }, 0, pollInterval);
	}

	/**
	 * 
	 * @pre None
	 * @post The server's model number is different if true, false otherwise
	 * @return Whether the local ClientModel needs an update
	 * @throws ServerException when this function fails when it shouldn't
	 */
	private boolean needUpdate() throws ServerException {
		int version = 0;//ClientFacade.getSingleton().getVersion();
		if (/*proxy.getVersion > clientFacade.getVersion*/) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * The update method will use the proxy to get the latest ServerModel
	 * and loads it into the ClientModel locally
	 * @pre needUdate() returns true
	 * @post The local model class is replaced with a clone of the model class on the server
	 * @throws ServerException when this function fails when it shouldn't
	 */
	private void update() throws ServerException{
		int version = 0;//ClientFacade.getSingleton().getVersion();
		PrintWriter out;
		try {
			// write the model to the file
			out = new PrintWriter("json/model.json");
			out.print(proxy.gamesModel(Integer.toString(version)));
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
