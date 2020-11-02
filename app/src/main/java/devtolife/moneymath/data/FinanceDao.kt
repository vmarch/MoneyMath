package devtolife.moneymath.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface FinanceDao {

//    @Query("SELECT * FROM finance_table")
//    fun getAllStartFinanceData(): LiveData<List<Finance>>

    @Query("SELECT * FROM finance_table WHERE id = 0")
    fun getSimpleFinanceData(): LiveData<Finance>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStartFinanceData(finance: Finance)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateStartFinanceData(finance: Finance)


    @Query("UPDATE finance_table SET start_year = :dataValue WHERE id = :financeId")
    fun updYearByID(financeId: Long, dataValue: Int)

    @Query("UPDATE finance_table SET start_capital = :dataValue WHERE id = :financeId")
    fun updStartCapitalByID(financeId: Long, dataValue: Double)


    @Query("UPDATE finance_table SET start_salary = :dataValue WHERE id = :financeId")
    fun updStartSalaryByID(financeId: Long, dataValue: Double)


    @Query("UPDATE finance_table SET percent_increase_salary = :dataValue WHERE id = :financeId")
    fun updPercentageIncreaseSalaryByID(financeId: Long, dataValue: Double)


    @Query("UPDATE finance_table SET start_costs = :dataValue WHERE id = :financeId")
    fun updStartCostsByID(financeId: Long, dataValue: Double)


    @Query("UPDATE finance_table SET percent_increase_costs = :dataValue WHERE id = :financeId")
    fun updPercentageIncreaseCostsByID(financeId: Long, dataValue: Double)


    @Query("UPDATE finance_table SET percent_increase_capital = :dataValue WHERE id = :financeId")
    fun updPercentageIncreaseCapitalByID(financeId: Long, dataValue: Double)


    @Query("UPDATE finance_table SET percent_profit_tax = :dataValue WHERE id = :financeId")
    fun updPercentageProfitTaxByID(financeId: Long, dataValue: Double)

    @Query("UPDATE finance_table SET percent_region_inflation = :dataValue WHERE id = :financeId")
    fun updPercentageRegionInflationByID(financeId: Long, dataValue: Double)

    @Query("UPDATE finance_table SET goal_capital = :dataValue WHERE id = :financeId")
    fun updGoalCapitalByID(financeId: Long, dataValue: Double)


    @Delete
    fun deleteStartFinanceData(finance: Finance)

    @Query("DELETE FROM finance_table")
    fun deleteAllFinance()

}