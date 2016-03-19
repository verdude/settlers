package server;

import model.IFacade;

public abstract class ICatanCommand {
	public abstract String execute(IFacade facade);
}
