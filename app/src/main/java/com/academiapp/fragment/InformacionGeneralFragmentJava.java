package com.academiapp.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.academiapp.R;
import com.academiapp.activities.MainActivity;
import com.academiapp.activities.WorkDModel;
import com.academiapp.content.degree_work.DWorkActivity;

import org.w3c.dom.Text;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InformacionGeneralFragmentJava#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InformacionGeneralFragmentJava extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    View v;

    TextView edtFecha_de_inicio, edtFecha_de_finalización, edtFecha_de_exposición, edt_Áreas, edt_Modalidad,
            edt_ODS, edt_Resumen;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public InformacionGeneralFragmentJava() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InformacionGeneralFragmentJava.
     */
    // TODO: Rename and change types and number of parameters
    public static InformacionGeneralFragmentJava newInstance(String param1, String param2) {
        InformacionGeneralFragmentJava fragment = new InformacionGeneralFragmentJava();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        v = inflater.inflate(R.layout.fragment_informacion_general, container, false);

        edtFecha_de_inicio = v.findViewById(R.id.edt_Fecha_de_inicio);
        edtFecha_de_finalización = v.findViewById(R.id.edt_Fecha_de_finalización);
        edtFecha_de_exposición = v.findViewById(R.id.edt_Fecha_de_exposición);
        edt_Áreas = v.findViewById(R.id.edt_Áreas);
        edt_Modalidad = v.findViewById(R.id.edt_Modalidad);
        edt_ODS = v.findViewById(R.id.edt_ODS);
        edt_Resumen = v.findViewById(R.id.edt_Resumen);

        WorkDModel.ProjectsDegree pro=((MainActivity)getContext()).getWorkdetail();
        String t1="null";
        if(pro.getStart_date()!=null){
            t1=""+pro.getStart_date();
        }

        TextView tt= v.findViewById(R.id.txt_Mantenimiento_Franco_Sati);
        tt.setText(pro.getTitle());

        edtFecha_de_inicio.setText(t1);

        t1="null";
        if(pro.getFinish_date()!=null){
            t1=""+pro.getFinish_date();
        }
        edtFecha_de_finalización.setText(t1);


        t1="null";
        if(pro.getDate_exposure_project()!=null){
            t1=""+pro.getDate_exposure_project();
        }
        edtFecha_de_exposición.setText(t1);



        if(pro.getAreas().size()>0){
            edt_Áreas.setText(pro.getAreas().get(0).getArea().getArea());
        }

        if(pro.getOds().size()>0){
            edt_ODS.setText(pro.getOds().get(0).getOds().getDescription());
        }

        if(pro.getProject_modality()!=null){
            edt_Modalidad.setText(pro.getProject_modality().getName());
        }

        if(pro.getDescription()!=null){
            edt_Resumen.setText(pro.getDescription());
        }



        if(pro.getLaureate()){
            v.findViewById(R.id.img_form_icon1).setVisibility(View.VISIBLE);
        }
        if(pro.getMeritorious()){
            v.findViewById(R.id.img_form_icon2).setVisibility(View.VISIBLE);
        }


        return v;
    }
}