package com.example.appsriv01.gergstore.Activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.appsriv01.gergstore.Adapter.CustGridCategory;
import com.example.appsriv01.gergstore.R;

public class CategoryPage extends FragmentActivity {



    GridView cat_gird;
    String[] cat_txt = {
            "DesignerWear","PartyWear",
            "DesignerWear","PartyWear",
            "DesignerWear","PartyWear",
            "DesignerWear","PartyWear",
            "DesignerWear","PartyWear",
            "DesignerWear","PartyWear",
            "DesignerWear","PartyWear",
            "DesignerWear"

    };
    int[] cat_image = {
            R.drawable.fbicon24x24,R.drawable.twitter,
            R.drawable.fbicon24x24,R.drawable.twitter,
            R.drawable.fbicon24x24,R.drawable.twitter,
            R.drawable.fbicon24x24,R.drawable.twitter,
            R.drawable.fbicon24x24,R.drawable.twitter,
            R.drawable.fbicon24x24,R.drawable.twitter,
            R.drawable.fbicon24x24,R.drawable.twitter,
            R.drawable.fbicon24x24
    };



    static final int NUM_ITEMS = 6;

    //ImageFragmentPagerAdapter imageFragmentPagerAdapter;
    ViewPager viewPager;

    CustomPagerAdapter mCustomPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_page);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        mCustomPagerAdapter = new CustomPagerAdapter(this);

        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(mCustomPagerAdapter);

        viewPager.setClipToPadding(false);
        viewPager.setPadding(150, 0, 150, 0);
        viewPager.setClipChildren(false);

        cat_gird = (GridView) findViewById(R.id.category_page_grid);
        cat_gird.setAdapter(new CustGridCategory(CategoryPage.this,cat_txt,cat_image));
     }



    class CustomPagerAdapter extends PagerAdapter {

        Context mContext;
        LayoutInflater mLayoutInflater;

        int[] mResources =
                {
                R.drawable.landingbackground,
                R.drawable.category,
                R.drawable.landingbackground,
                R.drawable.category,
                R.drawable.landingbackground,
                R.drawable.category
                };


        public CustomPagerAdapter(Context context)
        {
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
