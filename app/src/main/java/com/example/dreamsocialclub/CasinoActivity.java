package com.example.dreamsocialclub;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class CasinoActivity extends AppCompatActivity implements View.OnClickListener {
    boolean blnButtonRotation = true;
    int intMember = 6;
    long longDegree = 6;
    ImageView wheel,iv_spin_now;
    TextView resultTv;
    private static final String[] sectors = { "4","5","6","7","0","1","2","3"};
    private static final Random RANDOM = new Random();
    private int degree = 0, degreeOld = 0;
    // We have 37 sectors on the wheel, we divide 360 by this value to have angle for each sector
    // we divide by 2 to have a half sector
    private static final float HALF_SECTOR = 360f / 10f / 2f;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_casino);
        wheel = findViewById(R.id.wheel);
        iv_spin_now = findViewById(R.id.iv_spin_now);
        resultTv = findViewById(R.id.resultTv);
        iv_spin_now.setOnClickListener(this);

    }

    private void spin_wheel() {
        degreeOld = degree % 360;
        // we calculate random angle for rotation of our wheel
        degree = RANDOM.nextInt(360) + 720;
        // rotation effect on the center of the wheel
        RotateAnimation rotateAnim = new RotateAnimation(degreeOld, degree,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        rotateAnim.setDuration(3600);
        rotateAnim.setFillAfter(true);
        rotateAnim.setInterpolator(new DecelerateInterpolator());

        rotateAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                // we empty the result text view when the animation start
                resultTv.setText("");
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                // we display the correct sector pointed by the triangle at the end of the rotate animation
                resultTv.setText(getSector(360 - (degree % 360)));
            }
            private String getSector(int degrees) {
                int i = 0;
                String text = null;

                do {
                    // start and end of each sector on the wheel
                    float start = HALF_SECTOR * (i * 2 + 1);
                    float end = HALF_SECTOR * (i * 2 + 3);

                    if (degrees >= start && degrees < end) {
                        // degrees is in [start;end[
                        // so text is equals to sectors[i];
                        text = sectors[i];
                    }

                    i++;
                    // now we can test our Android Roulette Game :)
                    // That's all !
                    // In the second part, you will learn how to add some bets on the table to play to the Roulette Game :)
                    // Subscribe and stay tuned !

                } while (text == null  &&  i < sectors.length);

                return text;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        wheel.startAnimation(rotateAnim);



    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_spin_now:
                spin_wheel();
                break;
        }
    }
}
