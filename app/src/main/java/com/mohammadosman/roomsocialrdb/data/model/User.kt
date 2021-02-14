package com.mohammadosman.roomsocialrdb.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @PrimaryKey(autoGenerate = false)
    val uid: String,

    @ColumnInfo(name = "room_mail_address")
    val roomMail: String,

    @ColumnInfo(name = "user_name")
    val userName: String,
)