package com.nine__square.property.Avtivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.nine__square.property.R;
import com.nine__square.property.Utils.JavaMailAPI;
import com.nine__square.property.Utils.VolleySingleton;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ContactActivity extends AppCompatActivity {
    public EditText mEmail;
    public EditText mSubject;
    public EditText mMessage;
    public EditText mPhone;
    public EditText mName;
    public Button sendmail;

    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);


        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Contact Us..");
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                finish();
            }
        });
        progressDialog = new ProgressDialog(ContactActivity.this);
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(false);
        mEmail = (EditText) findViewById(R.id.mailID);
        mMessage = (EditText) findViewById(R.id.messageID);
        mSubject = (EditText) findViewById(R.id.subjectID);
        mPhone = (EditText) findViewById(R.id.phoneID);
        mName = (EditText) findViewById(R.id.nameID);
        sendmail = findViewById(R.id.sendmail);

        mName.setText(VolleySingleton.getInstance(getApplicationContext()).fname());
        mEmail.setText(VolleySingleton.getInstance(getApplicationContext()).email());
        mPhone.setText(VolleySingleton.getInstance(getApplicationContext()).mobile());


        sendmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail = mEmail.getText().toString().trim();
                String message = mMessage.getText().toString();
                String subject = mSubject.getText().toString().trim();
                String name = mName.getText().toString().trim();
                String phone = mPhone.getText().toString().trim();
                if(isValid(mail,subject,message,name,phone)){
//                    savecontact(name,mail,phone,subject,message);
                    sendMail();

                }

            }
        });

    }



//    private void savecontact(final String name1, final String email1, final String mobile1, final String subject1, final String msg1){
//        progressDialog.show();
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, Endpoints.contact, new Response.Listener<String>() {
//
//            @Override
//            public void onResponse(String response) {
//                progressDialog.dismiss();
//                try {
//                    JSONObject obj = new JSONObject(response);
//                    if(obj.getBoolean("error")){
//                    }else{
////                        showmessage(obj.getString("message"));
//                        sendMail();
//
//
//                    }
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(ContactActivity.this,"Something Went Wrong",Toast.LENGTH_SHORT).show();
//                Log.d("VOLLEY", String.valueOf(error));
//            }
//        }){
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> params = new HashMap<>();
//                params.put("name", name1);
//                params.put("email", email1);
//                params.put("msg", msg1);
//                params.put("subject", subject1);
//                params.put("mobile", mobile1);
//
//                return params;
//            }
//        };
//        stringRequest.setRetryPolicy(new DefaultRetryPolicy(50000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
//    }

    private void sendMail() {

        String mail = mEmail.getText().toString().trim();
        String message = mMessage.getText().toString();
        String subject = mSubject.getText().toString().trim();
        String name = mName.getText().toString().trim();
        String phone = mPhone.getText().toString().trim();

        String message2 = message + "\n"+ "Name : "+name+ "\n"+ "Phone : "+phone+ "\n"+ "Email : "+mail;

//        Send Mail
        JavaMailAPI javaMailAPI = new JavaMailAPI(this, mail, subject, message2);

        javaMailAPI.execute();


    }



    private boolean isValid(String email, String subject, String message, String name, String phone) {
        Pattern emailPattern = Pattern.compile("^[(a-zA-Z-0-9-\\_\\+\\.)]+@[(a-z-A-z)]+\\.[(a-zA-z)]{2,3}$");
        Matcher emailMatcher = emailPattern.matcher(email);

        if (name.isEmpty()) {
            mName.setError("Please enter name");
            return false;
        }
        else if (email.isEmpty()) {
            mEmail.setError("Please enter Email");
            return false;
        } else if (!emailMatcher.matches()) {
            mEmail.setError("Please enter valid Email");
            return false;
        }
        else if (phone.isEmpty()) {
            mPhone.setError("Please enter Phone");
            return false;
        }
        else if(phone.length() != 10){
            mPhone.setError("Invalid Phone Number, Number should be of 10 Digits");
            return false;
        }
        else if (subject.isEmpty()) {
            mSubject.setError("Subject is empty");
            return false;
        } else if (message.isEmpty()) {
            mMessage.setError("Message is empty");
            return false;
        }
        return true;
    }
}

