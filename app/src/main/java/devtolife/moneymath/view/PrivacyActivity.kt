package devtolife.moneymath.view

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.text.method.LinkMovementMethod
import androidx.appcompat.app.AppCompatActivity
import devtolife.moneymath.R
import kotlinx.android.synthetic.main.activity_privacy.*
import kotlinx.android.synthetic.main.content_privacy.*

class PrivacyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_privacy)
        setSupportActionBar(toolbarPrivacy)

        toolbarPrivacy.setNavigationIcon(R.drawable.baseline_arrow_back_white_24)
        toolbarPrivacy.navigationContentDescription =
            resources.getString(R.string.context_description_arrow_back)
        toolbarPrivacy.setNavigationOnClickListener { finish() }

        fillActivityFields()

    }

    private fun fillActivityFields() {
        val htmlText = resources.getString(R.string.privacy_full)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            // FROM_HTML_MODE_LEGACY is the behaviour that was used for versions below android N
            // we are using this flag to give a consistent behaviour
            textViewPrivacyFull.text = Html.fromHtml(htmlText, Html.FROM_HTML_MODE_LEGACY);
        } else {
            @Suppress("DEPRECATION")
            textViewPrivacyFull.text = Html.fromHtml(htmlText);
        }

        textViewPrivacyFull.movementMethod = LinkMovementMethod.getInstance()
    }
}
