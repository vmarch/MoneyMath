package devtolife.moneymath

import android.text.InputFilter
import android.text.Spanned
import java.util.regex.Pattern


class DecimalDigitsInputFilter: InputFilter {

    var mPattern: Pattern? = null

    constructor(digitsAfterZero: Int){
        mPattern =            Pattern.compile("[0-9]+((\\.[0-9]{0," + (digitsAfterZero - 1) + "})?)||(\\.)?")
    }

//    fun DecimalDigitsInputFilter(digitsAfterZero: Int) {
//        mPattern =            Pattern.compile("[0-9]+((\\.[0-9]{0," + (digitsAfterZero - 1) + "})?)||(\\.)?")
//    }

    override fun filter(
        source: CharSequence?,
        start: Int,
        end: Int,
        dest: Spanned?,
        dstart: Int,
        dend: Int
    ): CharSequence? {
        val matcher = mPattern!!.matcher(dest)
        return if (!matcher.matches()) "" else null
    }
}