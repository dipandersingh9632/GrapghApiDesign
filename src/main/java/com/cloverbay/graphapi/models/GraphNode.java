package com.cloverbay.graphapi.models;

import java.util.HashSet;
import java.util.Set;

public class GraphNode {
    private Integer nodeValue;
    private Set<GraphNode> connectedNodes;

    public GraphNode(Integer nodeValue){
        this.nodeValue = nodeValue;
        this.connectedNodes = new HashSet<>();
    }

    public Integer getNodeValue() {
        return nodeValue;
    }

    public Set<GraphNode> getConnectedNodes() {
        return connectedNodes;
    }
}
