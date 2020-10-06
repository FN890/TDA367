package com.backendboys.battlerace.services.protocols;


import com.badlogic.gdx.math.Vector2;

class ServerProtocolWriter {

    String writeResponse(String msg) {
        return "response:" + msg;
    }

    String writeError(String msg) {
        return "error:" + msg;
    }

    String writePosition(String name, Vector2 pos, float rotation) {
        return "pos:" + name + "," + pos.x + "," + pos.y + "," + rotation;
    }

    String writeGameInfo() {
        return "";
    }

}
