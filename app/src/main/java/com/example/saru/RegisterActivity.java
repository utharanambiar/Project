package com.example.saru;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {
    private Button CreateAccountButton;
    private EditText InputName, InputPhoneNumber, InputPassword;
    private ProgressDialog loadingbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        CreateAccountButton = (Button) findViewById(R.id.register_btn);
        InputName = (EditText) findViewById(R.id.register_username_input);
        InputPhoneNumber = (EditText) findViewById(R.id.register_phone_number_input);
        InputPassword = (EditText) findViewById(R.id.register_password_input);
        loadingbar=new ProgressDialog(this);
        CreateAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateAccount();
            }
        });
    }

    private void CreateAccount() {
        String name = InputName.getText().toString();
        String phone = InputPhoneNumber.getText().toString();
        String password = InputPassword.getText().toString();
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "Please enter your name", Toast.LENGTH_SHORT).show();
        }
       else if (TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "Please enter your number", Toast.LENGTH_SHORT).show();
        }
       else if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please enter your password", Toast.LENGTH_SHORT).show();
        }
       else {
loadingbar.setTitle("Create Account");
loadingbar.setMessage("Please wait while your credentials are being checked...");
loadingbar.setCanceledOnTouchOutside(false);
loadingbar.show();
ValidatephoneNumber(name,phone,password);
        }
    }

    private void ValidatephoneNumber(final String name, final String phone, final String password) {
        final DatabaseReference Rootref;
        Rootref= FirebaseDatabase.getInstance().getReference();
        Rootref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if(!(dataSnapshot.child("Users").child(phone).exists())){
                        HashMap<String,Object>userDataMap=new HashMap<>();
                        userDataMap.put("phone",phone);
                        userDataMap.put("password",password);
                        userDataMap.put("name",name);
                        Rootref.child("Users").child(phone).updateChildren(userDataMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
if(task.isSuccessful()){
    Toast.makeText(RegisterActivity.this,"Congratulations! Your account has been created!",Toast.LENGTH_SHORT).show();
    loadingbar.dismiss();
    Intent intent =new Intent(RegisterActivity.this,loginActivity.class);
    startActivity(intent);
    RegisterActivity.this.finish();
}
else {
    loadingbar.dismiss();
    Toast.makeText(RegisterActivity.this,"Network Error",Toast.LENGTH_SHORT).show();
}
                            }
                        });
                }
                else{
Toast.makeText(RegisterActivity.this,"This "+phone+" already exists",Toast.LENGTH_SHORT).show();
loadingbar.dismiss();
Toast.makeText(RegisterActivity.this,"Please try again using another phone number",Toast.LENGTH_SHORT).show();
                        Intent intent =new Intent(RegisterActivity.this,MainActivity.class);
                        startActivity(intent);
                        RegisterActivity.this.finish();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}