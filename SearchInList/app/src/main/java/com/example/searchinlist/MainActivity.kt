package com.example.searchinlist

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.searchinlist.R

class MainActivity : AppCompatActivity() {

    data class Student(
        val name: String,
        val mssv: String
    ) {
        override fun toString(): String {
            return "$name - $mssv"
        }
    }

    private lateinit var studentList: List<Student>
    private lateinit var filteredList: MutableList<Student>
    private lateinit var arrayAdapter: ArrayAdapter<Student>
    private lateinit var searchEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        searchEditText = findViewById(R.id.searchEditText)
        val listView = findViewById<ListView>(R.id.listView)

        studentList = listOf(
            Student("Tran Thi B", "20120002"),
            Student("Le Van C", "20120003"),
            Student("Nguyen Thi D", "20120004"),
            Student("Pham Van E", "20120005"),
            Student("Hoang Thi F", "20120006"),
            Student("Nguyen Van G", "20120007"),
            Student("Le Thi H", "20120008"),
            Student("Pham Thi I", "20120009"),
            Student("Hoang Van J", "20120010"),
            Student("Tran Van K", "20120011")
        )

        filteredList = studentList.toMutableList()

        arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, filteredList)
        listView.adapter = arrayAdapter

        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                filterList(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun filterList(query: String) {
        filteredList.clear()
        if (query.length > 2) {
            val lowerCaseQuery = query.lowercase()
            filteredList.addAll(studentList.filter {
                it.name.lowercase().contains(lowerCaseQuery) || it.mssv.contains(query)
            })
        } else {
            filteredList.addAll(studentList)
        }
        arrayAdapter.notifyDataSetChanged()
    }
}


