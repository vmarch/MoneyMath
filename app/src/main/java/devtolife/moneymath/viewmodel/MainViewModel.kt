package devtolife.moneymath.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import devtolife.moneymath.data.Finance
import devtolife.moneymath.data.FinanceRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: FinanceRepository = FinanceRepository(application)

//    private val liveDataAllFinance: LiveData<List<Finance>> = repository.getAllFinanceData()
    private val liveDataFinance: LiveData<Finance> = repository.getFinanceData()
    internal var editorBlockVisibility = false

//    fun getFinanceData(): Finance{
//        var iii = Finance()
//        Log.d("TAG", "VM getFinanceData()1 = ${iii.startYear}")
//        viewModelScope.launch(Dispatchers.IO) {
//            iii =  repository.getFinanceData()
//            Log.d("TAG", "VM getFinanceData()2 = ${iii.startYear}")
//        }
//        Log.d("TAG", "VM getFinanceData()3 = ${iii.startYear}")
//        return iii
//    }

//    fun getAllFinanceLiveData(): LiveData<List<Finance>> {
//
////        Log.d("TAG", "VM getAllFinanceLiveData()")
//        return liveDataAllFinance
//    }

    fun getFinanceLiveData(): LiveData<Finance> {

        Log.d("TAG", "VM getFinanceLiveData() = $liveDataFinance")
        return liveDataFinance
    }

    fun createEmptyFinanceInDB() {

        val currentDate = Date()
        val dateFormat = SimpleDateFormat("yyyy", Locale.getDefault())
        val dateText = dateFormat.format(currentDate)

        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(Finance())
            repository.updYear(dateText.toInt() + 1)

        }
    }

    //region UPD SIMPLE VALUES in repository

    fun updYearInDB(newYear: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updYear(newYear)
        }
    }

    fun updCapitalInDB(newStartCapital: Double) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updCapital(newStartCapital)
        }
    }

    fun updSalaryInDB(newSalary: Double) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updSalary(newSalary)
        }
    }

    fun updPercentageIncreaseSalaryInDB(newPercentIncreaseSalary: Double) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updPercentageIncreaseSalary(newPercentIncreaseSalary)
        }
    }

    fun updCostsInDB(newCosts: Double) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updCosts(newCosts)
        }
    }

    fun updPercentageIncreaseCostsInDB(newPercentIncreaseCosts: Double) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updPercentageIncreaseCosts(newPercentIncreaseCosts)
        }
    }

    fun updPercentageIncreaseCapitalInDB(newPercentIncreaseCapital: Double) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updPercentageIncreaseCapital(newPercentIncreaseCapital)
        }
    }

    fun updPercentageProfitTaxInDB(newPercentProfitTax: Double) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updPercentageProfitTax(newPercentProfitTax)
        }
    }

    fun updPercentageRegionInflationInDB(newPercentRegionInflation: Double) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updPercentageRegionInflation(newPercentRegionInflation)
        }
    }

    fun updGoalCapitalInDB(newGoalCapital: Double) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updGoalCapital(newGoalCapital)
        }
    }

    //endregion


    fun changeEditorsBlockVisibility() {
        editorBlockVisibility = !editorBlockVisibility
    }
}
