package com.zenika.uglysystem;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class UglySystemApplicationTests {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private UserDetailsService userDetailsService;

    @Test
    public void contextLoads() {
    }

    @Test
    public void should_return_error_message_for_admin_access() throws Exception{
        mvc.perform(MockMvcRequestBuilders.get("/admin")
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isUnauthorized())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @WithUserDetails("admin")
    public void should_return_success_for_admin_access() throws Exception{
        mvc.perform(MockMvcRequestBuilders.get("/admin")
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().string("SUCCESS"))
                .andDo(MockMvcResultHandlers.print());
    }
}
