package com.saugatrai.androidnetwork.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.saugatrai.androidnetwork.R;
import com.saugatrai.androidnetwork.model.PhotoResponse;
import com.saugatrai.androidnetwork.network.NetworkService;
import com.saugatrai.androidnetwork.network.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private PhotoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(
                new LinearLayoutManager(this)
        );
        adapter = new PhotoAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);
    }

    private void getPhotoFromNetwork() {
        NetworkService networkService = RetrofitInstance.getRetrofit()
                .create(NetworkService.class);
        Call<List<PhotoResponse>> call = networkService.getPhotos();
        call.enqueue(new Callback<List<PhotoResponse>>() {
            @Override
            public void onResponse(Call<List<PhotoResponse>> call, Response<List<PhotoResponse>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    adapter.setResponseList(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<PhotoResponse>> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getLocalizedMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getPhotoFromNetwork();
    }
}