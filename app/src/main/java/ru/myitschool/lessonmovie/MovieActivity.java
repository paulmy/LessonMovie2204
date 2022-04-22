package ru.myitschool.lessonmovie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import ru.myitschool.lessonmovie.databinding.ActivityMovieBinding;

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
        id = getIntent().getLongExtra(ARUMENT_ID,0);
        binding.name.setText(id+" ");
    }

    public static Intent newIntent(Context context, long id) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(ARUMENT_ID, id);
        return intent;
    }


}