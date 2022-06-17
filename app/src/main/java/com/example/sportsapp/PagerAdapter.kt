package com.example.sportsapp

import android.content.res.Resources
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.adapter.FragmentStateAdapter

class PagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity){
    override fun getItemCount() = 4

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> { AllRoutesFragment() }
            1 -> { EasyRoutesFragment() }
            2 -> { MediumRoutesFragment() }
            3 -> { HardRoutesFragment() }
            else -> { throw Resources.NotFoundException("Position Not Found") }
        }
    }

}