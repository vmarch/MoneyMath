package devtolife.moneymath.data

import android.app.Application
import android.util.Log
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import devtolife.moneymath.data.AppDatabase
import devtolife.moneymath.data.Finance
import devtolife.moneymath.data.FinanceDao

class FinanceRepository {

    private var financeDao: FinanceDao
//    private var liveDataAllFinance: LiveData<List<Finance>>
    private var liveDataFinance: LiveData<Finance>

    constructor(application: Application) {
        var appDatabase: AppDatabase = AppDatabase.getDatabase(application)
        financeDao = appDatabase.financeDao()
//        liveDataAllFinance = financeDao.getAllStartFinanceData()
        liveDataFinance = financeDao.getSimpleFinanceData()
    }

    suspend fun insert(finance: Finance) {
        financeDao.insertStartFinanceData(finance)
    }

    fun update(finance: Finance) {
        financeDao.updateStartFinanceData(finance)
    }

//    fun getAllFinanceData(): LiveData<List<Finance>> {
//        return liveDataAllFinance
//    }

    fun getFinanceData(): LiveData<Finance>{
        return liveDataFinance
    }

    //region UPD SIMPLE VALUES in financeDao

    fun updYear(newYear: Int) {
        financeDao.updYearByID(0, newYear)
    }

    fun updCapital(newCapital: Double) {
        financeDao.updStartCapitalByID(0, newCapital)
    }

    fun updSalary(newSalary: Double) {
        financeDao.updStartSalaryByID(0, newSalary)
    }

    fun updPercentageIncreaseSalary(newPercentIncreaseSalary: Double) {
        financeDao.updPercentageIncreaseSalaryByID(0, newPercentIncreaseSalary)
    }

    fun updCosts(newCosts: Double) {
        financeDao.updStartCostsByID(0, newCosts)
    }

    fun updPercentageIncreaseCosts(newPercentIncreaseCosts: Double) {
        financeDao.updPercentageIncreaseCostsByID(0, newPercentIncreaseCosts)
    }

    fun updPercentageIncreaseCapital(newPercentIncreaseCapital: Double) {
        financeDao.updPercentageIncreaseCapitalByID(0, newPercentIncreaseCapital)
    }

    fun updPercentageProfitTax(newPercentProfitTax: Double) {
        financeDao.updPercentageProfitTaxByID(0, newPercentProfitTax)
    }

    fun updPercentageRegionInflation(newPercentRegionInflation: Double) {
        financeDao.updPercentageRegionInflationByID(0, newPercentRegionInflation)
    }

    fun updGoalCapital(newGoalCapital: Double) {
        financeDao.updGoalCapitalByID(0, newGoalCapital)
    }
    //endregion


    public fun delete(finance: Finance) {
        financeDao.deleteStartFinanceData(finance)
    }
}