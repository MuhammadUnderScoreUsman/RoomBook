package com.mohammadosman.roomsocialrdb.data.relations.onetoone

import androidx.room.Embedded
import androidx.room.Relation
import com.mohammadosman.roomsocialrdb.data.model.User
import com.mohammadosman.roomsocialrdb.data.model.UserAccount

data class UserAndUserAccounts(
    @Embedded
    val user: User,

    @Relation(
        parentColumn = "uid",
        entityColumn = "uid"
    )
    val userAccount: UserAccount? = null
)