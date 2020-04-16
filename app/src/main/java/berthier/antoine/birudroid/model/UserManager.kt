package berthier.antoine.birudroid.model

import android.content.Context
import android.provider.ContactsContract
import berthier.antoine.birudroid.util.SingletonHolder

class UserManager private constructor(context: Context) {
    init {
    }

    companion object : SingletonHolder<UserManager, Context>(::UserManager){
        var haveUser: Boolean = false
        lateinit var mName: String
        lateinit var mEmail: String
        lateinit var mId: String
    }

    fun create(name: String, email: String, id: String){
        mName = name
        mEmail = email
        mId = id
        haveUser = true
    }

    fun getName():String{
        return mName;
    }

    fun haveUser():Boolean{
        return haveUser;
    }
}