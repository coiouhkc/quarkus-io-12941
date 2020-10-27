package org.acme.rest.client;

import com.github.tomakehurst.wiremock.WireMockServer;
import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.Collections;
import java.util.Map;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class WiremockSomeApi implements QuarkusTestResourceLifecycleManager {

  private WireMockServer wireMockServer;

  @Override
  public Map<String, String> start() {
    wireMockServer = new WireMockServer();
    wireMockServer.start();

    stubFor(get(urlEqualTo("/v1/value"))
      .willReturn(aResponse()
        .withHeader("Content-Type", "text/plain")
        .withBody("0123456789")));

    stubFor(get(urlEqualTo("/v1/largevalue"))
      .willReturn(aResponse()
        .withHeader("Content-Type", "text/plain")
        .withBody(RandomStringUtils.randomAlphabetic(10_000_001))));

    stubFor(get(urlMatching(".*")).atPriority(10).willReturn(aResponse().proxiedFrom("http://localhost:8080")));

    return Collections.singletonMap("some-api/mp-rest/url", wireMockServer.baseUrl());
  }

  @Override
  public void stop() {

  }
}
