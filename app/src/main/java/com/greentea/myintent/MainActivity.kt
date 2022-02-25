package com.greentea.myintent

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity(), View.OnClickListener {

    //melakukan assigned pada tv_result
    private lateinit var tvResult: TextView

    private val resultLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult())
        { result ->
        //mengambil data dari activity MoveForResult
            if(result.resultCode == MoveForResultActivity.RESULT_CODE && result.data != null){
                val selectedValue = result.data?.getIntExtra(MoveForResultActivity.EXTRA_SELECTED_VALUE, 0)
                tvResult.text = "Hasil : $selectedValue"
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //menyamakan id btn
        val btnMoveActivity: Button = findViewById(R.id.btn_move_activity)
        val btnMoveWithDataActivity: Button = findViewById(R.id.btn_move_activity_data)
        val btnMoveWithObjectActivity: Button = findViewById(R.id.btn_move_activity_object)
        val btnDialNumber: Button = findViewById(R.id.btn_dial_number)
        val btnMoveWithResultActivity: Button = findViewById(R.id.btn_move_for_result)
        tvResult = findViewById(R.id.tv_result)

        btnMoveActivity.setOnClickListener(this)
        btnMoveWithDataActivity.setOnClickListener(this)
        btnMoveWithObjectActivity.setOnClickListener(this)
        btnDialNumber.setOnClickListener(this)
        btnMoveWithResultActivity.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.btn_move_activity -> {
                //this is where we want to call the MoveActivity using Explicit Intent
                val moveIntent = Intent(this@MainActivity, MoveActivity::class.java)
                startActivity(moveIntent)
            }

            R.id.btn_move_activity_data -> {
                //berpindah actvity dengan data
                val moveWithDataIntent = Intent(this@MainActivity, MoveWithDataActivity::class.java)
                moveWithDataIntent.putExtra(MoveWithDataActivity.EXTRA_NAME, "Marchenda Fayza")
                moveWithDataIntent.putExtra(MoveWithDataActivity.EXTRA_AGE, 19)
                startActivity(moveWithDataIntent)
            }

            R.id.btn_move_activity_object -> {
                val person = Person(
                    "Marchenda Fayza Madjid",
                    19,
                    "marchendafayzam@gmail.com",
                    "Balikpapan, Kalimantan Timur"
                )

                //berpindah actvity dengan objek
                val moveWithObjectIntent = Intent(this@MainActivity, MoveWithObjectActivity::class.java)
                moveWithObjectIntent.putExtra(MoveWithObjectActivity.EXTRA_PERSON, person)
                startActivity(moveWithObjectIntent)
            }

            //mulai menggunakan implicit intent untuk menelpon seseorang
            R.id.btn_dial_number -> {
                val noHP = "08123456789"

                //menggunakan action dial, dengan melakukan parse nomor HP menggunakan Uri.parse
                val dialPhoneIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$noHP"))
                startActivity(dialPhoneIntent)
            }

            R.id.btn_move_for_result -> {
                val moveForResultIntent = Intent(this@MainActivity, MoveForResultActivity::class.java)

                //we use resultLauncher.Launch(), not startActivity()
                resultLauncher.launch(moveForResultIntent)
            }
        }
    }
}