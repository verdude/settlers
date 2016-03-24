package server.commands;

import model.IFacade;
import server.ICatanCommand;

/**
 * Created by santi on 3/23/2016.
 */
public class GamesSaveCommand extends ICatanCommand {

    private int id;
    private String name;

    /**
     *
     * @param id The id of the game to save
     * @param name The name of the file without an extension
     */
    public GamesSaveCommand(int id, String name) {
        this.id = id;
        this.name = name;
    }


    @Override
    public String execute(IFacade facade) {
        String response = facade.gamesSave(id, name);

        return response;
    }
}
