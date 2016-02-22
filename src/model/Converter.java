package model;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import client.data.GameInfo;
import client.data.PlayerInfo;

public class Converter {


	/**
	 * Deserializes and json into a ClientModel object
	 * @param jsonData The json to de-serialize
	 * @throws FileNotFoundException When the file is not found.
	 * @pre None
	 * @post The JSON data contained in the file is parsed and maps it to the model objects.
	 */
	public static Object deserializeClientModel(String jsonData) {
		Gson gson = new Gson();
		return gson.fromJson(jsonData, ClientModel.class);
	}

	/**
	 * Generic version of Deserialize for whatever class is passed in
	 * @param jsonData The json to de-serialize
	 * @param classType The class of the object accessed by ClassName.class
	 * @throws FileNotFoundException When the file is not found.
	 * @return Object of Type classType
	 * @pre None
	 * @post The JSON data contained in the file is parsed and creates an object of type classType
	 */
	public static <T> T deserialize(String jsonData, Class<T> classType) {
		Gson gson = new Gson();
		return gson.fromJson(jsonData, classType);
	}

	public static GameInfo[] deserializeGamesArray(String jsonArray) {
		Gson gson = new Gson();
		GameInfo[] games = (GameInfo[])gson.fromJson(jsonArray, GameInfo[].class);
		for (int i = 0; i < games.length; ++i) {
			List<PlayerInfo> newPlayers = new ArrayList<PlayerInfo>();
			List<PlayerInfo> players = games[i].getPlayers();
			for (int j = 0; j < players.size(); ++j) {
				if (!players.get(j).getName().isEmpty()) {
					newPlayers.add(players.get(j));
				}
			}
			games[i].setPlayers(newPlayers);
		}
		return games;
	}
	
	/**
	 * @param o The object to Serialize
	 * @return The serialized model object
	 * @pre None
	 * @post The object serialized as JSON
	 * @throws ClientException when this function fails when it shouldn't 
	 */
	public static String serialize(Object o) throws ClientException{
		Gson gson = new Gson();
		return gson.toJson(o);
	}
	
}
