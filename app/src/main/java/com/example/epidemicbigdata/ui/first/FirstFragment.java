package com.example.epidemicbigdata.ui.first;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.epidemicbigdata.R;
import com.example.epidemicbigdata.adapter.ChinaTabAdapter;
import com.example.epidemicbigdata.databinding.FragmentFirstBinding;
import com.google.android.material.tabs.TabLayout;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;
    private ViewPager chinaViewPager;
    private TabLayout chinaTableLayout;
    private List<Fragment> fragments = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        chinaTableLayout = view.findViewById(R.id.chinaTableLayout);
        chinaViewPager = view.findViewById(R.id.chinaViewPager);
        fragments.add(new ChinaFragment());
        fragments.add(new ChineseFragment());

        ChinaTabAdapter chinaTabAdapter = new ChinaTabAdapter(getActivity().getSupportFragmentManager(), new String[] {"全国信息", "省份信息"}, fragments);
        chinaViewPager.setAdapter(chinaTabAdapter);
        chinaTableLayout.setupWithViewPager(chinaViewPager);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}