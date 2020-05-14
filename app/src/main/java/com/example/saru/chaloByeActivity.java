package com.example.saru;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.saru.Model.Products;
import com.example.saru.ViewHolder.ProductViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class chaloByeActivity extends AppCompatActivity
{
    private Button backButon;
    private TextView uths;
    private RecyclerView categoryVieww;
    private String cateName="";
    private String varsha="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chalo_bye);
        uths=(TextView) findViewById(R.id.uthara);
        backButon = (Button) findViewById(R.id.back_btn);
        categoryVieww = findViewById(R.id.categories_search_list);
        categoryVieww.setLayoutManager(new LinearLayoutManager(chaloByeActivity.this));
        cateName=getIntent().getStringExtra("category");

        uths.setText(cateName);
        backButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(chaloByeActivity.this,MenumainActivity.class);
                startActivity(intent);
                chaloByeActivity.this.finish();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        DatabaseReference reference= FirebaseDatabase.getInstance().getReference().child("Products");

        FirebaseRecyclerOptions<Products>options=
                new FirebaseRecyclerOptions.Builder<Products>()
                        .setQuery(reference.orderByChild("category").equalTo(cateName),Products.class)
                        .build();
        FirebaseRecyclerAdapter<Products, ProductViewHolder>adapter=new FirebaseRecyclerAdapter<Products, ProductViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull ProductViewHolder holder, int i, @NonNull final Products products)
            {
                holder.txtProductName.setText(products.getName());
                holder.txtProductDescription.setText(products.getDescription());
                holder.txtProductPrice.setText("Price=" + products.getPrice() + "$");
                Picasso.get().load(products.getImage()).into(holder.imageView);
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(chaloByeActivity.this,ProductDetailsActivity.class);
                        intent.putExtra("pid",products.getPid());
                        startActivity(intent);
                        chaloByeActivity.this.finish();
                    }
                });
            }

            @NonNull
            @Override
            public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_items_layout, parent, false);
                ProductViewHolder holder = new ProductViewHolder(view);
                return holder;
            }
        };
        categoryVieww.setAdapter(adapter);
        adapter.startListening();
    }
}




