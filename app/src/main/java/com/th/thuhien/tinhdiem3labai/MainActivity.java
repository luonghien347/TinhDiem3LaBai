package com.th.thuhien.tinhdiem3labai;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ImageView imgOne, imgTwo, imgThree;
    private TextView txtResult;
    private Button btnAgain;

    private String[] cardType = {"clubs", "spades", "diamonds", "hearts"};
    private int[] cardNumber = {1,2,3,4,5,6,7,8,9,10,11,12,13};
    private ArrayList<String> listCards;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AnhXa();
    }

    private void AnhXa() {

        imgOne = (ImageView) findViewById(R.id.imgOne);
        imgTwo = (ImageView) findViewById(R.id.imgTwo);
        imgThree = (ImageView) findViewById(R.id.imgThree);

        txtResult = (TextView) findViewById(R.id.textViewResult);

        btnAgain = (Button) findViewById(R.id.buttonAgain);

        listCards = new ArrayList<String>();

        View.OnClickListener ivClickChange = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView imageView = (ImageView) v;
                int randcardType = createRandomNumber(0, 3);
                int randcardNumber = createRandomNumber(0, 12);

                String cardValue = cardType[randcardType] + "_" + cardNumber[randcardNumber];

                while (listCards.contains(cardValue)){
                    randcardType = createRandomNumber(0, 3);
                    randcardNumber = createRandomNumber(0, 12);

                    cardValue = cardType[randcardType] + "_" + cardNumber[randcardNumber];
                }

                listCards.add(cardValue);

                if (listCards.size() == 3){
                    showResult(v);
                }
                int id = getResources().getIdentifier(cardValue,"drawable", getPackageName());

                imageView.setImageResource(id);
                imageView.setClickable(false);
            }
        };

        imgOne.setOnClickListener(ivClickChange);
        imgTwo.setOnClickListener(ivClickChange);
        imgThree.setOnClickListener(ivClickChange);

        btnAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playAgain(v);
            }
        });

    }

    public int createRandomNumber(int from, int to){
        Random random = new Random();
        return from + random.nextInt(to - from + 1);
    }

    private void showToast(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
    }

    public void playAgain(View v){
        imgOne.setImageResource(R.drawable.blue_back);
        imgTwo.setImageResource(R.drawable.blue_back);
        imgThree.setImageResource(R.drawable.blue_back);

        imgOne.setClickable(true);
        imgTwo.setClickable(true);
        imgThree.setClickable(true);

        txtResult.setText("Score: 0");
        listCards.clear();
    }

    public int getScore(String card){
        String splitCard[] = card.split("_");
        int score = Integer.parseInt(splitCard[1]);
        if (score >= 10){
            score = 0;
        }
        return score;
    }

    public void showResult(View v){
        int score = 0;
        Iterator<String> iterator = listCards.iterator();
        while (iterator.hasNext()){
            score += getScore(iterator.next());
        }
        txtResult.setText(String.format("Score: %d", score % 10));
    }
}