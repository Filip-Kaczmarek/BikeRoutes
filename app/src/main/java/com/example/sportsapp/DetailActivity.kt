package com.example.sportsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.FragmentTransaction.TRANSIT_FRAGMENT_FADE

class DetailActivity : AppCompatActivity(R.layout.activity_detail) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val routeId = intent.getStringExtra("routeId")
        val routeName = intent.getStringExtra("routeName")
        val routeCategory = intent.getStringExtra("routeCategory")
        val routeDescription = intent.getStringExtra("routeDescription")
        val fragment = RouteDetailsFragment(id = routeId!!.toInt(), name=routeName!!, description=routeDescription!!)

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.routeDetailsFragment, fragment)
            commit()
        }

        val fragmentTimer = TimerFragment()

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.timerFragment, fragmentTimer)
            setTransition(TRANSIT_FRAGMENT_FADE)
            commit()
        }

        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}