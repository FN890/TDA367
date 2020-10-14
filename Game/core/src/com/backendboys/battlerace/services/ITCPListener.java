package com.backendboys.battlerace.services;

public interface ITCPListener{

    void gotMessage(String message);

    void lostConnection(String message);

    void onConnectionCallback();
}
