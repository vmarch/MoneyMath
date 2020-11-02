package devtolife.moneymath.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import devtolife.moneymath.BuildConfig
import devtolife.moneymath.R
import kotlinx.android.synthetic.main.activity_about_app.*
import kotlinx.android.synthetic.main.content_about_app.*

class AboutAppActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_app)
        setSupportActionBar(toolbarAboutApp)

        toolbarAboutApp.setNavigationIcon(R.drawable.baseline_arrow_back_white_24)
        toolbarAboutApp.navigationContentDescription =
            resources.getString(R.string.context_description_arrow_back)
        toolbarAboutApp.setNavigationOnClickListener { finish() }

        fillActivityFields()


    }

    private fun fillActivityFields() {

        val appNameText =
            resources.getString(R.string.about_row_name) + ":  " + resources.getString(
                R.string.app_name
            )
        val devNameText =
            resources.getString(R.string.about_row_author) + ":  " + resources.getString(
                R.string.dev_name
            )
        val appVersionText =
            resources.getString(R.string.about_row_version) + ":  " + BuildConfig.VERSION_NAME

        textViewNameApp.text = appNameText
        textViewNameDeveloper.text = devNameText
        textViewVersionApp.text = appVersionText
    }
}
