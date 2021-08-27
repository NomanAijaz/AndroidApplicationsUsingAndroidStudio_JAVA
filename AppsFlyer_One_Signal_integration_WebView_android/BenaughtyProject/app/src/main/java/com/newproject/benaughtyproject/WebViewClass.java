package com.newproject.benaughtyproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.Manifest;
import android.app.Activity;
import android.app.DownloadManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.os.PersistableBundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.DownloadListener;
import android.webkit.URLUtil;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WebViewClass extends AppCompatActivity {

   private ProgressBar progressBar;
   private WebView mWebView;
   private String url;
   //private SwipeRefreshLayout swipeRefreshLayout;

   private RelativeLayout relativeLayout;
   private Button button;



    private ValueCallback<Uri[]> mFilePathCallback;
    private String mCameraPhotoPath;


    private static final int FILECHOOSER_RESULTCODE = 1;
    private ValueCallback<Uri> mUploadMessage;
    private Uri mCapturedImageURI = null;

    private static final int INPUT_FILE_REQUEST_CODE = 1;


    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);
        File imageFile = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );
        return imageFile;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        getSupportActionBar().hide();

        System.out.println("1");

        mWebView = (WebView) findViewById(R.id.webView);
        progressBar = (ProgressBar) findViewById(R.id.progrssBar);
        relativeLayout = (RelativeLayout) findViewById(R.id.noInternetLayout);
        button = (Button) findViewById(R.id.btnNoConnection);
        //      swipeRefreshLayout =(SwipeRefreshLayout) findViewById(R.id.swipeLayout);

        System.out.println("2");


        Intent intent = getIntent();
        url = intent.getStringExtra("link");


//        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                progressBar.setVisibility(View.GONE);
//                webView.reload();
//            }
//        });
//


        if (savedInstanceState != null) {
            System.out.println("3");

            mWebView.restoreState(savedInstanceState);

        }else{
            System.out.println("4");

            mWebView.setVisibility(View.INVISIBLE);
            mWebView.getSettings().setPluginState(WebSettings.PluginState.OFF);
            mWebView.getSettings().setLoadWithOverviewMode(true);
            mWebView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
            mWebView.getSettings().setUseWideViewPort(true);
            mWebView.getSettings().setUserAgentString("Android Mozilla/5.0 AppleWebKit/534.30 (KHTML, like Gecko) Version/4.0 Mobile Safari/534.30");
            mWebView.getSettings().setAllowFileAccess(true);
            mWebView.getSettings().setAllowFileAccess(true);
            mWebView.getSettings().setDomStorageEnabled(true);
            mWebView.getSettings().setAllowContentAccess(true);
            mWebView.getSettings().supportZoom();
            mWebView.getSettings().setJavaScriptEnabled(true);


            if(checkConnection()){
                System.out.println("5");
                mWebView.loadUrl(url);
            }
        }





        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkConnection()){
                    mWebView.loadUrl(url);
                }
            }
        });

        mWebView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.contains(".pdf")) {

                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setDataAndType(Uri.parse(url), "application/pdf");
                    try {
                        view.getContext().startActivity(intent);
                    } catch (ActivityNotFoundException e) {
                    }
                } else {
                    System.out.println("I in 3");
                    view.loadUrl(url);
                }
                return false; // then it is not handled by default action
            }


            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {

                Log.e("error", description);
            }


            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {        //show progressbar here

                final String Url = url;

                if (Url.contains("mailto:") || Url.contains("sms:") || Url.contains("tel:")) {
                    mWebView.stopLoading();
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(Url));
                    startActivity(intent);
                }

            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressBar.setVisibility(View.GONE);
                mWebView.setVisibility(View.VISIBLE);
            }

        });


        System.out.println("I in 6");

        mWebView.setWebChromeClient(new ChromeClient());

        mWebView = (WebView) findViewById(R.id.webView);

        mWebView.loadUrl(url);//put here your website



};





public class ChromeClient extends WebChromeClient {


    @Override
    public void onProgressChanged(WebView view, int newProgress) {
        progressBar.setVisibility(View.VISIBLE);
        progressBar.setProgress(newProgress);

        if(newProgress ==100){
            progressBar.setVisibility(View.GONE);
        }

        super.onProgressChanged(view, newProgress);
    }


    // For Android 5.0
    public boolean onShowFileChooser(WebView view, ValueCallback<Uri[]> filePath, WebChromeClient.FileChooserParams fileChooserParams) {
        // Double check that we don't have any existing callbacks
        System.out.println("I in 7");

        if (mFilePathCallback != null) {
            mFilePathCallback.onReceiveValue(null);
        }
        mFilePathCallback = filePath;

        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
                takePictureIntent.putExtra("PhotoPath", mCameraPhotoPath);
            } catch (IOException ex) {
                // Error occurred while creating the File
                Log.e("ErrorCreatingFile", "Unable to create Image File", ex);
            }

