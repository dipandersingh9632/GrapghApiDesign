package com.cloverbay.graphapi.service;

import com.cloverbay.graphapi.models.GraphNode;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GraphNodeServiceImpl implements  GraphNodeService {

    private Map<Integer, GraphNode> graphNodeMap;

    public GraphNodeServiceImpl(){
        this.graphNodeMap = new HashMap<>();
    }
    @Override
    public void joinNodes(Integer nodeVal1, Integer nodeVal2) {
        GraphNode node1 = graphNodeMap.get(nodeVal1);
        GraphNode node2 = graphNodeMap.get(nodeVal2);

        if(node1 == null){
            node1 = new GraphNode(nodeVal1);
        }
        if(node2 == null){
            node2 = new GraphNode(nodeVal2);
        }

        node1.getConnectedNodes().add(node2);
        node2.getConnectedNodes().add(node1);
        graphNodeMap.put(nodeVal1, node1);
        graphNodeMap.put(nodeVal2, node2);
    }

    @Override
    public Boolean isNodesConnected(Integer nodeVal1, Integer nodeVal2) {
        // Checking by DFS Algorithm
        Set<Integer> visitedSet = new HashSet<>();
        if(nodeVal1 == null || (!graphNodeMap.containsKey(nodeVal1))) return false;
        if(nodeVal2 == null || (!graphNodeMap.containsKey(nodeVal2))) return false;
        return dfs(nodeVal1, nodeVal2, graphNodeMap, visitedSet);
    }

    private boolean dfs(int nodeVal1, int nodeVal2, Map<Integer, GraphNode> graphNodeMap, Set<Integer> visitedSet) {
        if(nodeVal1 == nodeVal2) return true;
        visitedSet.add(nodeVal1);

        // Visit the connected nodes of nodeVal1
        GraphNode node1 = graphNodeMap.get(nodeVal1);
        for(GraphNode neighbor : node1.getConnectedNodes()){
            int neighborVal = neighbor.getNodeValue();
            if(!visitedSet.contains(neighborVal) && dfs(neighborVal, nodeVal2, graphNodeMap, visitedSet)) return true;
        }
        return false;
    }
}
