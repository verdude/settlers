package server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.URI;
import java.net.UnknownHostException;

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

}
