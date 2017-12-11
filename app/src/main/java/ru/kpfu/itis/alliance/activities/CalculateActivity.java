package ru.kpfu.itis.alliance.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.kpfu.itis.alliance.Constants;
import ru.kpfu.itis.alliance.R;
import ru.kpfu.itis.alliance.ResourceCalculator;
import ru.kpfu.itis.alliance.allianceAPI.Api;
import ru.kpfu.itis.alliance.fragments.ResultFragment;
import ru.kpfu.itis.alliance.models.CalculationResult;
import ru.kpfu.itis.alliance.models.ResponseError;
import ru.kpfu.itis.alliance.models.ResponseSuccess;

public class CalculateActivity extends AppCompatActivity {
    public static final int CUSTOMER = 1;
    public static final int DESIGNER = 2;
    public static final int EXECUTOR = 3;
    public static final int PRIVATEPERSON = 4;
    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9#_~!$&'()*+,;=:.\"(),:;<>@\\[\\]\\\\]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$";
    public static final String PHONE_PATTERN = "^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$";
    private final int REQUEST_CODE_WHO_ARE = 10;
    private final int REQUEST_CODE_TYPE_OF_SYSTEM = 20;

    private Pattern pattern = Pattern.compile(EMAIL_PATTERN);
    private Pattern patternPhone = Pattern.compile(PHONE_PATTERN);

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
    private Api api;
    private ResourceCalculator calculator;

    private int typeCladdingValue = Constants.COMPOSITE;
    private int whoAreYou = CalculateActivity.CUSTOMER;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculate_activity);

        nameCompany = findViewById(R.id.et_name);
        whoAre = findViewById(R.id.tv_who_are);
        typeCladding = findViewById(R.id.type_of_cladding);
        perimetrWall = findViewById(R.id.et_perimetr);
        buildingHeight = findViewById(R.id.et_building_height);
        squareWindow = findViewById(R.id.et_square_window);
        quantityWindow = findViewById(R.id.et_quantity_window);
        squareDoor = findViewById(R.id.et_square_door);
        quantityDoor = findViewById(R.id.et_quantity_door);
        etNumberOfPhone = findViewById(R.id.et_number_of_phone);
        etEmail = findViewById(R.id.et_email);
        btnCalculate = findViewById(R.id.btn_calculate_price);

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        toolbarTitle = myToolbar.findViewById(R.id.toolbar_title);
        toolbarTitle.setText("Рассчет");

        whoAre.setText("Заказчик");

        setTypeCladding();
        setWhoAre();


        whoAre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, WhoAreActivity.class);
                intent.putExtra("who_are", DESIGNER);
                startActivityForResult(intent, REQUEST_CODE_WHO_ARE);
            }
        });

        typeCladding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, TypeOfSystemActivity.class);
                intent.putExtra("type", Constants.COMPOSITE);
                startActivityForResult(intent, REQUEST_CODE_TYPE_OF_SYSTEM);
            }
        });


        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nameCompany.length() == 0 || perimetrWall.length() == 0 || buildingHeight.length()==0||squareWindow.length()==0
                        ||quantityWindow.length()==0||squareDoor.length()==0||quantityDoor.length()==0) {
                    Toast.makeText(context, "Заполните все поля", Toast.LENGTH_LONG).show();
                } else if (!validatePhone(etNumberOfPhone.getText().toString())) {
                    Toast.makeText(context, "Введите корректный номер телефона", Toast.LENGTH_LONG).show();
                } else if (!validateEmail(etEmail.getText().toString())) {
                    Toast.makeText(context, "Проверьте правильность email", Toast.LENGTH_SHORT).show();
                } else {
//                    Intent intent = new Intent(context, ResultAcitivity.class);
                    api = Api.getInstance();
                    calculator = new ResourceCalculator(
                            Double.valueOf(perimetrWall.getText().toString()),
                            Double.valueOf(buildingHeight.getText().toString()),
                            Double.valueOf(squareWindow.getText().toString()),
                            Integer.valueOf(quantityWindow.getText().toString()),
                            Double.valueOf(squareDoor.getText().toString()),
                            Integer.valueOf(quantityDoor.getText().toString()),
                            whoAreYou);
                    try {
                        List<CalculationResult> list = calculator.getCalculationResults();
                        ResultFragment resultFragment = ResultFragment.newInstance(list, typeCladdingValue);
                        getSupportFragmentManager().beginTransaction().add(R.id.fragment_result, resultFragment, ResultFragment.class.toString()).commit();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Call<Object> call = api.getApi().sendData(etEmail.getText().toString(),
                            etNumberOfPhone.getText().toString(),
                            whoAre.getText().toString(),
                            "android",
                            calculator.getTotalSum(),
                            true);

                    call.enqueue(new Callback<Object>() {
                        @Override
                        public void onResponse(Call<Object> call, Response<Object> response) {
                            if (response.body() instanceof ResponseSuccess) {

                            } else if (response.body() instanceof ResponseError) {
                                for (Object error : ((ResponseError) response.body()).getErrors()) {
                                    Toast.makeText(CalculateActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<Object> call, Throwable t) {
                            Toast.makeText(CalculateActivity.this, "Что-то пошло не так, повторите позднее", Toast.LENGTH_SHORT).show();
                        }
                    });
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

    public boolean validatePhone(String phone) {
        Matcher matcher = patternPhone.matcher(phone);
        return matcher.matches();
    }

    public void setTypeCladding() {
        switch (typeCladdingValue) {
            case Constants.COMPOSITE:
                typeCladding.setText(R.string.composit);
                break;
            case Constants.KERAMOGRANITE:
                typeCladding.setText(R.string.keramogranit);
                break;
            case Constants.FIBROPLITA:
                typeCladding.setText(R.string.fibroplita);
                break;
            case Constants.METALLOKASSETA:
                typeCladding.setText(R.string.metallokaseta);
        }
    }

    public void setWhoAre() {
        switch (whoAreYou) {
            case CUSTOMER:
                whoAre.setText(R.string.customer);
                break;
            case EXECUTOR:
                whoAre.setText(R.string.executor);
                break;
            case PRIVATEPERSON:
                whoAre.setText(R.string.private_person);
                break;
            case DESIGNER:
                whoAre.setText(R.string.designer);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_CODE_TYPE_OF_SYSTEM:
                    typeCladdingValue = data.getIntExtra("type", Constants.COMPOSITE);
                    setTypeCladding();
                    break;
                case REQUEST_CODE_WHO_ARE:
                    whoAreYou = data.getIntExtra("who_are", CUSTOMER);
                    setWhoAre();
                    break;
            }
        }
    }
}
