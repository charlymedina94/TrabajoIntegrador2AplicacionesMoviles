package com.example.trabajointegrador2aplicacionesmoviles.tag;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.trabajointegrador2aplicacionesmoviles.R;
import com.example.trabajointegrador2aplicacionesmoviles.entidades.Momento;

import java.util.ArrayList;


public class TagListAdapter extends BaseAdapter {

    private Context context;
    private  int layout;
    private ArrayList<Momento> momentosList;

    public TagListAdapter(Context context, int layout, ArrayList<Momento> momentosList) {
        this.context = context;
        this.layout = layout;
        this.momentosList = momentosList;
    }

    @Override
    public int getCount() {
        return momentosList.size();
    }

    @Override
    public Object getItem(int position) {
        return momentosList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder{
        ImageView imageView;
        TextView txtDescripcion;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        View row = view;
        ViewHolder holder = new ViewHolder();

        if(row == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout, null);

            holder.txtDescripcion = (TextView) row.findViewById(R.id.txtDescripcionTag);
            holder.imageView = (ImageView) row.findViewById(R.id.imgFoodTag);
            row.setTag(holder);
        }
        else {
            holder = (ViewHolder) row.getTag();
        }

        Momento momento = momentosList.get(position);

        holder.txtDescripcion.setText(momento.getDescripcion());


        byte[] foodImage = momento.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(foodImage, 0, foodImage.length);
        holder.imageView.setImageBitmap(bitmap);

        return row;
    }
}