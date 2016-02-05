package clientTests;

import java.net.MalformedURLException;

import model.ServerProxy;

import org.junit.Test;

public class ServerTest {

	@Test
	public void test() throws MalformedURLException {
		ServerProxy proxy = new ServerProxy("localhost", "8081");
		System.out.println(proxy.userLogin("Sam", "sam"));
		System.out.println(proxy.gamesJoin(0, "orange"));
		System.out.println(proxy.gamesModel());
	}

}
