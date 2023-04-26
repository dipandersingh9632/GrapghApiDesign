package com.cloverbay.graphapi.controller;

import com.cloverbay.graphapi.service.GraphNodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class GraphNodeController {
    private GraphNodeService graphNodeService;

    @Autowired
    public GraphNodeController(GraphNodeService graphNodeService){this.graphNodeService = graphNodeService;}

    @GetMapping("/connected/{nodeVal1}/{nodeVal2}")
    public ResponseEntity<Boolean> nodesConnected(@PathVariable Integer nodeVal1, @PathVariable Integer nodeVal2){
        return new ResponseEntity<>(graphNodeService.isNodesConnected(nodeVal1, nodeVal2), HttpStatus.OK);
    }

    @PostMapping("/joins/{nodeVal1}/{nodeVal2}")
    public ResponseEntity<String> nodesJoin(@PathVariable Integer nodeVal1, @PathVariable Integer nodeVal2){
        graphNodeService.joinNodes(nodeVal1, nodeVal2);
        return new ResponseEntity<>("Nodes Joined Successfully", HttpStatus.OK);
    }
}
