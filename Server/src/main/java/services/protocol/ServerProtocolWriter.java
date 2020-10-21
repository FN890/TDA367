package services.protocol;

import data.Vector2;
import model.Game;
import model.Player;

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

    String writeMissile(float x, float y, float rotation, float playerXSpeed, float playerYSpeed) {
        return "missile:" + x + "," + y + "," + rotation + "," + playerXSpeed + "," + playerYSpeed;
    }

    String writeGameInfo(Game game) {
        StringBuilder sb = new StringBuilder();

        sb.append(game.getId());
        sb.append(",").append(game.isRunning());

        for (Player p : game.getPlayers()) {
            sb.append(",").append(p.getName());
        }

        return writeResponse(sb.toString());
    }

    // Game Updates
    String writeGamePlayerUpdate(String name, boolean joined) {
        if (joined) {
            return "game:joined," + name;
        }
        return "game:left," + name;
    }

    String writeGamePlayerWinner(String name) {
        return "game:winner," + name;
    }

    String writeGameStatusUpdate(boolean started) {
        if (started) {
            return "game:started";
        }
        return "game:paused";
    }

    String writeGameEndedUpdate() {
        return "game:ended";
    }

}
