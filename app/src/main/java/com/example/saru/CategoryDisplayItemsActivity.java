package com.example.saru;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class CategoryDisplayItemsActivity extends AppCompatActivity
{
    private ImageView beds2,dinings2;
    private ImageView cupboards2,sofas2;
    private ImageView doors2,lamps2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_display_items);

        beds2=(ImageView)findViewById(R.id.bed1);
        dinings2=(ImageView)findViewById(R.id.dining1);
        cupboards2=(ImageView)findViewById(R.id.cupboard1);
        sofas2=(ImageView)findViewById(R.id.sofa1);
        doors2=(ImageView)findViewById(R.id.door1);
        lamps2=(ImageView)findViewById(R.id.lamp1);
        beds2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(CategoryDisplayItemsActivity.this,chaloByeActivity.class);
                intent.putExtra("category","beds");
                startActivity(intent);
            }
        });
        dinings2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(CategoryDisplayItemsActivity.this,chaloByeActivity.class);
                intent.putExtra("category","dinings");
                startActivity(intent);
            }
        });
        cupboards2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(CategoryDisplayItemsActivity.this,chaloByeActivity.class);
                intent.putExtra("category","cupboards");
                startActivity(intent);
            }
        });

        sofas2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(CategoryDisplayItemsActivity.this,chaloByeActivity.class);
                intent.putExtra("category","sofas");
                startActivity(intent);
            }
        });

        lamps2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(CategoryDisplayItemsActivity.this,chaloByeActivity.class);
                intent.putExtra("category","lamps");
                startActivity(intent);
            }
        });

        doors2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(CategoryDisplayItemsActivity.this,chaloByeActivity.class);
                intent.putExtra("category","doors");
                startActivity(intent);
            }
        });
    }

}
