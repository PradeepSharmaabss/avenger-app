package com.example.mylibrary;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.widget.Toast;

public class TestingActivity extends AppCompatActivity {

    static Context co;
    public static Intent getCallingIntent(@NonNull Context context) {
        Intent intent = new Intent(context, TestingActivity.class);
        co =context;
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testing);
        Toast.makeText(this, "new module", Toast.LENGTH_LONG).show();
        setResult();
    }

    private void setResult() {
        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();
    }
}
