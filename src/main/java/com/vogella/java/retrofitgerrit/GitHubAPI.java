package com.vogella.java.retrofitgerrit;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import java.util.List;

public interface GitHubAPI {
    @GET("repos/")
    Call<List<Change>> loadChanges(@Query("q") String status);

}
