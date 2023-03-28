package com.example.booking_android.login.view;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.booking_android.R;
import com.example.booking_android.entities.Room;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

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
        HashMap<String,Object> RoomInfo = new HashMap<String,Object>();

        holder.price.setText(r.getPrice()  + " €");
        holder.people.setText(r.getPeople());
        holder.description.setText(r.getDescription());
        holder.roomNumber.setText(r.getRoom_number());
        Picasso.get().load(r.getPhoto()).into(holder.photo);



        /*

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
       });   */

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

        public ViewHolder(View itemView) {
            super(itemView);

            price        = itemView.findViewById(R.id.room_price);
            people        = itemView.findViewById(R.id.room_people);
            roomNumber        = itemView.findViewById(R.id.room_room_number);
            description        = itemView.findViewById(R.id.room_description);
            photo        = itemView.findViewById(R.id.room_photo);
        }
    }
}
