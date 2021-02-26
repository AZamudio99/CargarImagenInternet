package com.example.cargarimageninternet;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PersonajesAdapter extends RecyclerView.Adapter<PersonajesAdapter.ViewHolder> {
    private List<imagen> datos;

    public PersonajesAdapter(List<imagen> itemList){
        this.datos = itemList;
    }

    @NonNull
    @Override
    public PersonajesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.images,parent,false);
        return new PersonajesAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonajesAdapter.ViewHolder holder, int position) {
        imagen imagen = datos.get(position);
        holder.name.setText(imagen.getName());
        Picasso.get().load(imagen.getImageurl()).into(holder.imagen);
    }

    @Override
    public int getItemCount() {
        return datos.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imagen;
        public TextView name;

        public ViewHolder(View v)
        {
            super(v);
            imagen = v.findViewById(R.id.imagepersonajes);
            name = v.findViewById(R.id.txtvpersonajes);
        }
    }

}
