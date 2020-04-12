package com.example.yatranslete;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.yatranslete.Transl.ManyTransTask;
import com.example.yatranslete.Transl.Request;
import com.example.yatranslete.Transl.YaTransTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    static public HashMap<String, String> langs;
    static public Spinner langf;
    static public Spinner langt;
    TextView tb;
    static public EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tb = findViewById(R.id.Tb);
        langs = new HashMap<>();
        et = findViewById(R.id.text);
        createHashLangs();
        dispLangs();
    }

    private void createHashLangs() {
        for (int i = 0; i < getResources().getStringArray(R.array.langs).length; i++) {
            langs.put(getResources().getStringArray(R.array.laungs)[i],getResources().getStringArray(R.array.langs)[i]);
        }
    }

    public void dispLangs() {
        langf = findViewById(R.id.langf);
        langt = findViewById(R.id.langt);

        langf.setAdapter(new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, getResources().getStringArray(R.array.laungs)));
        langt.setAdapter(new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, getResources().getStringArray(R.array.laungs)));
    }


    public void displayResult(String text) {
        TextView tv = findViewById(R.id.result);
        Log.d("TransResult", text);
        tv.setText(text);
    }

    public void onClick(View v) throws ExecutionException, InterruptedException {
        if(tb.getText().toString().equals("")) {
            String txt = et.getText().toString();
            Request req = new Request(txt, langs.get(langf.getSelectedItem().toString()) + "-" + langs.get(langt.getSelectedItem().toString()));
            YaTransTask task = new YaTransTask(this);
            task.execute(req);
        } else {
            ManyTransTask task = new ManyTransTask(this);
            task.execute(Integer.parseInt(tb.getText().toString()));
        }
    }
}