package com.example.expandable2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import com.example.expandable2.adapters.StatusBaseAdapter
import com.example.expandable2.databinding.ActivityAddToDoBinding
import com.example.expandable2.models.MyToDo
import com.example.expandable2.models.Status
import com.example.expandable2.utils.MyData
import com.example.expandable2.utils.MySharedPreference

class AddToDoActivity : AppCompatActivity() {
    private val binding by lazy { ActivityAddToDoBinding.inflate(layoutInflater) }

    private lateinit var list: ArrayList<Status>
    private lateinit var statusBaseAdapter: StatusBaseAdapter

    private var clickedPosition = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar?.title = "Add to do"

        MySharedPreference.init(this)

        loadData()

        statusBaseAdapter = StatusBaseAdapter(list)

        binding.edtDegree.adapter = statusBaseAdapter

        binding.edtDegree.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                clickedPosition = p2
                Toast.makeText(this@AddToDoActivity, "$p2", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }

        binding.apply {

            addBtn.setOnClickListener {
                val name = edtName.text.toString()
                val description = edtDesc.text.toString()
                val createdDate = edtCreatedDate.text.toString()
                val dedline = edtDedline.text.toString()

                val myToDo = MyToDo(name, description, clickedPosition, createdDate, dedline)
                val list = MySharedPreference.getTodoListByKey("${MyData.radioPosition}")
                list.add(myToDo)
                MySharedPreference.setTodoListByKey("${MyData.radioPosition - 1}", list)
                Toast.makeText(this@AddToDoActivity, "Saqlandi", Toast.LENGTH_SHORT).show()
                finish()
            }


        }

    }

    private fun loadData() {
        list = ArrayList()
        list.add(Status(R.drawable.high_flag, "High"))
        list.add(Status(R.drawable.low_flag, "Low"))
        list.add(Status(R.drawable.normal_flag, "Normal"))
        list.add(Status(R.drawable.urgent_flag, "Urgent"))
    }
}