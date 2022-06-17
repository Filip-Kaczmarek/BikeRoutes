package com.example.sportsapp.route

import android.content.Intent
import android.graphics.BitmapFactory
import android.util.Log
import android.view.KeyCharacterMap.load
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sportsapp.DetailActivity
import com.example.sportsapp.MainActivity
import com.example.sportsapp.R
import com.google.firebase.database.core.Context
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import io.grpc.InternalServiceProviders.load
import java.io.File
import java.lang.System.load
import java.util.ServiceLoader.load

class RouteAdapter(private val routesList: ArrayList<RouteModel>) : RecyclerView.Adapter<RouteAdapter.RouteViewHolder>() {

    private lateinit var mListener: onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener){
        mListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RouteViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.route_item, parent, false)
        return RouteViewHolder(v, mListener)
    }

    override fun onBindViewHolder(holder: RouteViewHolder, position: Int) {
        val route = routesList[position]
        holder.bindView(route)
    }

    override fun getItemCount(): Int {
        return routesList.size;
    }

    inner class RouteViewHolder(itemView: View, listener: onItemClickListener) : RecyclerView.ViewHolder(itemView){
        private val name = itemView.findViewById<TextView>(R.id.routeName)
        private val length = itemView.findViewById<TextView>(R.id.routeLength)
        private var image = itemView.findViewById<ImageView>(R.id.routeImage)

        init {

            itemView.setOnClickListener{
                listener.onItemClick(adapterPosition)
            }
        }

        fun bindView(routeModel: RouteModel){
            name.text = routeModel.name
            length.text = routeModel.length.toString() + " km"
            val storage = FirebaseStorage.getInstance()
            val gsReference = storage.reference.child(routeModel.pictureUrl)
            val localFile = File.createTempFile("tempImg", "png")
            gsReference.getFile(localFile).addOnSuccessListener {
                val bitmap = BitmapFactory.decodeFile(localFile.absolutePath)
                image.setImageBitmap(bitmap)
            }.addOnFailureListener{
                Log.d("error", "failed to download picture from database")
            }
        }

        fun getImage(routeModel: RouteModel){

        }

    }
}