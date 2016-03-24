package server;

import com.sun.deploy.util.SessionState;
import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;
import com.sun.net.httpserver.HttpServer;
import model.ClientException;
import model.ClientFacade;
import model.ServerProxy;

import javax.ws.rs.core.UriBuilder;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.nio.charset.StandardCharsets;

/**
 * This class contains the main class for the server. Everything is run from here to start the server.
 * @author S Jacob Powell
 *
 */
@SuppressWarnings("restriction")
public class Server {

	private static int PORT = 8081;
	private static String encodedCookie;
	private static String decodedCookie;
	private static String playerID;
	private static String gameID;

	/**
	 * @pre The port is open
	 * @post When run, the server is started on the given port
	 * @param args Contains the port upon which to run, which should be open on the host machine
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		System.out.println("Starting server...\n");
		HttpServer HTTPServer = createHttpServer();
		HTTPServer.start();
		System.out.println("Server started.");

		// initialize the facade and proxy
		ClientFacade.getSingleton(ServerProxy.getSingleton("localhost","8081"));
		auto_test_endpoints();
	}

	private static void register_basic_players() {
		post("/user/register", "{\"username\": \"Sam\", \"password\" : \"sam\"}");
		post("/user/register", "{\"username\": \"Brooke\", \"password\" : \"brooke\"}");
		post("/user/register", "{\"username\": \"b\", \"password\" : \"b\"}");
		post("/user/register", "{\"username\": \"e\", \"password\" : \"e\"}");
	}

	private static void auto_test_endpoints() {
		post("/user/register", "{\"username\": \"Sam\", \"password\" : \"sam\"}");
		userLogin("Sam", "sam");
		post("/games/create", "{" +
				"  \"randomTiles\": true," +
				"  \"randomNumbers\": true," +
				"  \"randomPorts\": true," +
				"  \"name\": \"Test game\"" +
				"}");
		gamesJoin(0,"puce");

		post("/user/register", "{\"username\": \"Brooke\", \"password\" : \"brooke\"}");
		userLogin("Brooke", "brooke");
		post("/games/join", "{" +
				"  \"id\": 0," +
				"  \"color\": \"green\"" +
				"}");
		post("/user/register", "{\"username\": \"b\", \"password\" : \"b\"}");
		userLogin("b", "b");
		gamesJoin(0,"red");
		post("/user/register", "{\"username\": \"e\", \"password\" : \"e\"}");

/*		String chatResponse = post("/moves/sendChat", "{" +
				"\"playerIndex\": " + 0 +
				",\"content\": \"Sam says Hi\""+
				"}");
		System.out.println(chatResponse);
		//save
		String response = get("/game/model?version=-2000000");
		System.out.println(response);
		String saveResponse = post("/games/save", "{" +
				"  \"id\": 0," +
				"  \"name\": \"WeBeSavin\""+
				"}");
		System.out.println("Save response= " + saveResponse);
        response = get("/game/model?version=0");
		System.out.println(response);

		System.out.println(
				"RollNumber Response: " +
			post("/moves/rollNumber", "{\n" +
					"  \"playerIndex\": 0,\n" +
					"  \"number\": 3\n" +
					"}")
		);

		System.out.println(
				"Finish Turn Response: " +
			post("/moves/finishTurn", "{\"playerIndex\":0}")
		);*/
	}

	@SuppressWarnings("unchecked")
	private static HttpServer createHttpServer() throws IOException {
		ResourceConfig ResourceConfig = new PackagesResourceConfig("server.api");
		ResourceConfig.getContainerResponseFilters().add(CORSFilter.class);
		return HttpServerFactory.create(getURI(), ResourceConfig);
	}

	private static URI getURI() {
		return UriBuilder.fromUri("http://" + getHostName() + "/").port(PORT).build();
	}

	private static String getHostName() {
		String hostName = "localhost";
		try {
			hostName = InetAddress.getLocalHost().getCanonicalHostName();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return hostName;
	}

	private static String post(String endPoint, String data)
	{
		try {
			byte[] postData = data.getBytes(StandardCharsets.UTF_8);
			int postDataLength = postData.length;
			HttpURLConnection conn= (HttpURLConnection) new URL("http://localhost:8081" + endPoint).openConnection();
			conn.setDoOutput(true);
			conn.setInstanceFollowRedirects(false);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("charset", "utf-8");
			conn.setRequestProperty("Content-Length", Integer.toString( postDataLength ));
			conn.setRequestProperty("Cookie", "catan.user=" + encodedCookie + "; catan.game=" + gameID);
			conn.setUseCaches(false);
			DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
			wr.write(postData);
			StringBuilder response = new StringBuilder();
			String line;
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			while ((line = reader.readLine()) != null) {
				response.append(line);
			}
			return response.toString();
		} catch (Exception e) {
			//e.printStackTrace();
			return "Error";
		}
	}

	public static String userLogin(String username, String password) {
		try {
			byte[] postData = ("{\"username\": \"" + username + "\", \"password\": \"" + password + "\"}").getBytes(StandardCharsets.UTF_8);
			int postDataLength = postData.length;
			HttpURLConnection conn= (HttpURLConnection) new URL("http://localhost:8081/user/login").openConnection();
			conn.setDoOutput(true);
			conn.setInstanceFollowRedirects(false);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("charset", "utf-8");
			conn.setRequestProperty("Content-Length", Integer.toString( postDataLength ));
			conn.setUseCaches(false);
			DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
			wr.write(postData);
			StringBuilder response = new StringBuilder();
			String line;
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			encodedCookie = conn.getHeaderField("Set-cookie").split(";")[0].split("=")[1];
			decodedCookie = URLDecoder.decode(encodedCookie);
			playerID = decodedCookie.split("playerID\":")[1].split("}")[0];
			while ((line = reader.readLine()) != null) {
				response.append(line);
			}
			return response.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return "Error";
		}
	}

	public static String gamesJoin(int id, String color) {
		try {
			byte[] postData = ("{\"id\": \"" + id + "\", \"color\": \"" + color + "\"}").getBytes(StandardCharsets.UTF_8);
			int postDataLength = postData.length;
			HttpURLConnection conn= (HttpURLConnection) new URL("http://localhost:8081/games/join").openConnection();
			conn.setDoOutput(true);
			conn.setInstanceFollowRedirects(false);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("charset", "utf-8");
			conn.setRequestProperty("Content-Length", Integer.toString( postDataLength ));
			conn.setRequestProperty("Cookie", "catan.user=" + encodedCookie);
			conn.setUseCaches(false);
			DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
			wr.write(postData);
			StringBuilder response = new StringBuilder();
			String line;
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			gameID = conn.getHeaderField("Set-cookie").split(";")[0].split("catan.game=")[1];
			while ((line = reader.readLine()) != null) {
				response.append(line);
			}
			return response.toString();
		} catch (Exception e) {
			//e.printStackTrace();
			return "Error";
		}
	}

	private static String get(String endPoint)
	{
		try {
			HttpURLConnection conn= (HttpURLConnection) new URL("http://localhost:8081" + endPoint).openConnection();
			conn.setDoOutput(true);
			conn.setInstanceFollowRedirects(false);
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("charset", "utf-8");
			conn.setRequestProperty("Cookie", "catan.user=" + encodedCookie + "; catan.game=" + gameID);
			conn.setUseCaches(false);

			StringBuilder response = new StringBuilder();
			String line;
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			while ((line = reader.readLine()) != null) {
				response.append(line);
			}
			return response.toString();
		} catch (Exception e) {
			//e.printStackTrace();
			return "Error";
		}
	}

}
