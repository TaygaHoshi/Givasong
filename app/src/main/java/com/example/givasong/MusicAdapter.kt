package com.example.givasong

import android.content.Context
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import androidx.recyclerview.widget.RecyclerView

class MusicAdapter (private val mMusic: List<Music>) : RecyclerView.Adapter<MusicAdapter.ViewHolder>(){

    private var selectedPosition = -1
    private var previousSelectedPosition = -1
    private lateinit var context: Context


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val musicNameTextView: TextView = itemView.findViewById<TextView>(R.id.card_music_name)
        val artistNameTextView: TextView = itemView.findViewById<TextView>(R.id.card_artist_name)
        val durationTextView: TextView = itemView.findViewById<TextView>(R.id.card_music_duration)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicAdapter.ViewHolder {

        context = parent.context

        // Inflate the custom layout
        val inflater = LayoutInflater.from(context)
        val contactView = inflater.inflate(R.layout.muslc_card_layout, parent, false)

        return ViewHolder(contactView) // Return a new holder instance
    }


    override fun onBindViewHolder(viewHolder: MusicAdapter.ViewHolder, position: Int) {
        // Populate data into the item through viewholder

        // Highlight randomly selected item using theme colors
        if (selectedPosition == position){
            viewHolder.itemView.setBackgroundColor(context.themeColor(androidx.appcompat.R.attr.colorPrimary))
        }
        else{
            viewHolder.itemView.setBackgroundColor(context.themeColor(com.google.android.material.R.attr.colorOnPrimary))
        }

        // Get the data model based on position
        val tempMusic: Music = mMusic[position]

        // Set item views based on views and data model
        val musicDurationView = viewHolder.durationTextView
        musicDurationView.text = tempMusic.duration
        val musicNameView = viewHolder.musicNameTextView
        musicNameView.text = tempMusic.name
        val artistNameView = viewHolder.artistNameTextView
        artistNameView.text = tempMusic.artist
    }


    override fun getItemCount(): Int {
        // Returns the total count of items in the list
        return mMusic.size
    }

    fun chooseRandom(index: Int) {
        // Update the highlighted item based on its position

        selectedPosition = index

        notifyItemChanged(selectedPosition)
        notifyItemChanged(previousSelectedPosition)

        previousSelectedPosition = selectedPosition

    }

    // Get theme colors
    @ColorInt
    fun Context.themeColor(@AttrRes attrRes: Int): Int = TypedValue()
        .apply { theme.resolveAttribute (attrRes, this, true) }
        .data


}