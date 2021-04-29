package com.pokemon.game;

public class GameStatus {
    private TriggerBoxEnum triggerBox;
    private String nextDialog;
    private boolean startup;
    private GameDialogs dialogs;
    private boolean firstDialog;
    private boolean ingame;
    private static GameStatus instance;

    public GameStatus() {
        this.startup = true;
        this.dialogs = new GameDialogs();
        this.firstDialog = false;
        this.ingame = false;
    }

    public static GameStatus instance() {
        if (instance == null) {
            instance = new GameStatus();
        }

        return instance;
    }

    public boolean isIngame() {
        return ingame;
    }

    public void setIngame(boolean value) {
        ingame = value;
    }

    public boolean isStartup() {
        return startup;
    }

    public void setStartup(boolean startup) {
        this.startup = startup;
    }

    public boolean isFirstDialog(){
        return firstDialog;
    }

    public void setFirstDialog(boolean firstDialog){
        this.firstDialog = firstDialog;
    }

    public String getNextDialog() {
        return nextDialog;
    }

    public void triggerDialog(){
        if(isStartup() && getTriggerBox() == TriggerBoxEnum.NONE){
            nextDialog = this.dialogs.getNextIntroText();
            if(nextDialog == null){
                setStartup(false);
                setFirstDialog(true);
            }
        }
        if(isFirstDialog() && getTriggerBox() == TriggerBoxEnum.TRIGGER_BOX_1){
            nextDialog = this.dialogs.getNextDialog1();
            if(nextDialog == null){
               setFirstDialog(false);
            }
        }
    }
    public TriggerBoxEnum getTriggerBox() {
        return triggerBox;
    }

    public void setTriggerBox(TriggerBoxEnum triggerBox) {
        this.triggerBox = triggerBox;
    }
}