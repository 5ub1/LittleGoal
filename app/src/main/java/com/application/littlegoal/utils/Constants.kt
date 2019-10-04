package com.application.littlegoal.utils

const val DATABASE_NAME = "goals-db"
const val GOAL_DATA_FILENAME = "goals.json"
val FREQUENCY_ITEMS = arrayOf("每天", "每三天", "每周", "每月")
val FREQUENCY_MAP = mapOf(Pair("每天", 1), Pair("每三天", 3),
    Pair("每周", 7), Pair("每月", 30))