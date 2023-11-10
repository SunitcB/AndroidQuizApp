package com.example.quizapp

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Date

class MainActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        var answer1 = "private, protected, public, internal"
        var answer2 = arrayListOf<String>("sealed", "data", "abstract", "open")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var resetBtn = findViewById<Button>(R.id.resetBtn)
        var submitBtn = findViewById<Button>(R.id.submitBtn)
        var radioGrp = findViewById<RadioGroup>(R.id.radioGroup)

        var checkBox1 = findViewById<CheckBox>(R.id.checkbox1)
        var checkBox2 = findViewById<CheckBox>(R.id.checkbox2)
        var checkBox3 = findViewById<CheckBox>(R.id.checkbox3)
        var checkBox4 = findViewById<CheckBox>(R.id.checkbox4)
        var checkBox5 = findViewById<CheckBox>(R.id.checkbox5)

        var question1answer = ""

        resetBtn.setOnClickListener {
            radioGrp.clearCheck()
            question1answer = ""
            checkBox1.isChecked = false
            checkBox2.isChecked = false
            checkBox3.isChecked = false
            checkBox4.isChecked = false
            checkBox5.isChecked = false
        }

        radioGrp.setOnCheckedChangeListener { group, checkedId ->
            val checkedRadio = findViewById<RadioButton>(checkedId)
            if (checkedRadio != null) {
                question1answer = checkedRadio.text.toString()
            }
        }


        submitBtn.setOnClickListener {
            var question2answer = arrayListOf<String>()
            var correctness = 0
            if (checkBox1.isChecked) {
                question2answer.add(checkBox1.text.toString())
            }
            if (checkBox2.isChecked) {
                question2answer.add(checkBox2.text.toString())
            }
            if (checkBox3.isChecked) {
                question2answer.add(checkBox3.text.toString())
            }
            if (checkBox4.isChecked) {
                question2answer.add(checkBox4.text.toString())
            }
            if (checkBox5.isChecked) {
                question2answer.add(checkBox5.text.toString())
            }

            if (question1answer.equals("") && question2answer.size == 0) {
                Toast.makeText(
                    this,
                    "Please choose answers to the given questions",
                    Toast.LENGTH_SHORT
                )
            } else {
                if (question1answer.equals(answer1)) {
                    correctness += 50
                }
                if (question2answer == answer2) {
                    correctness += 50
                }
                val alertDialogBuilder = AlertDialog.Builder(this)
                val suffix = "\n\nSubmitted: " + LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("dd, MMM YYYY - hh:mm:ss a")) + "\nResult Percentage: " + correctness + " %"
                alertDialogBuilder.setTitle("Results")
                if (correctness == 50) {
                    alertDialogBuilder.setMessage("Congratulations! you've 1 correct answer, but you could be better." + suffix)
                } else if (correctness == 100) {
                    alertDialogBuilder.setMessage("OUTSTANDING! You are the Kotlin master." + suffix)
                } else {
                    alertDialogBuilder.setMessage("Boo Hoo! go study you muggle." + suffix)
                }
                alertDialogBuilder.setNegativeButton("Close") { dialog, which ->
                    dialog.dismiss() // Close the dialog
                }
                val alertDialog = alertDialogBuilder.create()
                alertDialog.show()
            }
        }
    }
}