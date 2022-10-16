package com.example.flappy_bird;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    // Draws the main screen
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // Starts the game by creating a game screen
    public void startGame(View view){
        Intent intent = new Intent(this, StartGame.class);
        startActivity(intent);
        finish();
    }
}