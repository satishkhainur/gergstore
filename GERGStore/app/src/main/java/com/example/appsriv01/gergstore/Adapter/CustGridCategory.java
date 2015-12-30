package com.example.appsriv01.gergstore.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appsriv01.gergstore.R;
import com.example.appsriv01.gergstore.Activity.SubCategoryPage;

/**
 * Created by appsriv01 on 21/12/15.
 */
public class CustGridCategory extends BaseAdapter {

    private Context mContext;
    private final String[] cat_txt;
    private final int[] cat_image;




  //  private final int[] Imageid;
    public CustGridCategory(Context c, String[] cat_txt,  int[] cat_image){
        mContext = c;
        this.cat_txt = cat_txt;
        this.cat_image = cat_image;


    }


    @Override
    public int getCount()
    {

        return cat_txt.length;
    }

    /*@Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null)
        {
            grid = new View(mContext);
            grid = inflater.inflate(R.layout.cust_grid_category, null);

            TextView text = (TextView) grid.findViewById(R.id.category_txt);
            ImageView imageView = (ImageView) grid.findViewById(R.id.category_view);
            imageView.setImageResource(cat_image[position]);

             text.setText(cat_txt[position].toString());


        }
        else {

        }


          return grid;


       // return null;
    }*/

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class Holder
    {
        TextView tv;
        ImageView img;
    }

    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder=new Holder();
        View rowView ;
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        rowView = inflater.inflate(R.layout.cust_grid_category, null);
        holder.tv=(TextView) rowView.findViewById(R.id.category_txt);
        holder.img=(ImageView) rowView.findViewById(R.id.category_view);

        holder.tv.setText(cat_txt[position]);
        holder.img.setImageResource(cat_image[position]);

        rowView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent i = new Intent(mContext,SubCategoryPage.class);
                mContext.startActivity(i);
            }
        });

        return rowView;
    }



}
