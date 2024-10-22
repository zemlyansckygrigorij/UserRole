package org.example.userrole.web.controller;

import jakarta.transaction.Transactional;
import org.example.userrole.services.DataTestValues;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
@AutoConfigureMockMvc
@WithMockUser("user")
class UserRoleControllerTest {

    @Autowired
    private DataTestValues testValues;
    @Autowired
    private MockMvc mockMvc;
    @LocalServerPort
    private int port;

    @Test
    void commit() {
        try{
            this.mockMvc.perform(MockMvcRequestBuilders
                        .delete(testValues.role)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

            mockMvc.perform(get(testValues.localhost + port + testValues.api))
                .andExpect(jsonPath("$", hasSize(testValues.cuttedSize)));

            mockMvc.perform(MockMvcRequestBuilders
                            .post(testValues.role)
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andReturn();

            mockMvc.perform(get(testValues.localhost + port + testValues.api))
                    .andExpect(jsonPath("$", hasSize(testValues.fullSize)));
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    void findAll() {
        try{
            mockMvc.perform(get(testValues.localhost + port + testValues.api))
                    .andExpect(jsonPath("$", hasSize(testValues.fullSize)));
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    void deleteUserById() {
        try{
            mockMvc.perform(get(testValues.localhost + port + testValues.api))
                    .andExpect(jsonPath("$", hasSize(testValues.fullSize)));

            this.mockMvc.perform(MockMvcRequestBuilders
                            .delete(testValues.role)
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
            mockMvc.perform(get(testValues.localhost + port + testValues.api))
                    .andExpect(jsonPath("$", hasSize(testValues.cuttedSize)));
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}