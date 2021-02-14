package com.mohammadosman.roomsocialrdb.repository

import android.app.Application
import com.mohammadosman.roomsocialrdb.data.dao.UserDao
import com.mohammadosman.roomsocialrdb.data.db.SocialDatabase
import com.mohammadosman.roomsocialrdb.data.model.User
import com.mohammadosman.roomsocialrdb.data.model.UserAccount
import com.mohammadosman.roomsocialrdb.util.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.*

class AccountRepositoryImpl(
    private val app: Application
) : AccountRepository {

    var userDao: UserDao

    init {
        val db = SocialDatabase.invoke(app)
        userDao = db.userDao()
    }

    override fun createAccount(
        uid: String?,
        roomMail: String,
        userName: String,
        prefilePic: Int
    ): Flow<Response<Unit>> {

        return flow {
            if (roomMail.isEmpty() || userName.isEmpty()) {
                emit(
                    Response.Error(
                        null,
                        "Please Fill the fields"
                    )
                )
            } else {

                val insertUser = User(
                    uid = uid ?: UUID.randomUUID().toString(),
                    roomMail = roomMail,
                    userName = userName
                )

                val checkUser = userDao
                    .checkUserViaEmail(insertUser.roomMail)


                if (insertUser.roomMail == checkUser?.roomMail ?: "") {
                    emit(
                        Response.Error(
                            data = null,
                            error = "Can't build account on same Id " +
                                    "Because it is One to One Relation!!"
                        )
                    )
                } else {

                    val insertU = userDao.insertUser(
                        insertUser
                    )

                    if (insertU > 0) {

                        val userAcc = userDao.insertUserAccount(

                            UserAccount(
                                uAid = UUID.randomUUID().toString(),
                                userName = insertUser.userName,
                                profilePic = prefilePic,
                                uid = insertUser.uid
                            )
                        )

                        if (userAcc > 0) {
                            emit(
                                Response.Success(
                                    Unit,
                                    Success_AccountCreated
                                )
                            )
                        } else {
                            emit(
                                Response.Error(
                                    null,
                                    "Child Table Account cannot be created"
                                )
                            )
                        }
                    } else {
                        emit(
                            Response.Error(
                                null,
                                "Child and Parent Table, No Account has created"
                            )
                        )
                    }
                }
            }
        }
    }


    override fun loginAccount(
        userName: String
    ): Flow<Response<Unit>> {
        return flow {
            if (userName.isEmpty()) {
                emit(Response.Error(null, "Please fill the field."))
            } else {
                val checkInBoth = userDao.getUser(userName = userName)
                checkInBoth?.let { check ->
                    if (check.user.userName == userName && check.userAccount?.userName == userName) {
                        emit(Response.Success(Unit, SuccessFully_LoginIn))
                    }
                } ?: emit(Response.Error(null, NoSuch_Acc))
            }
        }
    }

    companion object {

        const val Success_AccountCreated =
            "Account Created, both Parent And Child table has been Successfully populated!!"

        const val SuccessFully_LoginIn = "Logged in Successfully"

        const val NoSuch_Acc = "No Such Account Exists!!"
    }
}