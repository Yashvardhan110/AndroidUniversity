package com.capgemini.universityapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "student_table")
data class Student(@ColumnInfo(name = "first_name") var firstName: String,
                   var lastName: String,
                   var marks: Int,
                   @PrimaryKey(autoGenerate = true) var id: Int = 0)