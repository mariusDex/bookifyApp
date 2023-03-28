package com.example.booking_android.lst_hotel_rooms.view;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.booking_android.BookingActivity;
import com.example.booking_android.R;
import com.example.booking_android.entities.Hotel;
import com.example.booking_android.entities.Room;
import com.example.booking_android.lst_hotels.ShowInfoHotelActivity;
import com.example.booking_android.lst_hotels.view.LstHotelActivity;
import com.google.gson.annotations.SerializedName;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LstRoomAdapter extends RecyclerView.Adapter<LstRoomAdapter.ViewHolder>  {
    ArrayList<Room> roomDataset;
    private Context mContext;
    public ArrayList<Room> getRoomDataset() {
        return roomDataset;
    }



    public LstRoomAdapter(Context mContext, ArrayList<Room> roomDataset) {
        this.roomDataset = roomDataset;
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this.mContext).inflate(R.layout.item_room, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Room r = roomDataset.get(position);
        HashMap<String,Object> bookingInfo = new HashMap<String,Object>();

        String price = r.getPrice() + "€";
        String people = r.getPeople();
        String description = r.getDescription();
        String roomNumber = r.getRoom_number();
        String photo = r.getPhoto();

        holder.price.setText(price);
        holder.people.setText(people);
        holder.description.setText(description);
        holder.roomNumber.setText(roomNumber);
        Picasso.get().load(photo).into(holder.photo);

        bookingInfo.put("room_price",price);
        bookingInfo.put("room_description",description);
        bookingInfo.put("room_number",roomNumber);
        bookingInfo.put("room_id",r.getRoom_id());
        bookingInfo.put("room_photo",photo);



        holder.book_room.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(mContext, BookingActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                for (Map.Entry<String, Object> o : bookingInfo.entrySet()) {
                    intent.putExtra(o.getKey(), String.valueOf(o.getValue()));
                }

                mContext.startActivity(intent);
            }
       });

    }

    @Override
    public int getItemCount() {
        return roomDataset.size();
    }



    /* VIEW HOLDER */

    public class ViewHolder extends RecyclerView.ViewHolder{
       private TextView price;
       private TextView people;
       private TextView roomNumber;
       private TextView description;
       private ImageView photo;
       private Button book_room;

        public ViewHolder(View itemView) {
            super(itemView);
            price        = itemView.findViewById(R.id.room_price);
            people        = itemView.findViewById(R.id.room_people);
            roomNumber        = itemView.findViewById(R.id.room_room_number);
            description        = itemView.findViewById(R.id.room_description);
            photo        = itemView.findViewById(R.id.room_photo);
            book_room    = itemView.findViewById(R.id.book_room);
        }
    }
}
