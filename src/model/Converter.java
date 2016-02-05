package model;

import java.io.FileNotFoundException;

public class Converter {


	/**
	 * Deserializes and parses the game model data and maps it to the model objects.
	 * @param jsonData The json to de-serialize
	 * @throws FileNotFoundException When the file is not found.
	 * @pre None
	 * @post The JSON data contained in the file is parsed and maps it to the model objects.
	 */
	public static Object deserialize(String jsonData) {
		return jsonData;
		
	}
	
	/**
	 * @param o The object to Serialize
	 * @return The serialized model object
	 * @pre None
	 * @post The object serialized as JSON
	 * @throws ClientException when this function fails when it shouldn't 
	 */
	public static String serialize(Object o) throws ClientException{
		return null;
		
	}
	
}
