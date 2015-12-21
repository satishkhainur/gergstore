package com.example.appsriv01.gergstore;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by appsriv01 on 21/12/15.
 */
public class GridSubcategory extends BaseAdapter {

    private Context mContext;
    private final String[] text1;
    private final String[] text2;
    private final String[] text3;
    private final int[] Imageid;
    View grid;




  //  private final int[] Imageid;
    public GridSubcategory(Context c,String[] text1,String[] text2,String[] text3,int[] Imageid){
        mContext = c;
        this.text1 = text1;
        this.text2 = text2;
        this.text3 = text3;
        this.Imageid = Imageid;


    }


    @Override
    public int getCount()
    {

        return text1.length;
    }

    @Override
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
            grid = inflater.inflate(R.layout.grid_subcategory, null);

            TextView text = (TextView) grid.findViewById(R.id.grid_subprod1);
            TextView textView1 = (TextView) grid.findViewById(R.id.grid_subprod2);
            TextView textView2 = (TextView) grid.findViewById(R.id.grid_subprod3);
            ImageView imageView = (ImageView) grid.findViewById(R.id.imagesub);
            imageView.setImageResource(Imageid[position]);

             text.setText(text1[position].toString());
             textView1.setText(text2[position].toString());
             textView2.setText(text3[position].toString());

        }
        else {

        }


          return grid;


       // return null;
    }
}