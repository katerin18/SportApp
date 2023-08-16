package com.example.sportapp.RecyclerFiles

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sportapp.R

class WorkoutAdapter(val workoutList: ArrayList<ModelWorkout>) :
    RecyclerView.Adapter<WorkoutAdapter.WorkoutViewHolder>() {
    class WorkoutViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewWorkout: TextView = itemView.findViewById(R.id.textView_workout)
        val imageViewWorkout: ImageView = itemView.findViewById(R.id.imageView_muscle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkoutViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_workout, parent, false)
        return WorkoutViewHolder(v)
    }

    override fun onBindViewHolder(holder: WorkoutViewHolder, position: Int) {
        val currentWorkout = workoutList[position]

        holder.textViewWorkout.setText(currentWorkout.titleWorkout)
        holder.imageViewWorkout.setImageResource(currentWorkout.imageWorkout)
    }

    override fun getItemCount(): Int {
        return workoutList.size
    }
}
