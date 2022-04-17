package com.nine__square.property.Avtivities;

/*import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;

import com.nine__square.property.R;

import java.util.concurrent.Executor;*/

public class BioMatricActivity/* extends AppCompatActivity*/ {

 /*   Executor executor;
    BiometricManager biometricManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bio_matric);

        executor = ContextCompat.getMainExecutor(this);

        biometricManager = BiometricManager.from(this);
        redirectToScreen();

    }


    public  void  authuser(Executor ee)
    {
        BiometricPrompt.PromptInfo promptInfo =new  BiometricPrompt.PromptInfo.Builder()
                // 2
                .setTitle("Xlix Cab Driver")
                // 3

                // 4
                .setDescription("You need to Verify Yourself!! use PIN,Pattern or Password")
                // 5
                .setDeviceCredentialAllowed(true)
                // 6
                .build();

        BiometricPrompt promt =  new  BiometricPrompt(this,ee,new BiometricPrompt.AuthenticationCallback(){

            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                startActivity(new Intent(BioMatricActivity.this, MainActivity.class));
            }

            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                finish();
            }
        });

        promt.authenticate(promptInfo);
    }


    private void redirectToScreen() {
     if(executor!=null){
                switch (biometricManager.canAuthenticate())
                {
                    case  BiometricManager.BIOMETRIC_SUCCESS: authuser(executor); break;
                    case BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE : break;

                    case BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE :  ;break;
                    case  BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED :

                }}



    }
*/
}