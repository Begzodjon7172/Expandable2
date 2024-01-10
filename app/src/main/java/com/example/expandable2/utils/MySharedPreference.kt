package com.example.expandable2.utils

import android.content.Context
import android.content.SharedPreferences
import com.example.expandable2.models.MyToDo
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object MySharedPreference {
    private const val NAME = "todo"
    private const val MODE = Context.MODE_PRIVATE
    private lateinit var preferences: SharedPreferences

    fun init(context: Context) {
        preferences = context.getSharedPreferences(NAME, MODE)
    }

    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }

//    var toDoList: ArrayList<MyToDo>
//        get() = jsonStringToList(preferences.getString("myToDo", "[]")!!)
//        set(value) = preferences.edit {
//            if (value != null) {
//                it.putString("myToDo", listToJsonString(value))
//            }
//        }

    fun setTodoListByKey(key: String, list:ArrayList<MyToDo>) {
        preferences.edit {
            it.putString(key, listToJsonString(list))
        }
    }

    fun getTodoListByKey(key: String): ArrayList<MyToDo> {
        return jsonStringToList(preferences.getString(key, "[]")!!)
    }

    private fun listToJsonString(arrayList: ArrayList<MyToDo>): String {
        return Gson().toJson(arrayList)
    }

    private fun jsonStringToList(gsonString: String): ArrayList<MyToDo> {
        val typeToken = object : TypeToken<ArrayList<MyToDo>>() {}.type
        return Gson().fromJson(gsonString, typeToken)
    }
}