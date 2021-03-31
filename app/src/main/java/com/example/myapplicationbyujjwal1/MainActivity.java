package com.example.myapplicationbyujjwal1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Toast;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public EditText messageText ;
    Button send_Button;
    Button voiceButton;
    String inputMessage="";
    private final int REQ_CODE = 100;
    //SharedPreferences savedValue;
    String output;
    boolean flag=false;
    boolean flag_1=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        messageText=(EditText) findViewById(R.id.msg);
        send_Button=(Button) findViewById(R.id.sendButton);
        inputMessage="";
        //sending message to the second activity
        send_Button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                inputMessage=messageText.getText().toString();
                Intent i=new Intent(getApplicationContext(),SecondActivity.class);
                i.putExtra("typedMsg",inputMessage);
                startActivity(i);
            }
        });

        //taking voice input
        voiceButton=(Button) findViewById(R.id.voiceButton);
        voiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                        RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "en-US");
                intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Please speak");
                try {
                    startActivityForResult(intent, REQ_CODE);
                } catch (ActivityNotFoundException a) {
                    Toast.makeText(getApplicationContext(),
                            "Device not supported",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        inputMessage=messageText.getText().toString();
        SharedPreferences savedValue=getSharedPreferences("savedValue_1",MODE_PRIVATE);
        SharedPreferences.Editor editor=savedValue.edit();
        System.out.println("On Pause input string"+inputMessage);
        editor.putString("msg",inputMessage);
        editor.commit();
    }
    @Override
    protected void onResume() {
        super.onResume();
        if(flag==false){
            SharedPreferences savedValue=getSharedPreferences("savedValue_1",MODE_PRIVATE);
            output=savedValue.getString("msg","");
            messageText.setText(output);
            //Toast.makeText(getApplicationContext(),"Entered on resume"+output,Toast.LENGTH_LONG).show();
       }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        flag=true;
        ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
        messageText.setText(result.get(0));
    }

}