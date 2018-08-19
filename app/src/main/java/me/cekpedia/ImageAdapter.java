package me.cekpedia;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rezadwihendarno on 18/02/2018.
 */
public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.MyHolder> {

    private int icons[];
    private String text[];
    private Context context;
    private ClickListener clickListener;

    private LayoutInflater inflater;
    View view;

    public ImageAdapter(Context context,  int[] icons, String[] text) {
        this.icons = icons;
        this.text = text;
        this.context = context;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_view, null);
        MyHolder myHolder = new MyHolder(layout);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        holder.imgview.setImageResource(icons[position]);
        holder.txt.setText(text[position]);
    }

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @Override
    public int getItemCount() {
        return text.length;
    }

    class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imgview;
        TextView txt;
        public MyHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            imgview = itemView.findViewById(R.id.image_grid);
            txt = itemView.findViewById(R.id.text_grid);
        }

        @Override
        public void onClick(View view) {
            if (clickListener != null){
                clickListener.itemClicked(view, getPosition());
            }
        }
    }
    public interface ClickListener {
        public void itemClicked(View view, int position);

    }

}

//public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.MyHolder> {
//
//    private int icons[];
//    private String text[];
//    Context context;
//    List<Thumbnail> mData;
//    private ClickListener clickListener;
//
//    private LayoutInflater inflater;
//    View view;
//
////    public ImageAdapter(Context context,  List<Thumbnail> mData) {
////        this.context = context;
////        this.mData = mData;
////    }
//    public ImageAdapter(Context context,  int[] icons, String[] text) {
//        this.icons = icons;
//        this.text = text;
//        this.context = context;
//    }
//    @Override
//    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View layout = LayoutInflater.from(context).inflate(R.layout.grid_view, parent, false);
//        MyHolder myHolder = new MyHolder(layout);
//        return myHolder;
//    }
//
//    @Override
//    public void onBindViewHolder(MyHolder holder, int position) {
//
////        holder.imgview.setImageResource(mData.get(position).getGambar());
////        holder.txt.setText(mData.get(position).getNama());
//        holder.imgview.setImageResource(icons[position]);
//        holder.txt.setText(text[position]);
//    }
//
//    public void setClickListener(ClickListener clickListener) {
//        this.clickListener = clickListener;
//    }
//
//    @Override
//    public int getItemCount() {
//        return text.length;
////        return mData.size();
//    }
//    class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
//        ImageView imgview;
//        TextView txt;
//        public MyHolder(View itemView) {
//            super(itemView);
//            itemView.setOnClickListener(this);
//            imgview = itemView.findViewById(R.id.image_grid);
//            txt = itemView.findViewById(R.id.text_grid);
//        }
//
//        @Override
//        public void onClick(View view) {
//            if (clickListener != null){
//                clickListener.itemClicked(view, getPosition());
//            }
//        }
//    }
//    public interface ClickListener {
//        public void itemClicked(View view, int position);
//
//    }
//
//}
//    public static class MyHolder extends RecyclerView.ViewHolder {
//        private ImageView imgview;
//        private TextView txt;
//
//        public MyHolder(View itemView) {
//            super(itemView);
//
//            itemView.setOnClickListener(this);
//            imgview = itemView.findViewById(R.id.image_grid);
//            txt = itemView.findViewById(R.id.text_grid);
//        }
//
//        @Override
//        public void onClick(View view) {
//            if (clickListener != null){
//                Toast.makeText(view.getContext(), "position = " + getLayoutPosition(), Toast.LENGTH_SHORT).show();
//                clickListener.itemClicked(view, getPosition());
//            }
//        }
//    }
//    public interface ClickListener {
//        public void itemClicked(View view, int position);
//
//    }
//
//}
