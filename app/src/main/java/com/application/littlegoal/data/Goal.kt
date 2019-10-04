package com.application.littlegoal.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.application.littlegoal.utils.FormattedDate
import java.text.SimpleDateFormat
import java.util.*
@Entity(
    tableName = "goals"
)
data class Goal(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name="id") val goalId: Int = 0,
    val name: String,
    val description: String = "",
    val frequency: Int = 1,
    @ColumnInfo(name="start_date") val startDate: String = "",
    @ColumnInfo(name="end_date") val endDate: String = "",
    val imageUrl: String = "",
    val steps: Int = 0,
    @ColumnInfo(name="create_time") val createTime: String = "",
    val lastStepsDate: String = ""
) {
    override fun toString() = name
}