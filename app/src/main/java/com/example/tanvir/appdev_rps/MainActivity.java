package com.example.tanvir.appdev_rps;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ImageView rockimage,paperimage,scimage;
    TextView playerstate,compstate,playerpointtv,comppointtv;
    Random r=new Random();
    int playerpoint=0,comppoint=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playerpointtv=(TextView)findViewById(R.id.plpnt);
        comppointtv=(TextView)findViewById(R.id.cmppnt);

        rockimage=(ImageView)findViewById(R.id.rockimage);
        Bitmap rocktemp = BitmapFactory.decodeResource(getResources(), R.drawable.rock);
        Bitmap rock=getScaledBitmap(rocktemp,200);
        rockimage.setImageBitmap(rock);

        paperimage=(ImageView)findViewById(R.id.paperimage);
        Bitmap papertemp=BitmapFactory.decodeResource(getResources(),R.drawable.paper);
        Bitmap paper=getScaledBitmap(papertemp,200);
        paperimage.setImageBitmap(paper);

        scimage=(ImageView)findViewById(R.id.scissoimage);
        Bitmap sctemp=BitmapFactory.decodeResource(getResources(),R.drawable.scissor);
        Bitmap sc=getScaledBitmap(sctemp,200);
        scimage.setImageBitmap(sc);

        playerstate=(TextView)findViewById(R.id.playerstate);
        compstate=(TextView)findViewById(R.id.compstate);

        rockimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int x=r.nextInt();
                x%=3;
                playerstate.setText("ROCK");
                if(x==0){
                    compstate.setText("ROCK");
                }
                else if(x==1){
                    compstate.setText("PAPER");
                    comppoint++;
                    comppointtv.setText(Integer.toString(comppoint));
                }
                else{
                    compstate.setText("SCISSOR");
                    playerpoint++;
                    playerpointtv.setText(Integer.toString(playerpoint));
                }
                update();
            }
        });



        paperimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int x=r.nextInt();
                x%=3;
                playerstate.setText("PAPER");
                if(x==0){
                    compstate.setText("ROCK");
                    playerpoint++;
                    playerpointtv.setText(Integer.toString(playerpoint));
                }
                else if(x==1){
                    compstate.setText("PAPER");
                }
                else{
                    compstate.setText("SCISSOR");
                    comppoint++;
                    comppointtv.setText(Integer.toString(comppoint));
                }
                update();
            }
        });


        scimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int x=r.nextInt();
                x%=3;
                playerstate.setText("SCISSOR");
                if(x==0){
                    compstate.setText("ROCK");
                    comppoint++;
                    comppointtv.setText(Integer.toString(comppoint));
                }
                else if(x==1){
                    compstate.setText("PAPER");
                    playerpoint++;
                    playerpointtv.setText(Integer.toString(playerpoint));
                }
                else{
                    compstate.setText("SCISSOR");

                }
                update();
            }
        });

    }
    public static Bitmap getScaledBitmap(Bitmap b, int reqWidth)
    {
        int bWidth = b.getWidth();
        int bHeight = b.getHeight();
        float parentRatio = (float) bHeight / bWidth;
        int nWidth = reqWidth;
        int nHeight = (int) (nWidth*parentRatio);
        return Bitmap.createScaledBitmap(b, nWidth, nHeight, true);
    }
    void update(){
        if(playerpoint>=10){
            Intent intent = new Intent(MainActivity.this,Main2Activity.class);
            intent.putExtra("rslt", "Player Won");
            startActivity(intent);
            finish();
        }
        if(comppoint>=10){
            Intent intent = new Intent(MainActivity.this,Main2Activity.class);
            intent.putExtra("rslt", "Computer Won");
            startActivity(intent);
            finish();

        }
    }
}
