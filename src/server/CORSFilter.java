package server;

import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerResponse;
import com.sun.jersey.spi.container.ContainerResponseFilter;

/**
 * This handles the CORS issues for the server, by adding the correct header.
 */
public class CORSFilter implements ContainerResponseFilter {

	@Override
	/**
	 * Adds certain options and filters to each request and response
	 * @pre The server is running
	 * @post The correct filters and actions will be performed on each request
	 * @param requestContext contains the request, upon which more headers can be added or viewed
	 * @param responseContext The headers will be added onto each response and returned
	 */
	public ContainerResponse filter(ContainerRequest requestContext, ContainerResponse responseContext) {
		// TODO Auto-generated method stub
		responseContext.getHttpHeaders().add("Access-Control-Allow-Origin", "*");
		// responseContext.getHttpHeaders().add("Access-Control-Allow-Headers", "Content-Type");
		// responseContext.getHttpHeaders().add("Content-Type", "application/json");
		return responseContext;
	}

}