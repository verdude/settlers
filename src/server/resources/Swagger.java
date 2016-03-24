package server.resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
@Path("")
public class Swagger {
	@GET
	@Path("docs/api/data")
	@Produces(MediaType.APPLICATION_JSON)
	public InputStream getJsonFile() throws FileNotFoundException
	{
		return new FileInputStream("docs/api/data.json");
	}
	@GET
	@Path("{path:.*}")
	public InputStream viewSwagger(@PathParam("path") String path) throws FileNotFoundException
	{
		System.out.println(path);
		try {
			if(path.isEmpty()) {
				throw new FileNotFoundException();
			}
			if(path.contains("data")) {
				return new FileInputStream(path + ".json");
			}
			else {
				return new FileInputStream(path);
			}
		} catch (FileNotFoundException e) {
			return new FileInputStream("docs/api/view/index.html");
		}
	}
}
