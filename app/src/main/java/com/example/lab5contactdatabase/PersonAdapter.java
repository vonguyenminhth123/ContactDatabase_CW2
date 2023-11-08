package com.example.lab5contactdatabase;
import android.app.Person;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.PersonViewHolder> {
    private ArrayList<PersonModal> people;
    private Context context;
    public PersonAdapter(ArrayList<PersonModal> people, Context context){
        this.people = people;
        this.context = context;
    }
    @NonNull
    @Override
    public PersonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_person, parent, false);
        return new PersonViewHolder(v);
    }
    @Override
    public void onBindViewHolder(@NonNull PersonViewHolder holder, int position) {
        PersonModal modal = people.get(position);
        holder.tvName.setText(modal.getName());
        holder.tvDoB.setText(modal.getBirthDate());
        holder.tvEmail.setText(modal.getEmail());
        // Set the avatar image
        holder.avatarImage.setImageResource(modal.getAvatarResource());
    }
    @Override
    public int getItemCount() {
                return people.size();
    }
    public class PersonViewHolder extends RecyclerView.ViewHolder{
        public TextView tvName, tvDoB, tvEmail;
        public ImageView avatarImage;
        public PersonViewHolder(@NonNull View itemView){
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvDoB = itemView.findViewById(R.id.tvDoB);
            tvEmail = itemView.findViewById(R.id.tvEmail);
            avatarImage = itemView.findViewById(R.id.avatarImage);
        }
    }
}