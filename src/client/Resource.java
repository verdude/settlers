/**
 * 
 */
package client;

/**
 * @author santi
 * Wrapper for type of resource.
 */
public class Resource {
	
	/*
	 * The type of Resource Card maps to the ResourceEnum
	 */
	private int type;

	/**
	 * @param type = [ 0 || 1 || 2 || 3 || 4 ]
	 */
	public Resource(int type) {
		this.type = type;
	}

	public int getType() {return type;}

	public void setType(int type) {this.type = type;}

}
