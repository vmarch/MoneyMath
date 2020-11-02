package devtolife.moneymath.view

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import devtolife.moneymath.CustomDecimalFormat
import devtolife.moneymath.DecimalDigitsInputFilter
import devtolife.moneymath.R
import devtolife.moneymath.adapters.HorizontalRecyclerViewAdapter
import devtolife.moneymath.data.Finance
import devtolife.moneymath.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*
import java.text.DecimalFormat


class MainActivity : AppCompatActivity() {

    private val df: DecimalFormat = CustomDecimalFormat().cdf
    private lateinit var viewModel: MainViewModel

    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mAdapter: HorizontalRecyclerViewAdapter
    private lateinit var mLayoutManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.main_activity)
        setSupportActionBar(toolbarMain)

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        viewModel.getFinanceLiveData().observe(this, Observer { financeObj ->
            if (financeObj != null) financeObj.let {
                //                Log.d("TAG", "financeObj != null >> it.startYear = " + it.startYear)

                fillEditTextFields(it)

            }
        })

//        fillEditTextFields(viewModel.getFinanceData())

        changeEditorBlockCondition(viewModel.editorBlockVisibility)

        mRecyclerView = recyclerViewOfResults
        mRecyclerView.setHasFixedSize(true)

        mLayoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.HORIZONTAL,
            false
        ) //new LinearLayoutManager(this);

        mRecyclerView.setLayoutManager(mLayoutManager)
        mAdapter = HorizontalRecyclerViewAdapter(this)

        mRecyclerView.adapter = mAdapter

//        viewModel.getAllFinanceLiveData().observe(this, Observer { financeList ->
//            financeList?.let {
//                 Log.d("TAG", "FinanceResultList YEAR = " + it[0].startYear)
//
//                if (it.isEmpty()) {
//                    viewModel.createEmptyFinanceInDB()
//                    //  Log.d("TAG", "createEmptyFinanceInDB()")
//                } else {
//
//                    mAdapter.setFinanceResultList(it)
//                }
//            }
//        })

        viewModel.getFinanceLiveData().observe(this, Observer { financeObj ->
            if (financeObj != null) financeObj.let {
                //                Log.d("TAG", "financeObj != null >> it.startYear = " + it.startYear)
                mAdapter.setFinanceResultList(it)

            } else {
//                Log.d("TAG", "financeObj != null >> createEmptyFinanceInDB()")
                viewModel.createEmptyFinanceInDB()

            }
        })

        val itemDecoration: ItemDecoration =
            DividerItemDecoration(this, LinearLayoutManager.HORIZONTAL)

        mRecyclerView.addItemDecoration(itemDecoration)

