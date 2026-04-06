package com.example.studentapp

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var etNama: EditText
    private lateinit var etNim: EditText
    private lateinit var spinnerProdi: Spinner
    private lateinit var rgJenisKelamin: RadioGroup
    private lateinit var cbMembaca: CheckBox
    private lateinit var cbOlahraga: CheckBox
    private lateinit var cbMusik: CheckBox
    private lateinit var cbGaming: CheckBox
    private lateinit var cbMasak: CheckBox
    private lateinit var btnTampilkan: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
        setupSpinner()
        setupButton()
    }

    private fun initViews() {
        etNama         = findViewById(R.id.etNama)
        etNim          = findViewById(R.id.etNim)
        spinnerProdi   = findViewById(R.id.spinnerProdi)
        rgJenisKelamin = findViewById(R.id.rgJenisKelamin)
        cbMembaca      = findViewById(R.id.cbMembaca)
        cbOlahraga     = findViewById(R.id.cbOlahraga)
        cbMusik        = findViewById(R.id.cbMusik)
        cbGaming       = findViewById(R.id.cbGaming)
        cbMasak        = findViewById(R.id.cbMasak)
        btnTampilkan   = findViewById(R.id.btnTampilkan)
    }

    private fun setupSpinner() {
        val prodiList = resources.getStringArray(R.array.program_studi)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, prodiList)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerProdi.adapter = adapter
    }

    private fun setupButton() {
        btnTampilkan.setOnClickListener {
            if (validateInput()) {
                val hobi = arrayListOf<String>()
                if (cbMembaca.isChecked)  hobi.add(cbMembaca.text.toString())
                if (cbOlahraga.isChecked) hobi.add(cbOlahraga.text.toString())
                if (cbMusik.isChecked)    hobi.add(cbMusik.text.toString())
                if (cbGaming.isChecked)   hobi.add(cbGaming.text.toString())
                if (cbMasak.isChecked)    hobi.add(cbMasak.text.toString())

                val selectedRadioId = rgJenisKelamin.checkedRadioButtonId
                val jenisKelamin = findViewById<RadioButton>(selectedRadioId).text.toString()

                val userData = UserData(
                    namaLengkap  = etNama.text.toString().trim(),
                    nim          = etNim.text.toString().trim(),
                    programStudi = spinnerProdi.selectedItem.toString(),
                    jenisKelamin = jenisKelamin,
                    hobi         = hobi
                )

                val intent = Intent(this, ProfileActivity::class.java)
                intent.putExtra("USER_DATA", userData)
                startActivity(intent)
            }
        }
    }

    private fun validateInput(): Boolean {
        val nama = etNama.text.toString().trim()
        val nim  = etNim.text.toString().trim()

        if (nama.isEmpty()) {
            etNama.error = "Nama lengkap tidak boleh kosong"
            etNama.requestFocus()
            return false
        }
        if (nim.isEmpty()) {
            etNim.error = "NIM tidak boleh kosong"
            etNim.requestFocus()
            return false
        }
        if (rgJenisKelamin.checkedRadioButtonId == -1) {
            Toast.makeText(this, "Pilih jenis kelamin terlebih dahulu", Toast.LENGTH_SHORT).show()
            return false
        }
        val hobiDipilih = listOf(cbMembaca, cbOlahraga, cbMusik, cbGaming, cbMasak).count { it.isChecked }
        if (hobiDipilih == 0) {
            Toast.makeText(this, "Pilih minimal satu hobi", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }
}