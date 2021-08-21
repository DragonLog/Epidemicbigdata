package com.example.epidemicbigdata.ui.third;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.epidemicbigdata.R;
import com.example.epidemicbigdata.databinding.FragmentThirdBinding;

import org.jetbrains.annotations.NotNull;

public class ThirdFragment extends Fragment {

    private FragmentThirdBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentThirdBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.newsTarget, NewsFragment.class, null).commit();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}