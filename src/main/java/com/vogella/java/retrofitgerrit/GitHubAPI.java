package com.vogella.java.retrofitgerrit;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.List;

public interface GitHubAPI {
    @GET( "{username}/repos")
    Call<List<Change>> loadChanges(@Path("username") String username, @Query("q")String status);


}
