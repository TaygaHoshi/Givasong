package com.example.givasong

class Music (val artist: String, val name: String){

    companion object {
        private var lastMusicId = 0
        fun createMusicsList(numMusics: Int): ArrayList<Music> {
            val musics = ArrayList<Music>()
            for (i in 1..numMusics) {
                musics.add(Music("Person " + ++lastMusicId, "Test $lastMusicId"))
            }
            return musics
        }
    }


}