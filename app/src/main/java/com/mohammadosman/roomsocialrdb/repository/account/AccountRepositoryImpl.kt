package com.mohammadosman.roomsocialrdb.repository.account

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
    app: Application
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

                val checkUserEmail = userDao
                    .checkUserViaEmail(insertUser.roomMail)

                val checkUserName = userDao.checkUserViaUsername(
                    insertUser.userName
                )

                if (insertUser.roomMail == checkUserEmail?.roomMail ?: "" ||
                    insertUser.userName == checkUserName?.userName
                ) {
                    emit(
                        Response.Error(
                            data = null,
                            error = "Can't build account on same email Id or username " +
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
                        val updateUser = userDao.updateUserAccForLoginAuth(
                            id = check.userAccount.uid ?: "",
                            loginAu = check.userAccount.uid ?: ""
                        )
                        if (updateUser > 0) {
                            emit(Response.Success(Unit, SuccessFully_LoginIn))
                        } else {
                            emit(Response.Error(null, NoSuch_Acc))
                        }
                    }
                } ?: emit(Response.Error(null, NoSuch_Acc))
            }
        }
    }


    override suspend fun checkAuth(): Boolean {
        val authUser = userDao.checkAuthentication()
        var trueLogin = false
        for (loginAuth in authUser) {
            if (loginAuth.loginAuth != null && loginAuth.uid == loginAuth.loginAuth) {
                trueLogin = true
            }
        }
        return trueLogin
    }

    companion object {
        const val Success_AccountCreated =
            "Account Created, both Parent And Child table has been Successfully populated!!"

        const val SuccessFully_LoginIn = "Logged in Successfully"

        const val NoSuch_Acc = "No Such Account Exists!!"
    }


}