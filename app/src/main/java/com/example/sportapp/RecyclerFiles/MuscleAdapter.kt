package com.example.sportapp.RecyclerFiles

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.sportapp.R
import com.example.sportapp.WorkoutDetailsFragment

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
        holder.titleMuscleView.text = currentCard.title
        holder.imageMuscleView.setImageResource(currentCard.imageMuscle)

        holder.imageMuscleView.setOnClickListener { v ->
            val parentActivity = v!!.context as AppCompatActivity
            val bundle = Bundle()
            val detailsFragment = WorkoutDetailsFragment()

            bundle.putString("item", currentCard.title)
            detailsFragment.arguments = bundle

            parentActivity.supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_workout_container, detailsFragment)
                .addToBackStack(null)
                .commit()
        }
    }

    override fun getItemCount(): Int {
        return muscleList.size
    }

}