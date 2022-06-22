package com.academiapp.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.academiapp.R;
import com.academiapp.activities.MainActivity;
import com.academiapp.activities.WorkDModel;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PersonasFragmentJava#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PersonasFragmentJava extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    View v;
    RecyclerView rcv;
    PersonasAdapter personasAdapter;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PersonasFragmentJava() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PersonasFragmentJava.
     */
    // TODO: Rename and change types and number of parameters
    public static PersonasFragmentJava newInstance(String param1, String param2) {
        PersonasFragmentJava fragment = new PersonasFragmentJava();
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
        v = inflater.inflate(R.layout.fragment_personas, container, false);
        rcv = v.findViewById(R.id.rcv_personas);

//        List<String> list=new ArrayList<>();
//        list.add("test");
//        list.add("test");
//        list.add("test");
//        list.add("test");
        WorkDModel.ProjectsDegree pro=((MainActivity)getContext()).getWorkdetail();
        initRcv(pro.getPeople());

        return v;
    }

    private void initRcv(List<? extends WorkDModel.ProjectsDegree.People> allBirds) {
        rcv.setLayoutManager(new LinearLayoutManager(getContext()));
        personasAdapter = new PersonasAdapter( getContext(), allBirds);
        rcv.setAdapter(personasAdapter);
    }

}