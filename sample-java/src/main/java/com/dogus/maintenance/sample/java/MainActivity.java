package com.dogus.maintenance.sample.java;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.dogus.maintenance.core.MaintenanceConfig;
import com.dogus.maintenance.sample.java.databinding.ActivityMainBinding;
import com.dogus.maintenance.view.MaintenanceActivity;
import com.dogus.maintenance.view.MaintenanceDialog;
import com.dogus.maintenance.view.MaintenanceFragment;

/**
 * Sample activity demonstrating all usage patterns of the Maintenance library with Java.
 * 
 * This activity showcases:
 * 1. Full-screen activity
 * 2. Dialog
 * 3. Fragment
 * 4. Custom View
 * 5. Builder pattern
 * 6. Config-based approach
 */
public class MainActivity extends AppCompatActivity {
    
    private ActivityMainBinding binding;
    private boolean isCustomViewVisible = false;
    private boolean isFragmentVisible = false;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        
        setupListeners();
        setupCustomView();
    }
    
    private void setupListeners() {
        binding.btnShowActivity.setOnClickListener(v -> showMaintenanceActivity());
        binding.btnShowDialog.setOnClickListener(v -> showMaintenanceDialog());
        binding.btnShowFragment.setOnClickListener(v -> toggleFragment());
        binding.btnShowWithBuilder.setOnClickListener(v -> showMaintenanceWithBuilder());
        binding.btnShowWithConfig.setOnClickListener(v -> showMaintenanceWithConfig());
        binding.btnToggleCustomView.setOnClickListener(v -> toggleCustomView());
    }
    
    private void setupCustomView() {
        binding.maintenanceView.setOnRetryClickListener(() -> {
            showToast("Retry clicked from Custom View!");
            checkServerStatus();
        });
    }
    
    /**
     * Example 1: Show maintenance as a full-screen activity
     */
    private void showMaintenanceActivity() {
        MaintenanceActivity.show(
            this,
            "Bakım Modu",
            "Uygulama şu anda bakımdadır. Lütfen daha sonra tekrar deneyin.",
            true,
            null
        );
    }
    
    /**
     * Example 2: Show maintenance as a dialog
     */
    private void showMaintenanceDialog() {
        MaintenanceConfig config = new MaintenanceConfig(
            "Bakım Çalışması",
            "Sistem güncelleniyor. Bu işlem birkaç dakika sürebilir.",
            null, null, null, null,
            true,
            "Yeniden Dene",
            null
        );
        
        MaintenanceDialog.show(this, config, () -> {
            showToast("Retry clicked from Dialog!");
            checkServerStatus();
        });
    }
    
    /**
     * Example 3: Show maintenance as a fragment
     */
    private void toggleFragment() {
        if (isFragmentVisible) {
            Fragment fragment = getSupportFragmentManager().findFragmentById(binding.fragmentContainer.getId());
            if (fragment != null) {
                getSupportFragmentManager().beginTransaction()
                    .remove(fragment)
                    .commit();
            }
            binding.fragmentContainer.setVisibility(View.GONE);
            binding.btnShowFragment.setText(R.string.show_fragment);
            isFragmentVisible = false;
        } else {
            MaintenanceConfig config = new MaintenanceConfig(
                "Bakım Modu",
                "Fragment içinde bakım modu gösterimi",
                null, null, null, null,
                true,
                "Tekrar Dene",
                null
            );
            
            MaintenanceFragment fragment = MaintenanceFragment.newInstance(config);
            fragment.setOnRetryListener(() -> {
                showToast("Retry clicked from Fragment!");
                checkServerStatus();
            });
            
            getSupportFragmentManager().beginTransaction()
                .replace(binding.fragmentContainer.getId(), fragment)
                .commit();
            
            binding.fragmentContainer.setVisibility(View.VISIBLE);
            binding.btnShowFragment.setText("Hide Fragment");
            isFragmentVisible = true;
        }
    }
    
    /**
     * Example 4: Show maintenance using Builder pattern
     */
    private void showMaintenanceWithBuilder() {
        new MaintenanceActivity.Builder(this)
            .setTitle("Bakım Modu (Builder)")
            .setMessage("Builder pattern kullanarak bakım modu gösterimi")
            .setShowRetryButton(true)
            .setRetryButtonText("Tekrar Dene")
            .setOnRetryListener(() -> {
                showToast("Retry clicked!");
                checkServerStatus();
            })
            .show();
    }
    
    /**
     * Example 5: Show maintenance with advanced configuration
     */
    private void showMaintenanceWithConfig() {
        MaintenanceConfig config = MaintenanceConfig.builder()
            .title("Gelişmiş Bakım Modu")
            .message("Özelleştirilmiş ayarlar ile bakım modu")
            .showRetryButton(true)
            .retryButtonText("Yeniden Dene")
            .build();
        
        MaintenanceActivity.show(this, config);
    }
    
    /**
     * Example 6: Toggle custom view visibility
     */
    private void toggleCustomView() {
        if (isCustomViewVisible) {
            binding.maintenanceView.setVisibility(View.GONE);
            binding.btnToggleCustomView.setText(R.string.show_custom_view);
            isCustomViewVisible = false;
        } else {
            binding.maintenanceView.setVisibility(View.VISIBLE);
            binding.btnToggleCustomView.setText(R.string.hide_custom_view);
            isCustomViewVisible = true;
        }
    }
    
    /**
     * Simulates checking server status
     */
    private void checkServerStatus() {
        showToast("Sunucu durumu kontrol ediliyor...");
        // In real app, make network request here
    }
    
    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
