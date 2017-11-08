package axis.guren.com.cinemania.QuizType;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.valdesekamdem.library.mdtoast.MDToast;

import java.util.List;


import axis.guren.com.cinemania.MainActivity;
import axis.guren.com.cinemania.R;

/**
 * Created by Guren on 5/3/2017.
 */

public class QuizActivity extends AppCompatActivity {

    List<Question> quesList;
    int qid=0;
    Question currentQ;
    EditText etanswer;

    String title;
    String rawttitle="raw/";
    int hints;
    int savelevel , savehint;
    String hintcast ="",hintsynop="",hintype="";
    String hintfinal ="" , synopsisfinal ="";

    TextView txhint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);


        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getBaseContext()); // Save
        savelevel = sp.getInt("Level", 0); // LEVEL
        savehint = sp.getInt("hint", 5); // HINT
        hints = savehint;
        qid = savelevel;



        etanswer = (EditText)findViewById(R.id.et_answer); // EDIT TEXT
        TextView txlevel = (TextView)findViewById(R.id.tx_level);
        txhint = (TextView)findViewById(R.id.tx_hint);

        Button btsynopsis = (Button)findViewById(R.id.bt_syn);
        Button btcast = (Button)findViewById(R.id.bt_cast);

        txlevel.setText("Level "+qid);
        txhint.setText("Hints "+hints);


        DbHelper db=new DbHelper(this); // DB helper - Database
        quesList=db.getAllQuestions(); // get all movie question
        currentQ=quesList.get(qid); // get the number of movies
        title = currentQ.getQUESTION();

        final VideoView videoView = (VideoView) findViewById(R.id.videoView1);
        VideoView videoview = (VideoView) findViewById(R.id.videoView1);
        Uri uri = Uri.parse("android.resource://"+getPackageName()+"/"+rawttitle+title);

        videoview.setVideoURI(uri);
        videoView.start();
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp. setLooping(true);
            }
        });

        btsynopsis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // Hint synopsis

            hintype = "Synopsis";
            ConfirmUseHint();





            }
        });

       btcast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // hint cast

            hintype = "Cast";
            ConfirmUseHint();
            }
        });




        etanswer.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {

                    String usersanswer = etanswer.getText().toString();
                    String finaluseranswer = usersanswer.toLowerCase();
                    if(currentQ.getANSWER().equals(finaluseranswer)){ // CORRECT ANSWER


                        if(qid<15){
                          //  ProceedtoNextRound();
                            MDToast mdToast = MDToast.makeText(QuizActivity.this, "May tama ka :D",MDToast.LENGTH_LONG, MDToast.TYPE_SUCCESS);
                            mdToast.show();
                            int nextlevel  = qid + 1;
                           // alertDialog.dismiss();

                            currentQ=quesList.get(qid);

                            SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(QuizActivity.this);
                            SharedPreferences.Editor edit = sp.edit();
                            edit.clear();
                            edit.apply();
                            edit.putInt("Level", nextlevel);
                            edit.putInt("Hints", hints);
                            edit.apply();
                            edit.commit();

                            Intent intent = new Intent(QuizActivity.this, QuizActivity.class);
                            startActivity(intent);
                            overridePendingTransition(R.anim.animation_enter, R.anim.left_to_right);


                        }


                    }

                    else{ // WRONG ANSWER

                        MDToast mdToast = MDToast.makeText(QuizActivity.this, "Mali sagot mo.",MDToast.LENGTH_LONG, MDToast.TYPE_ERROR);
                        mdToast.show();
                      //  WrongAnswer();

                    }



                    return true;
                }
                return false;
            }
        });



    }

    private void ProceedtoNextRound() {


        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(QuizActivity.this, R.style.MyAlertDialogStyle);

        alertDialogBuilder.setTitle("Diagnose");
        alertDialogBuilder.setIcon(R.drawable.logo);

        alertDialogBuilder.setMessage("Correct!");
        /*
        // set positive button: Yes message
        alertDialogBuilder.setPositiveButton("Next", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {



            }
        });
        */
        final AlertDialog alertDialog = alertDialogBuilder.create();
        // show alert
        alertDialog.show();

        // ====
        // Hide after some seconds
        final Handler handler  = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (alertDialog.isShowing()) {

                    int nextlevel  = qid + 1;
                    alertDialog.dismiss();

                    currentQ=quesList.get(qid);

                    SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(QuizActivity.this);
                    SharedPreferences.Editor edit = sp.edit();
                    edit.clear();
                    edit.apply();
                    edit.putInt("Level", nextlevel);
                    edit.putInt("Hints", hints);
                    edit.apply();
                    edit.commit();

                    Intent intent = new Intent(QuizActivity.this, QuizActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.animation_enter, R.anim.left_to_right);
                }
            }
        };

        alertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                handler.removeCallbacks(runnable);
            }
        });

        handler.postDelayed(runnable, 2000);
    }

    @Override
    public void onBackPressed() {
        Log.d("CDA", "onBackPressed Called");

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(QuizActivity.this);
        SharedPreferences.Editor edit = sp.edit();
        edit.clear();
        edit.apply();
        edit.putInt("Level", qid);
        edit.putInt("Hints", hints);
        edit.apply();
        edit.commit(); // Save current level

        Intent intent = new Intent(QuizActivity.this, MainActivity.class);
        startActivity(intent);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        overridePendingTransition(R.anim.animation_enter, R.anim.left_to_right);

    }

    private void WrongAnswer(){

        MDToast mdToast = MDToast.makeText(QuizActivity.this, "Mali sagot mo.",MDToast.LENGTH_LONG, MDToast.TYPE_ERROR);
        mdToast.show();

        etanswer.setBackgroundColor(getResources().getColor(R.color.colorAccent));

        // Hide after some seconds
        final Handler handler  = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {

                etanswer.setBackgroundColor(getResources().getColor(R.color.colorETNormal));

            }
        };


        handler.postDelayed(runnable, 1000);
    }


    private void ConfirmUseHint() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(QuizActivity.this, R.style.MyAlertDialogStyle);

        alertDialogBuilder.setTitle("Use hint");
        alertDialogBuilder.setIcon(R.drawable.logo);

        alertDialogBuilder.setMessage("Are you sure you want to use hint?");
        // set positive button: Yes message
        alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                if(hints>0) {
                    switch (hintype) {


                        case "Synopsis":

                            if(hintfinal.equals("hinted")){

                                Synopsis();

                            }
                            else{

                                hints = hints - 1;
                                hintsynop = currentQ.getOPTB();
                                txhint.setText("Hints "+hints);
                                hintfinal = "hinted";
                                Synopsis();

                            }



                            break;

                        case "Cast":

                            if(synopsisfinal.equals("getit")){

                                Cast();

                            }

                            else{

                                hints = hints - 1;
                                hintcast = currentQ.getOPTA();
                                txhint.setText("Hints "+hints);
                                synopsisfinal = "getit";
                                Cast();
                            }


                            break;

                    }

                }
                else {

                    Toast.makeText(QuizActivity.this, "Ubos na", Toast.LENGTH_LONG).show();
                }

                txhint.setText("Hints "+hints);

            }
        });

        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

              dialog.dismiss();
            }
        });
        // set negative button: No message

        AlertDialog alertDialog = alertDialogBuilder.create();
        // show alert
        alertDialog.show();
    }

    private void Synopsis() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(QuizActivity.this, R.style.MyAlertDialogStyle);

        alertDialogBuilder.setTitle("Cinemania");
        alertDialogBuilder.setIcon(R.drawable.playnow);
        alertDialogBuilder.setMessage(hintsynop);


        // set positive button: Yes message
        alertDialogBuilder.setPositiveButton("Got it", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {


            }
        });



        AlertDialog alertDialog = alertDialogBuilder.create();
        // show alert
        alertDialog.show();
    }

    private void Cast() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(QuizActivity.this, R.style.MyAlertDialogStyle);

        alertDialogBuilder.setTitle("Cinemania");
        alertDialogBuilder.setIcon(R.drawable.playnow);
        alertDialogBuilder.setMessage(hintcast);


        // set positive button: Yes message
        alertDialogBuilder.setPositiveButton("Got it", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {


            }
        });



        AlertDialog alertDialog = alertDialogBuilder.create();
        // show alert
        alertDialog.show();
    }






}
