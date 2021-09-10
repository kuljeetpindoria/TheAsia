package com.example.theasia;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;


public class CountryInfo extends AppCompatActivity {

    String name, region, subRegion, capital, population, image ;
    TextView countryName, countryCapital, countryRegion, countrySubRegion,
            countryPopulation;
    ImageView countryFlag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_info);
        name = getIntent().getStringExtra("name");
        capital = getIntent().getStringExtra("capital");
        region = getIntent().getStringExtra("region");
        subRegion = getIntent().getStringExtra("subregion");
        population = getIntent().getStringExtra("population");
        image = getIntent().getStringExtra("flag");

        countryName = findViewById(R.id.country_name);
        countryCapital = findViewById(R.id.country_capital);
        countryRegion = findViewById(R.id.country_region);
        countrySubRegion = findViewById(R.id.country_subregion);
        countryPopulation = findViewById(R.id.country_population);
        countryFlag = findViewById(R.id.country_flag);

        Glide.with(this).load(image).into(countryFlag);
        countryName.setText(name);
        countryCapital.setText(capital);
        countryRegion.setText(region);
        countrySubRegion.setText(subRegion);
        countryPopulation.setText(population);


    }
}