package WildfireAnalysisAndPredictionSystem.test2;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;

public class PopUsername extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.username);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.8),(int)(height*.3));
    }
}
