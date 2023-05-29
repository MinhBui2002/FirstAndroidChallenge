package com.example.challenges1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private EditText edtTxtName, edtTxtEmail, edtTxtPassword, edtTxtRePassword;
    private Button btnAvatarSubmit, btnRegister;
    private TextView txtWarnName, txtWarnEmail, txtWarnPassword, txtWarnRePassword;
    private RadioGroup rgGender;
    private Spinner spinnerCountry;
    private CheckBox cbAgree;
    private ConstraintLayout parent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        btnAvatarSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Image picked!", Toast.LENGTH_SHORT).show();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initRegister();
            }
        });
    }

    private void initView() {
        Log.d(TAG, "initView: Started");

        edtTxtName = findViewById(R.id.edtTxtName);
        edtTxtEmail = findViewById(R.id.edtTxtEmail);
        edtTxtPassword = findViewById(R.id.edtTxtPassword);
        edtTxtRePassword = findViewById(R.id.edtTxtRePassword);

        btnAvatarSubmit = findViewById(R.id.btnAvatarSubmit);
        btnRegister = findViewById(R.id.btnRegister);

        txtWarnName = findViewById(R.id.txtWarnName);
        txtWarnEmail = findViewById(R.id.txtWarnEmail);
        txtWarnPassword = findViewById(R.id.txtWarnPassword);
        txtWarnRePassword = findViewById(R.id.txtWarnRePassword);

        rgGender = findViewById(R.id.rgGender);

        spinnerCountry = findViewById(R.id.spinnerCountry);

        cbAgree = findViewById(R.id.cbAgree);

        parent = findViewById(R.id.parent);
    }

    private void initRegister() {
        Log.d(TAG, "initRegister: Started");
        if(validateData()) {
            if(cbAgree.isChecked()) {
                showSnackBar();
            } else {
                Toast.makeText(MainActivity.this, "Please agree to terms and conditions", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean validateData() {
        Log.d(TAG, "validateData: Started");
        if(edtTxtName.getText().toString().isEmpty()) {
            txtWarnName.setVisibility(View.VISIBLE);
            txtWarnName.setText("Please enter your name");
            return false;
        }
        if(edtTxtEmail.getText().toString().isEmpty()) {
            txtWarnEmail.setVisibility(View.VISIBLE);
            txtWarnEmail.setText("Please enter your email");
            return false;
        }
        if(edtTxtPassword.getText().toString().isEmpty()) {
            txtWarnPassword.setVisibility(View.VISIBLE);
            txtWarnPassword.setText("Please enter your password");
            return false;
        }
        if(edtTxtRePassword.getText().toString().isEmpty()) {
            txtWarnRePassword.setVisibility(View.VISIBLE);
            txtWarnRePassword.setText("Please re-enter your password");
            return false;
        }
        if(!edtTxtPassword.getText().toString().equals(edtTxtRePassword.getText().toString())) {
            txtWarnRePassword.setVisibility(View.VISIBLE);
            txtWarnRePassword.setText("Passwords do not match");
            return false;
        }
        return true;
    }

    private void showSnackBar() {
        Log.d(TAG, "showSnackBar: Started");
        txtWarnName.setVisibility(View.GONE);
        txtWarnEmail.setVisibility(View.GONE);
        txtWarnPassword.setVisibility(View.GONE);
        txtWarnRePassword.setVisibility(View.GONE);

        String name = edtTxtName.getText().toString();
        String email = edtTxtEmail.getText().toString();
        String country = spinnerCountry.getSelectedItem().toString();
        String gender = "";
        int checkedRadioButtonId = rgGender.getCheckedRadioButtonId();
        if (checkedRadioButtonId == R.id.rbMale) {
            gender = "Male";
        } else if (checkedRadioButtonId == R.id.rbFemale) {
            gender = "Female";
        } else if (checkedRadioButtonId == R.id.rbOther) {
            gender = "Other";
        } else {
            gender = "Unknown";
        }
        String snackText = "Name: " + name +
                "\nEmail: " + email +
                "\nCountry: " + country +
                "\nGender: " + gender;

        Log.d(TAG, "showSnackBar: Snack bar text: " + snackText);


        String password = edtTxtPassword.getText().toString();
        String rePassword = edtTxtRePassword.getText().toString();


        Snackbar.make(parent, snackText, Snackbar.LENGTH_SHORT)
                .setAction("Dismiss", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        edtTxtName.setText("");
                        edtTxtEmail.setText("");
                        edtTxtPassword.setText("");
                        edtTxtRePassword.setText("");
                    }

                }).show();
    }
}