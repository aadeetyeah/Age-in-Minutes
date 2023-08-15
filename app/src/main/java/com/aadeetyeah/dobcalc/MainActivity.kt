package com.aadeetyeah.dobcalc

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CalendarView
import android.widget.TextView
import android.widget.Toast
import java.text.DateFormatSymbols
import java.text.SimpleDateFormat
import java.util.*
import kotlin.time.Duration.Companion.minutes

class MainActivity : AppCompatActivity() {

    private var tvSelectedDate:TextView? = null

    private var tvAgeInMins:TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val ButtonDatePicker:Button = findViewById(R.id.buttonDatePicker)

        ButtonDatePicker.setOnClickListener {
            clickDatePicker()

        }
    }

    private fun clickDatePicker(){
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener { _view, selectedYear, selectedMonth, selectedDayOfMonth ->

                Toast.makeText(this,"Year was $selectedYear, month was ${selectedMonth+1} and day is $selectedDayOfMonth"
                    ,Toast.LENGTH_LONG).show()

                val selectedDate    = "$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"

                tvSelectedDate?.setText(selectedDate)
                //tvSelectedDate.text = selectedDate

                val sdf = SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH)

                val dateObj = sdf.parse(selectedDate)
                dateObj?.let {
                    val selectedDateInMinutes = dateObj.time/60000

                    val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))


                    currentDate?.let {
                        val currentDateInMins = currentDate.time/60000

                        val diffInMins = currentDateInMins - selectedDateInMinutes

                        tvAgeInMins = findViewById(R.id.tvAgeInMins)

                        tvAgeInMins?.text = diffInMins.toString()
                    }

                }

            },
            year,month,day)

        tvSelectedDate = findViewById(R.id.tvSelectedDate)

        dpd.datePicker.maxDate = System.currentTimeMillis() -86400000
        dpd.show()
        
        

    }
}