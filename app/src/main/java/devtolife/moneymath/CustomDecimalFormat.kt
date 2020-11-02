package devtolife.moneymath

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols

class CustomDecimalFormat {
    private val pattern = "0.00"
    private val dfs = DecimalFormatSymbols()
    var cdf:DecimalFormat

    init {
        dfs.decimalSeparator = '.'
        cdf = DecimalFormat(pattern,dfs)
    }
}