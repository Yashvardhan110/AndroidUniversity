package com.capgemini.universityapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.capgemini.universityapp.R
import com.capgemini.universityapp.model.Repository
import com.capgemini.universityapp.model.Student
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var studentList: List<Student>
    lateinit var repository: Repository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        repository = Repository(this)
        updateList()
//        Log.d("MainActivity","Students: $studentList")
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menu?.add("Add Student")
        menu?.add("Update")
        menu?.add("Delete")
        menu?.add("Delete All")
        menu?.add("Refresh")
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.title){
            "Add Student" -> {
                val intent =  Intent(this, StudentData::class.java)
                startActivity(intent)
                finish()
            }
            "Update" -> {}
            "Delete" -> {}
            "Delete All" ->{}
            "Refresh"->{
                updateList()
            }
        }

        return super.onOptionsItemSelected(item)
    }

    fun updateList(){
        CoroutineScope(Dispatchers.Default).launch {
            studentList = repository.allStudents()
            rView.layoutManager = LinearLayoutManager(this@MainActivity)
            rView.adapter = MyAdapter(studentList)

            //adapter.notifyDatasetChanged()
            Log.d("MainActivity","Updated List: $studentList")
        }
    }
}