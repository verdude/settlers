import org.ini4j.Ini;
import org.ini4j.Wini;
import pluginInterfaces.IGameDAO;

import java.io.File;
import java.io.IOException;

/**
 * Created by Sean_George on 4/6/16.
 */
public class GameDAO implements IGameDAO {

    private Wini ini;
    private File iniFile;
    private final String SECTION = "games";
    private Ini.Section backupSection;

    public GameDAO(){
        try {
            iniFile = new File(System.getProperty("user.dir") + "/persistence.ini");
            if(!iniFile.exists()) {
                iniFile.createNewFile();
                ini = new Wini(iniFile);
            }
            ini = new Wini(iniFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getGames() {

        StringBuilder games = new StringBuilder();
        games.append("[");
        Ini.Section section = ini.get(SECTION);

        if(section == null){
            return "[]";
        }
        for(String key : section.keySet()){
            if(key.contains("game")) {
                games.append(section.get(key) + ",");
            }
        }
        if(games.length() != 1) {
            games.deleteCharAt(games.length() - 1);
        }
        games.append("]");
        return games.toString();
    }



    @Override
    public void storeCommands(String commands, int gameID) {
        ini.put(SECTION,"commandList" + gameID,commands);

    }

    @Override
    public void storeGame(String game, int gameID) {
        ini.put(SECTION,"game" + gameID,game);

    }

    @Override
    public void startTransaction() {
        try {
            ini = new Wini(iniFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void endTransaction(boolean commit) {
        if(commit){
            try {
                ini.store();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void clearPersistence() {
        iniFile.delete();
        try {
            iniFile = new File(System.getProperty("user.dir") + "/persistence.ini");

            if(!iniFile.exists()) {
                iniFile.createNewFile();
                ini = new Wini(iniFile);
            }
            ini = new Wini(iniFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getCommands(int gameID) {
//        StringBuilder commandList = new StringBuilder();
//        commandList.append("[");
        Ini.Section section = ini.get(SECTION);

        if(section == null){
            return "[]";
        }
        String commands = section.get("commandList" + gameID);

        return commands == null || commands.isEmpty() ? "[]":commands;
//        boolean changed = false;
//
//        for(String key : section.keySet()){
//            if(key.contains("commandList")) {
//                changed = true;
//                commandList.append(section.get(key) + ",");
//            }
//        }
//        if(commandList.length() != 1) {
//            commandList.deleteCharAt(commandList.length() - 1);
//        }
//        commandList.append("]");
//        if(changed){
//            commandList.deleteCharAt(commandList.length() - 1);
//            commandList.deleteCharAt(0);
//
//        }
//        return commandList.toString();
    }


}
