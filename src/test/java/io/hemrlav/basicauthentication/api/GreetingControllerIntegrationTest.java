package io.hemrlav.basicauthentication.api;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GreetingControllerIntegrationTest {

    public static final String AVENGER = "Avenger";
    public static final String DIRECTOR = "Director";
    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }


    @Test
    public void givenRequestOnGreetCitizen_shouldSucceedWith200() throws Exception {
        mockMvc.perform(get("/greet/citizen")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @WithMockUser(roles = {AVENGER})
    @Test
    public void givenRequestOnGreetCitizen_WithAvengerUser_shouldSucceedWith200() throws Exception {
        mockMvc.perform(get("/greet/citizen")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @WithMockUser(roles = {DIRECTOR})
    @Test
    public void givenRequestOnGreetCitizen_WithDirectorUser_shouldSucceedWith200() throws Exception {
        mockMvc.perform(get("/greet/citizen")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void givenRequestOnGreetAvenger_WithoutUser_shouldFailWith401() throws Exception {
        mockMvc.perform(get("/greet/avenger")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }

    @WithMockUser(roles = {AVENGER})
    @Test
    public void givenRequestOnGreetAvenger_WithAvengerUser_shouldSucceedWith200() throws Exception {
        mockMvc.perform(get("/greet/avenger")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @WithMockUser(roles = {DIRECTOR})
    @Test
    public void givenRequestOnGreetAvenger_WithDirectorUser_shouldSucceedWith200() throws Exception {
        mockMvc.perform(get("/greet/avenger")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void givenRequestOnGreetDirector_WithoutUser_shouldFailWith401() throws Exception {
        mockMvc.perform(get("/greet/director")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }

    @WithMockUser(roles = {AVENGER})
    @Test
    public void givenRequestOnGreetDirector_WithAvengerUser_shouldFailWith401() throws Exception {
        mockMvc.perform(get("/greet/director")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }

    @WithMockUser(roles = {DIRECTOR})
    @Test
    public void givenRequestOnGreetDirector_WithDirectorUser_shouldSucceedWith200() throws Exception {
        mockMvc.perform(get("/greet/director")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}