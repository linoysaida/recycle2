package com.example.myapplication;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.mlkit.vision.barcode.Barcode;
import com.google.mlkit.vision.barcode.BarcodeScanner;
import com.google.mlkit.vision.barcode.BarcodeScannerOptions;
import com.google.mlkit.vision.barcode.BarcodeScanning;
import com.google.mlkit.vision.common.InputImage;

import java.util.List;

public class ScanFunction extends AppCompatActivity {
    private MaterialButton cameraBtn;
    private MaterialButton gallery;
    private ImageView image;
    private MaterialButton scan;
    private TextView result;


    private static final int CAMERA_REQUEST_CODE=100;
    private static final int STORAGE_REQUEST_CODE=101;

    private String [] cameraPermissions;
    private String [] storagePermissions;

    private Uri imageUri=null;


    private BarcodeScannerOptions barcodeScannerOptions;
    private BarcodeScanner barcodeScanner;



    private static final String TAG="MAIN_TAG";
    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_function);


        cameraBtn= findViewById(R.id.cameraBtn);
        gallery= findViewById(R.id.gallery);

        scan= findViewById(R.id.scan);
        result= findViewById(R.id.zxing_possible_result_points);

        cameraPermissions=new String[]{Manifest.permission.CAMERA,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE};

        storagePermissions=new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};




        barcodeScannerOptions=new BarcodeScannerOptions.Builder().setBarcodeFormats(Barcode.FORMAT_ALL_FORMATS).build();
        barcodeScanner= BarcodeScanning.getClient(barcodeScannerOptions);




        cameraBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(checkCameraPermission()){
                    pickImageCamera();
                }
                else {
                    requestCameraPermission();
                }
            }
        });

        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(checkStoragePermission()){
                    pickImageGallery();
                }
                else {
                    requestStoragePermission();
                }
            }
        });

        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(imageUri==null){
                    Toast.makeText(ScanFunction.this, "pck image first", Toast.LENGTH_SHORT).show();
                }
                else {
                    detectResultFormatImage();
                }
            }
        });

    }

    private void  detectResultFormatImage(){
        try{
            InputImage inputImage=InputImage.fromFilePath(this, imageUri)   ;

            Task<List<Barcode>> barcodeResult=barcodeScanner.process(inputImage)
                    .addOnSuccessListener(new OnSuccessListener<List<Barcode>>() {
                        @Override
                        public void onSuccess(List<Barcode> barcodes) {
                            extractBarCodeQRCodeInfo(barcodes);
                        }
                         }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(ScanFunction.this, "Failed scanning due to"+e.getMessage(), Toast.LENGTH_SHORT).show();

                        }
        });
        }
        
        catch (Exception e){
            Toast.makeText(this, "Failed scanning due to"+e.getMessage(), Toast.LENGTH_SHORT).show();

        }
    }


    @SuppressLint("SetTextI18n")
    public void extractBarCodeQRCodeInfo(List<Barcode> barcodes) {
        for (Barcode barcode : barcodes) {
            Rect bounds = barcode.getBoundingBox();
            Point[] corners = barcode.getCornerPoints();


            String rawValue = barcode.getRawValue();
            Log.d(TAG, "extractBarCodeQRCodeInfo: rawValue: " + rawValue);


            int valueType = barcode.getValueType();

            switch (valueType) {
                case Barcode.TYPE_WIFI: {
                    Barcode.WiFi typeWifi = barcode.getWifi();

                    String ssid = "" + typeWifi.getSsid();
                    String password = " " + typeWifi.getPassword();
                    String encryptionType = " " + typeWifi.getEncryptionType();

                    Log.d(TAG, "extractBarCodeQRCodeInfo: ssid: " + ssid);
                    Log.d(TAG, "extractBarCodeQRCodeInfo: password: " + password);
                    Log.d(TAG, "extractBarCodeQRCodeInfo: encryptionType: " + encryptionType);

                    result.setText("TYPE: TYPE_WIFI \nssid: " + ssid + "\npassword: " + password + "\nencryptionType" + encryptionType + "\nraw value: " + rawValue);
                }
                break;
                case Barcode.TYPE_URL: {
                    Barcode.UrlBookmark typeUrl = barcode.getUrl();

                    String title = " " + typeUrl.getTitle();
                    String url = " " + typeUrl.getUrl();

                    Log.d(TAG, "extractBarCodeQRCodeInfo: type Url");
                    Log.d(TAG, "extractBarCodeQRCodeInfo: title: " + title);
                    Log.d(TAG, "extractBarCodeQRCodeInfo: url: " + url);

                    result.setText("TYPE: TYPE_Url \ntitle: " + title + "\nurl: " + url + "\nraw value: " + rawValue);
                }
                break;
                default:{
                    result.setText("raw value: " + rawValue);

                }
            }

        }
    }





    private void pickImageGallery(){
        Intent intent=new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        galleryActivityResultLauncher.launch(intent);
    }

    private final ActivityResultLauncher<Intent> galleryActivityResultLauncher=
            registerForActivityResult( new ActivityResultContracts.StartActivityForResult(),
                    new ActivityResultCallback <ActivityResult>(){
                     @Override
                        public void onActivityResult(ActivityResult result){

                         if (result.getResultCode()==Activity.RESULT_OK){
                             Intent data=result.getData();
                             imageUri=data.getData();
                             image.setImageURI(imageUri);
                         }

                         else{
                             Toast.makeText(ScanFunction.this, "Cancelled", Toast.LENGTH_SHORT).show();
                         }
                        }
                 });
    private void pickImageCamera(){
        ContentValues contentValues= new ContentValues();
        contentValues.put( MediaStore.Images.Media.TITLE,"Sample Title");
        contentValues.put( MediaStore.Images.Media.DESCRIPTION,"Sample Title Description");
        imageUri=getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,contentValues);

        Intent intent= new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
        cameraActivityResultLauncher.launch(intent);

    }

    private final ActivityResultLauncher<Intent> cameraActivityResultLauncher=
            registerForActivityResult( new ActivityResultContracts.StartActivityForResult(),
                    new ActivityResultCallback <ActivityResult>(){
                        @Override
                        public void onActivityResult(ActivityResult result){

                            if (result.getResultCode()==Activity.RESULT_OK){
                                Intent data=result.getData();
                                Log.d(TAG, "onActivityResult:imageUri:"+imageUri);
                                image.setImageURI(imageUri);
                            }

                            else{
                                Toast.makeText(ScanFunction.this, "Cancelled", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

    private  boolean checkStoragePermission(){
        boolean result= ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED;

        return result;
    }

    private void  requestStoragePermission(){
        ActivityCompat.requestPermissions(this, storagePermissions,STORAGE_REQUEST_CODE);

    }


    private  boolean checkCameraPermission(){
        boolean resultCamera= ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED;

        boolean resultStorage= ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED;

        return resultCamera && resultStorage;
    }

    private void  requestCameraPermission(){
        ActivityCompat.requestPermissions(this, storagePermissions,CAMERA_REQUEST_CODE);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permission,@NonNull int[] grantResult){
        super.onRequestPermissionsResult(requestCode,permission,grantResult);

        switch (requestCode) {
            case CAMERA_REQUEST_CODE: {
                if(grantResult.length>0){
                    boolean cameraAccepted=grantResult[0]==PackageManager.PERMISSION_GRANTED;
                    boolean storageAccepted=grantResult[1]==PackageManager.PERMISSION_GRANTED;

                    if(cameraAccepted && storageAccepted ){
                            pickImageCamera();
                    }
                    else{
                        Toast.makeText(this, "camera and storage permission are required", Toast.LENGTH_SHORT).show();
                    }
                }

            }
            break;
            case STORAGE_REQUEST_CODE: {
                if(grantResult.length>0){
                    boolean storageAccepted=grantResult[0]==PackageManager.PERMISSION_GRANTED;

                    if( storageAccepted ){
                        pickImageGallery();
                    }
                    else{
                        Toast.makeText(this, "storage permission are required", Toast.LENGTH_SHORT).show();
                    }

                }

            }
            break;
        }
    }

    private boolean onNavigationItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_home) { // החלף ב-ID המדויק של פריט "ראשי" שלך
            // טען מחדש את HomePage או נווט אליו אם אתה נמצא בפעילות אחרת
            Intent intent = new Intent(ScanFunction.this, HomePage.class);
            startActivity(intent);
            return true;
            // אפשר להוסיף כאן מקרים נוספים לפריטים אחרים בתפריט
        }

        if (item.getItemId() == R.id.action_logout) { // החלף ב-ID המדויק של פריט "ראשי" שלך
            // טען מחדש את HomePage או נווט אליו אם אתה נמצא בפעילות אחרת
            Intent intent = new Intent(ScanFunction.this, LogIn.class);
            startActivity(intent);
            return true;
            // אפשר להוסיף כאן מקרים נוספים לפריטים אחרים בתפריט
        }
        return false;
    }


}
