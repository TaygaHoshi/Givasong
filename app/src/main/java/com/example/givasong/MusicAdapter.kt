package com.example.givasong

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MusicAdapter (private val mMusic: List<Music>) : RecyclerView.Adapter<MusicAdapter.ViewHolder>(){


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Your holder should contain and initialize a member variable
        // for any view that will be set as you render a row
        val musicNameTextView: TextView = itemView.findViewById<TextView>(R.id.music_name)
        val artistNameTextView: TextView = itemView.findViewById<TextView>(R.id.artist_name)
    }

    // ... constructor and member variables
    // Usually involves inflating a layout from XML and returning the holder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicAdapter.ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        // Inflate the custom layout
        val contactView = inflater.inflate(R.layout.music_layout, parent, false)
        // Return a new holder instance
        return ViewHolder(contactView)
    }

    // Involves populating data into the item through holder
    override fun onBindViewHolder(viewHolder: MusicAdapter.ViewHolder, position: Int) {
        // Get the data model based on position
        val tempMusic: Music = mMusic[position]
        // Set item views based on your views and data model
        val musicNameView = viewHolder.musicNameTextView
        musicNameView.text = tempMusic.name
        val artistNameView = viewHolder.artistNameTextView
        artistNameView.text = tempMusic.artist
    }

    // Returns the total count of items in the list
    override fun getItemCount(): Int {
        return mMusic.size
    }

}