import org.ini4j.Ini;
import org.ini4j.Wini;

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
    }



}
