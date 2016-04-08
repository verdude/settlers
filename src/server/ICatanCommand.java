package server;

import model.IFacade;

public abstract class ICatanCommand {


	protected String type;
	public abstract String execute(IFacade facade);

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
