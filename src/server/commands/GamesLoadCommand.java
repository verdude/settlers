package server.commands;

import model.IFacade;
import server.ICatanCommand;

/**
 * Created by santi on 3/23/2016.
 */
public class GamesLoadCommand extends ICatanCommand {

    private String name;

    public GamesLoadCommand(String name) {
        this.name = name;
    }

    @Override
    public String execute(IFacade facade) {
        String result = facade.gamesLoad(name);
        return result;
    }
}
