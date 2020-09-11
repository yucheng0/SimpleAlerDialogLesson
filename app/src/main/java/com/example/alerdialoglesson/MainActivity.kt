package com.example.alerdialoglesson

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val builder = AlertDialog.Builder(this)
        builder.setTitle("尊敬的用户")
        builder.setMessage("你真的要卸载我吗？")
        builder.setPositiveButton("残忍卸载") { dialog, which -> tv_alert.text = "虽然依依不舍，还是只能离开了" }
        builder.setNegativeButton("我再想想") { dialog, which -> tv_alert.text = "让我再陪你三百六十五个日夜" }
        val alert = builder.create()
        alert.show()
    }
}