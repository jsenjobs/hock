package com.jsen.test.service.quartz.test.contract.producer;

import com.jsen.test.service.quartz.Boot;
import com.jsen.test.service.quartz.controller.TestContractController;
import com.jsen.test.service.quartz.service.TestService;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.contract.stubrunner.server.EnableStubRunnerServer;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * <p>
 * </p>
 *
 * @author ${User}
 * @since 2018/4/27
 * contract 测试 verify要在测试前设置一些变量，如需要的controller mock等
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Boot.class)
public class ContractVerifierBase {

    // @MockBean
    // private TestService testService;


    @Before
    public void setup() {
        RestAssuredMockMvc.standaloneSetup(new TestContractController());
    }

    // @Test
    public void test() {}
}
