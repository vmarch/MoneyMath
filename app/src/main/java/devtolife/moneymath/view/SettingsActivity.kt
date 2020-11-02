package devtolife.moneymath.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import devtolife.moneymath.R
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        setSupportActionBar(toolbarSettings)

        toolbarSettings.setNavigationIcon(R.drawable.baseline_arrow_back_white_24)
        toolbarSettings.navigationContentDescription =
            resources.getString(R.string.context_description_arrow_back)
        toolbarSettings.setNavigationOnClickListener { finish() }

        fillActivityFields()

    }

    private fun fillActivityFields() {

    }
}
