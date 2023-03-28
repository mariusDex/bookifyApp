package com.example.booking_android.utils;


import android.widget.Toast;

import com.example.booking_android.entities.BookingRespuesta;
import com.example.booking_android.entities.HotelRespuesta;
import com.example.booking_android.entities.RoomRespuesta;
import com.example.booking_android.entities.UserRespuesta;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiInterface {
    // https://pokeapi.co/api/v2/pokemon?limit=20&offset=20

    @GET("hotels/{check_in}/{check_out}/{country}/{city}/{guests}/{top_hotels}")
    Call<HotelRespuesta> getHotels(
            @Path("check_in") String check_in,
            @Path("check_out") String check_out,
            @Path("country") String country,
            @Path("city") String city,
            @Path("guests") String guests,
            @Path("top_hotels") String top_hotels
    );
    @GET("hotels")
    Call<HotelRespuesta> getHotelsFull();

    @GET("rooms/{hotel_id}/{check_in}/{check_out}/{guests}")
    Call<RoomRespuesta> getHotelRooms(
            @Path("hotel_id") String hotel_id,
            @Path("check_in") String check_in,
            @Path("check_out") String check_out,
            @Path("guests") String guests
    );
    @GET("user/{email}/{password}")
    Call<UserRespuesta> getUser(
            @Path("email") String email,
            @Path("password") String password
    );
    @GET("booking/{client_id}")
    Call<BookingRespuesta> getClientBookings(
            @Path("client_id") String client_id
    );
    @POST("booking/{client_id}/{room_id}/{guests}/{check_in}/{check_out}/{description}")
    Call<Integer> insertBooking(
            @Path("client_id") String client_id,
            @Path("room_id") String room_id,
            @Path("guests") String guests,
            @Path("check_in") String check_in,
            @Path("check_out") String check_out,
            @Path("description") String description
    );

    //Call<ArrayList<Simpson>>  getPopularSimpson(@Path("count") int count);
/*
    @GET("movie/{movie_id}")
    Call<Movie> getMovieDetails(@Path("movie_id") int movieId,
                @Query("api_key") String apiKey, @Query("append_to_response") String credits);
*/
}
