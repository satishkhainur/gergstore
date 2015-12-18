package com.example.appsriv01.gergstore;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class CategoryPage extends FragmentActivity {


    TextView t1, t2, t3, t4, t5, t6, t7, t8, t9;
    ImageView im1, im2, ig3, ig4, ig5;
    private ImageView imageclick;

    static final int NUM_ITEMS = 6;
    //ImageFragmentPagerAdapter imageFragmentPagerAdapter;
    ViewPager viewPager;
    CustomPagerAdapter mCustomPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_page);


        mCustomPagerAdapter = new CustomPagerAdapter(this);

        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(mCustomPagerAdapter);
        imageclick =(ImageView)findViewById(R.id.imageclick);

        imageclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CategoryPage.this,SubCategoryPage.class);
                startActivity(i);
            }
        });


    }


    class CustomPagerAdapter extends PagerAdapter {

        Context mContext;
        LayoutInflater mLayoutInflater;

        int[] mResources = {
                R.drawable.landingbackground,
                R.drawable.category,
                R.drawable.landingbackground,
                R.drawable.category,
                R.drawable.landingbackground,
                R.drawable.category
        };

        public CustomPagerAdapter(Context context) {
            mContext = context;
            mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return mResources.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == ((LinearLayout) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View itemView = mLayoutInflater.inflate(R.layout.swipe_fragment, container, false);

            ImageView imageView = (ImageView) itemView.findViewById(R.id.imageView);
            imageView.setImageResource(mResources[position]);

            container.addView(itemView);

            return itemView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((LinearLayout) object);
        }
    }
}
