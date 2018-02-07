package com.tamu.jcabelloc.mathoperations;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Arrays;
import java.util.Random;

public class Game extends AppCompatActivity {
    Random rand = new Random();
    Button answer0;
    Button answer1;
    Button answer2;
    Button answer3;
    TextView questionTextView;
    int indexCorrectAnswer;
    String operator;
    int[] answers = new int[4];
    int operator1;
    int operator2;
    int correctAnswers;
    int wrongAnswers;
    TextView scoreTextView;
    Toast myToast;

    public void checkAnswer(View view){
        if (indexCorrectAnswer == Integer.valueOf(view.getTag().toString()))
        {
            myToast.setText("Correct!");
            myToast.show();
            correctAnswers += 1;
        }else {
            myToast.setText("Wrong!");
            myToast.show();
            wrongAnswers += 1;
        }
        generateQuestion(operator);
        scoreTextView.setText(String.valueOf(correctAnswers) + " / " + String.valueOf(correctAnswers + wrongAnswers));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        myToast = Toast.makeText(this, "", Toast.LENGTH_SHORT);
        answer0 = (Button)findViewById(R.id.answer0);
        answer1 = (Button)findViewById(R.id.answer1);
        answer2 = (Button)findViewById(R.id.answer2);
        answer3 = (Button)findViewById(R.id.answer3);
        questionTextView = (TextView)findViewById(R.id.questionTextView);
        final TextView timerTextView = (TextView)findViewById(R.id.timerTextView);
        scoreTextView = (TextView)findViewById(R.id.scoreTextView);

        CountDownTimer countDownTimer = new CountDownTimer(30000 + 100, 1000) {
            @Override
            public void onTick(long l) {
                //Log.i("Clock", String.valueOf((int)l / 1000));
                int seconds = (int)l / 1000;
                timerTextView.setText("00:" + (seconds<10?"0"+String.valueOf(seconds):String.valueOf(seconds)));
            }
            @Override
            public void onFinish() {
                timerTextView.setText("00:00");
                answer0.setVisibility(View.INVISIBLE);
                answer1.setVisibility(View.INVISIBLE);
                answer2.setVisibility(View.INVISIBLE);
                answer3.setVisibility(View.INVISIBLE);
                myToast.setText("Game Over! \n Your Final score is: " + String.valueOf(correctAnswers) + " / " + String.valueOf(correctAnswers + wrongAnswers));
                myToast.show();
                //Toast.makeText(Game.this, "Game Over! \n Your Final score is: " + String.valueOf(correctAnswers) + " / " + String.valueOf(correctAnswers + wrongAnswers), Toast.LENGTH_LONG).show();
            }
        }.start();

        Intent intent = getIntent();
        operator = intent.getStringExtra("operator");
        generateQuestion(operator);


    }
    public void generateQuestion(String typeOperator){
        String strOperator = "";
        switch (typeOperator) {
            case "addition":
                startAdditionQuestion();
                strOperator = "+";
                break;
            case "subtraction":
                startSubtractionQuestion();
                strOperator = "-";
                break;
            case "multiplication":
                startMultiplicationQuestion();
                strOperator = "x";
                break;
            case "division":
                startDivisionQuestion();
                strOperator = "/";
                break;
            case "random":
                {   int op = rand.nextInt(3);
                    switch (op) {
                        case 0: startAdditionQuestion(); strOperator = "+"; break;
                        case 1: startSubtractionQuestion(); strOperator = "-"; break;
                        case 2: startMultiplicationQuestion(); strOperator = "x"; break;
                        case 3: startDivisionQuestion(); strOperator = "/"; break;
                    }
                    break;
                }
            default:
                break;
        }
        questionTextView.setText( String.valueOf(operator1) + " " + strOperator + " " + String.valueOf(operator2) );
        answer0.setText(String.valueOf(answers[0]));
        answer1.setText(String.valueOf(answers[1]));
        answer2.setText(String.valueOf(answers[2]));
        answer3.setText(String.valueOf(answers[3]));

    }
    public void startAdditionQuestion(){
        operator1 = rand.nextInt(50) + 1;
        operator2 = rand.nextInt(50) + 1;
        int correctAnswer = operator1 + operator2;
        indexCorrectAnswer = rand.nextInt(4);
        for (int i = 0; i < 4; i++ ) {
            if (i == indexCorrectAnswer) {
                answers[i] = correctAnswer;
            } else {
                do {
                    answers[i] = rand.nextInt(100) + 1;
                } while (answers[i]==correctAnswer);
            }
        }

    }
    public void startSubtractionQuestion(){
        operator1 = rand.nextInt(50) + 51;
        operator2 = rand.nextInt(50) + 1;
        int correctAnswer = operator1 - operator2;
        indexCorrectAnswer = rand.nextInt(4);
        for (int i = 0; i < 4; i++ ) {
            if (i == indexCorrectAnswer) {
                answers[i] = correctAnswer;
            } else {
                do {
                    answers[i] = rand.nextInt(99) + 1;
                } while (answers[i]==correctAnswer);
            }
        }
    }

    public void startMultiplicationQuestion(){
        operator1 = rand.nextInt(10) + 1;
        operator2 = rand.nextInt(10) + 1;
        int correctAnswer = operator1 * operator2;
        indexCorrectAnswer = rand.nextInt(4);
        for (int i = 0; i < 4; i++ ) {
            if (i == indexCorrectAnswer) {
                answers[i] = correctAnswer;
            } else {
                do {
                    answers[i] = rand.nextInt(100) + 1;
                } while (answers[i]==correctAnswer);
            }
        }
    }

    public void startDivisionQuestion(){
        operator1 = rand.nextInt(8) + 3;
        operator2 = rand.nextInt(5) + 6;
        int correctAnswer = operator1 * operator2;
        operator2 = operator1;
        operator1 = correctAnswer;
        correctAnswer = operator1 / operator2;
        indexCorrectAnswer = rand.nextInt(4);
        for (int i = 0; i < 4; i++ ) {
            if (i == indexCorrectAnswer) {
                answers[i] = correctAnswer;
            } else {
                do {
                    answers[i] = rand.nextInt(10) + 1;
                } while (answers[i]==correctAnswer);
            }
        }
    }


}
