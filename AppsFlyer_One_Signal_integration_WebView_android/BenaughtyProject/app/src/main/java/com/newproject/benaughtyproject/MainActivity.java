



package com.newproject.benaughtyproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.appsflyer.AppsFlyerLib;
import com.appsflyer.AppsFlyerConversionListener;
import com.onesignal.OneSignal;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


public class MainActivity extends AppCompatActivity {

    public static final String LOG_TAG = "AppsFlyerOneLinkSimApp";
    public static final String DL_ATTRS = "dl_attrs";


    public String link_I_will_give ="https://www.benaughty.com/";
    public String link = link_I_will_give;

    private static final String ONESIGNAL_APP_ID = "41db8615-1ef4-456a-acdb-a881baf9fc78";

    Map<String, Object> conversionData;
    private TextView textView;

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Window window =getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();


        //-------------------one signal

        // Enable verbose OneSignal logging to debug issues if needed.
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);

        // OneSignal Initialization
        OneSignal.initWithContext(this);
        OneSignal.setAppId(ONESIGNAL_APP_ID);

        //--------------------------


        String did = AppsFlyerLib.getInstance().getAppsFlyerUID(getApplicationContext());;
        String afDevKey = "eaVsDPnpZ79DHohRSePArc";

        AppsFlyerLib appsflyer = AppsFlyerLib.getInstance();

        AppsFlyerLib.getInstance().registerConversionListener(this, new AppsFlyerConversionListener() {
            @Override
            public void onConversionDataSuccess(Map<String, Object> conversionData) {
                System.out.println("2");

                for (String attrName : conversionData.keySet()){

                    System.out.println("3");
                    Log.d(LOG_TAG, "Conversion attribute: " + attrName + " = " + conversionData.get(attrName));

                }

                System.out.println(conversionData);
                //TODO - remove this
                String status = Objects.requireNonNull(conversionData.get("af_status")).toString();

                if(status.equals("Non-organic")){
                    if( Objects.requireNonNull(conversionData.get("is_first_launch")).toString().equals("true")){

                        //  TODO SDK in future versions - match the input types
                        Map<String,String> newMap = new HashMap<>();
                        for (Map.Entry<String, Object> entry : conversionData.entrySet()) {
                            newMap.put(entry.getKey(), String.valueOf(entry.getValue()));
                        }
                        onAppOpenAttribution(newMap);
                    }else{

                        System.out.println("Iam in else:1");
                        String adset,c,source;
                        if(conversionData.get("adset") ==null){
                             adset =null;
                        }else{
                             adset =  Objects.requireNonNull(conversionData.get("adset")).toString();
                        }
                        System.out.println("Iam in else:2");

                        if(conversionData.get("campaign") ==null){
                            c=null;
                        }else{
                            c =  Objects.requireNonNull(conversionData.get("campaign")).toString();
                        }
                        System.out.println("Iam in else:3");
                        if(conversionData.get("media_source") ==null){
                            source=null;
                        }else{
                            source =  Objects.requireNonNull(conversionData.get("media_source")).toString();
                        }


                        link = link_I_will_give+"?did="+did+"&c="+c+"&adset="+adset+"&source="+source;

                        System.out.println(link);

                        Intent intent = new Intent(MainActivity.this, WebViewClass.class);
                        intent.putExtra("link",link);
                        startActivity(intent);
                        finish();

                    }
                } else {

                    link = link_I_will_give+"?did="+did+"&c="+null+"&adset="+null+"&source="+null;

                    System.out.println(link);

                    Intent intent = new Intent(MainActivity.this, WebViewClass.class);
                    intent.putExtra("link",link);
                    startActivity(intent);
                    finish();

                    Log.d(LOG_TAG,"Conversion: This is an organic install.");
                }
            }

            @Override
            public void onConversionDataFail(String errorMessage) {
                Log.d(LOG_TAG, "error getting conversion data: " + errorMessage);
            }

            @Override
            public void onAppOpenAttribution(Map<String, String> attributionData) {

                System.out.println("I am IN");

                if (!attributionData.containsKey("is_first_launch"))
                    Log.d(LOG_TAG, "onAppOpenAttribution: This is NOT deferred deep linking");


                System.out.println("Iam in app:1");

                String adset,c,source;

                if(conversionData.get("adset") ==null){
                    adset =null;
                }else{
                    adset =  Objects.requireNonNull(conversionData.get("adset")).toString();
                }
                System.out.println("Iam in else:2");

                if(conversionData.get("campaign") ==null){
                    c=null;
                }else{
                    c =  Objects.requireNonNull(conversionData.get("campaign")).toString();
                }
                System.out.println("Iam in else:3");
                if(conversionData.get("media_source") ==null){
                    source=null;
                }else{
                    source =  Objects.requireNonNull(conversionData.get("media_source")).toString();
                }

                link = link_I_will_give+"?did="+did+"&c="+c+"&adset="+adset+"&source="+source;

                System.out.println(link);

                Intent intent = new Intent(MainActivity.this, WebViewClass.class);
                intent.putExtra("link",link);
                startActivity(intent);
                finish();

            }

            @Override
            public void onAttributionFailure(String errorMessage) {
                Log.d(LOG_TAG, "error onAttributionFailure : " + errorMessage);
            }
        });

    }


}