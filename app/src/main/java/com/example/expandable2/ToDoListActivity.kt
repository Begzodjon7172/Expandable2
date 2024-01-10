package com.example.expandable2

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ExpandableListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.expandable2.adapters.ToDoAdapter
import com.example.expandable2.databinding.ActivityToDoListBinding
import com.example.expandable2.models.MyToDo
import com.example.expandable2.utils.MySharedPreference

class ToDoListActivity : AppCompatActivity() {
    private val binding by lazy { ActivityToDoListBinding.inflate(layoutInflater) }

    private lateinit var map: HashMap<String, ArrayList<MyToDo>>
    private lateinit var titleList: ArrayList<String>
    private lateinit var toDoAdapter: ToDoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.title = "To do list"

    }

    override fun onResume() {
        super.onResume()
        MySharedPreference.init(this)

        loadData()

        toDoAdapter = ToDoAdapter(map, titleList, object : ToDoAdapter.ChildClickListener {
            override fun childClick(childPosition: Int, parentPosition: Int) {
                val intent = Intent(this@ToDoListActivity, ToDoInfoActivity::class.java)
                intent.putExtra("radioId", parentPosition)
                intent.putExtra("child", childPosition)
                startActivity(intent)
            }
        })

        binding.expandableListView.setAdapter(toDoAdapter)
    }

    private fun loadData() {
        map = HashMap()

        val list = MySharedPreference.getTodoListByKey("0")
        map["Open"] = list

        val list1 = MySharedPreference.getTodoListByKey("1")
        map["Development"] = list1

        val list2 = MySharedPreference.getTodoListByKey("2")
        map["Uploading"] = list2

        val list3 = MySharedPreference.getTodoListByKey("3")
        map["Reject"] = list3

        val list4 = MySharedPreference.getTodoListByKey("4")
        map["Closed"] = list4

        titleList = arrayListOf("Open", "Development", "Uploading", "Reject", "Closed")

    }
}