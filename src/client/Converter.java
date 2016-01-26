package client;

import java.io.File;
import java.io.FileNotFoundException;

public class Converter {


	/**
	 * Deserializes and parses the game model data and maps it to the model objects.
	 * @param jsonData The json to de-serialize
	 * @throws FileNotFoundException When the file is not found.
	 * @pre None
	 * @post The JSON data contained in the file is parsed and maps it to the model objects.
	 */
	public void deserialize(File jsonData)throws FileNotFoundException{
		
	}
	
	/**
	 * @param o The object to Serialize
	 * @return The serialized model object
	 * @pre None
	 * @post The object serialized as JSON
	 * @throws ClientException when this function fails when it shouldn't 
	 */
	public Object serialize(Object o) throws ClientException{
		return null;
		
	}
	
}
