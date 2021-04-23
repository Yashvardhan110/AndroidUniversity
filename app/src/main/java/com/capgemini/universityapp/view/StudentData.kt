package com.capgemini.universityapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.capgemini.universityapp.R
import com.capgemini.universityapp.model.Repository
import com.capgemini.universityapp.model.Student
import com.capgemini.universityapp.viewModels.StudentViewModel
import kotlinx.android.synthetic.main.activity_student_data.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StudentData : AppCompatActivity() {
    //    lateinit var repository: Repository
    lateinit var model: StudentViewModel

    lateinit var studentList: List<Student>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_data)
//        repository = Repository(this)
        val vmProvider = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory(application)
        )
        model = vmProvider.get(StudentViewModel::class.java)

        model.studentList.observe(this, Observer {
            studentList = it
            Log.d("Student Data", "$studentList")
            val int = intent
            val id = int.getStringExtra("ID") ?: ""
            Toast.makeText(this, "ID: $id", Toast.LENGTH_SHORT).show()
//            Toast.makeText(this,"Student got: $studentList",Toast.LENGTH_LONG).show()
            if(id.isNotEmpty()){
                for(std in studentList){
                    if(std.id.toString()==id){
                        fNameE.setText(std.firstName)
                        lNameE.setText(std.lastName)
                        marksE.setText(std.marks.toString())
                        idE.setText(std.id.toString())
                    }
                }
            }
        })
    }


    fun buttonClick(view: View) {

        if (fNameE.text.isNotEmpty() && lNameE.text.isNotEmpty() && marksE.text.isNotEmpty()) {
            val fname = fNameE.text.toString()
            val lname = lNameE.text.toString()
            val marks = marksE.text.toString().toInt()
            if (idE.text.isNotEmpty()) {
                val id = idE.text.toString().toInt()
                model.addStudent(Student(fname, lname, marks, id))
            } else {
                model.addStudent(Student(fname, lname, marks))
            }

            finish()

        }
    }


}