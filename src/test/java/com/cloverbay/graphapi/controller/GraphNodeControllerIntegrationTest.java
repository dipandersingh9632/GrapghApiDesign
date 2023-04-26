package com.cloverbay.graphapi.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class GraphNodeControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testJoinAndIsConnected() throws Exception {
        // Join nodes 2 and 3
        mockMvc.perform(post("/join/2/3"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.TEXT_PLAIN_VALUE + ";charset=UTF-8"))
                .andExpect(content().string("Nodes Joined Successfully"));

        // Join nodes 2 and 4
        mockMvc.perform(post("/join/2/4"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.TEXT_PLAIN_VALUE + ";charset=UTF-8"))
                .andExpect(content().string("Nodes Joined Successfully"));

        // Join nodes 4 and 5
        mockMvc.perform(post("/join/4/5"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.TEXT_PLAIN_VALUE + ";charset=UTF-8"))
                .andExpect(content().string("Nodes Joined Successfully"));

        // Join nodes 7 and 8
        mockMvc.perform(post("/join/7/8"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.TEXT_PLAIN_VALUE + ";charset=UTF-8"))
                .andExpect(content().string("Nodes Joined Successfully"));

        // Verify that nodes 2 and 3 are connected
        mockMvc.perform(get("/isConnected/2/3"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));

        // Verify that nodes 2 and 5 are connected
        mockMvc.perform(get("/isConnected/2/5"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));

        // Verify that nodes 2 and 7 are not connected
        mockMvc.perform(get("/isConnected/2/7"))
                .andExpect(status().isOk())
                .andExpect(content().string("false"));

        // Join nodes 3 and 7
        mockMvc.perform(post("/join/3/7"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.TEXT_PLAIN_VALUE + ";charset=UTF-8"))
                .andExpect(content().string("Nodes Joined Successfully"));

        // Verify that nodes 2 and 7 are now connected
        mockMvc.perform(get("/isConnected/2/7"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }
}
