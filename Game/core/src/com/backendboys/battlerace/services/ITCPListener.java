package com.backendboys.battlerace.services;

public interface ITCPListener {

    void gotMessage(String msg);
    void lostConnection(String msg);

}
