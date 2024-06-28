package src;

public enum GameState {
    WIN("YOU WON"), LOST ("YOU LOST"), CONTINUE ("");

    private String message;

    GameState(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
