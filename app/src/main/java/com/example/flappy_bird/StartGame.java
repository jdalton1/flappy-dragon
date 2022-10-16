package com.example.flappy_bird;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;


public class StartGame extends Activity {

    GameView gameView;

    // Starts the GameView Class
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gameView = new GameView(this);
        setContentView(gameView);
    }
}
