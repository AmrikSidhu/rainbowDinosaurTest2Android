package com.example.tappyspaceship01;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.Random;

public class GameEngine extends SurfaceView implements Runnable {

    // Android debug variables
    final static String TAG="DINO-RAINBOWS";

    // screen size
    int screenHeight;
    int screenWidth;

    // game state
    boolean gameIsRunning;

    // threading
    Thread gameThread;


    // drawing variables
    SurfaceHolder holder;
    Canvas canvas;
    Paint paintbrush;


    //Player Image


    Player player;
    Item item;
    Bitmap playerImage;
    int playerXposition;
    int playerYposition;
    Rect playerHitBox;
    int playerHitBoxXPosition;
    int playerHitBoxYPosition;

    // enemy

    Bitmap enemyOneImage;
    int enemyOneXposition;
    int enemyOneYposition;
    Rect enemyOneHitBox;
    int enemyOneHitBoxXPosition;
    int enemyOneHitBoxYPosition;




    Bitmap enemySecondImage;
    int enemySecondImageXPosition;
    int enemySecondImageYPosition;
    Rect enemySecondHitBox;
    int hitBoxSecondEnemyXPosition;
    int hitBoxSecondEnemyYPosition;

    Bitmap enemyThirdImage;
    int enemyThirdImageXPosition;
    int enemyThirdImageYPosition;
    Rect enemyThirdHitBox;
    int hitBoxThirdEnemyXPosition;
    int hitBoxThirdEnemyYPosition;


    // -----------------------------------
    // GAME SPECIFIC VARIABLES
    // -----------------------------------

    // ----------------------------
    // ## SPRITES
    // ----------------------------

    // represent the TOP LEFT CORNER OF THE GRAPHIC

    // ----------------------------
    // ## GAME STATS
    // ----------------------------


    public GameEngine(Context context, int w, int h) {
        super(context);

        this.holder = this.getHolder();
        this.paintbrush = new Paint();

        this.screenWidth = w;
        this.screenHeight = h;

//        player = new Player(context,100,100);

        this.playerXposition = 1500;
        this.playerYposition = 300;
        this.playerHitBoxXPosition = 300;
        this.playerHitBoxYPosition = 300;

// enemy

        this.enemyOneXposition=20;
        this.enemyOneYposition = 300;
        this.enemyOneHitBoxXPosition = 20;
        this.enemyOneHitBoxYPosition = 300;




        this.enemySecondImageXPosition=20;
        this.enemySecondImageYPosition = 3;
        this.hitBoxSecondEnemyXPosition=20;
        this.hitBoxSecondEnemyYPosition = 3;

        this.enemyThirdImageXPosition=20;
        this.enemyThirdImageYPosition = 597;
        this.hitBoxThirdEnemyXPosition=20;
        this.hitBoxThirdEnemyYPosition = 597;


        this.playerImage = BitmapFactory.decodeResource(this.getContext().getResources(),
                R.drawable.dino64);

        this.enemyOneImage = BitmapFactory.decodeResource(this.getContext().getResources(),R.drawable.poop64);
        this.enemySecondImage= BitmapFactory.decodeResource(this.getContext().getResources(),R.drawable.poop32);
        this.enemyThirdImage= BitmapFactory.decodeResource(this.getContext().getResources(),R.drawable.poop64);
        this.playerHitBox = new Rect(this.playerHitBoxXPosition,


                this.playerHitBoxYPosition,
                this.playerHitBoxXPosition+playerImage.getWidth(),
                this.playerHitBoxYPosition+playerImage.getHeight());

        this.enemyOneHitBox = new Rect(this.enemyOneXposition,
                this.enemyOneYposition,
                this.enemyOneHitBoxXPosition+enemyOneImage.getWidth(),
                this.enemyOneHitBoxYPosition+enemyOneImage.getHeight());

        // enemy box 2
        this.enemySecondHitBox = new Rect(this.hitBoxSecondEnemyXPosition,
                this.hitBoxSecondEnemyYPosition,
                hitBoxSecondEnemyXPosition+enemySecondImage.getWidth(),
                hitBoxSecondEnemyYPosition+enemySecondImage.getHeight());

// enemy box 3
        this.enemyThirdHitBox = new Rect(this.enemyThirdImageXPosition,
                this.enemyThirdImageYPosition,
                this.hitBoxThirdEnemyXPosition+enemyThirdImage.getWidth(),
                this.hitBoxThirdEnemyYPosition+enemyThirdImage.getHeight());


        this.printScreenInfo();
    }



    private void printScreenInfo() {

        Log.d(TAG, "Screen (w, h) = " + this.screenWidth + "," + this.screenHeight);
    }

