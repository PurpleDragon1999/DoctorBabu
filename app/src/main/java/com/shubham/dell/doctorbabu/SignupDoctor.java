package com.shubham.dell.doctorbabu;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupDoctor extends AppCompatActivity {

    public EditText username;
    public EditText Email;
    public EditText userphone;
    public EditText Password;
    public EditText ConfirmPassword, speciality;
    public Button registerButton;
    private FirebaseAuth mAuth;
    private FirebaseUser firebaseUser;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_doctor);
        username = findViewById(R.id.nameDoc);
        Email = findViewById(R.id.emailDoc);
        userphone = findViewById(R.id.phoneDoc);
        Password = findViewById(R.id.passwordDoc);
        ConfirmPassword = findViewById(R.id.confirmPasswordDoc);
        speciality = findViewById(R.id.speciality);
        registerButton = findViewById(R.id.registerDoc);
        mAuth = FirebaseAuth.getInstance();
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                registerDoctor();
            }
        });

    }

    public void registerDoctor()
    {
        String name=username.getText().toString().trim();
        String usermail=Email.getText().toString().trim();
        String phone=userphone.getText().toString();
        String pass=Password.getText().toString();
        String conPass=ConfirmPassword.getText().toString();
        String sp=speciality.getText().toString().trim();
        mAuth.createUserWithEmailAndPassword(usermail, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(SignupDoctor.this,"Successful",Toast.LENGTH_SHORT).show();
                            firebaseUser = mAuth.getCurrentUser();
                            saveData();


                        } else {
                            // If sign in fails, display a message to the user.
                            // Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(SignupDoctor.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();}



    /*public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radioButton:
                if (checked)

                    break;
            case R.id.radioButton2:
                if (checked)
                    // Ninjas rule
                    break;
        }
    }*/

                    }});
    }
    public void saveData()
    {
        if(firebaseUser!=null)
        {    String name=username.getText().toString().trim();
            String usermail=Email.getText().toString().trim();
            String phone=userphone.getText().toString();
            String special=speciality.getText().toString().trim();
            String role="doctor";
            //database=FirebaseDatabase.getInstance();
            databaseReference=FirebaseDatabase.getInstance().getReference("doctor");
            String id=databaseReference.push().getKey();
            doctorinfo userdata=new doctorinfo(name,usermail,phone,role,special);
            databaseReference.child(id).setValue(userdata);
            Toast.makeText(SignupDoctor.this,"Data saved succesfully",Toast.LENGTH_SHORT).show();
        }
    }
}
