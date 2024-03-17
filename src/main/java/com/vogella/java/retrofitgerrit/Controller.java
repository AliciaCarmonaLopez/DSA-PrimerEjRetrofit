package com.vogella.java.retrofitgerrit;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class Controller implements  Callback<List<Change>> {
    private String username;
    static final String BASE_URL = "https://api.github.com/users/";


    public void start(String username) {
        this.username = username + "/";
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL + this.username)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        GitHubAPI gerritAPI = retrofit.create(GitHubAPI.class);

        Call<List<Change>> call = gerritAPI.loadChanges("status:open");
        call.enqueue(this);

    }

    @Override
    public void onResponse(Call<List<Change>> call, Response<List<Change>> response) {
        if(response.isSuccessful()) {
            List<Change> changesList = response.body();
            changesList.forEach(change -> System.out.println("Identificador: " + change.getId() + " Nombre: "+ change.getName() + " Descripcion: " + change.getDescription()));
        } else {
            System.out.println(response.errorBody());
        }
    }

    @Override
    public void onFailure(Call<List<Change>> call, Throwable t) {
        t.printStackTrace();
    }
}
