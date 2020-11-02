package devtolife.moneymath.data

class FinanceResult {
    constructor(
        id: Long,
        year: Int,
        previousYearCapital: Double,
        yearSalary: Double,
        yearCosts: Double,
        yearSavings: Double,
        yearProfitCapital: Double,
        yearProfitSavings: Double,
        yearPassiveIncome: Double,
        currentYearCapital: Double
    ) {
        this.id = id
        this.year = year
        this.previousYearCapital = previousYearCapital
        this.yearSalary = yearSalary
        this.yearCosts = yearCosts
        this.yearSavings = yearSavings
        this.yearProfitCapital = yearProfitCapital
        this.yearProfitSavings = yearProfitSavings
        this.yearPassiveIncome = yearPassiveIncome
        this.currentYearCapital = currentYearCapital
    }

    var id: Long

    var year: Int

    var previousYearCapital: Double

    var yearSalary: Double
    var yearCosts: Double
    var yearSavings: Double

    var yearProfitCapital: Double
    var yearProfitSavings: Double
    var yearPassiveIncome: Double

    var currentYearCapital: Double

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as FinanceResult

        if (id != other.id) return false
        if (year != other.year) return false
        if (previousYearCapital != other.previousYearCapital) return false
        if (yearSalary != other.yearSalary) return false
        if (yearCosts != other.yearCosts) return false
        if (yearSavings != other.yearSavings) return false
        if (yearProfitCapital != other.yearProfitCapital) return false
        if (yearProfitSavings != other.yearProfitSavings) return false
        if (yearPassiveIncome != other.yearPassiveIncome) return false
        if (currentYearCapital != other.currentYearCapital) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + year
        result = 31 * result + previousYearCapital.hashCode()
        result = 31 * result + yearSalary.hashCode()
        result = 31 * result + yearCosts.hashCode()
        result = 31 * result + yearSavings.hashCode()
        result = 31 * result + yearProfitCapital.hashCode()
        result = 31 * result + yearProfitSavings.hashCode()
        result = 31 * result + yearPassiveIncome.hashCode()
        result = 31 * result + currentYearCapital.hashCode()
        return result
    }
}