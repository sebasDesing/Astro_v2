package com.example.astrop.ui.userProfile

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.astrop.data.model.UserData

class UserProfileViewModel : ViewModel() {


    fun getUserData(
        keyPrefs: String,
        keyName: String,
        keyEmail: String,
        keyPhoto: String,
        context: Context
    ): UserData {
        val prefs = context.getSharedPreferences(
            keyPrefs,
            Context.MODE_PRIVATE
        )
        val phtoUrl = prefs.getString(keyPhoto, null)
        val name = prefs.getString(keyName, null)
        val email = prefs.getString(keyEmail, null)
        return UserData("$name", "$email", "$phtoUrl")

    }


    fun singout(keyPrefs: String, context: Context) {
        val prefs = context.getSharedPreferences(
            keyPrefs,
            Context.MODE_PRIVATE
        )
        val p = prefs.edit()
        p.clear()
        p.apply()
    }
}