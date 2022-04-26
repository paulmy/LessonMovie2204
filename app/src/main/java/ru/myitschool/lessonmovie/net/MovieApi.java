package ru.myitschool.lessonmovie.net;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import ru.myitschool.lessonmovie.data.dto.MovieInfo;
import ru.myitschool.lessonmovie.data.dto.MovieList;

public interface MovieApi {
    @GET("movie/popular")
    Call<MovieList> getPopularMovie(@Query("page") int page);

    @GET("movie/{id}")
    Call<MovieInfo> getMovie(@Path("id") long id);

}
