package unispark.view;


import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;

import com.example.unispark.R;
import unispark.controller.guicontroller.SplashScreenGuiController;

public class SplashScreenView extends AppCompatActivity {


    SplashScreenGuiController splashScreenGuiController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generic_splash_screen);

        this.splashScreenGuiController = new SplashScreenGuiController(this);
        this.splashScreenGuiController.showLoginView();
    }

}
