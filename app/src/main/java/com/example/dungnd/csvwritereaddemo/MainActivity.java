package com.example.dungnd.csvwritereaddemo;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.opencsv.CSVWriter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText etCommodityCode;
    private EditText etCategory;
    private EditText etNumber;
    private EditText etPrice;
    private EditText etMoney;
    private Button btnCreate;
    private Button btnShow;
    private List<Model> models;
    private CSVWriter writer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        models = new ArrayList<>();
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                try {
//                    readCSV();
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                }
                try {
                    readFileData(StringUtils.OUTPUT);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
                writeCSV();
            }
        });

    }

    private void initView() {
        etCommodityCode = findViewById(R.id.et_commodity_code);
        etCategory = findViewById(R.id.et_category);
        etNumber = findViewById(R.id.et_number);
        etPrice = findViewById(R.id.et_price);
        etMoney = findViewById(R.id.et_money);
        btnCreate = findViewById(R.id.btn_create);
        btnShow = findViewById(R.id.btn_show);
    }

    private void getData() {
        String code = etCommodityCode.getText().toString();
        String category = etCategory.getText().toString();
        String number = etNumber.getText().toString();
        String price = etPrice.getText().toString();
        String money= etMoney.getText().toString();
        models.add(new Model(code, category, Integer.valueOf(number), Float.valueOf(price), Float.valueOf(money)));
    }

    private void writeCSV() {
        try {
            writer = new CSVWriter(new FileWriter(StringUtils.OUTPUT));
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < models.size(); i++) {
            String data[] = {models.get(i).getCommodityCode(),
                    models.get(i).getCategory(), String.valueOf(models.get(i).getNumber()), String.valueOf(models.get(i).getPrice()), String.valueOf(models.get(i).getMoney())};
            writer.writeNext(data);
        }
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void readFileData(String path) throws FileNotFoundException
    {

        String[] data;
        File file = new File(path);
        if (file.exists())
        {
            BufferedReader br = new BufferedReader(new FileReader(file));
            try
            {
                String csvLine;
                while ((csvLine = br.readLine()) != null)
                {
                    data=csvLine.split(",");
                    try
                    {
                        Toast.makeText(getApplicationContext(),data[0]+" "+data[1],Toast.LENGTH_SHORT).show();
                    }
                    catch (Exception e)
                    {
                        Log.e("Problem",e.toString());
                    }
                }
            }
            catch (IOException ex)
            {
                throw new RuntimeException("Error in reading CSV file: "+ex);
            }
        }
        else
        {
            Toast.makeText(getApplicationContext(),"file not exists",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1) {
            if (grantResults.length == 1 &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(MainActivity.this, "Permision Write File is Granted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "Permision Write File is Denied", Toast.LENGTH_SHORT).show();

            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

//    public void initPermission() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) && checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)  != PackageManager.PERMISSION_GRANTED) {
//
//                //Permisson don't granted
//                if (shouldShowRequestPermissionRationale(Manifest.permission.READ_EXTERNAL_STORAGE)) {
//                    Toast.makeText(MainActivity.this, "Permission isn't granted ", Toast.LENGTH_SHORT).show();
//                }
//                // Permisson don't granted and dont show dialog again.
//                else {
//                    Toast.makeText(MainActivity.this, "Permisson don't granted and dont show dialog again ", Toast.LENGTH_SHORT).show();
//                }
//                //Register permission
//                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
//
//            }
//        }
//    }
}
