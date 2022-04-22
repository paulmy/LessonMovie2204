package ru.myitschool.lessonmovie;

import com.google.gson.annotations.SerializedName;

public class MovieModel {
    @SerializedName("poster_path")
    private final String imageUrl;
    @SerializedName("original_title")
    private final String name;
    @SerializedName("release_date")
    private final String date;
    @SerializedName("overview")
    private final String description;

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

}
