package com.academiapp.widgets


import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.academiapp.R
import com.academiapp.base.ActivityViewBinding
import com.academiapp.databinding.RemitDialogBinding
import com.academiapp.models.Person
import com.academiapp.models.Pqrs
import com.academiapp.utils.getActivity
import com.academiapp.utils.showMessage
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.text.SimpleDateFormat
import java.util.*
import javax.xml.datatype.DatatypeConstants.MONTHS


class RemitDialog(private val context: Context,private val idPqrs: Int) : KoinComponent,
    AdapterView.OnItemSelectedListener {
    private val viewModel by inject<RemitVM>()
    private var viewGroup: ViewGroup? = null
    private lateinit var binding: RemitDialogBinding
    var onAcceptClickListener: (() -> Unit)? = null
    private var personList: ArrayList<Person> = arrayListOf()
    private var idPerson = -1
    fun show() {
        viewGroup = getActivity(context)?.window?.decorView?.rootView as ViewGroup?
        binding = RemitDialogBinding.inflate(LayoutInflater.from(context))
        viewGroup?.addView(binding.root)
        viewModelListener()
        setDate()
        setCalendarView()
        binding.apply {
            clContainer.setOnClickListener {
                hide()
            }

            btnCancel.setOnClickListener {
                hide()
            }

            btnRemit.setOnClickListener {
                viewModel.remitPqrs(binding.txtDate.text.toString(),idPerson, idPqrs)
            }
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun setDate() {
        val sdf = SimpleDateFormat("dd-MM-yyyy")
        val currentDate = sdf.format(Date())
        binding.txtDate.text = currentDate
    }

    private fun setCalendarView(){

        val date: Calendar = Calendar.getInstance()
        val thisAYear = date.get(Calendar.YEAR)
        val thisAMonth = date.get(Calendar.MONTH)
        val thisADay = date.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(context,
            R.style.DialogTheme, { _, thisYear, thisMonth, thisDay ->
            val day = if(thisDay < 10) "0$thisDay" else thisDay
            val month = if(thisMonth < 10) "0${thisMonth + 1}" else thisMonth+1
            binding.txtDate.text = "$day-$month-$thisYear"
        }, thisAYear, thisAMonth, thisADay)

        binding.btnOpenCalendar.setOnClickListener {
            dpd.show()
        }
    }

    private fun viewModelListener() {
        viewModel.apply {
            getPersonSuccess.observe((context as AppCompatActivity)) { personList2 ->
                personList = personList2
                val spinnerList = ArrayList<String>()
                personList.forEach { spinnerList.add(it.firstName + " " + it.lastName) }
                with(binding.spPerson) {
                    adapter = ArrayAdapter(
                        context,
                        android.R.layout.simple_spinner_item,
                        spinnerList
                    )
                    onItemSelectedListener = this@RemitDialog
                }
            }

            progressStatus.observe(context) {
                (context as ActivityViewBinding<*, *>).progressDialog.show(it)
            }

            error.observe(context) {
                (context as ActivityViewBinding<*, *>).showMessage(it)
            }

            remissionPqrsSuccess.observe(context){
                (context as ActivityViewBinding<*, *>).showMessage("Pqrs remitido con éxito")
                hide()
            }

            getPersons()
        }
    }


    private fun hide() {
        if (viewGroup != null) {
            viewGroup?.removeView(binding.root)
        }
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        when (p0) {
            binding.spPerson -> {
                idPerson = personList[p2].idPerson ?: -1
            }

        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }

}