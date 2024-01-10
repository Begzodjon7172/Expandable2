package com.example.expandable2.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Filterable
import com.example.expandable2.databinding.ItemSpinnerBinding
import com.example.expandable2.models.Status

class StatusBaseAdapter(private var list: ArrayList<Status>) : BaseAdapter() {
    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(p0: Int): Any {
        return list[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        var binding: ItemSpinnerBinding = if (p1 == null) {
            ItemSpinnerBinding.inflate(LayoutInflater.from(p2?.context), p2, false)
        } else {
            ItemSpinnerBinding.bind(p1)
        }
        binding.apply {
            flagTv.text = list[p0].title
            flagImg.setImageResource(list[p0].img)
        }
        return binding.root
    }

}