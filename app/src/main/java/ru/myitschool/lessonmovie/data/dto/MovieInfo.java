package ru.myitschool.lessonmovie.data.dto;

import com.google.gson.annotations.SerializedName;

public class MovieInfo {
    @SerializedName("backdrop_path")
    public String photoUrl;

    @SerializedName("poster_path")
    public String posterUrl;

    @SerializedName("title")
    public String title;

    @SerializedName("overview")
    public String description;

    @SerializedName("release_date")
    public String data;

    @SerializedName("runtime")
    public int  runtime;

    @SerializedName("revenue")
    public String revenue;

    @SerializedName("vote_average")
    public String rating;

}
