package model;

import java.io.FileNotFoundException;

import com.google.gson.Gson;

public class Converter {


	/**
	 * Deserializes and json into a ClientModel object
	 * @param jsonData The json to de-serialize
	 * @throws FileNotFoundException When the file is not found.
	 * @pre None
	 * @post The JSON data contained in the file is parsed and maps it to the model objects.
	 */
	public static Object deserialize(String jsonData) {
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
	
	public static void main(String[] args) {
		MockServerProxy p = MockServerProxy.getSingleton("localhost", "8081");
		Converter.deserialize(p.gamesModel("0"));
	}
	
}
