package com.example.credapp;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;

public class ProfileActivity extends AppCompatActivity {

    private EditText etUserName;
    private ImageButton imgEdit; // Changed to ImageButton
    private boolean isEditing = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        // Make status bar transparent
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );

        // Initialize views
        initViews();

        // Set up click listeners
        setupClickListeners();
    }

    private void initViews() {
        etUserName = findViewById(R.id.etUserName);
        imgEdit = findViewById(R.id.btnEdit); // Get the direct reference to the ImageButton
    }

    private void setupClickListeners() {
        // Back button
        findViewById(R.id.btnBack).setOnClickListener(v -> finish());

        // Support button
        findViewById(R.id.support).setOnClickListener(v ->
                Toast.makeText(this, "Support clicked", Toast.LENGTH_SHORT).show()
        );

        // Edit button
        imgEdit.setOnClickListener(v -> { // Use the direct reference
            if (isEditing) {
                saveUserName();
            } else {
                enableEditing();
            }
        });

        // Set up done action on keyboard
        etUserName.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                saveUserName();
                return true;
            }
            return false;
        });

        // Rest of your click listeners...
    }

    private void enableEditing() {
        isEditing = true;
        etUserName.setEnabled(true);
        etUserName.setFocusable(true);
        etUserName.setFocusableInTouchMode(true);
        etUserName.requestFocus();

        // Show keyboard
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(etUserName, InputMethodManager.SHOW_IMPLICIT);

        // Position cursor at the end of text
        etUserName.setSelection(etUserName.getText().length());

        // Change edit icon to save icon
        imgEdit.setImageResource(R.drawable.ic_check);
    }

    private void saveUserName() {
        isEditing = false;
        etUserName.setEnabled(false);
        etUserName.setFocusable(false);
        etUserName.clearFocus();

        // Hide keyboard
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(etUserName.getWindowToken(), 0);

        // Change save icon back to edit icon
        imgEdit.setImageResource(R.drawable.ic_edit);

        // Show confirmation message
        Toast.makeText(this, "Profile name updated", Toast.LENGTH_SHORT).show();
    }

}