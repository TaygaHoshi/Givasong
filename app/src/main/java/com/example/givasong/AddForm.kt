package com.example.givasong


import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class AddForm  : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_music_layout)

        // Initialize textboxes and button
        val durationBox: EditText = findViewById(R.id.add_music_duration)
        val artistBox: EditText = findViewById(R.id.add_artist_name)
        val nameBox: EditText = findViewById(R.id.add_music_name)
        val button:Button = findViewById(R.id.add_music_button)

        // Add music button OnClickListener
        button.setOnClickListener {
            addMusic(durationBox, artistBox, nameBox)
        }

    }


    private fun addMusic(durationBox:EditText, artistBox:EditText, nameBox:EditText) {

        // Updates SharedPreferences with newly added data

        val duration = durationBox.text
        val artist = artistBox.text
        val name = nameBox.text
        val temp = "$duration;;$artist;;$name\n"

        val sharedPref = getSharedPreferences("Saved",MODE_PRIVATE)

        with (sharedPref.edit()) {
            putString("Saved", sharedPref.getString("Saved", "") + temp)
            commit()
        }

        // Clear the textboxes
        durationBox.text.clear()
        artistBox.text.clear()
        nameBox.text.clear()
    }

}