package client.base;

import model.ClientModel;

/**
 * @author santi
 *
 */
public interface IObserver {

	/**
	 * Notify will make the changes in the controller that are necessary every time
	 * the model is updated
	 * @param model
	 */
	public void notify(ClientModel model);
	
}
