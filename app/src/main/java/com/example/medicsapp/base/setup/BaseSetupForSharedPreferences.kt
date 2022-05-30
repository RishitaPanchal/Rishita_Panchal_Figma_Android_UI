package com.example.medicsapp.base.setup

import android.content.Context
import android.content.SharedPreferences

class BaseSetupForSharedPreferences private constructor(){

    /** Companion object */
    companion object {

        /** Instance variable */
        private lateinit var preferences: SharedPreferences
        private const val PREF_NAME = "MedicsApp"
        private const val PREF_MODE = Context.MODE_PRIVATE
        private const val IS_LOGIN = "isLoggedIn"
        private const val IS_FIRST_RUN = "isFirstRun"
        private const val KEY_TOKEN = "token"

        /** Functions */
        private fun init(context: Context): SharedPreferences {
            synchronized(this) {
                preferences = context.getSharedPreferences(PREF_NAME, PREF_MODE)
                return preferences
            }
        }

        private fun edit(context: Context, operation: (SharedPreferences.Editor) -> Unit) {
            val editor = init(context).edit()
            operation(editor)
            editor.apply()
        }

        fun createLoginSession(context: Context, token: String) {
            edit(context) {
                it.putBoolean(IS_LOGIN, true)
                it.putString(KEY_TOKEN, token)
            }
        }

        fun firstRunSession(context: Context) {
            edit(context) {
                it.putBoolean(IS_FIRST_RUN, true)
            }
        }

        fun clearPref(context: Context) {
            edit(context) {
                it.clear()
            }
        }

        fun isLogin(context: Context): Boolean {
            return init(context).getBoolean(IS_LOGIN, false)
        }

        fun isFirstRun(context: Context): Boolean {
            return init(context).getBoolean(IS_FIRST_RUN, false)
        }
    }

}

