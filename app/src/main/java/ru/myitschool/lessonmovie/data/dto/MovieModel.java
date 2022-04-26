package ru.myitschool.lessonmovie.data.dto;

import com.google.gson.annotations.SerializedName;

public class MovieModel {
    @SerializedName("id")
    public long id;
    @SerializedName("poster_path")
    public String imageUrl;
    @SerializedName("title")
    public String name;
    @SerializedName("release_date")
    public String date;
    @SerializedName("overview")
    public String description;
/*

    public MovieModel(String imageUrl, String name, String date, String description) {
        this.imageUrl = imageUrl;
        this.name = name;
        this.date = date;
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }
*/

}
