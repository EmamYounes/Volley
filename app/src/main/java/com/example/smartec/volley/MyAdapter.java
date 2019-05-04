package com.example.smartec.volley;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.smartec.volley.R;
import com.example.smartec.volley.Result;

import java.util.ArrayList;

/**
 * Created by Smartec on 2/26/2018.
 */

public class MyAdapter extends BaseAdapter {


    Context context;
    ArrayList<Result> results;

    public MyAdapter(Context context, ArrayList<Result> results) {

        this.context = context;
        this.results = results;
    }

    @Override
    public int getCount() {
        return results.size();
    }

    @Override
    public Object getItem(int position) {
        return results.get(position);
    }

    @Override
    public long getItemId(int position) {
        return results.indexOf(getItem(position));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.custom_list_view, null);
        }

        TextView textId = (TextView) convertView.findViewById(R.id.id);
        TextView textTitle = (TextView) convertView.findViewById(R.id.title);
        TextView textAveraage = (TextView) convertView.findViewById(R.id.average);
        TextView textReview = (TextView) convertView.findViewById(R.id.overview);
        textId.setText("" + results.get(position).getId());
        textTitle.setText(results.get(position).getTitle().toString());
        textAveraage.setText(""+results.get(position).getVote_average());
        textReview.setText(results.get(position).getOverview().toString());
        return convertView;
    }
}
