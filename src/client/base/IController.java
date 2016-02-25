package client.base;

/**
 * Base controller interface
 */
public interface IController extends IObserver
{
	
	/**
	 * View getter
	 * 
	 * @return The controller's view
	 */
	IView getView();
}

