package com.example.givasong

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity() {
    private lateinit var musics: MutableList<Music>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        onResume()
    }

    override fun onResume() {
        super.onResume()

        setContentView(R.layout.activity_main)

        // get recycler view into variable
        val rvContacts: RecyclerView = findViewById(R.id.rvMusic)

        // get FAB into variable
        val fabToAddMusic: FloatingActionButton = findViewById(R.id.floatingActionButton) //startActivity(Intent(this@MainActivity, OtherActivity::class.java))
        val givasongButton: Button = findViewById(R.id.givasongbutton)

        fabToAddMusic.setOnClickListener {
            val intent = Intent(this, AddForm::class.java).apply { }
            startActivity(intent)
        }


        // Initialize music
        //// get musics from file
        musics = getMusicsFromFile()
        //musics = Music.createMusicsList(20)

        // Create adapter passing in the sample user data
        val adapter = MusicAdapter(musics)
        // Attach the adapter to the recyclerview to populate items
        rvContacts.adapter = adapter
        // Set layout manager to position the items
        rvContacts.layoutManager = LinearLayoutManager(this)



        givasongButton.setOnClickListener {
            val chosenIndex = (0 until adapter.itemCount).random()
            adapter.chooseRandom(chosenIndex)
        }

    }

    private fun getMusicsFromFile(): MutableList<Music> {

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

