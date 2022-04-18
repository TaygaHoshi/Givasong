package com.example.givasong

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity() {
    private lateinit var musics: ArrayList<Music>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // get recycler view into variable
        val rvContacts: RecyclerView = findViewById(R.id.rvMusic)

        // get FAB into variable
        val fabToAddMusic: FloatingActionButton = findViewById(R.id.floatingActionButton) //startActivity(Intent(this@MainActivity, OtherActivity::class.java))

        fabToAddMusic.setOnClickListener { view ->

            val intent = Intent(this, AddForm::class.java).apply { }
            startActivity(intent)




        }


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

