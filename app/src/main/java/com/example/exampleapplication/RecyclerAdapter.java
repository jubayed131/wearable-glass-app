package com.example.exampleapplication;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerAdapter
        extends RecyclerView.Adapter<RecyclerAdapter.TextViewHolder> {

    private int mCount;

    public RecyclerAdapter(int count) {
        mCount = count;
    }

    @NonNull @Override
    public TextViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return TextViewHolder.createViewHolder(parent);
    }

    @Override public void onBindViewHolder(@NonNull TextViewHolder holder, int position) {
        holder.bindView(position);
    }

    @Override public int getItemCount() {
        return mCount;
    }

    public void add() {
        int position = mCount;
        mCount++;
        notifyItemInserted(position);


    }

    public void remove() {
        if (mCount == 0) {
            return;
        }
        mCount--;
        int position = mCount;
        notifyItemRemoved(position);
    }

    static class TextViewHolder extends RecyclerView.ViewHolder {

        static TextViewHolder createViewHolder(@NonNull ViewGroup parent) {
            View newView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, null);
            newView.setBackgroundColor(Color.BLACK);
            newView.setLayoutParams(
                    new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT,
                            RecyclerView.LayoutParams.MATCH_PARENT));

            return new TextViewHolder(newView);
        }

        TextViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        void bindView(int position) {
            final TextView taskName = itemView.findViewById(R.id.textView4);
            switch (position) {
                case 0:
                    taskName.setText("Service Task 0");
                    break;
                case 1:
                    taskName.setText("Service Task 1");
                    break;
                case 2:
                    taskName.setText("Service Task 2");
                    break;
                case 3:
                    taskName.setText("Service Task 3");
                    break;
                default:
                    taskName.setText("Another task");

            }
        }
    }
}