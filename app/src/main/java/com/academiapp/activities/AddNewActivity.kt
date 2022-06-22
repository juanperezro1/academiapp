package com.academiapp.activities

import android.app.Dialog
import com.academiapp.app.MyApplication.Companion.tempdata
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import android.widget.EditText
import android.widget.Spinner
import com.academiapp.widgets.CustomProgress
import android.widget.ArrayAdapter
import com.academiapp.activities.ModalisedDModel.ProjectModalities
import com.academiapp.activities.AreasDModel.Areas_projects
import com.academiapp.services.SharedPreferencesManager
import com.academiapp.activities.WorkDModel.ProjectsDegree
import androidx.recyclerview.widget.RecyclerView
import android.os.Bundle
import com.academiapp.R
import android.widget.LinearLayout
import androidx.cardview.widget.CardView
import android.view.ViewGroup
import android.widget.CheckBox
import android.os.Build
import androidx.core.widget.CompoundButtonCompat
import android.content.res.ColorStateList
import android.graphics.Color
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.academiapp.fragment.PersonasAdapter
import com.academiapp.activities.WorkDModel.ProjectsDegree.People.Rol
import okhttp3.OkHttpClient
import okhttp3.Interceptor
import kotlin.Throws
import retrofit2.Retrofit
import com.academiapp.functions.ApiBaseURl
import retrofit2.converter.gson.GsonConverterFactory
import android.widget.Toast
import com.academiapp.activities.AddWorkModel.PeoplePut
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

class AddNewActivity : AppCompatActivity() {
    lateinit var tab1: TextView
    lateinit var tab2: TextView
    lateinit var sp_Ods: TextView
    lateinit var laytab1: ConstraintLayout
    lateinit var laytab2: ConstraintLayout
    lateinit var edt_Titulo: EditText
    lateinit var edt_Resumen_edit: EditText
    lateinit var edt_Palabras_clave: EditText
    lateinit var sp_modalidad: Spinner
    lateinit var sp_Areas: Spinner

