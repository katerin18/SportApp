package com.example.sportapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sportapp.RecyclerFiles.ModelWorkout
import com.example.sportapp.RecyclerFiles.WorkoutAdapter

class WorkoutDetailsFragment : Fragment() {
    private val buttTitleListWorkouts = listOf(
        R.string.text_butt_work1,
        R.string.text_butt_work2,
        R.string.text_butt_work3,
        R.string.text_butt_work4,
        R.string.text_butt_work5
    )
    private val buttImageListWorkouts = listOf(
        R.drawable.butt_one,
        R.drawable.butt_2,
        R.drawable.butt_3,
        R.drawable.butt_4,
        R.drawable.butt_5
    )

    private val absTitleListWorkouts = listOf(
        R.string.text_abs_work1,
        R.string.text_abs_work2,
        R.string.text_abs_work3,
        R.string.text_abs_work4,
        R.string.text_abs_work5
    )
    private val absImageListWorkouts = listOf(
        R.drawable.abs_one,
        R.drawable.abs2,
        R.drawable.abs3,
        R.drawable.abs4,
        R.drawable.abs5,
    )

    private val armsTitleListWorkouts = listOf(
        R.string.text_arms_work1,
        R.string.text_arms_work2,
        R.string.text_arms_work3,
        R.string.text_arms_work4,
        R.string.text_arms_work5
    )
    private val armsImageListWorkouts = listOf(
        R.drawable.arm_one,
        R.drawable.arm2,
        R.drawable.arm3,
        R.drawable.arm4,
        R.drawable.arm5
    )

    private val chestTitleListWorkouts = listOf(
        R.string.text_chest_work1,
        R.string.text_chest_work2,
        R.string.text_chest_work3,
        R.string.text_chest_work4,
        R.string.text_chest_work5
    )
    private val chestImageListWorkouts = listOf(
        R.drawable.ch_one,
        R.drawable.ch2,
        R.drawable.ch3,
        R.drawable.ch4,
        R.drawable.ch5,
    )

    private val legsTitleListWorkouts = listOf(
        R.string.text_legs_work1,
        R.string.text_legs_work2,
        R.string.text_legs_work3,
        R.string.text_legs_work4,
        R.string.text_legs_work5,
    )
    private val legsImageListWorkouts = listOf(
        R.drawable.leg1,
        R.drawable.leg2,
        R.drawable.leg3,
        R.drawable.leg4,
        R.drawable.leg5
    )

    private val spineTitleListWorkouts = listOf(
        R.string.text_spine_work1,
        R.string.text_spine_work2,
        R.string.text_spine_work3,
        R.string.text_spine_work4,
        R.string.text_spine_work5
    )
    private val spineImageListWorkouts = listOf(
        R.drawable.sp1,
        R.drawable.sp2,
        R.drawable.sp3,
        R.drawable.sp4,
        R.drawable.sp5
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_workout_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val workoutAdapter = WorkoutAdapter(getWorkoutsList())
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView_workouts)

        recyclerView.adapter = workoutAdapter
        recyclerView.layoutManager = LinearLayoutManager(context)
    }

    private fun getWorkoutsList(): ArrayList<ModelWorkout> {
        val listWorkout: ArrayList<ModelWorkout> = arrayListOf()
        when (requireArguments().getString("item")) {
            "Butt" -> {
                for (i in buttTitleListWorkouts.indices) {
                    listWorkout.add(
                        ModelWorkout(
                            buttTitleListWorkouts[i],
                            buttImageListWorkouts[i]
                        )
                    )
                }
            }

            "ABS" -> {
                for (i in absTitleListWorkouts.indices) {
                    listWorkout.add(ModelWorkout(absTitleListWorkouts[i], absImageListWorkouts[i]))
                }
            }

            "Arms" -> {
                for (i in armsTitleListWorkouts.indices) {
                    listWorkout.add(
                        ModelWorkout(
                            armsTitleListWorkouts[i],
                            armsImageListWorkouts[i]
                        )
                    )
                }
            }

            "Chest" -> {
                for (i in chestTitleListWorkouts.indices) {
                    listWorkout.add(
                        ModelWorkout(
                            chestTitleListWorkouts[i],
                            chestImageListWorkouts[i]
                        )
                    )
                }
            }

            "Legs" -> {
                for (i in legsTitleListWorkouts.indices) {
                    listWorkout.add(
                        ModelWorkout(
                            legsTitleListWorkouts[i],
                            legsImageListWorkouts[i]
                        )
                    )
                }
            }

            "Spine" -> {
                for (i in spineTitleListWorkouts.indices) {
                    listWorkout.add(
                        ModelWorkout(
                            spineTitleListWorkouts[i],
                            spineImageListWorkouts[i]
                        )
                    )
                }
            }
        }
        return listWorkout
    }

}