package devtolife.moneymath.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "finance_table")
class Finance {

    @PrimaryKey(autoGenerate = false)
    var id: Long = 0

    @ColumnInfo(name = "start_year")
    var startYear: Int = 2020


    @ColumnInfo(name = "start_capital")
    var startCapital: Double = 0.0


    @ColumnInfo(name = "start_salary")
    var startSalary: Double = 0.0

    @ColumnInfo(name = "percent_increase_salary")
    var percentageIncreaseSalary: Double = 0.0

    @ColumnInfo(name = "start_costs")
    var startCosts: Double = 0.0

    @ColumnInfo(name = "percent_increase_costs")
    var percentageIncreaseCosts: Double = 0.0


    @ColumnInfo(name = "percent_increase_capital")
    var percentageIncreaseCapital: Double = 0.0

    @ColumnInfo(name = "percent_profit_tax")
    var percentageProfitTax: Double = 0.0

    @ColumnInfo(name = "percent_region_inflation")
    var percentageRegionInflation: Double = 0.0

    @ColumnInfo(name = "goal_capital")
    var goalCapital: Double = 0.0

}