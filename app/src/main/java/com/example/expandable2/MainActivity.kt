package com.example.expandable2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.expandable2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar?.title = "To do app"

        binding.apply {
            listBtn.setOnClickListener {
                val intent = Intent(this@MainActivity, ToDoListActivity::class.java)
                startActivity(intent)
            }
            addBtn.setOnClickListener {
                val intent = Intent(this@MainActivity, AddToDoActivity::class.java)
                startActivity(intent)
            }
        }
    }
}