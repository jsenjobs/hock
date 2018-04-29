package com.jsen.test.service.quartz.test.contract.consumer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

import static com.toomuchcoding.jsonassert.JsonAssertion.assertThatJson;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * <p>
 * </p>
 *
 * @author ${User}
 * @since 2018/4/28
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.NONE)
@DirtiesContext
@AutoConfigureStubRunner(ids = {"com.jsen.test:service-quartz:+:stubs:8081"}, stubsMode = StubRunnerProperties.StubsMode.LOCAL)
public class TestContract {
    // $ java -jar stub-runner.jar --stubrunner.ids=com.jsen.test:service-quartz:+:8081 --stubrunner.stubsMode=LOCAL
    @Autowired
    RestTemplate restTemplate;

    @Test
    public void testContract() {
        // assertThat(response).isEqualTo("{\"fraudCheckStatus\":\"FRAUD\",\"rejectionReason\":\"Amount too high\"}");

    }
}
