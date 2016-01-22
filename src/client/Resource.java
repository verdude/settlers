/**
 * 
 */
package client;

/**
 * @author santi
 * Wrapper for type of resource.
 */
public class Resource {
	
	private String type; // [ore || brick || wood || wool || wheat]

	/**
	 * 
	 */
	public Resource(String type) {
		// TODO Auto-generated constructor stub
		this.type = type;
	}

	public String getType() {return type;}

	public void setType(String type) {this.type = type;}

}
