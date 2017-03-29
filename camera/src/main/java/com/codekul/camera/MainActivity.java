package com.codekul.camera;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import id.zelory.compressor.Compressor;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private static final int REQ_CAPTURE = 4589;
    private static final int REQ_VIDEO = 8956;
    private File file;
    private Uri fileUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void capture(View view) {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "" + System.currentTimeMillis() + ".jpg");
            fileUri = Uri.fromFile(file);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
            startActivityForResult(intent, REQ_CAPTURE);
        } else {
            Toast.makeText(this, "Media Failed", Toast.LENGTH_SHORT).show();
        }
    }

    public void video(View view) {
        file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "my.mp4");

        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        fileUri = Uri.fromFile(file);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
        startActivityForResult(intent, REQ_VIDEO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQ_CAPTURE) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, "Image Captured Successfully", Toast.LENGTH_SHORT).show();

                Compressor.getDefault(this)
                        .compressToFileAsObservable(file)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnNext(new Action1<File>() {
                            @Override
                            public void call(File file) {
                                try {
                                    FileInputStream fis = new FileInputStream(file);
                                    byte[] imageBytes = new byte[(int) file.length()];
                                    fis.read(imageBytes);
                                    fis.close();

                                    File fileOut = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), file.getName() + ".jpg");
                                    FileOutputStream fos = new FileOutputStream(fileOut);
                                    fos.write(imageBytes);
                                    fos.close();

                                    //((ImageView) findViewById(R.id.imageCaptured)).setImageURI(Uri.fromFile(file));
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        })
                        .subscribe();
            }
        }
        if(requestCode == REQ_VIDEO){
            if(resultCode == RESULT_OK) {
                ((VideoView)findViewById(R.id.imageCaptured)).setVideoURI(fileUri);
                ((VideoView)findViewById(R.id.imageCaptured)).start();
            }
        }
    }
}
