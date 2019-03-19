package com.zenika.uglysystem;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.hamcrest.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UglySystemApplicationTests {


    @Autowired
    private MockMvc mvc;

    @Test
    public void contextLoads() {

    }

    @Test
    public void runRoot() throws Exception {
        this.mvc.perform(get("/")).andExpect(status().isOk())
                .andExpect(content().string(containsString( "_links")));
    }


    @Test
    public void runEntities() throws Exception {
        this.mvc.perform(get("/entities/1")).andExpect(status().isOk())
                .andExpect(content().string(containsString( "lala")));
    }


    @Test
    public void runFlags() throws Exception {
        this.mvc.perform(get("/flags")).andExpect(status().isOk())
                .andExpect(content().string(containsString( "SECRET_9_DEV0_SEC0_OPS2_59S74TSc9P9dAP46Yc22FwG5ViDa8a6gW9aE8uAdpkd8WheW")));
    }

}

