package org.acme.rest.client;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.annotations.GZIP;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/v1")
@RegisterRestClient(configKey = "some-api")
public interface SomeApiClient {
  @GET
  @Path("/value")
  @GZIP
  @Produces({"text/plain"})
  String getSomeValue();

  @GET
  @Path("/largevalue")
  @GZIP
  @Produces({"text/plain"})
  String getSomeLargeValue();
}
