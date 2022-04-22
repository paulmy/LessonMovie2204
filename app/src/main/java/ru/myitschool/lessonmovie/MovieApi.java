package ru.myitschool.lessonmovie;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieApi {
    @GET("movie/popular")
    Call<MovieList> getPopularMovie(@Query("page") int page);
}
