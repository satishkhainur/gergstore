package com.example.appsriv01.gergstore;

import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class SubCategoryPage extends AppCompatActivity {


   ImageView btnlistview;
    ImageView btngridview;
    //private ImageView imagesub;
   // private  TextView text11,text12,text13;
       GridView grid;
        ListView list;
        boolean flag=true;

    String[] text1 = {
            "Product1",
            "Product2",
            "Product3",
            "Product4",
            "Product5",
            "Product6",
            "Product7",
            "Product8",
            "Product9",
            "Product10",
            "Product1",
            "Product12",
            "Product13",
            "Product14",
            "Product15"
    } ;

    String[] text2 = {
            "Women's Wear",
            "Women's Wear",
            "Women's Wear",
            "Women's Wear",
            "Women's Wear",
            "Women's Wear",
            "Women's Wear",
            "Women's Wear",
            "Women's Wear",
            "Women's Wear",
            "Women's Wear",
            "Women's Wear",
            "Women's Wear",
            "Women's Wear",
            "Women's Wear"
    } ;

    String[] text3 = {
            "Rs 5823.80",
            "Rs 3568.80",
            "Rs 4289.80",
            "Rs 5823.80",
            "Rs 5823.80",
            "Rs 3568.80",
            "Rs 4289.80",
            "Rs 5823.80",
            "Rs 3568.80",
            "Rs 4289.80",
            "Rs 5823.80",
            "Rs 3568.80",
            "Rs 4289.80",
            "Rs 5823.80",
            "Rs 3568.80"
    } ;



    int[] imageId = {
            R.drawable.image50,
            R.drawable.image50,
            R.drawable.image50,
            R.drawable.image50,
            R.drawable.image50,
            R.drawable.image50,
            R.drawable.image50,
            R.drawable.image50,
            R.drawable.image50,
            R.drawable.image50,
            R.drawable.image50,
            R.drawable.image50,
            R.drawable.image50,
            R.drawable.image50,
            R.drawable.image50

    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_category_page);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);


        btnlistview = (ImageView) findViewById(R.id.btnlistview);
        btngridview = (ImageView) findViewById(R.id.btngridview);

        list = (ListView) findViewById(R.id.listview);
        grid = (GridView) findViewById(R.id.gridview);


        btnlistview.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                grid.setVisibility(View.GONE);
                list.setVisibility(View.VISIBLE);
              //  list = (ListView) findViewById(R.id.listview);
                list.setAdapter(new CustListSubcategory(SubCategoryPage.this, text1, text2, text3, imageId));

            }

        });



        btngridview.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                list.setVisibility(View.GONE);
                grid.setVisibility(View.VISIBLE);
                //grid = (GridView) findViewById(R.id.gridview);
                grid.setAdapter(new CustGridSubcategory(SubCategoryPage.this, text1, text2, text3, imageId));

            }
        });

                   /* imagesub = (ImageView) findViewById(R.id.imagesub);₹
                    *  text11 = (TextView) findViewById(R.id.grid_subprod1);
                    * text12 = (TextView) findViewById(R.id.grid_subprod2);
                    * text13 = (TextView) findViewById(R.id.grid_subprod3);
*/
      grid.setAdapter(new CustGridSubcategory(SubCategoryPage.this, text1, text2, text3, imageId));

     /*   grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
               Intent i = new Intent(SubCategoryPage.this,ProductScreen.class);
                startActivity(i);

            }
        });
*/


     }

  }
