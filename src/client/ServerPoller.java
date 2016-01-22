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

	private IProxy proxy;
	/**
	 * Takes in a proxy in order to call the methods to check the 
	 * ServerModel's version and to retrieve it if needed in order to
	 * update.
	 * @param An IProxy to be used to query the server for needed updates
	 * @pre proxy is not null
	 * @post The proxy will be loaded and the other methods in ServerPoller will be callable after creation of a ServerPoller instance.
	 */
	public ServerPoller(IProxy proxy) {
		this.proxy = proxy;
	}

	/**
	 * 
	 * @pre None
	 * @post The server's model number is different if true, false otherwise
	 * @return Whether the local ClientModel needs an update
	 */
	public boolean needUpdate() {
		return false;
	}
	
	/**
	 * The update method will use the proxy to get the latest ServerModel
	 * and loads it into the ClientModel locally
	 * @pre needUdate() returns true
	 * @post The local model class is replaced with a clone of the model class on the server
	 */
	public void update() {
		
	}
	
}
