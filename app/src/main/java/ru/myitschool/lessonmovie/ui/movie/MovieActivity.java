package ru.myitschool.lessonmovie.ui.movie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.myitschool.lessonmovie.App;
import ru.myitschool.lessonmovie.data.dto.MovieInfo;
import ru.myitschool.lessonmovie.R;
import ru.myitschool.lessonmovie.databinding.ActivityMovieBinding;
import ru.myitschool.lessonmovie.utils.StringFormat;

public class MovieActivity extends AppCompatActivity {
    private static final String ARUMENT_ID = "argument_id";
    private long id = 0;
    private ActivityMovieBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMovieBinding.inflate(getLayoutInflater());
        //setContentView(R.layout.activity_movie);
        setContentView(binding.getRoot());
        id = getIntent().getLongExtra(ARUMENT_ID, 0);
        //binding.name.setText(id + " ");
        LoadMovie(id);

    }

    private void LoadMovie(long id) {
        App.api.getMovie(id).enqueue(new Callback<MovieInfo>() {
            @Override
            public void onResponse(Call<MovieInfo> call, Response<MovieInfo> response) {
                if (response.code() == 200) {
                    bindMovie(response.body());
                } else {
                    Toast.makeText(
                            getApplicationContext()
                            , response.message(),
                            Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<MovieInfo> call, Throwable t) {

            }
        });

    }

    private void bindMovie(MovieInfo info) {
        binding.name.setText(info.title);
        binding.description.setText(info.description);
        binding.date.setText(info.data);
        binding.revenue.setText(info.revenue);
        binding.runtime.setText(StringFormat.formatTime(getResources(), info.runtime));
        binding.vote.setText(info.rating);

        Picasso.get()
                .load("https://themoviedb.org/t/p/w780" + info.photoUrl)
                .placeholder(R.drawable.ic_down)
                .error(R.drawable.ic_error)
                .into(binding.background);
        Picasso.get()
                .load(App.IMAGE_URL + info.posterUrl)
                .placeholder(R.drawable.ic_down)
                .error(R.drawable.ic_error)
                .into(binding.poster);

        binding.back.setOnClickListener(v -> onBackPressed());
        binding.loader.setVisibility(View.GONE);
        binding.content.setVisibility(View.VISIBLE);

    }

    public static Intent newIntent(Context context, long id) {
        Intent intent = new Intent(context, MovieActivity.class);
        intent.putExtra(ARUMENT_ID, id);
        return intent;
    }


}