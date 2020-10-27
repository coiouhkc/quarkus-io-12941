package org.acme.rest.client;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@QuarkusTest
@QuarkusTestResource(WiremockSomeApi.class)
public class SomeApiClientTest {
  @Inject
  @RestClient
  SomeApiClient someApiClient;

  /**
   * Should fail if used in conjunction with <code>quarkus.resteasy.gzip.max-input=5</code>
   */
  @Test
  void GET_someValue() {
    assertEquals("0123456789", someApiClient.getSomeValue());
  }

  /**
   * Should succeed if used in conjunction with <code>quarkus.resteasy.gzip.max-input=10000001</code>
   */
  @Test
  void GET_someLargeValue() {
    assertTrue(someApiClient.getSomeLargeValue().length() == 10_000_001);
  }
}
