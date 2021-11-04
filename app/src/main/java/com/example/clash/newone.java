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
    ImageView imagepage,imageicon,star1,star2,star3,star4,star5,star6,star7,star8,star9,
               star10,star11,star12,star13,star14,star15;

    TextView titalpage,start;

    Animation imgon,imgon2,imgon3,imgon4, imgon5, imgon6, imgon7, imgon8, imgon9, imgon10,
              imgon11, imgon12, imgon13, imgon14, imgon15, imgon16, imgon17,buttone,buttwo,buthree,ltr;

    View bgprogress,bgprogresstop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newone);

        imagepage =(ImageView) findViewById(R.id.imagepage);
        imageicon=(ImageView) findViewById(R.id.imageicon);
        star1=(ImageView) findViewById(R.id.star1);
        star2=(ImageView) findViewById(R.id.star2);
        star3=(ImageView) findViewById(R.id.star3);
        star4=(ImageView) findViewById(R.id.star4);
        star5=(ImageView) findViewById(R.id.star5);
        star6=(ImageView) findViewById(R.id.star6);
        star7=(ImageView) findViewById(R.id.star7);
        star8=(ImageView) findViewById(R.id.star8);
        star9=(ImageView) findViewById(R.id.star9);
        star10=(ImageView) findViewById(R.id.star10);
        star11=(ImageView) findViewById(R.id.star11);
        star12=(ImageView) findViewById(R.id.star12);
        star13=(ImageView) findViewById(R.id.star13);
        star14=(ImageView) findViewById(R.id.star14);
        star15=(ImageView) findViewById(R.id.star15);
        titalpage = (TextView)  findViewById(R.id.titalpage);
        start=(TextView) findViewById(R.id.start);
        bgprogress=(View) findViewById(R.id.bgprogress);
        bgprogresstop= (View)findViewById(R.id.bgprogresstop);



//        load animation
       imgon = AnimationUtils.loadAnimation(this, R.anim.imgon);
       imgon2 = AnimationUtils.loadAnimation(this, R.anim.imgon2);
        imgon3 = AnimationUtils.loadAnimation(this, R.anim.imgon3);
        imgon4 = AnimationUtils.loadAnimation(this, R.anim.imgon4);
        imgon5 = AnimationUtils.loadAnimation(this, R.anim.imgon5);
        imgon6 = AnimationUtils.loadAnimation(this, R.anim.imgon6);
        imgon7 = AnimationUtils.loadAnimation(this, R.anim.imgon7);
        imgon8 = AnimationUtils.loadAnimation(this, R.anim.imgon8);
        imgon9 = AnimationUtils.loadAnimation(this, R.anim.imgon9);
        imgon10 = AnimationUtils.loadAnimation(this, R.anim.imgon10);
        imgon11 = AnimationUtils.loadAnimation(this, R.anim.imgon11);
        imgon12= AnimationUtils.loadAnimation(this, R.anim.imgon12);
        imgon13 = AnimationUtils.loadAnimation(this, R.anim.imgon13);
        imgon14 = AnimationUtils.loadAnimation(this, R.anim.imgon14);
        imgon15 = AnimationUtils.loadAnimation(this, R.anim.imgon15);
        imgon16 = AnimationUtils.loadAnimation(this, R.anim.imgon16);
        imgon17 = AnimationUtils.loadAnimation(this, R.anim.imgon17);
        buttone = AnimationUtils.loadAnimation(this, R.anim.buttone);
       buttwo = AnimationUtils.loadAnimation(this, R.anim.buttwo);
       buthree = AnimationUtils.loadAnimation(this, R.anim.buthree);
       ltr= AnimationUtils.loadAnimation(this, R.anim.ltr);



//        export animate
        imagepage.startAnimation(imgon);
        imageicon.startAnimation(imgon2);
        star1.startAnimation(imgon3);
        star2.startAnimation(imgon4);
        star3.startAnimation(imgon5);
        star4.startAnimation(imgon6);
        star5.startAnimation(imgon7);
        star6.startAnimation(imgon8);
        star7.startAnimation(imgon9);
        star8.startAnimation(imgon10);
        star9.startAnimation(imgon11);
        star10.startAnimation(imgon12);
        star11.startAnimation(imgon13);
        star12.startAnimation(imgon14);
        star13.startAnimation(imgon15);
        star14.startAnimation(imgon16);
        star15.startAnimation(imgon17);
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