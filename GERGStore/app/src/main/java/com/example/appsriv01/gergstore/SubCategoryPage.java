package com.example.appsriv01.gergstore;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

public class SubCategoryPage extends AppCompatActivity {


    private ImageView imagesub;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_category_page);
        imagesub = (ImageView) findViewById(R.id.imagesub);

        imagesub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(SubCategoryPage.this, ProductScreen.class);
                startActivity(i);

            }
        });



    }

}
