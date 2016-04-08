package model;

import client.data.GameInfo;
import client.data.PlayerInfo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import server.ICatanCommand;
import server.commands.*;

import java.io.FileNotFoundException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

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

	public static List<ICatanCommand> deserializeCommands(String json) {
		RuntimeTypeAdapterFactory<ICatanCommand> runtimeTypeAdapterFactory = RuntimeTypeAdapterFactory
            .of(ICatanCommand.class, "type")
            .registerSubtype(MovesAcceptTradeCommand.class,MovesAcceptTradeCommand.class.toString())
            .registerSubtype(MovesBuildCityCommand.class,MovesBuildCityCommand.class.toString())
            .registerSubtype(MovesBuildRoadCommand.class,MovesBuildRoadCommand.class.toString())
            .registerSubtype(MovesBuildSettlementCommand.class,MovesBuildSettlementCommand.class.toString())
            .registerSubtype(MovesBuyDevCardCommand.class,MovesBuyDevCardCommand.class.toString())
            .registerSubtype(MovesDiscardCardsCommand.class,MovesDiscardCardsCommand.class.toString())
            .registerSubtype(MovesFinishTurnCommand.class,MovesFinishTurnCommand.class.toString())
            .registerSubtype(MovesMaritimeTradeCommand.class,MovesMaritimeTradeCommand.class.toString())
            .registerSubtype(MovesMonopolyCommand.class,MovesMonopolyCommand.class.toString())
            .registerSubtype(MovesMonumentCommand.class,MovesMonumentCommand.class.toString())
            .registerSubtype(MovesOfferTradeCommand.class,MovesOfferTradeCommand.class.toString())
            .registerSubtype(MovesRoadBuildingCommand.class,MovesRoadBuildingCommand.class.toString())
            .registerSubtype(MovesRobPlayerCommand.class,MovesRobPlayerCommand.class.toString())
            .registerSubtype(MovesRollNumberCommand.class,MovesRollNumberCommand.class.toString())
            .registerSubtype(MovesSendChatCommand.class,MovesSendChatCommand.class.toString())
            .registerSubtype(MovesSoldierCommand.class,MovesSoldierCommand.class.toString())
            .registerSubtype(MovesYearOfPlentyCommand.class,MovesYearOfPlentyCommand.class.toString());

		//System.out.println("Deserializing command: " + json);
		Gson gson = new GsonBuilder().registerTypeAdapterFactory(runtimeTypeAdapterFactory).create();

		Type listType = new TypeToken<List<ICatanCommand>>(){}.getType();
		List<ICatanCommand> commands = gson.fromJson(json, listType);
		return commands;
	}

	public static String serializeCommands(List<ICatanCommand> commands) {
		RuntimeTypeAdapterFactory<ICatanCommand> runtimeTypeAdapterFactory = RuntimeTypeAdapterFactory
            .of(ICatanCommand.class, "type")
				.registerSubtype(MovesAcceptTradeCommand.class,MovesAcceptTradeCommand.class.toString())
				.registerSubtype(MovesBuildCityCommand.class,MovesBuildCityCommand.class.toString())
				.registerSubtype(MovesBuildRoadCommand.class,MovesBuildRoadCommand.class.toString())
				.registerSubtype(MovesBuildSettlementCommand.class,MovesBuildSettlementCommand.class.toString())
				.registerSubtype(MovesBuyDevCardCommand.class,MovesBuyDevCardCommand.class.toString())
				.registerSubtype(MovesDiscardCardsCommand.class,MovesDiscardCardsCommand.class.toString())
				.registerSubtype(MovesFinishTurnCommand.class,MovesFinishTurnCommand.class.toString())
				.registerSubtype(MovesMaritimeTradeCommand.class,MovesMaritimeTradeCommand.class.toString())
				.registerSubtype(MovesMonopolyCommand.class,MovesMonopolyCommand.class.toString())
				.registerSubtype(MovesMonumentCommand.class,MovesMonumentCommand.class.toString())
				.registerSubtype(MovesOfferTradeCommand.class,MovesOfferTradeCommand.class.toString())
				.registerSubtype(MovesRoadBuildingCommand.class,MovesRoadBuildingCommand.class.toString())
				.registerSubtype(MovesRobPlayerCommand.class,MovesRobPlayerCommand.class.toString())
				.registerSubtype(MovesRollNumberCommand.class,MovesRollNumberCommand.class.toString())
				.registerSubtype(MovesSendChatCommand.class,MovesSendChatCommand.class.toString())
				.registerSubtype(MovesSoldierCommand.class,MovesSoldierCommand.class.toString())
				.registerSubtype(MovesYearOfPlentyCommand.class,MovesYearOfPlentyCommand.class.toString());

		Gson gson = new GsonBuilder().registerTypeAdapterFactory(runtimeTypeAdapterFactory).create();

		//System.out.println("serializing command: ");
		String json = gson.toJson(commands);
		return json;
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
