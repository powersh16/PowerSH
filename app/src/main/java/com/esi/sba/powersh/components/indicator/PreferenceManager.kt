package com.esi.sba.powersh.components.indicator

import android.content.Context
import android.content.SharedPreferences

class PreferenceManager (context: Context) {
    companion object {
        private const val PREF_PACKAGE_NAME = "com.esi.sba.powersh.preferences"
        private const val PREF_KEY_ONBOARDING = "splash_screen"
    }

    private val pref: SharedPreferences =
        context.getSharedPreferences(PREF_PACKAGE_NAME, Context.MODE_PRIVATE)

    var hasOnboarding: Boolean
        get() = pref.getBoolean(PREF_KEY_ONBOARDING, true)
        set(hasOnboarding) = pref.edit().putBoolean(PREF_KEY_ONBOARDING, hasOnboarding).apply()
}