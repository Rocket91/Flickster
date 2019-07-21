package ht.applicatech.flickster.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.load.resource.bitmap.RoundedCorners;

import org.parceler.Parcels;

import java.util.List;

import ht.applicatech.flickster.DetailActivity;
import ht.applicatech.flickster.GlideApp;
import ht.applicatech.flickster.R;
import ht.applicatech.flickster.models.Movie;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> {

    Context context;
    List<Movie> movies;

    public MoviesAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("smile", "onCreateViewHolder");
        View view = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false );
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d("smile", "onBindViewHolder" + position);
        Movie movie = movies.get(position);
        // bind the movie data into the view holder
        holder.bind(movie);

    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle;
        TextView tvOverview;
        ImageView ivPoster;
        TextView tvReleaseDate;
        RelativeLayout container;
        int radius = 30;
        int margin = 10;




        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvOverview = itemView.findViewById(R.id.tvOverview);
            ivPoster = itemView.findViewById(R.id.ivPoster);
            tvReleaseDate = itemView.findViewById(R.id.tvReleaseDate2);
            container = itemView.findViewById(R.id.container);


        }

        public void bind(final Movie movie) {
            tvTitle.setText(movie.getTitle());
            tvOverview.setText(movie.getOverview());

            GlideApp.with(context).load(movie.getPosterPath())
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.error)
                    .override(400, 500)
                    .transform(new RoundedCorners(radius))
                    .into(ivPoster);

            tvReleaseDate.setText(movie.getReleaseDate());
            // add click listener to the whole row

            container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //navigate to details activity on tap
                    Intent i = new Intent(context, DetailActivity.class);
                    i.putExtra("movie", Parcels.wrap(movie));
                    context.startActivity(i);
                }
            });


        }
    }
}