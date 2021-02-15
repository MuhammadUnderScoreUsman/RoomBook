package com.mohammadosman.roomsocialrdb.data.relations.onetomany

import androidx.room.Embedded
import androidx.room.Relation
import com.mohammadosman.roomsocialrdb.data.model.Posts
import com.mohammadosman.roomsocialrdb.data.model.UserAccount

// todo one to many Relation
/*
data class UserAccountWithPosts(

    @Embedded
    val userAccount: UserAccount,

    @Relation(
        parentColumn = "uid",
        entityColumn = "uid"
    )
    val listOfPosts: List<Posts>? = emptyList()

)*/
