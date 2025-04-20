package com.example.lutemon;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class LutemonAdapter extends RecyclerView.Adapter<LutemonAdapter.LutemonViewHolder> {

    public interface OnLutemonClickListener {
        void onLutemonSelected(Lutemon lutemon);
        void onMoveToHome(int id);
        void onMoveToTraining(int id);
        void onMoveToBattle(int id);
    }

    private ArrayList<Lutemon> lutemons;
    private OnLutemonClickListener listener;
    private String currentArea;

    public LutemonAdapter(ArrayList<Lutemon> lutemons, String currentArea, OnLutemonClickListener listener) {
        this.lutemons = lutemons;
        this.listener = listener;
        this.currentArea = currentArea;
    }

    @NonNull
    @Override
    public LutemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lutemon, parent, false);
        return new LutemonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LutemonViewHolder holder, int position) {
        Lutemon lutemon = lutemons.get(position);
        holder.bind(lutemon, listener, currentArea);
    }

    @Override
    public int getItemCount() {
        return lutemons.size();
    }

    public void updateLutemons(ArrayList<Lutemon> newLutemons) {
        this.lutemons = newLutemons;
        notifyDataSetChanged();
    }

    static class LutemonViewHolder extends RecyclerView.ViewHolder {
        TextView textViewLutemonInfo;
        Button btnMoveHome, btnMoveTraining, btnMoveBattle;

        public LutemonViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewLutemonInfo = itemView.findViewById(R.id.textViewLutemonInfo);
            btnMoveHome = itemView.findViewById(R.id.btnMoveHome);
            btnMoveTraining = itemView.findViewById(R.id.btnMoveTraining);
            btnMoveBattle = itemView.findViewById(R.id.btnMoveBattle);
        }

        public void bind(Lutemon lutemon, OnLutemonClickListener listener, String currentArea) {
            textViewLutemonInfo.setText(lutemon.toString());

            itemView.setOnClickListener(v -> listener.onLutemonSelected(lutemon));

            // Disable the button for the current area
            if ("home".equals(currentArea)) {
                btnMoveHome.setEnabled(false);
            } else if ("training".equals(currentArea)) {
                btnMoveTraining.setEnabled(false);
            } else if ("battle".equals(currentArea)) {
                btnMoveBattle.setEnabled(false);
            }

            btnMoveHome.setOnClickListener(v -> listener.onMoveToHome(lutemon.getId()));
            btnMoveTraining.setOnClickListener(v -> listener.onMoveToTraining(lutemon.getId()));
            btnMoveBattle.setOnClickListener(v -> listener.onMoveToBattle(lutemon.getId()));
        }
    }
}