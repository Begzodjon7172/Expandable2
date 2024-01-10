package com.example.expandable2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioGroup
import android.widget.Toast
import com.example.expandable2.databinding.ActivityToDoInfoBinding
import com.example.expandable2.models.MyToDo
import com.example.expandable2.utils.MyData
import com.example.expandable2.utils.MySharedPreference

class ToDoInfoActivity : AppCompatActivity() {
    private val binding by lazy { ActivityToDoInfoBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        MySharedPreference.init(this)


        binding.apply {

            val radioId = intent.getIntExtra("radioId", 0)
            val childPosition = intent.getIntExtra("child", 0)

            val oldList = MySharedPreference.getTodoListByKey("$radioId")
            val myToDo = oldList[childPosition]

            supportActionBar?.title = myToDo.name

            dateTv.text = myToDo.createdDate
            dedlineTv.text = myToDo.dedline
            tvDesc.text = myToDo.description
            val statusId = myToDo.statusId
            flagImg.setImageResource(MyData.statusList[statusId].img)
            flagTv.text = MyData.statusList[statusId].title


            binding.apply {
                when (radioId) {
                    0 -> {
                        radioOpen.isChecked = true
                    }

                    1 -> {
                        radioDevelopment.isChecked = true
                    }

                    2 -> {
                        radioUploading.isChecked = true
                    }

                    3 -> {
                        radioReject.isChecked = true
                    }

                    else -> {
                        radioClosed.isChecked = true
                    }
                }

            }

            radioGroup.setOnCheckedChangeListener { radioGroup, i ->
                var id = -1

                when (i) {
                    radioOpen.id -> {
                        id = 0
                    }

                    radioDevelopment.id -> {
                        id = 1
                    }

                    radioUploading.id -> {
                        id = 2
                    }

                    radioReject.id -> {
                        id = 3
                    }

                    radioClosed.id -> {
                        id = 4
                    }
                }

                oldList.remove(myToDo)
                MySharedPreference.setTodoListByKey("$radioId", oldList)
                val newList = MySharedPreference.getTodoListByKey("$id")
                newList.add(myToDo)
                MySharedPreference.setTodoListByKey("$id", newList)

            }


            okBtn.setOnClickListener {
                Toast.makeText(this@ToDoInfoActivity, "Saqlandi", Toast.LENGTH_SHORT).show()
                finish()
            }

        }
    }
}