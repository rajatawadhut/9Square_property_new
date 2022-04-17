package com.nine__square.property.Avtivities;

/*import androidx.appcompat.app.AppCompatActivity;

import com.nine__square.property.R;


import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.payumoney.core.PayUmoneyConfig;
import com.payumoney.core.PayUmoneyConstants;
import com.payumoney.core.PayUmoneySdkInitializer;
import com.payumoney.core.entity.TransactionResponse;
import com.payumoney.sdkui.ui.utils.PayUmoneyFlowManager;
import com.payumoney.sdkui.ui.utils.ResultModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;*/

public class PaymentActivity{}

//
//    final int GOOGLE_PAY_REQUEST_CODE = 123;
//    String amount;
//    double amountValue = 0.0;
//    private PayUmoneySdkInitializer.PaymentParam mPaymentParams;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_payu_money);
//
//
//
//        try {
//            amountValue = Double.parseDouble(getIntent().getStringExtra("Amount"));
//            launchPayUMoneyFlow(amountValue);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }    }
//
//
//
//
//    private PayUmoneySdkInitializer.PaymentParam calculateServerSideHashAndInitiatePayment1(final PayUmoneySdkInitializer.PaymentParam paymentParam) {
//        StringBuilder stringBuilder = new StringBuilder();
//        HashMap<String, String> params = paymentParam.getParams();
//        stringBuilder.append(params.get(PayUmoneyConstants.KEY) + "|");
//        stringBuilder.append(params.get(PayUmoneyConstants.TXNID) + "|");
//        stringBuilder.append(params.get(PayUmoneyConstants.AMOUNT) + "|");
//        stringBuilder.append(params.get(PayUmoneyConstants.PRODUCT_INFO) + "|");
//        stringBuilder.append(params.get(PayUmoneyConstants.FIRSTNAME) + "|");
//        stringBuilder.append(params.get(PayUmoneyConstants.EMAIL) + "|");
//        stringBuilder.append(params.get(PayUmoneyConstants.UDF1) + "|");
//        stringBuilder.append(params.get(PayUmoneyConstants.UDF2) + "|");
//        stringBuilder.append(params.get(PayUmoneyConstants.UDF3) + "|");
//        stringBuilder.append(params.get(PayUmoneyConstants.UDF4) + "|");
//        stringBuilder.append(params.get(PayUmoneyConstants.UDF5) + "||||||");
//
//        AppEnvironment appEnvironment = AppEnvironment.PRODUCTION;
//        stringBuilder.append(appEnvironment.salt());
//
//        String hash = hashCal(stringBuilder.toString());
//        paymentParam.setMerchantHash(hash);
//        return paymentParam;
//    }
//
//    public static String hashCal(String str) {
//        byte[] hashseq = str.getBytes();
//        StringBuilder hexString = new StringBuilder();
//        try {
//            MessageDigest algorithm = MessageDigest.getInstance("SHA-512");
//            algorithm.reset();
//            algorithm.update(hashseq);
//            byte messageDigest[] = algorithm.digest();
//            for (byte aMessageDigest : messageDigest) {
//                String hex = Integer.toHexString(0xFF & aMessageDigest);
//                if (hex.length() == 1) {
//                    hexString.append("0");
//                }
//                hexString.append(hex);
//            }
//        } catch (NoSuchAlgorithmException ignored) {
//        }
//        return hexString.toString();
//    }
//
//    /**
//     * This function prepares the data for payment and launches payumoney plug n play sdk
//     * @param amountValue
//     */
//    private void launchPayUMoneyFlow(double amountValue) {
//
//        PayUmoneyConfig payUmoneyConfig = PayUmoneyConfig.getInstance();
//
//        //Use this to set your custom text on result screen button
//        payUmoneyConfig.setDoneButtonText("Go to Dashboard");
//
//        //Use this to set your custom title for the activity
//        payUmoneyConfig.setPayUmoneyActivityTitle("Make Payment");
//
//        PayUmoneySdkInitializer.PaymentParam.Builder builder = new PayUmoneySdkInitializer.PaymentParam.Builder();
//
//
//
//        String txnId = System.currentTimeMillis() + "";
//        String phone = "9766260279";
//        String productName = "9Square Property";
//        String firstName = "Rajat";
//        String email = "rajatawadhut@gmail.com";
//
//        String udf1 = "";
//        String udf2 = "";
//        String udf3 = "";
//        String udf4 = "";
//        String udf5 = "";
//        String udf6 = "";
//        String udf7 = "";
//        String udf8 = "";
//        String udf9 = "";
//        String udf10 = "";
//
//        AppEnvironment appEnvironment = AppEnvironment.PRODUCTION;
//        builder.setAmount("" + amountValue)
//                .setTxnId(txnId)
//                .setPhone(phone)
//                .setProductName(productName)
//                .setFirstName(firstName)
//                .setEmail(email)
//                .setsUrl(appEnvironment.surl())
//                .setfUrl(appEnvironment.furl())
//                .setUdf1(udf1)
//                .setUdf2(udf2)
//                .setUdf3(udf3)
//                .setUdf4(udf4)
//                .setUdf5(udf5)
//                .setUdf6(udf6)
//                .setUdf7(udf7)
//                .setUdf8(udf8)
//                .setUdf9(udf9)
//                .setUdf10(udf10)
//                .setIsDebug(appEnvironment.debug())
//                .setKey(appEnvironment.merchant_Key())
//                .setMerchantId(appEnvironment.merchant_ID());
//
//        try {
//            mPaymentParams = builder.build();
//
//            /*
//             * Hash should always be generated from your server side.
//             * */
////            generateHashFromServer(mPaymentParams);
//
//
////         //**
////             * Do not use below code when going live
////             * Below code is provided to generate hash from sdk.
////             * It is recommended to generate hash from server side only.
////             * *//*
//            mPaymentParams = calculateServerSideHashAndInitiatePayment1(mPaymentParams);
//
//            PayUmoneyFlowManager.startPayUMoneyFlow(mPaymentParams, PaymentActivity.this, R.style.AppTheme_default, false);
//
//        } catch (Exception e) {
////             some exception occurred
//            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
//            e.printStackTrace();
////            bt_make_payment.setEnabled(true);
//        }
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        // Result Code is -1 send from Payumoney activity
//        Log.d("MainActivity", "request code " + requestCode + " resultcode " + resultCode);
//
//        switch (requestCode) {
//
//            case 10000:
//                if (requestCode == PayUmoneyFlowManager.REQUEST_CODE_PAYMENT && resultCode == RESULT_OK && data !=
//                        null) {
//                    TransactionResponse transactionResponse = data.getParcelableExtra(PayUmoneyFlowManager
//                            .INTENT_EXTRA_TRANSACTION_RESPONSE);
//
//                    ResultModel resultModel = data.getParcelableExtra(PayUmoneyFlowManager.ARG_RESULT);
//
//                    // Check which object is non-null
//                    if (transactionResponse != null && transactionResponse.getPayuResponse() != null) {
//
//                        String payuResponse = transactionResponse.getPayuResponse();
//                        Log.d("TAG" + "resp", payuResponse);
//                        try {
//                            JSONObject jsonObject = new JSONObject(payuResponse.toString());
//                            JSONObject jsonObject1 = jsonObject.getJSONObject("result");
//                            String status = jsonObject1.getString("status");
//                            String p_id = jsonObject1.getString("paymentId");
//                            String t_id = jsonObject1.getString("txnid");
//                            String date = jsonObject1.getString("addedon");
//                            String amt = jsonObject1.getString("amount");
//                            if (status.equalsIgnoreCase("success")) {
//                                System.out.println("getDataPayment"+ p_id+ "   ::   "  +t_id+ "   ::   "  + amt+ "   ::   "  + date+ "   ::   "  + status+ "   ::   "  +"PAYUMONEY");
//                                //                        ATMTSharedPreferenceManager.setPaymentStatus("p_status", true, getApplicationContext());
//                                sendPaymetStatus(p_id, t_id, amt, date, status, "PAYUMONEY");
//                            }
//                        } catch (JSONException e) {
//                            System.out.println("getDataPayment" + e.getMessage());
//                            e.printStackTrace();
//                        }
//
//
//                    } else if (resultModel != null && resultModel.getError() != null) {
//                        Log.d("TAG", "Error response : " + resultModel.getError().getTransactionResponse());
//                    } else {
//                        Log.d("TAG", "Both objects are null!");
//                    }
//                }
//                break;
//            case GOOGLE_PAY_REQUEST_CODE:
//                if ((RESULT_OK == resultCode)) {
//                    if (data != null) {
//                        String trxt = data.getStringExtra("response");
//                        Log.e("UPI", "onActivityResult: " + trxt);
//                        ArrayList<String> dataList = new ArrayList<>();
//                        dataList.add(trxt);
//                        upiPaymentDataOperation(dataList);
//                    } else {
//                        Log.e("UPI", "onActivityResult: " + "Return data is null");
//                        ArrayList<String> dataList = new ArrayList<>();
//                        dataList.add("nothing");
//                        upiPaymentDataOperation(dataList);
//                    }
//                } else {
//                    //when user simply back without payment
//                    Log.e("UPI", "onActivityResult: " + "Return data is null");
//                    ArrayList<String> dataList = new ArrayList<>();
//                    dataList.add("nothing");
//                    upiPaymentDataOperation(dataList);
//                }
//                break;
//        }
//
////        if (data != null) {
////            status = data.getStringExtra("Status").toLowerCase();
////        }
////
////        if ((RESULT_OK == resultCode) && status.equals("success")) {
////            Toast.makeText(PaymentActivity.this, "Transaction Successful", Toast.LENGTH_SHORT).show();
////            // sendPaymetStatus(p_id, t_id, amt, date, status,"PAYUMONEY");
////
////        } else {
////            Toast.makeText(PaymentActivity.this, "Transaction Failed", Toast.LENGTH_SHORT).show();
////        }
//
//    }
//
//    private void upiPaymentDataOperation(ArrayList<String> data) {
//        if (isConnectionAvailable(PaymentActivity.this)) {
//            String str = data.get(0);
//            Log.e("UPIPAY", "upiPaymentDataOperation: "+str);
//            String paymentCancel = "";
//            if(str == null) str = "discard";
//            String status = "";
//            String approvalRefNo = "";
//            String response[] = str.split("&");
//            for (int i = 0; i < response.length; i++) {
//                String equalStr[] = response[i].split("=");
//                if(equalStr.length >= 2) {
//                    if (equalStr[0].toLowerCase().equals("Status".toLowerCase())) {
//                        status = equalStr[1].toLowerCase();
//                    }
//                    else if (equalStr[0].toLowerCase().equals("ApprovalRefNo".toLowerCase()) || equalStr[0].toLowerCase().equals("txnRef".toLowerCase())) {
//                        approvalRefNo = equalStr[1];
//                    }
//                }
//                else {
//                    paymentCancel = "Payment cancelled by user.";
//                }
//            }
//            if (status.equals("success")) {
//                sendPaymetStatus("",approvalRefNo,amount,"","","GooglePay");
//                //Code to handle successful transaction here.
//                Toast.makeText(PaymentActivity.this, "Transaction successful.", Toast.LENGTH_SHORT).show();
//                Log.e("UPI", "payment successfull: "+approvalRefNo);
//            }
//            else if("Payment cancelled by user.".equals(paymentCancel)) {
//                Toast.makeText(PaymentActivity.this, "Payment cancelled by user.", Toast.LENGTH_SHORT).show();
//                Log.e("UPI", "Cancelled by user: "+approvalRefNo);
//            }
//            else {
//                Toast.makeText(PaymentActivity.this, "Transaction failed.Please try again", Toast.LENGTH_SHORT).show();
//                Log.e("UPI", "failed payment: "+approvalRefNo);
//            }
//        } else {
//            Log.e("UPI", "Internet issue: ");
//            Toast.makeText(PaymentActivity.this, "Internet connection is not available. Please check and try again", Toast.LENGTH_SHORT).show();
//        }
//    }
//
//    public static boolean isConnectionAvailable(Context context) {
//        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
//        if (connectivityManager != null) {
//            NetworkInfo netInfo = connectivityManager.getActiveNetworkInfo();
//            if (netInfo != null && netInfo.isConnected()
//                    && netInfo.isConnectedOrConnecting()
//                    && netInfo.isAvailable()) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    public void showToast(String msg) {
//        Toast.makeText(getApplicationContext(), "" + msg, Toast.LENGTH_SHORT).show();
//    }
//
//    private void sendPaymetStatus(final String p_id, final String t_id, final String amt, final String ddate, final String status, final String mode) {
//        Log.d("TAG", "Response Pay u money = " + t_id + " amount = " + amt + " status = " + status);
//
//
//        JSONObject object = new JSONObject();
//        try {
//            object.put("user_type", "provider");
//            object.put("payment_mode", mode);
//            object.put("amount", amt);
//            object.put("provider_id", "user Id");
//            object.put("transaction_id", t_id);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        Log.i("onResponseAAAAAA", "sendPaymetStatus: " + object);
//        final RequestQueue requestQueue = Volley.newRequestQueue(this);
//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
//                1,
//                "https://xoomcabs.in/api/provider/rchargewallet",
//                object,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        Toast.makeText(PaymentActivity.this, "Payment Success", Toast.LENGTH_SHORT).show();
//                        String status = response.optString("status");
//                        if (status.equals("success")) {
//                            Toast.makeText(PaymentActivity.this, "Payment Success", Toast.LENGTH_SHORT).show();
//                        } else {
//                            Toast.makeText(PaymentActivity.this, "Payment Fails", Toast.LENGTH_SHORT).show();
//                        }
////                        startActivity(new Intent(PaymentActivity.this, WalletActivity.class));
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(PaymentActivity.this, "" + error.getMessage(), Toast.LENGTH_SHORT).show();
//                    }
//                }
//        );
//        requestQueue.add(jsonObjectRequest);
//    }
//
//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//
//    }
//
//
//    public void launch(View view) {
//        launchPayUMoneyFlow(amountValue);
//    }
//}