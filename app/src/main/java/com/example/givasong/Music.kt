package com.example.givasong

class Music (val duration: String, val artist: String, val name: String){

    companion object {
        private var lastMusicId = 0
        fun createMusicsList(numMusics: Int): ArrayList<Music> {
            val musics = ArrayList<Music>()
            for (i in 1..numMusics) {
                musics.add(Music("01:00", "Person " + ++lastMusicId, "Music $lastMusicId"))
            }
            return musics
        }
    }


}