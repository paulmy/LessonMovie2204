package ru.myitschool.lessonmovie.ui.root;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import ru.myitschool.lessonmovie.App;
import ru.myitschool.lessonmovie.data.dto.MovieModel;
import ru.myitschool.lessonmovie.R;
import ru.myitschool.lessonmovie.databinding.ItemMovieBinding;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.ViewHolder> {
    private ArrayList<MovieModel> data = new ArrayList<>();
   private OnItemClickListener onClickListener = null;

    public void setOnItemClickListener(OnItemClickListener listener) {
        onClickListener = listener;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemMovieBinding binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(data.get(position),onClickListener);
    }

    public void setItems(List<MovieModel> newItems) {
        data.clear();
        data.addAll(newItems);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return data.size();
    }



  static class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemMovieBinding binding;

        public ViewHolder(@NonNull ItemMovieBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(MovieModel item,OnItemClickListener listener) {
            Picasso.get()
                    .load(App.IMAGE_URL+item.imageUrl)
                    .placeholder(R.drawable.ic_down)
                    .error(R.drawable.ic_error)
                    .into(binding.image);

            binding.date.setText(item.date);
            binding.name.setText(item.name);
            binding.description.setText(item.description);

           binding.getRoot().setOnClickListener(v ->listener.onClick(item));
            setVisibleOrGone(binding.date, item.date);
            setVisibleOrGone(binding.name, item.name);
            setVisibleOrGone(binding.description, item.description);
        }

        private void setVisibleOrGone(View v, Object obj) {
            if (obj == null) {
              v.setVisibility(View.GONE);
            } else {
               v.setVisibility(View.VISIBLE);
            }


        }
    }
}
