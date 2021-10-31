package com.example.clash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class newone extends AppCompatActivity {
    ImageView imagepage,imageicon,star1,star2;
    TextView titalpage,start;
    Animation imgon,imgon2,imgon3,imgon4,buttone,buttwo,buthree,ltr;
    View bgprogress,bgprogresstop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newone);

        imagepage =(ImageView) findViewById(R.id.imagepage);
        imageicon=(ImageView) findViewById(R.id.imageicon);
        star1=(ImageView) findViewById(R.id.star1);
        star2=(ImageView) findViewById(R.id.star2);
        titalpage = (TextView)  findViewById(R.id.titalpage);
        start=(TextView) findViewById(R.id.start);
        bgprogress=(View) findViewById(R.id.bgprogress);
        bgprogresstop= (View)findViewById(R.id.bgprogresstop);

//        load animation
       imgon = AnimationUtils.loadAnimation(this, R.anim.imgon);
       imgon2 = AnimationUtils.loadAnimation(this, R.anim.imgon2);
        imgon3 = AnimationUtils.loadAnimation(this, R.anim.imgon3);
        imgon4 = AnimationUtils.loadAnimation(this, R.anim.imgon4);
       buttone = AnimationUtils.loadAnimation(this, R.anim.buttone);
       buttwo = AnimationUtils.loadAnimation(this, R.anim.buttwo);
       buthree = AnimationUtils.loadAnimation(this, R.anim.buthree);
       ltr= AnimationUtils.loadAnimation(this, R.anim.ltr);



//        export animate
        imagepage.startAnimation(imgon);
        imageicon.startAnimation(imgon2);
        star1.startAnimation(imgon3);
        star2.startAnimation(imgon4);
        titalpage.startAnimation(buttone);
        start.startAnimation( buthree );
        bgprogress.startAnimation(buttwo);
        bgprogresstop.startAnimation(ltr);

//      giving an event to another page
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(newone.this,MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        });


    }
}