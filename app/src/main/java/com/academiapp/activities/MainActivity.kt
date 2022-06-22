package com.academiapp.activities

//import android.widget.Toolbar

//import com.academiapp.fragment.AsesoriasFragment
//import com.academiapp.fragment.AuditoriasFragment
//import com.academiapp.fragment.InformacionGeneralFragment
//import com.academiapp.fragment.PersonasFragment
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.academiapp.R
import com.academiapp.activities.WorkDModel.ProjectsDegree
import com.academiapp.fragment.AsesoriasFragmentJava
import com.academiapp.fragment.AuditoriasFragmentJava
import com.academiapp.fragment.InformacionGeneralFragmentJava
import com.academiapp.fragment.PersonasFragmentJava
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener

class MainActivity : AppCompatActivity(){

    var workdetail: ProjectsDegree? = null
    var selectedFragment: Fragment? = null
    var informacionGeneralFragment: InformacionGeneralFragmentJava? = null
    var asesoriasFragmentFragment: AsesoriasFragmentJava? = null
    var auditoriasFragment: AuditoriasFragmentJava? = null
    var personasFragment: PersonasFragmentJava? = null
    var tabsNavigation: TabLayout? = null
    var fragment = "gifs"

    //    ViewPager viewPager;
    //    FrameLayout
    var toolbar: Toolbar? = null
    var imgBack: ImageView? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        workdetail = intent.getSerializableExtra("modeldata") as ProjectsDegree?

        tabsNavigation = findViewById(R.id.tab_layout);
        imgBack = findViewById(R.id.img_back_arrow_gallery);

        //findViewById<View>(R.id.btnBack).setOnClickListener { finish() }
        findViewById<View>(R.id.img_back_arrow_gallery).setOnClickListener {
            finish()
        }

        toolbar = findViewById(R.id.toolbar_Top_g);
        tabsNavigation!!.addTab(tabsNavigation!!.newTab().setText("Informacion General"));
        tabsNavigation!!.addTab(tabsNavigation!!.newTab().setText("Personas"));
        tabsNavigation!!.addTab(tabsNavigation!!.newTab().setText("Auditorias"));
        tabsNavigation!!.addTab(tabsNavigation!!.newTab().setText("Asesorias"));
        informacionGeneralFragment = InformacionGeneralFragmentJava();
        asesoriasFragmentFragment =  AsesoriasFragmentJava();
        auditoriasFragment =  AuditoriasFragmentJava();
        personasFragment =  PersonasFragmentJava();
//        tabsNavigation!!.setTabGravity(TabLayout.GRAVITY_FILL);
        tabsNavigation!!.setBackgroundColor(getColor(R.color.white));

        if(intent.getBooleanExtra("isaddconsultant",false))
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                AsesoriasFragmentJava()).commit();

            tabsNavigation!!.getTabAt(3)!!.select()
        }
        else{
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                InformacionGeneralFragmentJava()).commit();
        }


        tabsNavigation!!.setOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                val id = tab.id.toString()
                //                CharSequence text = tab.getText();
                if (tab.text == "Informacion General") {
//                    selectedFragment = new ImagesFragment(toolbar);
                    selectedFragment = informacionGeneralFragment
                    fragment = "InformacionGeneralFragment()"
                } else if (tab.text == "Asesorias") {
//                    selectedFragment = new VideoFragment(toolbar);
                    selectedFragment = asesoriasFragmentFragment
                    fragment = "Asesorias()"
                } else if (tab.text == "Auditorias") {
//                    selectedFragment = new GifFragment(toolbar);
//                    Toast.makeText(GalleryActivity.this, "Please Wait...", Toast.LENGTH_LONG).show();
                    selectedFragment = auditoriasFragment
                    fragment = "AuditoriasFragment()"
                }else if (tab.text == "Personas") {
//                    selectedFragment = new GifFragment(toolbar);
//                    Toast.makeText(GalleryActivity.this, "Please Wait...", Toast.LENGTH_LONG).show();
                    selectedFragment = personasFragment
                    fragment = "PersonasFragment()"
                }
                supportFragmentManager.beginTransaction().replace(
                    R.id.fragment_container,
                    selectedFragment!!
                ).commit()
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })

    }
}