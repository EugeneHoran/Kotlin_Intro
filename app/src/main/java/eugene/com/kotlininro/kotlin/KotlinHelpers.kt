package eugene.com.kotlininro.kotlin

import android.content.SharedPreferences

/**
 * https://github.com/android/android-ktx/blob/master/src/main/java/androidx/content/SharedPreferences.kt
 *
 * Allows editing of this preference instance with a call to
 * [SharedPreferences.Editor.apply] to persist the changes.
 *
 * ```
 * prefs.edit {
 *     putString("key", value)
 * }
 * ```
 */
inline fun SharedPreferences.edit(action: SharedPreferences.Editor.() -> Unit) {
    val editor = edit()
    action(editor)
    editor.apply()
}