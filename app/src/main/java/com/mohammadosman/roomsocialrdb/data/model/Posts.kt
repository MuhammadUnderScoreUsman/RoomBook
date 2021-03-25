package com.mohammadosman.roomsocialrdb.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "posts"
)
data class Posts(
    @PrimaryKey(autoGenerate = false)
    val pId: String,

    @ColumnInfo(name = "post_picture")
    val postPicture: Int? = null,

    @ColumnInfo(name = "post_desc")
    val postDesc: String? = null,

    val uid: String? = null,
)