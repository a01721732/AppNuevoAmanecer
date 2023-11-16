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
    private var mediaPlayerDb: MediaPlayer? = null
    private var mediaPlayerEb: MediaPlayer? = null
    private var mediaPlayerGb: MediaPlayer? = null
    private var mediaPlayerAb: MediaPlayer? = null
    private var mediaPlayerBb: MediaPlayer? = null
    private var mediaPlayerDb2: MediaPlayer? = null

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
        mediaPlayerDb = MediaPlayer.create(this, R.raw.db)
        mediaPlayerEb = MediaPlayer.create(this, R.raw.eb)
        mediaPlayerGb = MediaPlayer.create(this, R.raw.gb)
        mediaPlayerAb = MediaPlayer.create(this, R.raw.ab)
        mediaPlayerBb = MediaPlayer.create(this, R.raw.bb)
        mediaPlayerDb2 = MediaPlayer.create(this, R.raw.db2)

        val btnReturn = findViewById(R.id.btnRegresarJuego1) as Button
        val btnA = findViewById(R.id.teclaA) as Button
        val btnB = findViewById(R.id.teclaB) as Button
        val btnC = findViewById(R.id.teclaC) as Button
        val btnD = findViewById(R.id.teclaD) as Button
        val btnE = findViewById(R.id.teclaE) as Button
        val btnF = findViewById(R.id.teclaF) as Button
        val btnG = findViewById(R.id.teclaG) as Button
        val btnC2 = findViewById(R.id.teclaC2) as Button
        val btnDb = findViewById(R.id.teclaDb) as Button
        val btnEb = findViewById(R.id.teclaEb) as Button
        val btnGb = findViewById(R.id.teclaGb) as Button
        val btnAb = findViewById(R.id.teclaAb) as Button
        val btnBb = findViewById(R.id.teclaBb) as Button
        val btnDb2 = findViewById(R.id.teclaDb2) as Button


        btnReturn.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        btnA.setOnClickListener{
            if (mediaPlayerA != null) {
                // Stop and reset the MediaPlayer to allow immediate replay
                mediaPlayerA?.stop()
                mediaPlayerA?.prepare()
                // Start playing the audio file
                mediaPlayerA?.start()
            }

        }

        btnB.setOnClickListener{

            if (mediaPlayerB != null) {
                // Stop and reset the MediaPlayer to allow immediate replay
                mediaPlayerB?.stop()
                mediaPlayerB?.prepare()
                // Start playing the audio file
                mediaPlayerB?.start()
            }
        }

        btnC.setOnClickListener{
            if (mediaPlayerC != null) {
                // Stop and reset the MediaPlayer to allow immediate replay
                mediaPlayerC?.stop()
                mediaPlayerC?.prepare()
                // Start playing the audio file
                mediaPlayerC?.start()
            }
        }

        btnD.setOnClickListener{
            if (mediaPlayerD != null) {
                // Stop and reset the MediaPlayer to allow immediate replay
                mediaPlayerD?.stop()
                mediaPlayerD?.prepare()
                // Start playing the audio file
                mediaPlayerD?.start()
            }
        }

        btnE.setOnClickListener{
            if (mediaPlayerE != null) {
                // Stop and reset the MediaPlayer to allow immediate replay
                mediaPlayerE?.stop()
                mediaPlayerE?.prepare()
                // Start playing the audio file
                mediaPlayerE?.start()
            }
        }

        btnF.setOnClickListener{
            if (mediaPlayerF != null) {
                // Stop and reset the MediaPlayer to allow immediate replay
                mediaPlayerF?.stop()
                mediaPlayerF?.prepare()
                // Start playing the audio file
                mediaPlayerF?.start()
            }
        }

        btnG.setOnClickListener{
            if (mediaPlayerG != null) {
                // Stop and reset the MediaPlayer to allow immediate replay
                mediaPlayerG?.stop()
                mediaPlayerG?.prepare()
                // Start playing the audio file
                mediaPlayerG?.start()
            }
        }

        btnC2.setOnClickListener{
            if (mediaPlayerC2 != null) {
                // Stop and reset the MediaPlayer to allow immediate replay
                mediaPlayerC2?.stop()
                mediaPlayerC2?.prepare()
                // Start playing the audio file
                mediaPlayerC2?.start()
            }
        }

        btnDb.setOnClickListener{
            if (mediaPlayerDb != null) {
                // Stop and reset the MediaPlayer to allow immediate replay
                mediaPlayerDb?.stop()
                mediaPlayerDb?.prepare()
                // Start playing the audio file
                mediaPlayerDb?.start()
            }
        }

        btnEb.setOnClickListener{
            if (mediaPlayerEb != null) {
                // Stop and reset the MediaPlayer to allow immediate replay
                mediaPlayerEb?.stop()
                mediaPlayerEb?.prepare()
                // Start playing the audio file
                mediaPlayerEb?.start()
            }
        }

        btnGb.setOnClickListener{
            if (mediaPlayerGb != null) {
                // Stop and reset the MediaPlayer to allow immediate replay
                mediaPlayerGb?.stop()
                mediaPlayerGb?.prepare()
                // Start playing the audio file
                mediaPlayerGb?.start()
            }
        }

        btnAb.setOnClickListener{
            if (mediaPlayerAb != null) {
                // Stop and reset the MediaPlayer to allow immediate replay
                mediaPlayerAb?.stop()
                mediaPlayerAb?.prepare()
                // Start playing the audio file
                mediaPlayerAb?.start()
            }
        }

        btnBb.setOnClickListener{
            if (mediaPlayerBb != null) {
                // Stop and reset the MediaPlayer to allow immediate replay
                mediaPlayerBb?.stop()
                mediaPlayerBb?.prepare()
                // Start playing the audio file
                mediaPlayerBb?.start()
            }
        }

        btnDb2.setOnClickListener{
            if (mediaPlayerDb2 != null) {
                // Stop and reset the MediaPlayer to allow immediate replay
                mediaPlayerDb2?.stop()
                mediaPlayerDb2?.prepare()
                // Start playing the audio file
                mediaPlayerDb2?.start()
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

        mediaPlayerDb?.release()
        mediaPlayerDb = null

        mediaPlayerEb?.release()
        mediaPlayerEb = null

        mediaPlayerGb?.release()
        mediaPlayerGb = null

        mediaPlayerAb?.release()
        mediaPlayerAb = null

        mediaPlayerBb?.release()
        mediaPlayerBb = null

        mediaPlayerDb2?.release()
        mediaPlayerDb2 = null
    }
}