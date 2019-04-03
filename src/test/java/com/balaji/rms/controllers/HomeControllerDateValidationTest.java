package com.balaji.rms.controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringJUnit4ClassRunner.class)
public class HomeControllerDateValidationTest {

    Logger logger = LoggerFactory.getLogger(HomeControllerDateValidationTest.class);

    private MockMvc mockMvc;
    @Autowired
    private Environment env;

    @Before
    public void setup(){
        HomeController homeController = new HomeController();
        homeController.setEnv(env);
        this.mockMvc = MockMvcBuilders.standaloneSetup(homeController).build();
    }

    @Test
    public void testEarlierEndDate() throws Exception{
        ResultActions result = this.mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/")
                        .accept(MediaType.TEXT_HTML)
                        .param("startDate", "2009-04-03")
                        .param("endDate", "2009-04-01")
        );
        logger.info(result.toString());
    }

}
