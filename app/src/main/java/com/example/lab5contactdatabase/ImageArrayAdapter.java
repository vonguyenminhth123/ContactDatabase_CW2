package com.example.lab5contactdatabase;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
public class ImageArrayAdapter extends ArrayAdapter<Integer> {
    private Context context;
    private Integer[] imageResources;
    public ImageArrayAdapter(Context context, int resource, Integer[] imageResources) {
        super(context, resource, imageResources);
        this.context = context;
        this.imageResources = imageResources;
    }
    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getImageForPosition(position, parent);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getImageForPosition(position, parent);
    }
    private View getImageForPosition(int position, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.custom_spinner_item, parent, false);
        ImageView imageView = row.findViewById(R.id.spinnerImageView);
        imageView.setImageResource(imageResources[position]);
        return row;
    }
}