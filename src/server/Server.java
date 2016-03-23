package server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.nio.charset.StandardCharsets;

import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriBuilder;

import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;
import com.sun.net.httpserver.HttpServer;

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
		post("/user/register", "{\"username\": \"Sam\", \"password\" : \"sam\"}");
		System.out.println("Debug user created as Sam, sam.");
		userLogin("Sam", "sam");
		System.out.println("Debug user logged in as Sam, sam.");
		String create = post("/games/create", "{" +
				"  \"randomTiles\": true," +
				"  \"randomNumbers\": true," +
				"  \"randomPorts\": true," +
				"  \"name\": \"Test game\"" +
				"}");
		System.out.println("Just created a game: " + create);
		String list = get("/games/list");
		System.out.println("Gameslist : " + list);
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
			conn.setRequestMethod("POST");
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
