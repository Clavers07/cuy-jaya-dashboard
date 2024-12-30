package com.example.database.bahan

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.database.Formatter
import com.example.database.services.DBHelper
import com.example.database.databinding.ActivityBahanTambahBinding

class BahanTambah : AppCompatActivity() {

    private lateinit var binding: ActivityBahanTambahBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Setup View Binding
        binding = ActivityBahanTambahBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.back.setOnClickListener{
            finish()
        }
        binding.kembali.setOnClickListener {
            finish()
        }


        binding.simpan.setOnClickListener {
            val db = DBHelper(this, null)

            // Mengambil nilai dari EditText
            val nameValue = binding.namaBahan.text.toString().trim()
            val hargaValue = binding.harga.text.toString()
            var num : Int
            try {
                num = Formatter.onlyInt(hargaValue)
            } catch (e: NumberFormatException) {
                num = 0 // Handle invalid input
            }

            // Cek apakah input valid
            if (nameValue.isNotEmpty() && num != null) {

                // Menambahkan data ke database
                val res = db.addBahan(nameValue, hargaValue)

                // Menampilkan pesan Toast
                if (res) {
                    Toast.makeText(this, "Berhasil menambahkan pengeluaran $nameValue", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this, "Gagal menambahkan pengeluaran $nameValue", Toast.LENGTH_LONG).show()
                }
//                // Membersihkan EditText
//                binding.enterName.text.clear()
//                binding.enterAge.text.clear()
            } else {
                Toast.makeText(this, "Masukkan data dengan benar!", Toast.LENGTH_LONG).show()
            }

            finish()

        }

    }
}