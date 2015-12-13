package com.example.vinothvino.internalstorage;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class MainActivity extends Activity {

    EditText e1,e2;
    Button save,show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        e1 = (EditText) findViewById(R.id.editText);
        e2 = (EditText) findViewById(R.id.editText2);
        save = (Button) findViewById(R.id.button);
        show = (Button) findViewById(R.id.button2);

        //Action Listener for button save
        // Here , we r gonna see how to store datas in android
        save.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                String filename = e1.getText().toString();
                String data = e2.getText().toString();

                FileOutputStream fos;

                try{

                    fos = openFileOutput(filename,MODE_PRIVATE);
                    fos.write(data.getBytes());
                    fos.close();

                    Toast.makeText(getApplicationContext(),filename+ " suceessfully saved",Toast.LENGTH_LONG).show();

                }catch (FileNotFoundException e){

                    e.printStackTrace();

                }catch (IOException e){

                    e.printStackTrace();

                }

            }
        });

        //Action Listener for button show
        // Here, we r gonna see how to show our data's which are resides in android internal storage

        show.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                String filename = e1.getText().toString();
                StringBuffer stringBuffer = new StringBuffer();

                try{

                    String inputStream;
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(openFileInput(filename)));

                    while((inputStream = bufferedReader.readLine())!= null){

                        stringBuffer.append(inputStream+" \n");

                    }

                    Toast.makeText(getApplicationContext(),stringBuffer.toString(),Toast.LENGTH_LONG).show();
                }catch (IOException e){

                    e.printStackTrace();

                }
            }
        });
    }
}
