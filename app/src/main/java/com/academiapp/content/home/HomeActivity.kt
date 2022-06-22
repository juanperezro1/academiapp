package com.academiapp.content.home

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.academiapp.R
import com.academiapp.activities.SelectUniversityActivity
import com.academiapp.base.ActivityViewBinding
import com.academiapp.content.degree_work.DWorkActivity
import com.academiapp.content.login.LoginActivity
import com.academiapp.content.pqrs.PQRSActivity
import com.academiapp.databinding.ActivityHomeBinding
import com.academiapp.services.SharedPreferencesManager
import com.academiapp.utils.openActivity
import java.util.*


class HomeActivity : ActivityViewBinding<ActivityHomeBinding,HomeVM>() {
    override fun inflateLayout(layoutInflater: LayoutInflater) =  ActivityHomeBinding.inflate(layoutInflater)
    var viewpermission = false
    override fun viewListeners() {

        PermissionBooleanGetP()
        binding.apply {
            if(!viewpermission){
                cardpqrs.visibility = View.GONE
            }
            btnPqrsMore.setOnClickListener {
                openActivity(PQRSActivity::class.java)
            }

            btnDegreeWorkMore.setOnClickListener {
                val sharedPreferencesManager = SharedPreferencesManager(this@HomeActivity)
                if (sharedPreferencesManager.getuserheadquater() < 1) {
                    val builder: AlertDialog.Builder = AlertDialog.Builder(this@HomeActivity)
                    builder.setMessage("User does not belong to a seat or academic program yet.")
                        .setCancelable(false)
                        .setPositiveButton("OK", DialogInterface.OnClickListener { dialog, id ->
                            dialog.dismiss()
                        })
                    val alert: AlertDialog = builder.create()
                    alert.show()
                }
                else{
                    openActivity(DWorkActivity::class.java)
                }
            }

            btnLogOut.setOnClickListener {

                val dialog = Dialog(this@HomeActivity)
                dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                dialog.setContentView(R.layout.questionpopup)
                dialog.window!!.setLayout(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                val btnprivate = dialog.findViewById<TextView>(R.id.txt1)
                val btnpublic = dialog.findViewById<TextView>(R.id.txt2)
                btnprivate.setOnClickListener {
                    val sharedPreferencesManager = SharedPreferencesManager(this@HomeActivity)
                    sharedPreferencesManager.saveUserName("" ?: "")
                    sharedPreferencesManager.savePassword("" ?: "")

                    openActivity(SelectUniversityActivity::class.java)
                    finish()
                }
                btnpublic.setOnClickListener { dialog.dismiss() }
                dialog.show()
            }
        }
    }

    override fun viewModelListeners() {

    }

    fun PermissionBooleanGetP() {
        val sharedPreferencesManager = SharedPreferencesManager(this@HomeActivity)
        val lli: List<String> = ArrayList(
            Arrays.asList(
                *sharedPreferencesManager.getpermisosp().split(",").toTypedArray()
            )
        )
        val llo: List<String> = ArrayList(
            Arrays.asList(
                *sharedPreferencesManager.getpermisosmovilp().split(",").toTypedArray()
            )
        )
        var iiopooo = 0
        for (dfg in lli) {
            if (dfg.lowercase(Locale.getDefault()).contains("search")) {
                if (llo[iiopooo].lowercase(Locale.getDefault()).contains("true")) {
                    viewpermission = true
                }
            }
            iiopooo++
        }
    }

}