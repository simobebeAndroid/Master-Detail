package com.simiomobile.masterdetail.data.local.preference

import android.content.Context


const val PREF_NAME = "MasterDetailPrefs"

interface SharedPreference {
    fun remove(key: String)
    fun put(key: String, value: String): Boolean
    fun get(key: String): String
}

class SharePreferenceImpl(context: Context) : SharedPreference {
    private var sp = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    private var editor = sp.edit()

    override fun remove(key: String) {
        editor.remove(key).commit()
    }

    override fun put(key: String, value: String): Boolean {
        return editor.putString(key, value).commit()
    }

    override fun get(key: String): String {
        return sp.getString(key, "") ?: ""
    }
}