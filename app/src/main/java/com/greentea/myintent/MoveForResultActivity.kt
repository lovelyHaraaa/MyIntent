package com.greentea.myintent

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RadioGroup

class MoveForResultActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var btnChoose: Button
    private lateinit var rgNumber: RadioGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_move_for_result)

        //menyamakan id
        btnChoose = findViewById(R.id.btn_choose)
        rgNumber = findViewById(R.id.rg_number)

        //because we use button, don't forget the onClickListener
        btnChoose.setOnClickListener(this)
    }

    //membuat objek dengan mengembalikan nilai
    companion object {
        const val EXTRA_SELECTED_VALUE = "extra_selected_value"
        const val RESULT_CODE = 110
    }

    override fun onClick(p0: View?) {
        if(p0?.id == R.id.btn_choose){
            if(rgNumber.checkedRadioButtonId > 0){
                var value = 0
                when(rgNumber.checkedRadioButtonId){
                    R.id.rb_50 -> value = 50
                    R.id.rb_100 -> value = 100
                    R.id.rb_150 -> value = 150
                    R.id.rb_200 -> value = 200
                }

                //menampilkan data dari hasil pengkodisian diatas menggunakan intent
                val resultIntent = Intent().putExtra(EXTRA_SELECTED_VALUE, value)

                setResult(RESULT_CODE, resultIntent)
                finish()
            }
        }
    }
}