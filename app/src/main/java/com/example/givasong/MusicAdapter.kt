package com.example.givasong

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import androidx.recyclerview.widget.RecyclerView

class MusicAdapter (private val appContext: Context) : RecyclerView.Adapter<MusicAdapter.ViewHolder>(){

    private var mMusic: List<Music> = getSongsFromSharedPreferences()
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

        viewHolder.itemView.setOnLongClickListener {

            onItemClick(tempMusic, position)

        }
    }

    private fun onItemClick(music: Music, position: Int):Boolean {

        chooseRandom(-1)

        val sharedPref = appContext.getSharedPreferences("Saved", MODE_PRIVATE)
        var temp = sharedPref.getString("Saved", "")

        temp = temp?.replace("${music.duration};;${music.artist};;${music.name}\n", "")

        with (sharedPref.edit()) {
            clear()
            putString("Saved", temp)
            commit()
        }

        mMusic = getSongsFromSharedPreferences()
        notifyItemRemoved(position)

        return true

    }

    private fun getSongsFromSharedPreferences(): MutableList<Music> {

        // Gets songs from sharedpreferences

        val temp: MutableList<Music> = ArrayList()
        var id = 0

        val sharedPref = appContext.getSharedPreferences("Saved", MODE_PRIVATE)
        val musicsList = sharedPref.getString("Saved", "")
        print("Musics: $musicsList\n\n\n\n")
        if (musicsList == null || musicsList == ""){
            temp += (Music(0, "00:00", "Add a new music!", ""))
        }
        else {
            val musicsArray = musicsList.split("\n")
            musicsArray.forEach {
                if(it.contains(";")){
                    print("it: $it \n\n\n")
                    val musicLine = it.split(";;")
                    val musicObjTemp = Music(id, musicLine[0], musicLine[1], musicLine[2])
                    temp += (musicObjTemp)

                    id += 1
                }

            }

        }
        return temp
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