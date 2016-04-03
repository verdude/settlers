package server;

import server.database.IDAO;
import server.database.*;

public class Factory {
	
	public static IDAO getDAO(String db_type, String jar_filename){
		
		switch(db_type){
		case "sql": return (IDAO) new SqlDAO(jar_filename); break;
		case "ini": return (IDAO) new IniDAO(jar_filename); break;
		default: 	System.out.println("ERROR: Invalid databaseType!");
				 	System.out.println("Valid Types: \"sql\" | \"ini\" ");
				 	break;
	}

}
