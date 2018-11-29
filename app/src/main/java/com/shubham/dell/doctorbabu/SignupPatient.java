package com.shubham.dell.doctorbabu;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupPatient extends AppCompatActivity {

    public EditText username;
    public EditText Email;
    public EditText userphone;
    public EditText Password;
    public EditText ConfirmPassword;
    public RadioButton r1,r2;
    public Button registerButton;
    private FirebaseAuth mAuth;
    private FirebaseUser firebaseUser;
    private DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_patient);
        username=findViewById(R.id.name);
        Email=findViewById(R.id.email);
        userphone=findViewById(R.id.phone);
        Password=findViewById(R.id.password);
        ConfirmPassword=findViewById(R.id.confirmPassword);
        registerButton=findViewById(R.id.register);
        mAuth=FirebaseAuth.getInstance();
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                registerUser();
            }
        });
    }
    public void registerUser()
    {
        String name=username.getText().toString().trim();
        String usermail=Email.getText().toString().trim();
        String phone=userphone.getText().toString();
        String pass=Password.getText().toString();
        String conPass=ConfirmPassword.getText().toString();
        mAuth.createUserWithEmailAndPassword(usermail, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(SignupPatient.this,"Successful",Toast.LENGTH_SHORT).show();
                            firebaseUser = mAuth.getCurrentUser();
                            saveData();

                        } else {
                            // If sign in fails, display a message to the user.
                            // Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(SignupPatient.this, "Authentication failed.",
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
            String role="patient";
            //database=FirebaseDatabase.getInstance();
            databaseReference=FirebaseDatabase.getInstance().getReference("patient");
            String id=databaseReference.push().getKey();
            Userinformation userdata=new Userinformation(name,usermail,phone,role);
            databaseReference.child(id).setValue(userdata);
            Toast.makeText(SignupPatient.this,"Data saved succesfully",Toast.LENGTH_SHORT).show();
        }
    }
}
