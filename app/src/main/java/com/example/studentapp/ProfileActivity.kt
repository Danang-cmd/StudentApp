package com.example.studentapp

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val userData = intent.getParcelableExtra<UserData>("USER_DATA")

        userData?.let { data ->
            val tvInitial = findViewById<TextView>(R.id.tvInitial)
            tvInitial.text = data.namaLengkap.firstOrNull()?.uppercaseChar()?.toString() ?: "?"

            findViewById<TextView>(R.id.tvNama).text        = data.namaLengkap
            findViewById<TextView>(R.id.tvNim).text         = data.nim
            findViewById<TextView>(R.id.tvProdi).text       = data.programStudi
            findViewById<TextView>(R.id.tvJenisKelamin).text = data.jenisKelamin
            findViewById<TextView>(R.id.tvHobi).text        =
                if (data.hobi.isEmpty()) "-" else data.hobi.joinToString(" • ")

            val ivGenderIcon = findViewById<ImageView>(R.id.ivGenderIcon)
            ivGenderIcon.setImageResource(
                if (data.jenisKelamin.equals("Laki-laki", ignoreCase = true))
                    android.R.drawable.ic_menu_compass
                else
                    android.R.drawable.ic_menu_agenda
            )
        }
    }
}
