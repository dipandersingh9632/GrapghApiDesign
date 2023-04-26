package com.cloverbay.graphapi.controller;

import com.cloverbay.graphapi.service.GraphNodeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@WebMvcTest(GraphNodeController.class)
@ExtendWith(SpringExtension.class)
class GraphNodeControllerUnitTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GraphNodeService graphNodeService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        when(graphNodeService.isNodesConnected(anyInt(), anyInt())).thenReturn(true);
    }

    @Test
    public void testJoinNodes() throws Exception{
        MvcResult res = mockMvc.perform(MockMvcRequestBuilders.post("/join/1/2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andReturn();
        String responseContent = res.getResponse().getContentAsString();
        assertEquals("Nodes Joined Successfully", responseContent);
    }

    @Test
    public void testIsConnectedReturnsTrue() throws Exception {
        when(graphNodeService.isNodesConnected(1,2)).thenReturn(true);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/isConnected/1/2")
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                            .andExpect(MockMvcResultMatchers.status().isOk())
                            .andReturn();
        String responseContent = result.getResponse().getContentAsString();
        assertEquals("true", responseContent);
    }

    @Test
    public void testIsConnectedReturnsFalse() throws Exception {
        when(graphNodeService.isNodesConnected(1, 7)).thenReturn(false);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/isConnected/1/7")
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                            .andExpect(MockMvcResultMatchers.status().isOk())
                            .andReturn();
        String responseContent = result.getResponse().getContentAsString();
        assertEquals("false", responseContent);
    }

    @Test
    public void testIsConnectedReturnsTrueAfterJoin() throws Exception {
        when(graphNodeService.isNodesConnected(1, 7)).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders.post("/join/1/7")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andReturn();
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/isConnected/1/7")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andReturn();
        String responseContent = result.getResponse().getContentAsString();
        assertEquals("true", responseContent);
    }

}