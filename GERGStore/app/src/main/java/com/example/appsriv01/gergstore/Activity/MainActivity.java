
package com.example.appsriv01.gergstore.Activity;



import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toolbar;

import com.example.appsriv01.gergstore.R;

public class MainActivity extends AppCompatActivity
{

    private Toolbar myToolbar;
    private ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // myToolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(myToolbar);
        image = (ImageView)findViewById(R.id.image);

        image.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this ,CategoryPage.class);
                startActivity(i);

            }
        });
    }

}

