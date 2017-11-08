package axis.guren.com.cinemania;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;

import axis.guren.com.cinemania.QuizType.QuizActivity;
import axis.guren.com.cinemania.QuizType.QuizProtoActivity;

public class MainActivity extends AppCompatActivity {


    Button Play, Set , About,Help;
    String Difficult;
  //  BackgroundSound mBackgroundSound = new BackgroundSound();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Play = (Button)findViewById(R.id.btn_play);
        Set  = (Button)findViewById(R.id.btn_settings);
        About = (Button)findViewById(R.id.btn_about);
        Help = (Button)findViewById(R.id.btn_help);



        Play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this, QuizActivity.class);
                startActivity(i);
             //   mBackgroundSound.cancel(true);
            }
        });

       Set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

         //       Intent i = new Intent(MainActivity.this, QuizProtoActivity.class);
            //    startActivity(i);

            }
        });

        About.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        Help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this, HowToUseActivity.class);
                startActivity(i);

            }
        });


}


    /*
    public class BackgroundSound extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            MediaPlayer player = MediaPlayer.create(MainActivity.this, R.raw.bgmusic);
            player.setLooping(true); // Set looping
            player.setVolume(1.0f, 1.0f);
            player.start();

            return null;
        }

    }
    */


    public void onResume() {
        super.onResume();
     // new BackgroundSound().execute();
    }

    public void onPause() {
        super.onPause();
      //  mBackgroundSound.cancel(true);
    }








}
