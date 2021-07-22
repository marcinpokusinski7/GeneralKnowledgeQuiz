package com.example.generalknowledgequiz.adapterPattern;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.generalknowledgequiz.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ScoreAdapter extends
        RecyclerView.Adapter<ScoreAdapter.ViewHolder> {
    private List<LatestScore> listScore;

    public ScoreAdapter(List<LatestScore> scores) {
        listScore = scores;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactView = inflater.inflate(R.layout.scores, parent, false);

        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ScoreAdapter.ViewHolder holder, int position) {
        LatestScore score = listScore.get(position);

        TextView textView = holder.nameTextView;
        textView.setText(score.getQuizType() + score.getScore());

    }

    @Override
    public int getItemCount() {
        return listScore.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView nameTextView;


        public ViewHolder(View itemView) {

            super(itemView);

            nameTextView = (TextView) itemView.findViewById(R.id.scores_view_text);

        }
    }
}