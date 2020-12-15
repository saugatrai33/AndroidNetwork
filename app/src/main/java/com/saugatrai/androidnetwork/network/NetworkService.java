package com.saugatrai.androidnetwork.network;

import com.saugatrai.androidnetwork.model.PhotoResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface NetworkService {

    @GET("/albums/1/photos")
    Call<List<PhotoResponse>> getPhotos();

}
