package com.example.pi.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button startButton ;
    ArrayList<Integer> answers = new ArrayList<>();
    int locationOfCorrectAnswer;
    TextView resultText;
    TextView pointsText;
    TextView timerText;
    int score = 0;
    int numOfQuestions = 0;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button playAgainButton;
    RelativeLayout gameRelativeLayout;
    TextView sumTextView;

    public void generateQuestion(){
        Random rand = new Random();
        int a = rand.nextInt(21);
        int b = rand.nextInt(21);


        sumTextView.setText(Integer.toString(a) + " + " + Integer.toString(b));
        locationOfCorrectAnswer = rand.nextInt(4);
        answers.clear();
        int incorrectAnswer;
        for(int i = 0; i < 4; i++){
            if(i == locationOfCorrectAnswer){

                answers.add(a+b);

            }
            else{
                incorrectAnswer = rand.nextInt(41);

                while(incorrectAnswer == a + b){
                    incorrectAnswer = rand.nextInt(41);
                }
                answers.add(incorrectAnswer);
            }

        }
        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));

    }
public void playAgain(View view){
    score = 0;
    numOfQuestions = 0;
    timerText.setText("30s");
    pointsText.setText("0/0");
    resultText.setText("");
    playAgainButton.setVisibility(View.VISIBLE);

    generateQuestion();
    new CountDownTimer(30000,1000){

        @Override
        public void onTick(long millisUntilFinished) {
            timerText.setText(String.valueOf(millisUntilFinished  / 1000) + "s");
        }

        @Override
        public void onFinish() {
            playAgainButton.setVisibility(View.VISIBLE);
            timerText.setText("0s");
            resultText.setText("Your score: " + Integer.toString(score)+ "/"+ Integer.toString(numOfQuestions));
        }
    }.start();
}
public void startButton(View view){
    startButton.setVisibility(View.INVISIBLE);
    gameRelativeLayout.setVisibility(RelativeLayout.VISIBLE);
    playAgain(findViewById(R.id.playAgain));

}

public void chooseAnswer(View view){
    if(view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))){
        Log.i("correct","correct");

        score ++ ;
        resultText.setText("Correct !");
    }
    else{
        resultText.setText("Wrong !");
    }
    numOfQuestions ++ ;
    pointsText.setText(Integer.toString(score)+"/"+Integer.toString(numOfQuestions));
    generateQuestion();
}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = (Button) findViewById(R.id.button);
        button0 = (Button) findViewById(R.id.button3) ;
        button1 = (Button) findViewById(R.id.button4) ;
        button2 = (Button) findViewById(R.id.button5) ;
        button3 = (Button) findViewById(R.id.button6) ;
        playAgainButton = (Button) findViewById(R.id.playAgain);
        timerText = (TextView) findViewById(R.id.timerTextView);
        sumTextView = (TextView) findViewById(R.id.sumTextView);
        resultText = (TextView) findViewById(R.id.resultTextView);
        pointsText = (TextView) findViewById(R.id.pointsTextView);
        gameRelativeLayout = (RelativeLayout)findViewById(R.id.gameRelativeLayout);

    }
}
