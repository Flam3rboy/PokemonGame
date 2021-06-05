package com.pokemon.game;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.entities.Spawnpoint;
import de.gurkenlabs.litiengine.entities.Trigger;
import de.gurkenlabs.litiengine.graphics.Camera;
import de.gurkenlabs.litiengine.graphics.PositionLockCamera;

import javax.swing.*;

public class GameLogic {


    public static void init() {
        // we'll use a camera in our game that is locked to the location of the player
        Camera camera = new PositionLockCamera(Player.instance());
        camera.setClampToMap(true);
        Game.world().setCamera(camera);
        Game.screens().setChangeCooldown(0);
        Game.graphics().setBaseRenderScale(4.001f);
        ((JFrame) Game.window().getHostControl()).setResizable(false);
        //GameStatus.instance().db.insertAttack(1, "Glut", 10);

        // Game.world().setGravity(120);

        Game.world().onLoaded(e -> {
            Game.world().camera().updateFocus();
            System.out.println("world loaded");
            // spawn the player instance on the spawn point with the name "enter"
            Spawnpoint enter = e.getSpawnpoint("enter");
            if (enter != null) {
                enter.spawn(Player.instance());
            }
        });

        Game.world().loadEnvironment("StarterhausInnen");
        Trigger trigger_l_s_1 = Game.world().environment().getTrigger("TRIGGER_BOX_START_INNEN");
        trigger_l_s_1.addTriggerListener(new PokemonTriggerListener(TriggerBoxEnum.TRIGGER_BOX_START_INNEN));
        Trigger trigger_g_s_1 = Game.world().environment().getTrigger("TRIGGER_BOX_G_START_1");
        trigger_g_s_1.addTriggerListener(new PokemonTriggerListener(TriggerBoxEnum.TRIGGER_BOX_G_START_1));

    }


}
