package com.example.wenzhou;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


public class shanshuiFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    ImageView jxy,yds,njd,tls,nxj;

    public shanshuiFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment shanshuiFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static shanshuiFragment newInstance(String param1, String param2) {
        shanshuiFragment fragment = new shanshuiFragment();
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
        View v= inflater.inflate(R.layout.fragment_shanshui, container, false);
        jxy = v.findViewById(R.id.jiangxinyu);
        yds=v.findViewById(R.id.yandnagshan);
        njd=v.findViewById(R.id.nanjidao);
        tls=v.findViewById(R.id.tonglingshan);
        nxj=v.findViewById(R.id.nanxijiang);
        jxy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), pjxyActivity.class);
                startActivity(intent);
            }
        });
        yds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), pydsActivity.class);
                startActivity(intent);
            }
        });
        njd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), pnjdActivity.class);
                startActivity(intent);
            }
        });
        tls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ptlsActivity.class);
                startActivity(intent);
            }
        });
        nxj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), pnxjActivity.class);
                startActivity(intent);
            }
        });
        jxy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), pjxyActivity.class);
                startActivity(intent);
            }
        });
        return v;
    }
}
