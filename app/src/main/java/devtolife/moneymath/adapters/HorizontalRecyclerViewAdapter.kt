package devtolife.moneymath.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import devtolife.moneymath.CustomDecimalFormat
import devtolife.moneymath.R
import devtolife.moneymath.data.Finance
import devtolife.moneymath.data.FinanceResult
import kotlinx.android.synthetic.main.layout_result_list_item.view.*
import java.text.DecimalFormat

class HorizontalRecyclerViewAdapter(
    private val context: Context
) : RecyclerView.Adapter<HorizontalRecyclerViewAdapter.ViewHolder>() {

    private val df: DecimalFormat = CustomDecimalFormat().cdf

    private val inflater: LayoutInflater = LayoutInflater.from(context)

    private var financeResultList = emptyList<FinanceResult>()  // Cached copy of FinanceResultList

    private lateinit var finance: Finance

    private val maxCountList = 30

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.layout_result_list_item, parent, false)
        return ViewHolder(view)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemYearView: TextView = itemView.textViewYearListItem
        val initCapitalView: TextView = itemView.textViewStartCapitalListItem
        val mSalaryView: TextView = itemView.textViewSalaryListItem
        val mCostView: TextView = itemView.textViewCostsListItem
        val mSavingsView: TextView = itemView.textViewSavingsListItem
        val mProfitCapitalView: TextView = itemView.textViewProfitCapitalListItem
        val mProfitSavingsView: TextView = itemView.textViewProfitSavingsListItem
        val mPassiveIncomeView: TextView = itemView.textViewPassiveIncomeListItem
        val mFinalCapitalView: TextView = itemView.textViewFinalCapitalListItem
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val itemFinanceResult = financeResultList[position]

        holder.itemYearView.text = itemFinanceResult.year.toString()
        holder.initCapitalView.text = itemFinanceResult.previousYearCapital.toString()
        holder.mSalaryView.text = itemFinanceResult.yearSalary.toString()
        holder.mCostView.text = itemFinanceResult.yearCosts.toString()
        holder.mSavingsView.text = itemFinanceResult.yearSavings.toString()
        holder.mProfitCapitalView.text = itemFinanceResult.yearProfitCapital.toString()
        holder.mProfitSavingsView.text = itemFinanceResult.yearProfitSavings.toString()
        holder.mPassiveIncomeView.text = itemFinanceResult.yearPassiveIncome.toString()
        holder.mFinalCapitalView.text = itemFinanceResult.currentYearCapital.toString()
    }

//    internal fun setFinanceResultList(allFinanceList: List<Finance>) {
//
////        Log.d("TAG", "In ADAPTER FinanceList SIZE = ${allFinanceList.size}")
//
//        finance = allFinanceList[0]
//
//        financeResultList = createListFinanceResults()
//        notifyDataSetChanged()
//    }

    internal fun setFinanceResultList(simpleFinanceObj: Finance) {

//        Log.d("TAG", "In ADAPTER FinanceList SIZE = ${allFinanceList.size}")
        financeResultList = emptyList()
        finance = simpleFinanceObj

        createListFinanceResults()

    }

    fun createNewPortionListItems() {
//        Log.d(
//            "TAG", "In ADAPTER FinanceList SIZE = ${financeResultList.size} + " +
//                    "\n createNewPortionListItems()"
//        )
        createListFinanceResults()
    }

    fun addItem(financeResult: FinanceResult, index: Int) {

        financeResultList = financeResultList + financeResult

        notifyItemInserted(index)
    }

    fun deleteItem(index: Int) {
        financeResultList = financeResultList - financeResultList[index]
        notifyItemRemoved(index)
    }

    override fun getItemCount(): Int = financeResultList.size


    private fun createListFinanceResults() {

        for (i in 1..5) {

            if (itemCount == 0) {

                val newListFinanceResult = emptyList<FinanceResult>().toMutableList()

                val year: Int = finance.startYear
                val startCapital: Double = finance.startCapital
                val startSalary: Double = finance.startSalary
                val startCosts: Double = finance.startCosts
                val savings: Double = df.format(startSalary - startCosts).toDouble()
                val profitCapital: Double =
                    df.format((startCapital / 100.0) * finance.percentageIncreaseCapital)
                        .toDouble()
                Log.d("TAG", "In ADAPTER profitCapital = $profitCapital")
                val profitSavings: Double =
                    df.format((savings / 100.0) * (finance.percentageIncreaseCapital / 2.0))
                        .toDouble()
                val passiveIncome: Double = df.format(profitCapital + profitSavings).toDouble()
                val totalYearCapital: Double =
                    df.format(startCapital + savings + passiveIncome).toDouble()


                newListFinanceResult.add(
                    FinanceResult(
                        0,
                        year,
                        startCapital,
                        startSalary,
                        startCosts,
                        savings,
                        profitCapital,
                        profitSavings,
                        passiveIncome,
                        totalYearCapital
                    )
                )

                financeResultList = newListFinanceResult

            } else if (itemCount <= maxCountList) {

                val lastFinanceResult = financeResultList[itemCount - 1]

                val startCapital: Double =
                    df.format(lastFinanceResult.currentYearCapital).toDouble()
                val currentSalary: Double =
                    df.format(((lastFinanceResult.yearSalary) + ((lastFinanceResult.yearSalary / 100.0) * finance.percentageIncreaseSalary)))
                        .toDouble()
                val currentCosts: Double =
                    df.format(((lastFinanceResult.yearCosts) + ((lastFinanceResult.yearCosts / 100.0) * finance.percentageIncreaseCosts)))
                        .toDouble()
                val savings: Double = df.format(currentSalary - currentCosts).toDouble()
                val profitCapital: Double =
                    df.format(((startCapital / 100.0) * finance.percentageIncreaseCapital))
                        .toDouble()
                val profitSavings: Double =
                    df.format((savings / 100.0) * (finance.percentageIncreaseCapital / 2.0))
                        .toDouble()
                val passiveIncome: Double = df.format(profitCapital + profitSavings).toDouble()
                val totalYearCapital: Double =
                    df.format(startCapital + savings + passiveIncome).toDouble()


                val newListFinanceResult = emptyList<FinanceResult>().toMutableList()

                newListFinanceResult.add(
                    FinanceResult(
                        lastFinanceResult.id + 1,
                        (lastFinanceResult.year + 1),
                        startCapital,
                        currentSalary,
                        currentCosts,
                        savings,
                        profitCapital,
                        profitSavings,
                        passiveIncome,
                        totalYearCapital
                    )
                )

                financeResultList = financeResultList + newListFinanceResult
            }
        }
    }
}