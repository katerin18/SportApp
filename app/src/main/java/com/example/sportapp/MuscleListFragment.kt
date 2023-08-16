package com.example.sportapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sportapp.RecyclerFiles.ModelMuscle
import com.example.sportapp.RecyclerFiles.MuscleAdapter

class MuscleListFragment : Fragment() {
    val imageMuscleList = listOf(
        R.drawable.butt_muscle,
        R.drawable.abs_muscle,
        R.drawable.biceps_muscle,
        R.drawable.chest_muscle,
        R.drawable.legs_muscle,
        R.drawable.spine_muscle
    )
    val titleMuscleList = listOf(
        R.string.text_butt_muscle,
        R.string.text_abs_muscle,
        R.string.text_arms_muscle,
        R.string.text_chest_muscle,
        R.string.text_legs_muscle,
        R.string.text_back_muscle
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_muscle_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val muscleList = getListOfModels()
        val muscleAdapter = MuscleAdapter(muscleList)
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView_muscles)

        recyclerView.layoutManager = GridLayoutManager(context, 2)
        recyclerView.adapter = muscleAdapter
    }

    private fun getListOfModels(): ArrayList<ModelMuscle> {
        val items: ArrayList<ModelMuscle> = ArrayList()
        for (i in titleMuscleList.indices) {
            items.add(ModelMuscle(titleMuscleList[i], imageMuscleList[i]))
        }

        return items
    }
}