package client;

import java.io.File;
import java.io.FileNotFoundException;

public class Converter {


	/**
	 * @param jsonData The json to de-serialize
	 * @throws FileNotFoundException When the file is not found.
	 * @pre None
	 * @post The JSON data contained in the file is parsed and maps it to the model objects.
	 */
	public void importJSON(File jsonData)throws FileNotFoundException{
		
	}
	
	/**
	 * @param o The object to Serialize
	 * @return The serialized model object
	 * @pre None
	 * @post The object serialized as JSON
	 */
	public Object serialize(Object o){
		return null;
		
	}
	
}
