package ru.myitschool.lessonmovie.ui.root;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.myitschool.lessonmovie.App;
import ru.myitschool.lessonmovie.data.dto.MovieList;
import ru.myitschool.lessonmovie.data.dto.MovieModel;
import ru.myitschool.lessonmovie.databinding.ActivityMainBinding;
import ru.myitschool.lessonmovie.ui.movie.MovieActivity;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private MovieListAdapter adapter = new MovieListAdapter();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        adapter.setOnItemClickListener(movie->startActivity(MovieActivity.newIntent(this,movie.id)));
        getDataFromNetwork();
        binding.recycler.setAdapter(adapter);

    }

    private void getDataFromNetwork() {

        App.api.getPopularMovie(1).enqueue(new Callback<MovieList>() {
            @Override
            public void onResponse(@NonNull Call<MovieList> call, @NonNull Response<MovieList> response) {
                if (response.code() == 200) {
                    //assert response.body() != null;
                    addToRecycler(response.body().results);

                    for (MovieModel l : response.body().results) {
                        Log.d("TESTT", " " + l.date + " " + l.name);

                    }
                } else {
                    Toast.makeText(MainActivity.this, " Code Error "
                            + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<MovieList> call, @NonNull Throwable t) {
                Toast.makeText(MainActivity.this, " Code Error "
                        + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void addToRecycler(List<MovieModel> body) {
        adapter.setItems(body);
    }

}