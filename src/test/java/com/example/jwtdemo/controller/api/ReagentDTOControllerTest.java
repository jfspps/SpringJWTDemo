package com.example.jwtdemo.controller.api;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.transaction.Transactional;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@Transactional
@SpringBootTest
class ReagentDTOControllerTest {

    @Autowired
    WebApplicationContext context;

    private final static String ADMINPWD = "admin123";
    private final static String USERPWD = "user123";

    public static Stream<Arguments> streamAllUsers() {
        return Stream.of(Arguments.of("admin", ADMINPWD),
                Arguments.of("user", USERPWD));
    }

    public static Stream<Arguments> streamDeniedUsers() {
        return Stream.of(Arguments.of("tommy", "blablabla"));
    }

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
    }

    @ParameterizedTest
    @MethodSource("streamAllUsers")
    void getAllReagents(String username, String password) throws Exception {
        mockMvc.perform(get("/api/reagent/").with(httpBasic(username, password))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.reagentDTOList", hasSize(2))); // $ is root, followed by DTO properties;
    }

    @ParameterizedTest
    @MethodSource("streamDeniedUsers")
    void getAllReagents_Denied(String username, String password) throws Exception {
        mockMvc.perform(get("/api/reagent/").with(httpBasic(username, password))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }

    @ParameterizedTest
    @MethodSource("streamAllUsers")
    void findReagentById(String username, String password) throws Exception {
        mockMvc.perform(get("/api/reagent/2").with(httpBasic(username, password))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.id", equalTo(2))); // $ is root, followed by DTO properties;
    }

    @ParameterizedTest
    @MethodSource("streamAllUsers")
    void findReagentById_NotFound(String username, String password) throws Exception {
        mockMvc.perform(get("/api/reagent/4").with(httpBasic(username, password))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @ParameterizedTest
    @MethodSource("streamDeniedUsers")
    void findReagentById_Denied(String username, String password) throws Exception {
        mockMvc.perform(get("/api/reagent/2").with(httpBasic(username, password))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }
}