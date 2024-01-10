package com.example.expandable2.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import com.example.expandable2.R
import com.example.expandable2.databinding.ItemChildBinding
import com.example.expandable2.databinding.ItemParentBinding
import com.example.expandable2.models.MyToDo

class ToDoAdapter(
    private var map: HashMap<String, ArrayList<MyToDo>>,
    private var titleList: ArrayList<String>,
    private var childClickListener: ChildClickListener

) : BaseExpandableListAdapter() {

    override fun getGroupCount(): Int {
        return titleList.size
    }

    override fun getChildrenCount(p0: Int): Int {
        return map[titleList[p0]]?.size!!
    }

    override fun getGroup(p0: Int): String {
        return titleList[p0]
    }

    override fun getChild(p0: Int, p1: Int): MyToDo {
        return map[titleList[p0]]?.get(p1)!!
    }

    override fun getGroupId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getChildId(p0: Int, p1: Int): Long {
        return p1.toLong()
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun getGroupView(p0: Int, p1: Boolean, p2: View?, p3: ViewGroup?): View {
        val binding: ItemParentBinding = if (p2 == null) {
            ItemParentBinding.inflate(LayoutInflater.from(p3?.context), p3, false)
        } else {
            ItemParentBinding.bind(p2)
        }
        binding.tvName.text = titleList[p0]

        if (p1) {
            binding.imgArrow.setImageResource(R.drawable.baseline_keyboard_arrow_up_24)
        } else {
            binding.imgArrow.setImageResource(R.drawable.baseline_keyboard_arrow_right_24)
        }
        return binding.root
    }

    override fun getChildView(p0: Int, p1: Int, p2: Boolean, p3: View?, p4: ViewGroup?): View {
        val binding: ItemChildBinding = if (p3 == null) {
            ItemChildBinding.inflate(LayoutInflater.from(p4?.context), p4, false)
        } else {
            ItemChildBinding.bind(p3)
        }
        val myToDo = map[titleList[p0]]?.get(p1)
        binding.tvName.text = myToDo?.name

        binding.root.setOnClickListener {
            if (myToDo != null) {
                childClickListener.childClick(p1, p0)
            }
        }

        return binding.root
    }

    override fun isChildSelectable(p0: Int, p1: Int): Boolean {
        return true
    }

    public interface ChildClickListener {
        fun childClick(childPosition: Int, parentPosition:Int)
    }
}