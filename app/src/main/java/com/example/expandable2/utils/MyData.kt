package com.example.expandable2.utils

import com.example.expandable2.R
import com.example.expandable2.models.Status

object MyData {
    var radioPosition = 1

    var statusList = mutableListOf(
        Status(R.drawable.high_flag, "High"),
        Status(R.drawable.low_flag, "Low"),
        Status(R.drawable.normal_flag, "Normal"),
        Status(R.drawable.urgent_flag, "Urgent")
    )

}