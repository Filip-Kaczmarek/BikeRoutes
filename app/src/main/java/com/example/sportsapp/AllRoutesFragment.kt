package com.example.sportsapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sportsapp.route.RouteAdapter
import com.example.sportsapp.route.RouteModel
import com.google.firebase.database.*

class AllRoutesFragment : Fragment() {
    lateinit var rootView: View
    lateinit var routeList: ArrayList<RouteModel>
    private lateinit var databaseReference: DatabaseReference
    private var routeAdapter: RouteAdapter? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_all_routes, container, false)
        val recyclerView = rootView.findViewById<RecyclerView>(R.id.allRoutesList)
        databaseReference = FirebaseDatabase.getInstance().getReference("routes")
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        routeList = ArrayList()
        routeAdapter = RouteAdapter(routeList)
        recyclerView.adapter = routeAdapter
        routeAdapter!!.setOnItemClickListener(object : RouteAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {
                val clickedItem = routeList[position]
                val routeId = clickedItem.routeId.toString()
                val routeCategory = clickedItem.category
                val routeName = clickedItem.name
                val routeDescription = clickedItem.description
                routeAdapter?.notifyItemChanged(position)
                val intent = Intent(context, DetailActivity::class.java).apply {
                    putExtra("routeId", routeId)
                    putExtra("routeName", routeName)
                    putExtra("routeCategory", routeCategory)
                    putExtra("routeDescription", routeDescription)
                }
                startActivity(intent)
            }
        })

        databaseReference.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (dataSnapshot in snapshot.children){
                    val route = dataSnapshot.getValue(RouteModel::class.java)
                    routeList.add(route!!)
                }
                routeAdapter!!.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w("DB error", "Failed to read value.", error.toException())
            }

        })
        return rootView
    }
}