package com.example.sportapp.RecyclerFiles

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.sportapp.R

class MuscleAdapter(var muscleList: ArrayList<ModelMuscle>) :
    RecyclerView.Adapter<MuscleAdapter.MuscleViewHolder>() {

    class MuscleViewHolder(itemView: View) : ViewHolder(itemView) {
        val titleMuscleView: TextView = itemView.findViewById(R.id.textView_muscle)
        val imageMuscleView: ImageView = itemView.findViewById(R.id.imageView_muscle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MuscleViewHolder {
        val inflateCard =
            LayoutInflater.from(parent.context).inflate(R.layout.card_muscle, parent, false)

        return MuscleViewHolder(inflateCard)
    }

    override fun onBindViewHolder(holder: MuscleViewHolder, position: Int) {
        val currentCard = muscleList[position]
        holder.titleMuscleView.setText(currentCard.title)
        holder.imageMuscleView.setImageResource(currentCard.imageMuscle)

        holder.imageMuscleView.setOnClickListener {
            // go to next fragment
        }
    }

    override fun getItemCount(): Int {
        return muscleList.size
    }

}