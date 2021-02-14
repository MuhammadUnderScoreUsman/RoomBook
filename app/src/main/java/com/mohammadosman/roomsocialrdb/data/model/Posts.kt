package com.mohammadosman.roomsocialrdb.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "posts"
)
data class Posts(
    @PrimaryKey(autoGenerate = false)
    val pId: String,
)