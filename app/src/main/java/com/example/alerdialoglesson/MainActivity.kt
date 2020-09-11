package com.example.alerdialoglesson

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //按鍵處理
        btnSingleWith2Button.setOnClickListener(this)
        btnSingleWith3Button.setOnClickListener(this)
        btnGeneralList.setOnClickListener(this)
        btnSingleListWithRadioButton.setOnClickListener(this)
        btnMultiList.setOnClickListener(this)
        btnCustom.setOnClickListener(this)


        //     fixAlertDialog()
        //       listAlerDialog()
        //  sigleselect_listAlerDialogwithRadioButton()
//        multiSelect_listAlerDialogwithRadioButton()
        //    cutomAlerDialog()
    }


    override fun onClick(p0: View?) {
        when (p0) {
            btnSingleWith2Button -> fixAlertDialogWith2Button()
            btnSingleWith3Button -> fixAlertDialogWith3Button()
            btnGeneralList -> listAlerDialog()
            btnSingleListWithRadioButton -> sigleselect_listAlerDialogwithRadioButton()
            btnMultiList -> multiSelect_listAlerDialogwithRadioButton()
            btnCustom -> cutomAlerDialog()
        }
    }

    fun fixAlertDialogWith2Button() {
        val builder = AlertDialog.Builder(this)
                //          builder.setTitle("尊敬的用户") 可以簡化buile.setTitle = .setTitle 它是一整串的
                .setTitle("尊敬的用户")
                .setMessage("你真的要卸载我吗？")
                .setPositiveButton("残忍卸载") { dialog, which -> tv_alert.text = "虽然依依不舍，还是只能离开了" }
                //       builder.setNeutralButton("都不做決定"){dialog, which -> tv_alert.text = "再見" }
                //簡化型的, 不用寫參數用_ 底線來代替dialog, which , which 代表那個按鈕被按了
                // 寫參數會比較好
                .setNeutralButton("都不做決定") { _, _ -> tv_alert.text = "再見" }
        val alert = builder.create()
        alert.show()
    }

    //
    fun fixAlertDialogWith3Button() {
        val builder = AlertDialog.Builder(this)
                //          builder.setTitle("尊敬的用户") 可以簡化buile.setTitle = .setTitle 它是一整串的
                .setTitle("尊敬的用户")
                .setMessage("你真的要卸载我吗？")
                .setPositiveButton("残忍卸载") { dialog, which -> tv_alert.text = "虽然依依不舍，还是只能离开了" }
                .setNegativeButton("我再想想") { dialog, which -> tv_alert.text = "让我再陪你三百六十五个日夜" }
                //       builder.setNeutralButton("都不做決定"){dialog, which -> tv_alert.text = "再見" }
                //簡化型的, 不用寫參數用_ 底線來代替dialog, which , which 代表那個按鈕被按了
                // 寫參數會比較好
                .setNeutralButton("都不做決定") { _, _ -> tv_alert.text = "再見" }
        val alert = builder.create()
        alert.show()
    }

    //

    fun listAlerDialog() {
        val lunch = ArrayList<String>()
        //lunch.add("雞腿飯")  不可以簡化. ddd("雞腿飯"), 第1個可以, 但第2個就不可以了
        lunch.add("雞腿飯")
        lunch.add("魯肉飯")
        lunch.add("排骨飯")
        lunch.add("蛋炒飯")
        lunch.add("陽春麵")
        AlertDialog.Builder(this@MainActivity)
                .setItems(lunch.toTypedArray()) { _, which ->
                    val name = lunch[which]
                    Toast.makeText(applicationContext, "你今天吃的是" + name, Toast.LENGTH_SHORT).show()
                }
                .show()
    }


    fun sigleselect_listAlerDialogwithRadioButton() {
        val lunch = ArrayList<String>()
        lunch.add("雞腿飯")
        lunch.add("魯肉飯")
        lunch.add("排骨飯")
        lunch.add("蛋炒飯")
        lunch.add("陽春麵")

        var singleChoiceIndex = -1  //預設選到誰，不想選到誰就-1即可 (範圍從0=雞腿飯開始）
        AlertDialog.Builder(this@MainActivity)
                .setSingleChoiceItems(lunch.toTypedArray(), singleChoiceIndex)
                { _, which -> singleChoiceIndex = which }
                .setPositiveButton("Ok") { dialog, which ->
                    Toast.makeText(this@MainActivity, "你選擇的是" + lunch[singleChoiceIndex], Toast.LENGTH_SHORT).show()
                    dialog.dismiss()
                }.show()
    }

    //=====================================================================
    fun multiSelect_listAlerDialogwithRadioButton() {
        val lunch = ArrayList<String>()
        lunch.add("雞腿飯")
        lunch.add("魯肉飯")
        lunch.add("排骨飯")
        lunch.add("蛋炒飯")
        lunch.add("陽春麵")

        val checkedStatusList = ArrayList<Boolean>()
        for (s in lunch) {
            checkedStatusList.add(false)
        }

        AlertDialog.Builder(this@MainActivity)
                .setMultiChoiceItems(lunch.toTypedArray(), BooleanArray(lunch.size)
                ) { _, which, isChecked -> checkedStatusList[which] = isChecked }
                .setPositiveButton("ok") { _, _ ->
                    val sb = StringBuilder()
                    var isEmpty = true
                    checkedStatusList.forEachIndexed { index, b ->
                        if (b) {
                            sb.append(lunch[index])
                            sb.append(" ")
                            isEmpty = false
                        }
                    }
                    if (!isEmpty) {
                        Toast.makeText(this@MainActivity, "你選擇的是$sb", Toast.LENGTH_SHORT).show()
                        for (s in lunch) {
                            checkedStatusList.add(false)
                        }
                    } else {
                        Toast.makeText(this@MainActivity, "請勾選項目", Toast.LENGTH_SHORT).show()
                    }
                }
                .show()
    }


    //=========================================================

    fun cutomAlerDialog() {
        //item 的定義很重要 -> 取得自訂的版面。
        val item = LayoutInflater.from(this@MainActivity).inflate(R.layout.itemlayout, null)
        AlertDialog.Builder(this@MainActivity)
                .setTitle("輸入自己的名字")
                .setView(item)

                .setMessage("在這裡輸入")
                .setPositiveButton("Ok") { _, _ ->
                    val editText = item.findViewById(R.id.edit_text) as EditText
                    val name = editText.text.toString()
                    if (TextUtils.isEmpty(name)) {
                        Toast.makeText(applicationContext, editText.text, Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(applicationContext, "Hi " + name, Toast.LENGTH_SHORT).show()
                    }
                }
                .show()
    }


}