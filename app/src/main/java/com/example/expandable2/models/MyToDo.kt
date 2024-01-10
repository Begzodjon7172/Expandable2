package com.example.expandable2.models

import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.Date

data class MyToDo(
    var name:String,
    var description: String,

    var statusId: Int,
    var createdDate: String,
    var dedline: String
):Serializable
