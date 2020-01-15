package pro.butovanton.sigal;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

public class camera extends FragmentActivity {

    private  final int MY_REQUEST_CODE_FOR_CAMERA =  110;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_REQUEST_CODE_FOR_CAMERA);
            }
        }
    }

    @Override

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        if (requestCode == MY_REQUEST_CODE_FOR_CAMERA) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
               // startCameraActivity(); // запускаем активность с камерой (ну или фрагмент)
            } else {
                Toast toast = Toast.makeText(getApplicationContext(), R.string.NoPermAver, Toast.LENGTH_SHORT);
                toast.show();
                finish();
            }
        }
    }



}
