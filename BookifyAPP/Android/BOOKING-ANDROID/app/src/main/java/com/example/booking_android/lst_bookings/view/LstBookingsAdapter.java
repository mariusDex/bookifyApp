package com.example.booking_android.lst_bookings.view;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.booking_android.BookingActivity;
import com.example.booking_android.R;
import com.example.booking_android.entities.Booking;
import com.example.booking_android.entities.Room;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LstBookingsAdapter extends RecyclerView.Adapter<LstBookingsAdapter.ViewHolder>  {
    ArrayList<Booking> bookingDataset;
    private Context mContext;
    public ArrayList<Booking> getBookingDataset() {
        return bookingDataset;
    }



    public LstBookingsAdapter(Context mContext, ArrayList<Booking> bookingDataset) {
        this.bookingDataset = bookingDataset;
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this.mContext).inflate(R.layout.item_booking, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Booking b = bookingDataset.get(position);
        HashMap<String,Object> bookingInfo = new HashMap<String,Object>();

        String booking_id = b.getBooking_id();
        String name = b.getName();
        String guests = b.getGuests();
        String date = b.getDate();
        String entry_date = b.getEntry_date();
        String exit_date = b.getExit_date();

        holder.booking_id.setText(booking_id);
        holder.name.setText(name);
        holder.guests.setText(guests);
        holder.date.setText(date);
        holder.entry_date.setText(entry_date);
        holder.exit_date.setText(exit_date);

    }

    @Override
    public int getItemCount() {
        return bookingDataset.size();
    }



    /* VIEW HOLDER */

    public class ViewHolder extends RecyclerView.ViewHolder{
       private TextView booking_id;
       private TextView name;
       private TextView guests;
       private TextView date;
       private TextView entry_date;
       private TextView exit_date;

        public ViewHolder(View itemView) {
            super(itemView);
            booking_id        = itemView.findViewById(R.id.booking_id);
            name        = itemView.findViewById(R.id.name);
            guests       = itemView.findViewById(R.id.guests);
            date        = itemView.findViewById(R.id.date);
            entry_date   = itemView.findViewById(R.id.entry_date);
            exit_date    = itemView.findViewById(R.id.exit_date);
        }
    }
}
