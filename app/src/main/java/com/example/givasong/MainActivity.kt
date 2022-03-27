package com.example.givasong

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {
    private lateinit var musics: ArrayList<Music>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // get recycler view into variable
        val rvContacts: RecyclerView = findViewById<RecyclerView>(R.id.rvMusic)

        // horizontal line splitting decoration
        rvContacts.addItemDecoration(
            DividerItemDecoration(
                rvContacts.getContext(),
                DividerItemDecoration.VERTICAL
            )
        )
        // Initialize music
        musics = Music.createMusicsList(20)
        // Create adapter passing in the sample user data
        val adapter = MusicAdapter(musics)
        // Attach the adapter to the recyclerview to populate items
        rvContacts.adapter = adapter
        // Set layout manager to position the items
        rvContacts.layoutManager = LinearLayoutManager(this)
    }
}

