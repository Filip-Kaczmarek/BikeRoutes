package com.example.sportsapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.sportsapp.route.RouteModel
import kotlin.properties.Delegates

class RouteDetailsFragment constructor(id: Int, val name: String, val description: String) : Fragment(R.layout.fragment_route_details) {
    private lateinit var routeNameView: TextView
    private lateinit var routeDescriptionView: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_route_details, container, false)
        routeNameView = view.findViewById<TextView>(R.id.fragmentRouteName)
        routeDescriptionView = view.findViewById<TextView>(R.id.fragmentRouteDescription)
        routeNameView.text = name
        routeDescriptionView.text = description
        return view
    }
}