package com.mohammadosman.roomsocialrdb.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_account")
data class UserAccount(
    @PrimaryKey(autoGenerate = false)
    val uAid: String,

    @ColumnInfo(name = "user_name")
    val userName: String?,

    @ColumnInfo(name = "profile_pic")
    val profilePic: Int?,

    val uid: String? = null
)