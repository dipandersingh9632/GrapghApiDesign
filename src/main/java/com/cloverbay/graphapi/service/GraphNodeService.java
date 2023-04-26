package com.cloverbay.graphapi.service;

public interface GraphNodeService {
    public void joinNodes(Integer nodeVal1, Integer nodeVal2);
    public Boolean isNodesConnected(Integer nodeVal1, Integer nodeVal2);
}
