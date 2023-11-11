package com.nuevo.nuevoamanecer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.media.MediaPlayer

class ActivityGame1 : AppCompatActivity() {

    private var mediaPlayerA: MediaPlayer? = null
    private var mediaPlayerB: MediaPlayer? = null
    private var mediaPlayerC: MediaPlayer? = null
    private var mediaPlayerD: MediaPlayer? = null
    private var mediaPlayerE: MediaPlayer? = null
    private var mediaPlayerF: MediaPlayer? = null
    private var mediaPlayerG: MediaPlayer? = null
    private var mediaPlayerC2: MediaPlayer? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game1)

        mediaPlayerA = MediaPlayer.create(this, R.raw.a)
        mediaPlayerB = MediaPlayer.create(this, R.raw.b)
        mediaPlayerC = MediaPlayer.create(this, R.raw.c)
        mediaPlayerD = MediaPlayer.create(this, R.raw.d)
        mediaPlayerE = MediaPlayer.create(this, R.raw.e)
        mediaPlayerF = MediaPlayer.create(this, R.raw.f)
        mediaPlayerG = MediaPlayer.create(this, R.raw.g)
        mediaPlayerC2 = MediaPlayer.create(this, R.raw.c2)

        val btnReturn = findViewById(R.id.btnRegresarJuego1) as Button
        val btnA = findViewById(R.id.teclaA) as Button
        val btnB = findViewById(R.id.teclaB) as Button
        val btnC = findViewById(R.id.teclaC) as Button
        val btnD = findViewById(R.id.teclaD) as Button
        val btnE = findViewById(R.id.teclaE) as Button
        val btnF = findViewById(R.id.teclaF) as Button
        val btnG = findViewById(R.id.teclaG) as Button
        val btnC2 = findViewById(R.id.teclaC2) as Button


        btnReturn.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        btnA.setOnClickListener{
            if(mediaPlayerA != null && !mediaPlayerA!!.isPlaying){
                mediaPlayerA?.start()
            }
        }

        btnB.setOnClickListener{
            if(mediaPlayerB != null && !mediaPlayerB!!.isPlaying){
                mediaPlayerB?.start()
            }
        }

        btnC.setOnClickListener{
            if(mediaPlayerC != null && !mediaPlayerC!!.isPlaying){
                mediaPlayerC?.start()
            }
        }

        btnD.setOnClickListener{
            if(mediaPlayerD != null && !mediaPlayerD!!.isPlaying){
                mediaPlayerD?.start()
            }
        }

        btnE.setOnClickListener{
            if(mediaPlayerE != null && !mediaPlayerE!!.isPlaying){
                mediaPlayerE?.start()
            }
        }

        btnF.setOnClickListener{
            if(mediaPlayerF != null && !mediaPlayerF!!.isPlaying){
                mediaPlayerF?.start()
            }
        }

        btnG.setOnClickListener{
            if(mediaPlayerG != null && !mediaPlayerG!!.isPlaying){
                mediaPlayerG?.start()
            }
        }

        btnC2.setOnClickListener{
            if(mediaPlayerC2 != null && !mediaPlayerC2!!.isPlaying){
                mediaPlayerC2?.start()
            }
        }






    }

    override fun onDestroy() {
        super.onDestroy()

        mediaPlayerA?.release()
        mediaPlayerA = null

        mediaPlayerB?.release()
        mediaPlayerB = null

        mediaPlayerC?.release()
        mediaPlayerC = null

        mediaPlayerD?.release()
        mediaPlayerD = null

        mediaPlayerE?.release()
        mediaPlayerE = null

        mediaPlayerF?.release()
        mediaPlayerF = null

        mediaPlayerG?.release()
        mediaPlayerG = null

        mediaPlayerC2?.release()
        mediaPlayerC2 = null
    }
}