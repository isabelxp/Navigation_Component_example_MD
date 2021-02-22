package com.practicas.mdpostres;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.ImageView;


import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;


public class MainActivity extends AppCompatActivity {

    private NavController navController;
    private BottomSheetBehavior bottomSheetBehavior;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navController = Navigation.findNavController(this, R.id.nav_host_fragment);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        NavController navController = Navigation.findNavController(this,R.id.nav_host_fragment);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph())
                .build();

        NavigationUI.setupWithNavController(toolbar,navController,appBarConfiguration);
        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
            toolbar.setTitle(destination.getLabel());
            toolbar.setNavigationIcon(null);
        });

        ConstraintLayout bottomSheet = findViewById(R.id.bottom_sheet);
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);

        MaterialButton btnExit = findViewById(R.id.btnExit);
        ImageView btnClose = findViewById(R.id.btnclose);

        btnClose.setOnClickListener(view -> bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN));
        btnExit.setOnClickListener(view -> finish());




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
      /*  if(item.getItemId() == R.id.action_confirmation){
           navController.navigate(R.id.action_global_confirmation);
        }
        return super.onOptionsItemSelected(item);*/
        return NavigationUI.onNavDestinationSelected(item, navController) ||
                super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {

        /*new MaterialAlertDialogBuilder(this)
                .setTitle(R.string.bottom_sheet_title)
                .setPositiveButton(R.string.bottom_sheet_exit, (dialogInterface, i) -> finish())
                .setNegativeButton(R.string.bottom_sheet_cancelar,null);*/
        //super.onBackPressed();

        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
    }
}