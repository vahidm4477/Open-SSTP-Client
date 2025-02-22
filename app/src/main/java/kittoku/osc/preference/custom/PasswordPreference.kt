package kittoku.osc.preference.custom

import android.content.Context
import android.text.InputType
import android.util.AttributeSet
import androidx.preference.Preference
import androidx.preference.Preference.SummaryProvider
import kittoku.osc.preference.OscPreference
import kittoku.osc.preference.accessor.getStringPrefValue


internal abstract class PasswordPreference(context: Context, attrs: AttributeSet) : StringPreference(context, attrs) {
    override val textType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
    override val provider = SummaryProvider<Preference> {
        val currentValue = getStringPrefValue(oscPreference, it.sharedPreferences!!)

        if (currentValue.isEmpty()) {
            "[No Value Entered]"
        } else {
            "[Password Entered]"
        }
    }
}

internal class HomePasswordPreference(context: Context, attrs: AttributeSet) : PasswordPreference(context, attrs) {
    override val oscPreference = OscPreference.HOME_PASSWORD
    override val preferenceTitle = "Password"
}

internal class ProxyPasswordPreference(context: Context, attrs: AttributeSet) : PasswordPreference(context, attrs) {
    override val oscPreference = OscPreference.PROXY_PASSWORD
    override val preferenceTitle = "Proxy Password (optional)"
    override val dependingPreference = OscPreference.PROXY_DO_USE_PROXY
}
