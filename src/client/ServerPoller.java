/**
 * 
 */
package client;

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
	 * Takes in a proxy in order to call the methods to check the 
	 * ServerModel's version and to retrieve it if needed in order to
	 * update.
	 * @param An IProxy to be used to query the server for needed updates
	 */
	public ServerPoller(IProxy proxy) {

	}

	/**
	 * 
	 * @return Whether the local ClientModel needs an update
	 */
	public boolean needUpdate() {
		return false;
	}
	
	/**
	 * The update method will use the proxy to get the latest ServerModel
	 * and loads it into the ClientModel locally
	 */
	public void update() {
		
	}
	
}
