package io.pledran.navigation

import android.content.Context
import androidx.appcompat.app.AppCompatActivity

class PreferencesManager(val context: Context) {

    companion object {
        const val NAV_KEY = "nav_key"
        const val NAV_NAME = "nav_name"
    }

    private val preferences = context.getSharedPreferences("dip_preferences", AppCompatActivity.MODE_PRIVATE)

    var navItemId: Int
        get() = preferences.getInt(NAV_KEY, R.id.nav_fragment1)
        set(value) = preferences.edit().putInt(NAV_KEY, value).apply()

    var navItemName: String?
        get() = preferences.getString(NAV_NAME, "Test")
        set(value) = preferences.edit().putString(NAV_NAME, value).apply()
}