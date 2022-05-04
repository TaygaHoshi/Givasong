package com.example.givasong

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity() {
    private lateinit var songs_data: MutableList<Music>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        onResume()
    }

    override fun onResume() {
        super.onResume()
        setContentView(R.layout.activity_main)

        // Initialize recyclerview variable
        val rvSongs: RecyclerView = findViewById(R.id.rvMusic)

        // Initialize buttons' variables
        val fabGivasong: FloatingActionButton = findViewById(R.id.givasong_fab) //startActivity(Intent(this@MainActivity, OtherActivity::class.java))
        val addMusic: Button = findViewById(R.id.add_music)

        // Get songs and attach them to the recyclerview
        songs_data = getSongsFromSharedPreferences()

        val adapter = MusicAdapter(songs_data)
        rvSongs.adapter = adapter
        rvSongs.layoutManager = LinearLayoutManager(this)


        // Buttons' OnClickListeners
        fabGivasong.setOnClickListener {
            val chosenIndex = (0 until adapter.itemCount).random()
            adapter.chooseRandom(chosenIndex)

        }

        addMusic.setOnClickListener {
            val intent = Intent(this, AddForm::class.java).apply { }
            startActivity(intent)
        }

    }

    private fun getSongsFromSharedPreferences(): MutableList<Music> {

        // Gets songs from sharedpreferences

        val temp: MutableList<Music> = ArrayList()
        var id = 0

        val sharedPref = getSharedPreferences("Saved", MODE_PRIVATE)
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

}

