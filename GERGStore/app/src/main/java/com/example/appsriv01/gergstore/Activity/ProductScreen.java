package com.example.appsriv01.gergstore.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.appsriv01.gergstore.R;

public class ProductScreen extends AppCompatActivity {

    private Button cart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_screen);

        cart = (Button)findViewById(R.id.addcart);
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(ProductScreen.this,Cart.class);
                startActivity(i);

            }
        });

    }

}
