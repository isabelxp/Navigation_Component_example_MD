package com.practicas.mdpostres;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class CarFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_car, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        CarAdapter adapter =new CarAdapter(CarFragmentArgs.fromBundle(getArguments()).getProducts());
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        TextView tvSum = view.findViewById(R.id.tvSum);
        tvSum.setText(getString(R.string.car_sum, (float) adapter.getItemCount()));

        view.findViewById(R.id.btnback).setOnClickListener(view1-> NavHostFragment.findNavController(this)
                .navigate(R.id.action_carFragment_to_productsFragment));

        view.findViewById(R.id.btnpay).setOnClickListener(view1-> NavHostFragment.findNavController(this)
                .navigate(R.id.action_car_to_confirmation));

        setHasOptionsMenu(true);
    }

    @Override
    public void onPrepareOptionsMenu(@NonNull Menu menu) {
        menu.findItem(R.id.action_confirmation).setVisible(false);
        menu.findItem(R.id.confirmationFragment).setVisible(false);
        super.onPrepareOptionsMenu(menu);
    }
}