    // TODO: Rename and change types of parameters
    lateinit private var mParam1: String
    private val mParam2: String? = null
    lateinit var jsonPlaceHolderApi: JsonPlaceHolderApi
    var customProgress: CustomProgress? = null
    var modalisedadapter: ArrayAdapter<String>? = null
    var areaadapter: ArrayAdapter<String>? = null
    var odsadapter: ArrayAdapter<String>? = null
    var modalisedLIST: MutableList<ProjectModalities>? = null
    var areaalist: MutableList<Areas_projects>? = null
    var odsalist: MutableList<ODSDModel.Ods>? = null
    var personlist: PersonDModel? = null
    var rollist: RolModel? = null
    var ismodalised = false
    var isods = false
    var isareas = false
    var addWorkModel: AddWorkModel? = null
    var sharedPreferencesManager: SharedPreferencesManager? = null
    private var workdetail: ProjectsDegree? = null
    lateinit var personarecylerview: RecyclerView
    var listodsadd: MutableList<Int> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new)
        findViewById<View>(R.id.img_back_arrow_edit).setOnClickListener { finish() }
        //
        modalisedLIST = ArrayList()
        areaalist = ArrayList()
        odsalist = ArrayList()
        sharedPreferencesManager = SharedPreferencesManager(this@AddNewActivity)
        addWorkModel = AddWorkModel()
        customProgress = CustomProgress(this@AddNewActivity)
        customProgress!!.show(true)
        tab1 = findViewById(R.id.tab1)
        tab2 = findViewById(R.id.tab2)
        laytab1 = findViewById(R.id.laytab1)
        laytab2 = findViewById(R.id.laytab2)
        edt_Titulo = findViewById(R.id.edt_Titulo)
        edt_Resumen_edit = findViewById(R.id.edt_Resumen_edit)
        edt_Palabras_clave = findViewById(R.id.edt_Palabras_clave)
        sp_modalidad = findViewById(R.id.sp_modalidad)
        sp_Areas = findViewById(R.id.sp_Areas)
        sp_Ods = findViewById(R.id.sp_Ods)
        sp_Ods.setOnClickListener(View.OnClickListener { v ->
            v.isEnabled = false
            val linearodsadd: LinearLayout
            val crd_Agregar_dialog: CardView
            val odsDialog = Dialog(this@AddNewActivity)
            odsDialog.setContentView(R.layout.perosna_odsdd_dialog)
            odsDialog.window!!.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            linearodsadd = odsDialog.findViewById(R.id.linearodsadd)
            for (sssdd in odsalist as ArrayList<ODSDModel.Ods>) {
                val cb = CheckBox(applicationContext)
                cb.text = sssdd.description
                if (listodsadd.contains(sssdd.id_ods)) {
                    cb.isChecked = true
                }
                cb.setOnCheckedChangeListener { buttonView, isChecked ->
                    if (isChecked) {
                        listodsadd.add(sssdd.id_ods!!)
                        var ghh = sp_Ods.getText().toString()
                        ghh = ghh + " " + sssdd.description
                        sp_Ods.setText(ghh)
                    } else {
                        listodsadd.remove(sssdd.id_ods)
                        var ghh = sp_Ods.getText().toString()
                        ghh = ghh.replace(" " + sssdd.description, "")
                        sp_Ods.setText(ghh)
                    }
                }
                if (Build.VERSION.SDK_INT < 21) {
                    CompoundButtonCompat.setButtonTintList(
                        cb,
                        ColorStateList.valueOf(Color.parseColor("#487FFF"))
                    ) //Use android.support.v4.widget.CompoundButtonCompat when necessary else
                } else {
                    cb.buttonTintList =
                        ColorStateList.valueOf(Color.parseColor("#487FFF")) //setButtonTintList is accessible directly on API>19
                }
                linearodsadd.addView(cb)
            }
            crd_Agregar_dialog = odsDialog.findViewById(R.id.crd_Agregar_dialog)
            crd_Agregar_dialog.setOnClickListener { v ->
                v.isEnabled = false
                odsDialog.dismiss()
                v.isEnabled = true
            }
            odsDialog.show()
            v.isEnabled = true
        })
        tab1.setOnClickListener(View.OnClickListener {
            tab1.setTextColor(getColor(R.color.tunselected))
            tab2.setTextColor(getColor(R.color.tunselected))
            tab1.setTextColor(getColor(R.color.tselected))
            laytab1.setVisibility(View.GONE)
            laytab2.setVisibility(View.GONE)
            laytab1.setVisibility(View.VISIBLE)
        })
        tab2.setOnClickListener(View.OnClickListener {
            tab1.setTextColor(getColor(R.color.tunselected))
            tab2.setTextColor(getColor(R.color.tselected))
            laytab1.setVisibility(View.GONE)
            laytab2.setVisibility(View.GONE)
            laytab2.setVisibility(View.VISIBLE)
        })
        RunListData()
        workdetail = intent.getSerializableExtra("modeldata") as ProjectsDegree?
        PutData()
        personarecylerview = findViewById(R.id.personarecylerview)
        personarecylerview.setLayoutManager(LinearLayoutManager(this@AddNewActivity))
        personasAdapter = PersonasAdapter(this@AddNewActivity, allBirds)
        personarecylerview.setAdapter(personasAdapter)
    }

    var personasAdapter: PersonasAdapter? = null
    var allBirds: MutableList<ProjectsDegree.People> = ArrayList()
    var addDialog: Dialog? = null
    lateinit var sp_persona_edit_dialog: Spinner
    lateinit var sp_rol_edit_dialog: Spinner
    lateinit var txt_Guardar_dialog: TextView
    private fun initAddDialog() {
        val personlistada =
            ArrayAdapter<String>(this@AddNewActivity, android.R.layout.simple_spinner_dropdown_item)
        val rollistada =
            ArrayAdapter<String>(this@AddNewActivity, android.R.layout.simple_spinner_dropdown_item)
        for (pp in personlist!!.persons!!) {
            personlistada.add(pp!!.first_name + " " + pp.last_name)
        }
        for (pp in rollist!!.projects_roles!!) {
            rollistada.add(pp!!.description)
        }
        addDialog = Dialog(this@AddNewActivity)
        addDialog!!.setContentView(R.layout.perosna_edit_dialog)
        addDialog!!.window!!.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        sp_persona_edit_dialog = addDialog!!.findViewById(R.id.sp_persona_edit_dialog)
        sp_rol_edit_dialog = addDialog!!.findViewById(R.id.sp_rol_edit_dialog)
        txt_Guardar_dialog = addDialog!!.findViewById(R.id.txt_Guardar_dialog)
        sp_persona_edit_dialog.setAdapter(personlistada)
        sp_rol_edit_dialog.setAdapter(rollistada)
        txt_Guardar_dialog.setOnClickListener(View.OnClickListener { //                personlist.getPersons().get(sp_persona_edit_dialog.getSelectedItemPosition())
            val aaa = ProjectsDegree.People()
            aaa.person = ProjectsDegree.People.Person()
            aaa.rol = Rol()
            aaa.person!!.id_person =
                personlist!!.persons!![sp_persona_edit_dialog.getSelectedItemPosition()]!!.id_person
            aaa.person!!.first_name =
                personlist!!.persons!![sp_persona_edit_dialog.getSelectedItemPosition()]!!.first_name + " " +
                        personlist!!.persons!![sp_persona_edit_dialog.getSelectedItemPosition()]!!.last_name
            aaa.rol!!.id_project_rol =
                rollist!!.projects_roles!![sp_rol_edit_dialog.getSelectedItemPosition()]!!.id_project_rol
            aaa.rol!!.description =
                rollist!!.projects_roles!![sp_rol_edit_dialog.getSelectedItemPosition()]!!.description
            allBirds.add(aaa)
            personasAdapter!!.notifyDataSetChanged()
            addDialog!!.dismiss()
        })
        addDialog!!.show()
    }

    fun RunListData() {
        val sharedPreferencesManager = SharedPreferencesManager(this@AddNewActivity)
        val client: OkHttpClient = OkHttpClient.Builder().addInterceptor(Interceptor { chain ->
            val newRequest = chain.request().newBuilder()
                .addHeader("Authorization", "Bearer " + sharedPreferencesManager.getUserToken())
                .build()
            chain.proceed(newRequest)
        }).build()
        val retrofit = Retrofit.Builder()
            .client(client)
            .baseUrl(sharedPreferencesManager.getmainurl())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi::class.java)
        modalisedadapter =
            ArrayAdapter(this@AddNewActivity, android.R.layout.simple_spinner_dropdown_item)
        areaadapter =
            ArrayAdapter(this@AddNewActivity, android.R.layout.simple_spinner_dropdown_item)
        odsadapter =
            ArrayAdapter(this@AddNewActivity, android.R.layout.simple_spinner_dropdown_item)




        //get the spinner from the xml.
        jsonPlaceHolderApi.GetModalised()!!.enqueue(object : Callback<ModalisedDModel?> {
            override fun onResponse(
                call: Call<ModalisedDModel?>,
                response: Response<ModalisedDModel?>
            ) {
                if (!response.isSuccessful) {
                    Toast.makeText(this@AddNewActivity, "Failed!", Toast.LENGTH_SHORT).show()
                    customProgress!!.show(false)
                }
                ismodalised = true
                customProgress!!.show(false)
                var imnlll1l = 0
                var imnllll = 0
                for (projectModalities in response.body()!!.projectModalities!!) {
                    modalisedadapter!!.add(projectModalities!!.name)
                    modalisedLIST!!.add(projectModalities)
                    if (workdetail != null) {
                        if (workdetail!!.project_modality!!.id_project_modality ===
                            projectModalities.id_project_modality) {
                            imnlll1l = imnllll
                        }
                    }
                    imnllll++
                }
                sp_modalidad!!.adapter = modalisedadapter
                if (workdetail != null) {
                    sp_modalidad!!.setSelection(imnlll1l)
                }
            }

            override fun onFailure(call: Call<ModalisedDModel?>, t: Throwable) {
                Toast.makeText(this@AddNewActivity, "No Internet Connection!", Toast.LENGTH_SHORT)
                    .show()
                customProgress!!.show(false)
            }
        })
        jsonPlaceHolderApi.GetAreas()!!.enqueue(object : Callback<AreasDModel?>{

            override fun onResponse(call: Call<AreasDModel?>, response: Response<AreasDModel?>) {
                if (!response.isSuccessful) {
                    Toast.makeText(this@AddNewActivity, "Failed!", Toast.LENGTH_SHORT).show()
                    customProgress!!.show(false)
                }
                isareas = true
                customProgress!!.show(false)
                var imnlll1l = 0
                var imnllll = 0
                for (projectModalities in response.body()!!.areas_projects!!) {
                    areaadapter!!.add(projectModalities!!.area)
                    areaalist!!.add(projectModalities)
                    if (workdetail != null) {
                        if (workdetail!!.areas!!.size > 0 && workdetail!!.areas!![0].id_area_project ===
                            projectModalities.id_area_project) {
                            imnlll1l = imnllll
                        }
                    }
                    imnllll++
                }
                sp_Areas!!.adapter = areaadapter
                if (imnlll1l != 0 && workdetail != null) {
                    sp_Areas!!.setSelection(imnlll1l)
                }
            }

            override fun onFailure(call: Call<AreasDModel?>, t: Throwable) {
                Toast.makeText(this@AddNewActivity, "No Internet Connection!", Toast.LENGTH_SHORT)
                    .show()
                customProgress!!.show(false)
            }

        })
        jsonPlaceHolderApi.GetODS()!!.enqueue(object : Callback<ODSDModel?> {

            override fun onResponse(call: Call<ODSDModel?>, response: Response<ODSDModel?>) {
                if (!response.isSuccessful) {
                    Toast.makeText(this@AddNewActivity, "Failed!", Toast.LENGTH_SHORT).show()
                    customProgress!!.show(false)
                }
                isods = true
                customProgress!!.show(false)
                var imnlll1l = 0
                var imnllll = 0
                for (projectModalities in response.body()!!.ods!!) {
                    odsadapter!!.add(projectModalities!!.description)
                    odsalist!!.add(projectModalities)
                    if (workdetail != null) {
                        if (workdetail!!.ods!!.size > 0 && workdetail!!.ods!![0].id_ods === projectModalities.id_ods) {
                            imnlll1l = imnllll
                        }
                    }
                    imnllll++
                }
                sp_Ods!!.text = ""
                //                sp_Ods.setAdapter(odsadapter);
//
//                if(imnlll1l!=0&&workdetail!=null){
//                    sp_Ods.setSelection(imnlll1l);
//                }
            }

            override fun onFailure(call: Call<ODSDModel?>, t: Throwable) {
                Toast.makeText(this@AddNewActivity, "No Internet Connection!", Toast.LENGTH_SHORT)
                    .show()
                customProgress!!.show(false)
            }
        })
        jsonPlaceHolderApi.GetPerson()!!.enqueue(object : Callback<PersonDModel?> {
            override fun onResponse(call: Call<PersonDModel?>, response: Response<PersonDModel?>) {
                if (response.isSuccessful) {
                    personlist = response.body()
                }
            }

            override fun onFailure(call: Call<PersonDModel?>, t: Throwable) {}
        })
        jsonPlaceHolderApi.GetRol()!!.enqueue(object : Callback<RolModel?> {
            override fun onResponse(call: Call<RolModel?>, response: Response<RolModel?>) {
                if (response.isSuccessful) {
                    rollist = response.body()
                }
            }

            override fun onFailure(call: Call<RolModel?>, t: Throwable) {}
        })
    }

    fun Btnimgnewpersona(view: View) {
        view.isEnabled = false
        initAddDialog()
        view.isEnabled = true
    }

    fun BtnSublitData(view: View) {
        view.isEnabled = false
        AddEntrytoModel()
        if (addWorkModel!!.title!!.length < 1) {
            view.isEnabled = true
            Toast.makeText(this, "Add Title", Toast.LENGTH_SHORT).show()
            return
        }
        if (addWorkModel!!.description!!.length < 1) {
            view.isEnabled = true
            Toast.makeText(this, "Add Desc", Toast.LENGTH_SHORT).show()
            return
        }
        if (addWorkModel!!.key_words!!.size < 1) {
            view.isEnabled = true
            Toast.makeText(this, "Add Keyword", Toast.LENGTH_SHORT).show()
            return
        }
        if (workdetail == null && listodsadd.size < 1) {
            view.isEnabled = true
            Toast.makeText(this, "Select Ods", Toast.LENGTH_SHORT).show()
            return
        }
        customProgress!!.show(true)
        if (workdetail != null) {
            jsonPlaceHolderApi!!.PutWorkdessgree(workdetail!!.id_project_degree, putDegreeModeld)!!.enqueue(object : Callback<ProjectsDegree?> {
                    override fun onResponse(
                        call: Call<ProjectsDegree?>,
                        response: Response<ProjectsDegree?>
                    ) {
                        if (response.isSuccessful) {
                            Toast.makeText(this@AddNewActivity, "Success!", Toast.LENGTH_SHORT)
                                .show()
                            customProgress!!.show(false)
                            if (response.body() != null) {
                                tempdata = response.body()
                            }
                            finish()
                        } else {
                            Toast.makeText(this@AddNewActivity, "Failed!", Toast.LENGTH_SHORT)
                                .show()
                            customProgress!!.show(false)
                        }
                    }

                    override fun onFailure(call: Call<ProjectsDegree?>, t: Throwable) {
                        Toast.makeText(
                            this@AddNewActivity,
                            "No Internet Connection!",
                            Toast.LENGTH_SHORT
                        ).show()
                        customProgress!!.show(false)
                    }
                })
        } else {
            jsonPlaceHolderApi!!.AddWorkModel(addWorkModel)!!.enqueue(object : Callback<ProjectsDegree?> {
                    override fun onResponse(
                        call: Call<ProjectsDegree?>,
                        response: Response<ProjectsDegree?>
                    ) {
                        if (response.isSuccessful) {
                            Toast.makeText(this@AddNewActivity, "Success!", Toast.LENGTH_SHORT)
                                .show()
                            customProgress!!.show(false)
                            if (response.body() != null) {
                                tempdata = response.body()
                            }
                            finish()
                        } else {
                            Toast.makeText(this@AddNewActivity, "Failed!", Toast.LENGTH_SHORT)
                                .show()
                            customProgress!!.show(false)
                        }
                    }

                    override fun onFailure(call: Call<ProjectsDegree?>, t: Throwable) {
                        Toast.makeText(
                            this@AddNewActivity,
                            "No Internet Connection!",
                            Toast.LENGTH_SHORT
                        ).show()
                        customProgress!!.show(false)
                    }
                })
        }


        //finish();
        view.isEnabled = true
    }

    fun PutData() {
        if (workdetail != null) {
            edt_Titulo!!.setText(workdetail!!.title)
            edt_Resumen_edit!!.setText(workdetail!!.description)
            var ssres = ""
            for (keyy in workdetail!!.key_words!!) {
                ssres = ssres + keyy.key + ","
            }
            if (ssres.length > 0) {
                ssres = ssres.substring(0, ssres.length - 1)
            }
            edt_Palabras_clave!!.setText(ssres)
            sp_modalidad!!.isEnabled = false
            sp_Areas!!.isEnabled = false
            sp_Ods!!.isEnabled = false

            //findViewById(R.id.img_add_personas).setVisibility(View.GONE);
        }
    }

    var putDegreeModeld: PutDegreeModeld? = null
    fun AddEntrytoModel() {
        addWorkModel!!.title = edt_Titulo!!.text.toString()
        addWorkModel!!.description = edt_Resumen_edit!!.text.toString()
        val listkeyword: MutableList<AddWorkModel.Key_words> = ArrayList()
        val ssd = edt_Palabras_clave!!.text.toString()
        val ssfg = ssd.split(",").toTypedArray()
        for (ghn in ssfg) {
            val tempp = AddWorkModel.Key_words()
            tempp.key = ghn
            listkeyword.add(tempp)
        }
        addWorkModel!!.key_words = listkeyword
        val listpeople: MutableList<AddWorkModel.People> = ArrayList()
        val tempp1 = AddWorkModel.People()
        tempp1.id_person = sharedPreferencesManager!!.getpersonid()
        tempp1.id_project_rol = 3
        listpeople.add(tempp1)
        for (ppp in allBirds) {
            val tempp11 = AddWorkModel.People()
            tempp11.id_person = ppp.person!!.id_person
            tempp11.id_project_rol = ppp.rol!!.id_project_rol
            listpeople.add(tempp11)
        }
        addWorkModel!!.people = listpeople
        addWorkModel!!.id_project_modality =
            modalisedLIST!![sp_modalidad!!.selectedItemPosition].id_project_modality
        val listods: MutableList<AddWorkModel.Ods> = ArrayList()
        for (iikko in listodsadd) {
            val oddte = AddWorkModel.Ods()
            oddte.id_ods = iikko
            listods.add(oddte)
        }
        addWorkModel!!.ods = listods
        val listarea1: MutableList<AddWorkModel.Areas> = ArrayList()
        val areaste = AddWorkModel.Areas()
        areaste.id_area_project = areaalist!![sp_Areas!!.selectedItemPosition].id_area_project
        listarea1.add(areaste)
        addWorkModel!!.areas = listarea1
        if (workdetail != null) {
            val pppk: MutableList<PeoplePut> = ArrayList()
            for (llls in addWorkModel!!.people!!) {
                pppk.add(
                    PeoplePut(
                        llls!!.id_person!!,
                        llls!!.id_project_rol!!,
                        workdetail!!.id_project_degree!!
                    )
                )
            }
            putDegreeModeld = PutDegreeModeld(
                addWorkModel!!.description!!,
                workdetail!!.id_project_degree!!,
                addWorkModel!!.title!!,
                pppk
            )
        }
    }
}


