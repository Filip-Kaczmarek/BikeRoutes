package com.example.sportsapp

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.view.isVisible
import java.util.*

class TimerFragment : Fragment(), View.OnClickListener{
    lateinit var rootView: View
    var seconds = 0
    var running = false
    var lastRunningState = false
    private lateinit var startStopButton: Button
    private lateinit var resetButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_timer, container, false)

        if(savedInstanceState != null){
            seconds = savedInstanceState.getInt("seconds")
            running = savedInstanceState.getBoolean("running")
            lastRunningState = savedInstanceState.getBoolean("lastRunningState")
        }
        runStopWatch(rootView)
        startStopButton = rootView.findViewById(R.id.startStopButton)
        startStopButton.setOnClickListener(this)
        resetButton = rootView.findViewById(R.id.resetButton)
        resetButton.setOnClickListener(this)
        // Inflate the layout for this fragment
        return rootView
    }

    private fun runStopWatch(view: View) {
        val timeText = view.findViewById<TextView>(R.id.timeView)
        val handler = Handler()

        handler.post(Runnable{
            run {
                var hours = seconds/3600
                var minutes = (seconds%3600)/60

                val time = String.format(Locale.getDefault(), "%d:%02d:%02d", hours, minutes, seconds)
                timeText.text = time

                if(running){
                    seconds++
                }

                handler.postDelayed({ }, 1000)
            }
        })
    }

    fun onClickStartStop() {
        running = !running
        when(startStopButton.text){
            "Stop" -> startStopButton.text = "Start"
            "Start" -> startStopButton.text = "Stop"
        }
    }

    fun onClickReset() {
        running = false
        startStopButton.text = "Start"
        seconds = 0
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.startStopButton -> onClickStartStop()
            R.id.resetButton -> onClickReset()
        }
    }
}