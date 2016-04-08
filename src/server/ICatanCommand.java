package server;

import model.IFacade;

public abstract class ICatanCommand {
	protected String type;
	public abstract String execute(IFacade facade);
}
