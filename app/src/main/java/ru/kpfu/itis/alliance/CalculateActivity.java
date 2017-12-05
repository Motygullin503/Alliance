package ru.kpfu.itis.alliance;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CalculateActivity extends AppCompatActivity {
    public static final String DESIGNER ="DESIGNER";
    public static final String EXECUTOR ="EXECUTOR";
    public static final String CUSTOMER ="CUSTOMER";
    public static final String PRIVATEPERSON ="PRIVATEPERSON";
    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9#_~!$&'()*+,;=:.\"(),:;<>@\\[\\]\\\\]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$";

    private Pattern pattern = Pattern.compile(EMAIL_PATTERN);

    private Activity context = this;
    private TextView toolbarTitle;
    private EditText nameCompany;
    private TextView whoAre;
    private TextView typeCladding;
    private EditText perimetrWall;
    private EditText buildingHeight;
    private EditText squareWindow;
    private EditText quantityWindow;
    private EditText squareDoor;
    private EditText quantityDoor;
    private EditText etEmail;
    private EditText etNumberOfPhone;
    private Button btnCalculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculate_activity);

        nameCompany = findViewById(R.id.et_name);
        whoAre = findViewById(R.id.tv_who_are);
        typeCladding = findViewById(R.id.type_of_cladding);
        perimetrWall = findViewById(R.id.et_perimetr);
        buildingHeight= findViewById(R.id.et_building_height);
        squareWindow= findViewById(R.id.et_square_window);
        quantityWindow= findViewById(R.id.et_quantity_window);
        squareDoor= findViewById(R.id.et_square_door);
        quantityDoor= findViewById(R.id.et_quantity_door);
        etNumberOfPhone= findViewById(R.id.et_number_of_phone);
        etEmail= findViewById(R.id.et_email);
        btnCalculate = findViewById(R.id.btn_calculate_price);

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        toolbarTitle = myToolbar.findViewById(R.id.toolbar_title);

        toolbarTitle.setText("Рассчет");

        whoAre.setText("Заказчик");
        typeCladding.setText("Композит");

        whoAre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        typeCladding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validateEmail(etEmail.getText().toString())){
                    Toast.makeText(context, "Проверьте правильность email", Toast.LENGTH_SHORT).show();
                } else {

                }
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
        return true;
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(context, MainActivity.class);
        startActivity(intent);
        finish();

    }

    public boolean validateEmail(String email) {
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

}