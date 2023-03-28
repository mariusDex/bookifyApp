package com.example.booking_android.lst_hotels.view;



import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.booking_android.R;
import com.example.booking_android.entities.Hotel;
import com.example.booking_android.lst_hotels.ShowInfoHotelActivity;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LstHotelAdapter extends RecyclerView.Adapter<LstHotelAdapter.ViewHolder>  {
    ArrayList<Hotel> hotelsDataset;
    private Context mContext;
    public ArrayList<Hotel> getHotelsDataset() {
        return hotelsDataset;
    }



    public LstHotelAdapter(Context mContext,ArrayList<Hotel> hotelsDataset) {
        this.hotelsDataset = hotelsDataset;
        this.mContext = mContext;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this.mContext).inflate(R.layout.item_hotel, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Hotel h = hotelsDataset.get(position);
        HashMap<String,Object> hotelInfo = new HashMap<String,Object>();
        // ======= INICIALIZAMOS DATOS ======
        String address      = h.getAddress(); hotelInfo.put("address",address);
        String name         = h.getName(); hotelInfo.put("name",name);
        String city         = h.getCity(); hotelInfo.put("city",city);
        String country      = h.getCountry(); hotelInfo.put("country",country);
        String description  = h.getDescription(); hotelInfo.put("description",description);
        int scoreCalculado  = Integer.parseInt(h.getScore()) / Integer.parseInt(h.getPeople_voted()); hotelInfo.put("scoreCalculado",scoreCalculado);
        String people_voted = h.getPeople_voted(); hotelInfo.put("people_voted",people_voted);
        String outstanding  = h.getOutstanding(); hotelInfo.put("outstanding",outstanding);
        String check_in     = h.getCheck_in(); hotelInfo.put("check_in",check_in);
        String check_out    = h.getCheck_out(); hotelInfo.put("check_out",check_out);
        String id           = String.valueOf(h.getHotel_id()); hotelInfo.put("hotel_id",id);
        String photo        = h.getPhoto(); hotelInfo.put("photo",photo);
        // Cálculos :

        // ---< Cálculo del SCORE de un hotel >----
        Drawable fotoScore = null;
        if(scoreCalculado <= 20 && scoreCalculado >= 0){
            fotoScore = ContextCompat.getDrawable(this.mContext, R.drawable.one_star);
        }else if (scoreCalculado <= 40 && scoreCalculado > 20){
            fotoScore = ContextCompat.getDrawable(this.mContext, R.drawable.two_stars);
        }else if (scoreCalculado <= 60 && scoreCalculado > 40){
            fotoScore = ContextCompat.getDrawable(this.mContext, R.drawable.three_stars);
        }else if (scoreCalculado <= 80 && scoreCalculado > 60){
            fotoScore = ContextCompat.getDrawable(this.mContext, R.drawable.four_stars);
        }else if (scoreCalculado <= 100 && scoreCalculado > 80){
            fotoScore = ContextCompat.getDrawable(this.mContext, R.drawable.five_stars);
        }
        String scoreCalculadoString = Integer.toString(scoreCalculado);
        // ---< Cálculo de si es destacado o no >----

        // ---< Cálculo de las estrellas del score >----

        holder.name.setText(name);
        holder.city.setText(city);
        holder.country.setText(country);
        holder.score.setImageDrawable(fotoScore);
        Picasso.get().load(photo).into(holder.photo);


        holder.see_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent;
                intent = new Intent(mContext, ShowInfoHotelActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                for (Map.Entry<String, Object> o : hotelInfo.entrySet()) {
                    if(o.getKey() == "scoreCalculado"){
                    }

                    intent.putExtra(o.getKey(), String.valueOf(o.getValue()));
                }

                mContext.startActivity(intent);
            }
       });

    }

    @Override
    public int getItemCount() {
        return hotelsDataset.size();
    }




    /* VIEW HOLDER */

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView name;
        private TextView address;
        private TextView city;
        private TextView country;
        private TextView description;
        private ImageView score;
        private TextView people_voted;
        private TextView outstanding;
        private ImageView photo;
        private TextView check_in;
        private TextView check_out;
        private TextView id;
        private Button see_more;

        public ViewHolder(View itemView) {
            super(itemView);
            see_more        = itemView.findViewById(R.id.see_more);
            //id           = itemView.findViewById(R.id.id);
            photo           = itemView.findViewById(R.id.hotel_photo);
            name            = itemView.findViewById(R.id.hotel_name);
            //address         = itemView.findViewById(R.id.hotel_address);
            city            = itemView.findViewById(R.id.hotel_city);
            country         = itemView.findViewById(R.id.hotel_country);
            //description     = itemView.findViewById(R.id.hotel_description);
            score             = itemView.findViewById(R.id.hotel_score);
            //outstanding     = itemView.findViewById(R.id.hotel_outstanding);
            //check_in        = itemView.findViewById(R.id.hotel_check_in);
            //check_out       = itemView.findViewById(R.id.hotel_check_out);
            //people_voted    = itemView.findViewById(R.id.hotel_people_voted);

        }
    }
}