    private void spawnPlayer() {
        //@TODO: Start the player at the left side of screen
    }
    private void spawnEnemyShips() {
        Random random = new Random();

        //@TODO: Place the enemies in a random location

    }

    // ------------------------------
    // GAME STATE FUNCTIONS (run, stop, start)
    // ------------------------------
    @Override
    public void run() {
        while (gameIsRunning == true) {
            this.updatePositions();
            this.redrawSprites();
            this.setFPS();
        }
    }


    public void pauseGame() {
        gameIsRunning = false;
        try {
            gameThread.join();
        } catch (InterruptedException e) {
            // Error
        }
    }

    public void startGame() {
        gameIsRunning = true;
        gameThread = new Thread(this);
        gameThread.start();
    }


    // ------------------------------
    // GAME ENGINE FUNCTIONS
    // - update, draw, setFPS
    // ------------------------------

    public void updatePositions() {
        // make player move
        this.playerYposition =this.playerYposition;
        this.playerHitBox.left  = this.playerXposition;
        this.playerHitBox.top = this.playerYposition;
        this.playerHitBox.right  = this.playerXposition + this.playerImage.getWidth();
        this.playerHitBox.bottom = this.playerYposition + this.playerImage.getHeight();

        // moving Enemy1
        this.enemyOneXposition = this.enemyOneXposition +35;
        this.enemyOneHitBox.left = this.enemyOneXposition;
        this.enemyOneHitBox.top = this.enemyOneYposition;
        this.enemyOneHitBox.right = this.enemyOneXposition + this.enemyOneImage.getWidth();
        this.enemyOneHitBox.bottom = this.enemyOneYposition + this.enemyOneImage.getHeight();


// Mobving Enemy 2

        this.enemySecondImageXPosition = enemySecondImageXPosition + 30;
        this.enemySecondHitBox.left = this.enemySecondImageXPosition;
        this.enemySecondHitBox.top = this.enemySecondImageYPosition;
        this.enemySecondHitBox.right = this.enemySecondImageXPosition + this.enemySecondImage.getWidth();
        this.enemySecondHitBox.bottom = this.enemySecondImageYPosition + this.enemySecondImage.getHeight();

// Mobving Enemy 3 Moving

        this.enemyThirdImageXPosition = enemyThirdImageXPosition + 25;
        this.enemyThirdHitBox.left = this.enemyThirdImageXPosition;
        this.enemyThirdHitBox.top = this.enemyThirdImageYPosition;
        this.enemyThirdHitBox.right = this.enemyThirdImageXPosition + this.enemyThirdImage.getWidth();
        this.enemyThirdHitBox.bottom = this.enemyThirdImageYPosition + this.enemyThirdImage.getHeight();

    }

    public void redrawSprites() {
        if (this.holder.getSurface().isValid()) {
            this.canvas = this.holder.lockCanvas();

            //----------------

            // configure the drawing tools
            this.canvas.drawColor(Color.argb(255,255,255,255));
            paintbrush.setColor(Color.WHITE);


            // DRAW THE PLAYER HITBOX
            // ------------------------
            // 1. change the paintbrush settings so we can see the hitbox
            paintbrush.setColor(Color.BLUE);
            paintbrush.setStyle(Paint.Style.STROKE);
            paintbrush.setStrokeWidth(5);

            canvas.drawBitmap(playerImage, playerXposition, playerYposition, paintbrush);
            canvas.drawBitmap(enemyOneImage,enemyOneXposition,enemyOneYposition,paintbrush);
            canvas.drawBitmap(enemySecondImage,enemySecondImageXPosition,enemySecondImageYPosition,paintbrush);
            canvas.drawBitmap(enemyThirdImage,enemyThirdImageXPosition,enemyThirdImageYPosition,paintbrush);

            canvas.drawRect(playerHitBox,paintbrush);
            canvas.drawRect(enemySecondHitBox,paintbrush);
            canvas.drawRect(enemyThirdHitBox,paintbrush);
            canvas.drawRect(enemyOneHitBox,paintbrush);
            //----------------
            this.holder.unlockCanvasAndPost(canvas);
        }
    }

    public void setFPS() {
        try {
            gameThread.sleep(120);
        }
        catch (Exception e) {

        }
    }

    // ------------------------------
    // USER INPUT FUNCTIONS
    // ------------------------------


    String fingerAction = "";

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int userAction = event.getActionMasked();
        //@TODO: What should happen when person touches the screen?
        if (userAction == MotionEvent.ACTION_DOWN) {

        }
        else if (userAction == MotionEvent.ACTION_UP) {

        }

        return true;
    }
}
