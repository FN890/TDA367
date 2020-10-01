package server.protocol;

import data.Vector2;

class ServerProtocolWriter {

    String writeError(String msg) {
        return "error:" + msg;
    }


    String writePosition(String name, Vector2 pos, float rotation) {
        return "pos:" + name + "," + pos.x + "," + pos.y + "," + rotation;
    }

}