//        inputAdditionItemToRecyclerViewList()

        // Code to Add an item with default animation
        //((MyRecyclerViewAdapter) mAdapter).addItem(obj, index);
        // Code to remove an item with default animation
        //((MyRecyclerViewAdapter) mAdapter).deleteItem(index);

        //region TextWatchers
        editTextInputRowCurrentYear.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable) {
                if (s.isEmpty() || s.toString() == "" || s.toString() == "0") {
                    viewModel.updYearInDB(0)
                    Log.i("TAG", "afterTextChanged: $s")
                } else {
                    viewModel.updYearInDB(s.toString().toInt())
                    Log.i("TAG", "afterTextChanged: $s")
                }
            }
        })

        editTextInputRowStartCapital.filters = arrayOf<InputFilter>(DecimalDigitsInputFilter(2));

        editTextInputRowStartCapital.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable) {

                if (s.isEmpty() || s.toString() == "" || s.toString() == "0" || s.toString() == "0.0" || s.toString() == "0,0" || s.toString() == ".") {
                    viewModel.updCapitalInDB(0.0)
                } else {
                    viewModel.updCapitalInDB(s.toString().toDouble())
                }
            }
        })

        editTextInputRowSalary.filters = arrayOf<InputFilter>(DecimalDigitsInputFilter(2));
        editTextInputRowSalary.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable) {

                if (s.isEmpty() || s.toString() == "" || s.toString() == "0" || s.toString() == "0.0" || s.toString() == "0,0" || s.toString() == ".") {
                    viewModel.updSalaryInDB(0.0)
                } else {
                    viewModel.updSalaryInDB(s.toString().toDouble())
                }
            }
        })

        editTextInputRowCosts.filters = arrayOf<InputFilter>(DecimalDigitsInputFilter(2));
        editTextInputRowCosts.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable) {

                if (s.isEmpty() || s.toString() == "" || s.toString() == "0" || s.toString() == "0.0" || s.toString() == "0,0" || s.toString() == ".") {
                    viewModel.updCostsInDB(0.0)
                } else {
                    viewModel.updCostsInDB(s.toString().toDouble())
                }
            }
        })

        editTextInputRowPercentageGrowCapital.filters =
            arrayOf<InputFilter>(DecimalDigitsInputFilter(2));
        editTextInputRowPercentageGrowCapital.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable) {

                if (s.isEmpty() || s.toString() == "" || s.toString() == "0" || s.toString() == "0.0" || s.toString() == "0,0" || s.toString() == ".") {
                    viewModel.updPercentageIncreaseCapitalInDB(0.0)
                } else {
                    viewModel.updPercentageIncreaseCapitalInDB(s.toString().toDouble())
                }
            }
        })

        //endregion


        mRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {

//            override fun onScrollStateChanged(view: RecyclerView, scrollState: Int) {}

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
//
//                val linLayoutManager = recyclerView.layoutManager as LinearLayoutManager
//
//                val firstCompletelyVisibleItemPosition: Int =
//                    linLayoutManager.findFirstCompletelyVisibleItemPosition()
//
//                val lastCompletelyVisibleItemPosition: Int =
//                    linLayoutManager.findLastCompletelyVisibleItemPosition()
//
//                val totalItemCount = mAdapter.itemCount


//                if (totalItemCount == (lastCompletelyVisibleItemPosition + 1) && firstCompletelyVisibleItemPosition != 0) {
//
//                } else {
//
//                }

                inputAdditionItemToRecyclerViewList()


            }
        })


        buttonConfirm.setOnClickListener {
            viewModel.changeEditorsBlockVisibility()
            changeEditorBlockCondition(viewModel.editorBlockVisibility)
        }
    }

    private fun inputAdditionItemToRecyclerViewList() {

        val linLayoutManager = mRecyclerView.layoutManager as LinearLayoutManager

        val lastCompletelyVisibleItemPosition: Int =
            linLayoutManager.findLastCompletelyVisibleItemPosition()

        val totalItemCount = mAdapter.itemCount

        if (totalItemCount < (lastCompletelyVisibleItemPosition + 6)) {

            mAdapter.createNewPortionListItems()

            mRecyclerView.post {
                mAdapter.notifyDataSetChanged()
//                mAdapter.notifyItemInserted(studentList.size() - 1)

            }
//            mAdapter.notifyDataSetChanged()
        }
    }

    //region  Insertion data from DB to EditText

    private fun fillEditTextFields(finance: Finance) {

        val mYear = finance.startYear
        val mStartCapital = finance.startCapital
        val mSalary = finance.startSalary
        val mCost = finance.startCosts
        val mSavings = df.format(mSalary - mCost).toDouble()
        val mPercentProfitCapital = finance.percentageIncreaseCapital
        val mPercentProfitSavings = df.format(mPercentProfitCapital / 2.0).toDouble()

        val mPassiveIncome = df.format(
            ((mStartCapital / 100.0) * mPercentProfitCapital) +
                    ((mSavings / 100.0) * mPercentProfitSavings)
        ).toDouble()

        Log.i("TAG", "fillEditTextFields() mPassiveIncome = $mPassiveIncome")
        val mFinalCapital = df.format(mStartCapital + mSavings + mPassiveIncome).toDouble()

        //YEAR
        if ((mYear != 0)
            && (editTextInputRowCurrentYear.text.isEmpty()
                    || editTextInputRowCurrentYear.text.toString().toInt() != mYear)
        ) {
//            Log.i("TAG", "MAIN ACTIVITY <<INPUT>> finance.startYear = ${finance.startYear}")
            editTextInputRowCurrentYear.setText(mYear.toString())
        }

        //START CAPITAL
        if ((mStartCapital != 0.0)
            && (editTextInputRowStartCapital.text.isEmpty()
                    || editTextInputRowStartCapital.text.toString().toDouble() != mStartCapital)
        ) {
//            Log.i("TAG", "MAIN ACTIVITY <<INPUT>> finance.startCapital = ${finance.startCapital}")
            editTextInputRowStartCapital.setText(mStartCapital.toString())
        }

        //START SALARY
        if ((mSalary != 0.0)
            && (editTextInputRowSalary.text.isEmpty()
                    || editTextInputRowSalary.text.toString().toDouble() != mSalary)
        ) {
            editTextInputRowSalary.setText(mSalary.toString())
        }

        //START COSTS
        if ((mCost != 0.0)
            && (editTextInputRowCosts.text.isEmpty()
                    || editTextInputRowCosts.text.toString().toDouble() != mCost)
        ) {
            editTextInputRowCosts.setText(mCost.toString())
        }

        //SAVINGS
        textViewInputRowSavings.text = mSavings.toString()

        //PERCENT INCREASE CAPITAL
        if ((mPercentProfitCapital != 0.0)
            && (editTextInputRowPercentageGrowCapital.text.isEmpty()
                    || editTextInputRowPercentageGrowCapital.text.toString().toDouble() != mPercentProfitCapital)
        ) {
            editTextInputRowPercentageGrowCapital.setText(mPercentProfitCapital.toString())
        }

        //PERCENT INCREASE CAPITAL ImageBlockView
        textViewIconExtraFieldPercentageCapital.text = ("$mPercentProfitCapital%")

        //PERCENT INCREASE SAVINGS
        textViewInputRowPercentageGrowSavings.text = "$mPercentProfitSavings"

        //PERCENT INCREASE SAVINGS ImageBlockView
        textViewIconExtraFieldPercentageSavings.text = ("$mPercentProfitSavings%")
        //PASSIVE INCOME
        textViewInputRowPassiveIncome.text = mPassiveIncome.toString()

        //TOTAL YEAR CAPITAL
        textViewInputRowFinalCapital.text = mFinalCapital.toString()

    }

    //endregion

    private fun changeEditorBlockCondition(value: Boolean) {
        when {
            value -> {
                showEditorsBlock()
            }
            else -> {
                hideEditorsBlock()
            }
        }
    }

    private fun hideEditorsBlock() {

        buttonConfirm.text = "EDIT"
        layoutEditorsText.visibility = View.GONE
        layoutTextName.visibility = View.GONE

        layoutImageNamePercentage.visibility = View.VISIBLE
        layoutImageName.visibility = View.VISIBLE

    }

    private fun showEditorsBlock() {
        buttonConfirm.text = "OK"
        layoutEditorsText.visibility = View.VISIBLE
        layoutTextName.visibility = View.VISIBLE

        layoutImageNamePercentage.visibility = View.GONE
        layoutImageName.visibility = View.GONE
    }

    //region Menu
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
//        Log.d("TAG", "MA onCreateOptionsMenu")

        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        Log.d("TAG", "MA onPrepareOptionsMenu")
//        if (menu != null) {
//                Log.d("TAG", "MA onPrepareOptionsMenu DONE")
//
//                menu.getItem(1).title = resources.getString(R.string.text_main_menu_show_active)
//
//        }
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {

            R.id.action_settings -> run {
                intent = Intent(this, SettingsActivity::class.java)
                startActivity(intent)
                return true
            }

            R.id.action_about_app -> run {
                intent = Intent(this, AboutAppActivity::class.java)
                startActivity(intent)
                return true
            }

            R.id.action_privacy -> run {
                intent = Intent(this, PrivacyActivity::class.java)
                startActivity(intent)
                return true
            }

            else -> {
                super.onOptionsItemSelected(item)
                return true
            }
        }
    }

//endregion
}
