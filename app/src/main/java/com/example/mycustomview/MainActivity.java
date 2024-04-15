package com.example.mycustomview;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private Button sumar;
    private Button restar;
    private Button preferencias;
    private int colorBarra;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        CustomGradeView customGradeView = findViewById(R.id.customGradeView);
        customGradeView.setGrade(50);
        ActivityResultLauncher activityResult=registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    Intent data=result.getData();
                    Bundle extras=data.getExtras();
                    assert extras != null;
                    String textColor=extras.getString("textColor");
                    int gradeProgress=extras.getInt("gradeSize");
                    int textSize=extras.getInt("textSize");
                    int barColor=extras.getInt("barColor");
                    assert textColor != null;
                    if (textColor.equals("black")){
                        customGradeView.getTextView().setTextColor(ContextCompat.getColor(customGradeView.getTextView().getContext(),R.color.black));
                    } else if (textColor.equals("white")) {
                        customGradeView.getTextView().setTextColor(ContextCompat.getColor(customGradeView.getTextView().getContext(),R.color.white));
                    }else if (textColor.equals("blue")) {
                        customGradeView.getTextView().setTextColor(ContextCompat.getColor(customGradeView.getTextView().getContext(),R.color.blue));
                    }else if (textColor.equals("yellow")) {
                        customGradeView.getTextView().setTextColor(ContextCompat.getColor(customGradeView.getTextView().getContext(),R.color.yellow));
                    }else if (textColor.equals("red")) {
                        customGradeView.getTextView().setTextColor(ContextCompat.getColor(customGradeView.getTextView().getContext(),R.color.red));
                    }else if (textColor.equals("green")) {
                        customGradeView.getTextView().setTextColor(ContextCompat.getColor(customGradeView.getTextView().getContext(),R.color.green));
                    }
                    customGradeView.getTextView().setTextSize(textSize);
                    customGradeView.setGrade(gradeProgress);
                    if (barColor==1){
                        colorBarra=ContextCompat.getColor(this,R.color.black);
                        customGradeView.getProgressBar().getProgressDrawable().setColorFilter(colorBarra,PorterDuff.Mode.SRC_IN);
                    } else if (barColor==2) {
                        colorBarra=ContextCompat.getColor(this,R.color.white);
                        customGradeView.getProgressBar().getProgressDrawable().setColorFilter(colorBarra,PorterDuff.Mode.SRC_IN);
                    }else if (barColor==3) {
                        colorBarra=ContextCompat.getColor(this,R.color.blue);
                        customGradeView.getProgressBar().getProgressDrawable().setColorFilter(colorBarra,PorterDuff.Mode.SRC_IN);
                    }else if (barColor==4) {
                        colorBarra=ContextCompat.getColor(this,R.color.yellow);
                        customGradeView.getProgressBar().getProgressDrawable().setColorFilter(colorBarra,PorterDuff.Mode.SRC_IN);
                    }else if (barColor==5) {
                        colorBarra=ContextCompat.getColor(this,R.color.red);
                        customGradeView.getProgressBar().getProgressDrawable().setColorFilter(colorBarra,PorterDuff.Mode.SRC_IN);
                    }else if (barColor==6) {
                        colorBarra=ContextCompat.getColor(this,R.color.green);
                        customGradeView.getProgressBar().getProgressDrawable().setColorFilter(colorBarra,PorterDuff.Mode.SRC_IN);
                    }
                    else {
                        colorBarra=ContextCompat.getColor(this,R.color.black);

                    }
                }
        );
        sumar=findViewById(R.id.sumar);
        restar=findViewById(R.id.restar);
        preferencias=findViewById(R.id.preferencias);


        sumar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customGradeView.setGrade(customGradeView.getGrade()+1);
            }
        });


        restar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customGradeView.setGrade(customGradeView.getGrade()-1);
            }
        });

        preferencias.setOnClickListener(v -> {
            Intent intent = new Intent(this,Preferencias.class);
            activityResult.launch(intent);
        });
    }
}