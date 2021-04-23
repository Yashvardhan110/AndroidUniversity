package com.capgemini.universityapp.model

import androidx.room.*

@Dao
interface StudentDao {
    //define methods for queries

    @Insert
    suspend fun insert(std: Student)

    @Update
    suspend fun update(std: Student)

    @Delete
    suspend fun delete(std: Student)

    @Query("DELETE FROM student_table")
    suspend fun deleteAll()

    @Query("SELECT * FROM student_table ORDER BY marks DESC")
    suspend fun getStudents(): List<Student>
}