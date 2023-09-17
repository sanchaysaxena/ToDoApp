package com.example.todoapp

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import androidx.room.Room
import com.example.todoapp.databinding.ActivityTaskBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

const val DB_NAME="todo.db"
class TaskActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var myCalender:Calendar
    lateinit var dateSetListener:DatePickerDialog.OnDateSetListener
    lateinit var timeSetListener:TimePickerDialog.OnTimeSetListener
    var finalDate=0L
    var finalTime=0L
    private val labels= arrayListOf("Personal","Groceries","Business","Insurance","Shopping","Banking")

    val db by lazy {
        AppDatabase.getDatabase(this)
    }

    private lateinit var binding:ActivityTaskBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.dateEdt.setOnClickListener (this)
        binding.timeEdt.setOnClickListener (this)
        binding.saveBtn.setOnClickListener(this)
        setUpSpinner()
    }

    private fun setUpSpinner() {
        val adapter=ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,labels)
        labels.sort()
        binding.spinnerCategory.adapter=adapter
    }

    override fun onClick(v: View?) {
        if (v != null) {
            when(v.id){
                R.id.dateEdt->{
                    setListener()
                }
                R.id.timeEdt->{
                    setTimeListener()
                }
                R.id.saveBtn->{
                    setButton()
                }
            }
        }
    }

    private fun setButton() {
        val category=binding.spinnerCategory.selectedItem.toString()
        val title=binding.titleInplay.editText?.text.toString()
        val description=binding.taskInplay.editText?.text.toString()
        if(category.isNullOrBlank() || title.isNullOrBlank() || description.isNullOrBlank() || finalTime==0L){
            Toast.makeText(this,"Please fill all the fields correctly",Toast.LENGTH_SHORT).show()
        }
        else{
            Toast.makeText(this,"everything seems fine",Toast.LENGTH_SHORT).show()
        }

    }

    private fun setTimeListener() {
        myCalender= Calendar.getInstance()
        timeSetListener=TimePickerDialog.OnTimeSetListener { _:TimePicker, hourOfDay:Int, minute:Int ->
            myCalender.set(Calendar.HOUR_OF_DAY,hourOfDay)
            myCalender.set(Calendar.MINUTE,minute)
            updateTime()
        }
        val timePickerDialog=TimePickerDialog(
            this,
            timeSetListener,
            myCalender.get(Calendar.HOUR_OF_DAY),
            myCalender.get(Calendar.MINUTE),false)

        timePickerDialog.show()
    }
    private fun updateTime() {
        val myFormat="hh:mm aa"
        val sdf=SimpleDateFormat(myFormat, Locale.ENGLISH)
        finalTime=myCalender.time.time
        binding.timeEdt.setText(sdf.format(myCalender.time))
    }

    private fun setListener() {
        myCalender= Calendar.getInstance()
        dateSetListener=DatePickerDialog.OnDateSetListener { _:DatePicker, year:Int, month:Int, dayOfMonth:Int ->
            myCalender.set(Calendar.YEAR,year)
            myCalender.set(Calendar.MONTH,month)
            myCalender.set(Calendar.DAY_OF_MONTH,dayOfMonth)
            updateDate()
        }
        val datePickerDialog=DatePickerDialog(
            this,
            dateSetListener,
            myCalender.get(Calendar.YEAR),
            myCalender.get(Calendar.MONTH),
            myCalender.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.datePicker.minDate=System.currentTimeMillis()+24*60*60*1000
        datePickerDialog.show()
    }
    private fun updateDate() {
        val myFormat="EEE, dd MMM yyyy"
        val sdf=SimpleDateFormat(myFormat, Locale.ENGLISH)
        binding.dateEdt.setText(sdf.format(myCalender.time))
        finalDate=myCalender.time.time
        binding.timeInptLay.visibility=View.VISIBLE
    }
}