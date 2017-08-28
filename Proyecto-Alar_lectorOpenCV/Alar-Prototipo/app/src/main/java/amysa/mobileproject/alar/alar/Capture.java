package amysa.mobileproject.alar.alar;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.media.Image;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.JavaCameraView;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.android.Utils;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;

import static org.opencv.highgui.Highgui.imread;
import static org.opencv.highgui.Highgui.imwrite;

public class Capture extends Activity implements CameraBridgeViewBase.CvCameraViewListener2, View.OnTouchListener {
    TextView testView;
    String name;
    private static String TAG = "Capture";
    ArrayList<data> studentsAnswers = new ArrayList<data>();
    CameraBridgeViewBase javaCameraView;
    //JavaCameraView javaCameraView;
    Mat mRgba, mGray;

    static {
        System.loadLibrary("MyOpencvLibs");
    }

    BaseLoaderCallback mLoaderCallback = new BaseLoaderCallback() {
        @Override
        public void onManagerConnected(int status) {
            switch (status) {
                case BaseLoaderCallback.SUCCESS:
                    javaCameraView.enableView();
                    break;
                default:
                    super.onManagerConnected(status);
                    break;
            }

        }
    };
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.capture_activity);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        javaCameraView = (JavaCameraView) findViewById(R.id.java_camera_view);
        javaCameraView.disableView();
        javaCameraView.setMaxFrameSize(5000, 5000);
        javaCameraView.findFocus();
        javaCameraView.setFocusable(true);
        javaCameraView.enableView();

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        javaCameraView.setVisibility(View.VISIBLE);
        javaCameraView.setCvCameraViewListener(this);


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (javaCameraView != null) {
            javaCameraView.disableView();
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        if (javaCameraView != null) {
            javaCameraView.disableView();
        }
    }

    protected void onResume() {
        super.onResume();
        if (OpenCVLoader.initDebug()) {
            Log.i(TAG, "OpenCv loaded successfully");
            mLoaderCallback.onManagerConnected(LoaderCallbackInterface.SUCCESS);
        } else {
            Log.i(TAG, "OpenCv  not loaded ");
            OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_2_4_11, this, mLoaderCallback);
        }
    }

    @Override
    public void onCameraViewStarted(int width, int height) {
        mRgba = new Mat(height, width, CvType.CV_8UC4);
        mGray = new Mat(height, width, CvType.CV_8UC1);

    }

    @Override
    public void onCameraViewStopped() {
        mRgba.release();
    }

    @Override
    public Mat onCameraFrame(CameraBridgeViewBase.CvCameraViewFrame inputFrame) {
        mRgba = inputFrame.rgba();
        OpenCVNativeClass.convertGray(mRgba.getNativeObjAddr(), mGray.getNativeObjAddr());
        return mRgba;
    }


    public void getAnswers() {
        // EditText editText = (EditText) findViewById(R.id.editText);
        try {

            System.loadLibrary("MyOpencvLibs");

            //Mat img = imread(name);
            Mat test = Utils.loadResource(Capture.this, R.drawable.examen, 1);
            Mat A = Utils.loadResource(Capture.this, R.drawable.a, 1);
            Mat B = Utils.loadResource(Capture.this, R.drawable.b, 1);
            Mat C = Utils.loadResource(Capture.this, R.drawable.c, 1);
            Mat D = Utils.loadResource(Capture.this, R.drawable.d, 1);
            Mat E = Utils.loadResource(Capture.this, R.drawable.e, 1);


            int totalQuestion = OpenCVNativeClass.loadImages(test.getNativeObjAddr(), A.getNativeObjAddr(), B.getNativeObjAddr(),
                    C.getNativeObjAddr(), D.getNativeObjAddr(), E.getNativeObjAddr());
/*
            data answers = new data(editText.getText().toString());

            for (int i = 0; i < totalQuestion; i++) {
                int result = OpenCVNativeClass.getAnswer(i);
                answers.addAnswer(result);
            }

            Log.i(TAG, answers.getStudentName());
            for (int i = 0; i < totalQuestion; i++) {
                Log.i(TAG, "Answer " + (i + 1) + answers.getOneAnswer(i));
            }

            studentsAnswers.add(answers);
    */
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void captureImage() {

        File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        Toast.makeText(getApplicationContext(), path.toString(), Toast.LENGTH_LONG).show();
        int time = (int) (System.currentTimeMillis());
        Timestamp tsTemp = new Timestamp(time);
        String filename = tsTemp.toString() + ".png";
        File file = new File(path, filename);

        Boolean bool = null;
        filename = file.toString();
        bool = Highgui.imwrite(filename, mRgba);

        if (bool == true)
            Log.d(TAG, "SUCCESS writing image to external storage");
        else
            Log.d(TAG, "Fail writing image to external storage");
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        return false;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_VOLUME_DOWN)) {
            captureImage();
            ArrayList<data> test = new ArrayList<>();
            test.add(new data("Nombre"));
            ((MyApplication) this.getApplication()).setStudents(test);
        }
        return true;
    }


    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Capture Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}



