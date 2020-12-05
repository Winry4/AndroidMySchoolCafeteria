package com.example.myschoolcafeteria;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AnimatorSet animatorSet = new AnimatorSet();
        //first animation Translate
        ObjectAnimator translate=ObjectAnimator.ofFloat(
                findViewById(R.id.cafeteria),"translationX",-800,0);
        translate.setDuration(4000); //duración 4 segundos
        //2nd animation: rotate 360º
        ObjectAnimator rotate = ObjectAnimator.ofFloat(findViewById(R.id.cafeteria), "rotationX", 0f, 360f);
        rotate.setDuration(4000);
        //show both animations at the same time
        animatorSet.play(translate).with(rotate);
        //start animation
        animatorSet.start();

        rotate.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                //wait for a sec
                try {Thread.sleep(1000);}catch (Exception e){};
                Intent i = new Intent(getApplicationContext(),
                        OrderActivity.class);
                startActivity(i);
                finish();
            }

            @Override
            public void onAnimationCancel(Animator animator) {
            }

            @Override
            public void onAnimationRepeat(Animator animator) {
            }
        });
    }

}