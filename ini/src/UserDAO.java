import org.ini4j.Ini;
import org.ini4j.Wini;
import pluginInterfaces.IUserDAO;

import java.io.File;
import java.io.IOException;

/**
 * Created by Sean_George on 4/6/16.
 */
public class UserDAO implements IUserDAO {
    private Wini ini;
    private File iniFile;
    private final String SECTION = "users";
    private Ini.Section backupSection;

    public UserDAO(){
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
    public void addUser(String username, String password, int userID) {
        String json = "{\"username\":\"" + username +"\"," +
                "\"password\":\"" + password + "\"," +
                "\"userID\":" + userID + "}";
        ini.put(SECTION, username, json);

    }

    @Override
    public boolean verifyUser(String username, String password) {
        String json = ini.get(SECTION, username);

        return json.contains("\"password\":\"" + password + "\"");
    }

    @Override
    public String getUsers() {
        StringBuilder users = new StringBuilder();
        users.append("[");
        Ini.Section section = ini.get(SECTION);


        if(section == null){
            return "[]";
        }
        for(String key : section.keySet()){
            users.append(section.get(key) + ",");
        }
        if(users.length() != 1) {
            users.deleteCharAt(users.length() - 1);
        }
        users.append("]");
        return users.toString();
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
}
