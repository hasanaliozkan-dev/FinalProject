package WildfireAnalysisAndPredictionSystem.test2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpPageActivity extends AppCompatActivity {
    String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
    Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
    FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);
        EditText userName = findViewById(R.id.input_username);
        EditText e_mail = findViewById(R.id.input_email);
        EditText password = findViewById(R.id.password_input);
        EditText confirm_password = findViewById(R.id.password_confirm_input);
         db = FirebaseFirestore.getInstance();
         Log.d("Ins",db.toString());


        TextView guide_director = findViewById(R.id.signup_3);
        guide_director.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpPageActivity.this, GuidePageActivity.class);
                startActivity(intent);
            }
        });

        Button sign_in_director = findViewById(R.id.button_sign_up);
        sign_in_director.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CharSequence inputStr = e_mail.getText().toString();
                Matcher matcher = pattern.matcher(inputStr);
                //TODO when the button clicked username, mail, password will be writing on firebase.


                    if(userName.getText().toString().length()<8){
                        Toast.makeText(SignUpPageActivity.this,"Username should contain 8 character",Toast.LENGTH_SHORT).show();
                        return;
                    }else if (!matcher.matches()){
                          Toast.makeText(SignUpPageActivity.this,"Please enter a valid e-mail",Toast.LENGTH_SHORT).show();
                        return;

                    }else if(password.getText().toString().length()<8){
                        Toast.makeText(SignUpPageActivity.this,"Password should contain 8 character",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    else if(!(password.getText().toString().equals(confirm_password.getText().toString()))){
                        Toast.makeText(SignUpPageActivity.this,"Passwords should match",Toast.LENGTH_SHORT).show();
                        return;
                    } // TODO else if (userName.getText().toString() isnot in database check this)

                if(userName.getText().toString().isEmpty()
                        ||e_mail.getText().toString().isEmpty()
                        ||password.getText().toString().isEmpty()
                        ||confirm_password.getText().toString().isEmpty()){
                    Toast.makeText(SignUpPageActivity.this,"Please fill all places",Toast.LENGTH_SHORT).show();
                    return;

                }else{
                    User user =new User(userName.getText().toString(),
                            password.getText().toString(),e_mail.getText().toString(),new ArrayList<>());
                    Log.d("SignUP","hereeee!!!!1");
                    Log.d("deneme",db.collection("users").document("000000").toString());
                    db.collection("users").add(user).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Log.d("SignUP","onsuccess!!!!1");

                            Toast.makeText(SignUpPageActivity.this,"Welcome to the WAPS",Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d("SignUP","onfailure!!!!1");

                            Toast.makeText(SignUpPageActivity.this,"Please try again",Toast.LENGTH_SHORT).show();

                        }
                    });
                }





                Intent intent = new Intent(SignUpPageActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}