            // Continue only if the File was successfully created
            if (photoFile != null) {
                mCameraPhotoPath = "file:" + photoFile.getAbsolutePath();
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                        Uri.fromFile(photoFile));
            } else {
                takePictureIntent = null;
            }
        }

        Intent contentSelectionIntent = new Intent(Intent.ACTION_GET_CONTENT);
        contentSelectionIntent.addCategory(Intent.CATEGORY_OPENABLE);
        contentSelectionIntent.setType("image/*");

        Intent[] intentArray;
        if (takePictureIntent != null) {
            intentArray = new Intent[]{takePictureIntent};
        } else {
            intentArray = new Intent[0];
        }

        Intent chooserIntent = new Intent(Intent.ACTION_CHOOSER);
        chooserIntent.putExtra(Intent.EXTRA_INTENT, contentSelectionIntent);
        chooserIntent.putExtra(Intent.EXTRA_TITLE, "Image Chooser");
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, intentArray);

        startActivityForResult(chooserIntent, INPUT_FILE_REQUEST_CODE);

        return true;

    }

    // openFileChooser for Android 3.0+
    public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType) {
        mUploadMessage = uploadMsg;

        try {
            File imageStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "DirectoryNameHere");

            if (!imageStorageDir.exists()) {
                imageStorageDir.mkdirs();
            }

            File file = new File(imageStorageDir + File.separator + "IMG_" + String.valueOf(System.currentTimeMillis()) + ".jpg");

            mCapturedImageURI = Uri.fromFile(file); // save to the private variable

            final Intent captureIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            captureIntent.putExtra(MediaStore.EXTRA_OUTPUT, mCapturedImageURI);
            // captureIntent.putExtra(MediaStore.EXTRA_SCREEN_ORIENTATION, ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

            Intent i = new Intent(Intent.ACTION_GET_CONTENT);
            i.addCategory(Intent.CATEGORY_OPENABLE);
            i.setType("image/*");

            Intent chooserIntent = Intent.createChooser(i, getString(R.string.image_chooser));
            chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Parcelable[]{captureIntent});

            startActivityForResult(chooserIntent, FILECHOOSER_RESULTCODE);
        } catch (Exception e) {
            Toast.makeText(getBaseContext(), "Camera Exception:" + e, Toast.LENGTH_LONG).show();
        }

    }


    // openFileChooser for Android < 3.0
    public void openFileChooser(ValueCallback<Uri> uploadMsg) {
        openFileChooser(uploadMsg, "");
    }

    //openFileChooser for other Android versions
    public void openFileChooser(ValueCallback<Uri> uploadMsg,
                                String acceptType,
                                String capture) {

        openFileChooser(uploadMsg, acceptType);
    }

}

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            if (requestCode != INPUT_FILE_REQUEST_CODE || mFilePathCallback == null) {
                super.onActivityResult(requestCode, resultCode, data);
                return;
            }

            Uri[] results = null;

            // Check that the response is a good one
            if (resultCode == Activity.RESULT_OK) {
                if (data == null) {
                    // If there is not data, then we may have taken a photo
                    if (mCameraPhotoPath != null) {
                        results = new Uri[]{Uri.parse(mCameraPhotoPath)};
                    }
                } else {
                    String dataString = data.getDataString();
                    if (dataString != null) {
                        results = new Uri[]{Uri.parse(dataString)};
                    }
                }
            }

            mFilePathCallback.onReceiveValue(results);
            mFilePathCallback = null;

        } else if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.KITKAT) {
            if (requestCode != FILECHOOSER_RESULTCODE || mUploadMessage == null) {
                super.onActivityResult(requestCode, resultCode, data);
                return;
            }

            if (requestCode == FILECHOOSER_RESULTCODE) {

                if (null == this.mUploadMessage) {
                    return;

                }

                Uri result = null;

                try {
                    if (resultCode != RESULT_OK) {

                        result = null;

                    } else {

                        // retrieve from the private variable if the intent is null
                        result = data == null ? mCapturedImageURI : data.getData();
                    }
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "activity :" + e,
                            Toast.LENGTH_LONG).show();
                }

                mUploadMessage.onReceiveValue(result);
                mUploadMessage = null;

            }
        }

        return;
    }





    @Override
    public void onBackPressed() {
        if(mWebView.canGoBack()){
            mWebView.goBack();
            progressBar.setVisibility(View.INVISIBLE);
        }else{
            super.onBackPressed();
        }

    }



    public  Boolean checkConnection(){
        System.out.println("9");
        ConnectivityManager connectivityManager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo network = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        if(wifi.isConnected()){
            System.out.println("10");
            mWebView.setVisibility(View.VISIBLE);
            relativeLayout.setVisibility(View.GONE);
            return true;

        }else if(network.isConnected()){
            System.out.println("11");
            mWebView.setVisibility(View.VISIBLE);
            relativeLayout.setVisibility(View.GONE);
            return true;

        }else{
            System.out.println("12");
            mWebView.setVisibility(View.GONE);
            relativeLayout.setVisibility(View.VISIBLE);
        }
        return false;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        mWebView.saveState(outState);
    }

}


