package com.example.myapplicationbyujjwal1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SecondActivity extends AppCompatActivity {
    public EditText phoneEmail ;
    public TextView showPassedMsg;
    Button confirmButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        phoneEmail=(EditText) findViewById(R.id.phoneEmail);
        confirmButton=(Button) findViewById(R.id.confirmButton);
        confirmButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String phoneNo=phoneEmail.getText().toString().trim();
                //System.out.println("phoneNo:"+phoneNo+"  passedString: "+ passedString);
                //checking if entered valid phone number
                if(phone_Validity(phoneNo)){
                    sendSms(view,phoneNo);
                }
                //checking if entered valid email id
                else if(android.util.Patterns.EMAIL_ADDRESS.matcher(phoneNo).matches()) {
                    sendEmail(view,phoneNo);
                }
                else{
                   phoneEmail.setError("Enter valid Phone no or Email address");
                    //phoneEmail.setText("Enter valid Phone no or Email address");
                }
            }
        });

    }
    //Phone No validation check
   public boolean phone_Validity(String phoneNo){
        String pattern = "[6-9][0-9]{9}";
        Pattern p = Pattern.compile(pattern);
       Matcher m=p.matcher(phoneNo);
       //System.out.println("valid phone no?"+m.matches()+"phone no"+phoneNo);
        return m.matches();
    }
    //method to send sms
    public void sendSms(View view, String phoneNo ){
        Toast.makeText(getApplicationContext(),"Sending SMS",Toast.LENGTH_LONG).show();
        Bundle extras=getIntent().getExtras();
        String passedString=extras.getString("typedMsg");
        Intent smsIntent = new Intent(Intent.ACTION_VIEW);
        smsIntent.setData(Uri.parse("smsto:"+phoneNo));
        smsIntent.putExtra("address"  , phoneNo);
        smsIntent.putExtra("sms_body"  , passedString);
        //smsIntent.setType("vnd.android-dir/mms-sms");
        startActivity(smsIntent);
    }
    //method to send email
    public void sendEmail(View view, String phoneNo ){
        Toast.makeText(getApplicationContext(),"Sending Email",Toast.LENGTH_LONG).show();
        Bundle extras=getIntent().getExtras();
        String passedString=extras.getString("typedMsg");
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"+phoneNo));
        emailIntent.setType("message/rfc822");
        emailIntent.putExtra(Intent.EXTRA_EMAIL  , new String[]{phoneNo});
        emailIntent.putExtra(Intent.EXTRA_TEXT   , passedString);
        startActivity(Intent.createChooser(emailIntent, "Send mail..."));
    }
}