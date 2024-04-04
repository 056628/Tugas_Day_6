package com.example.custom_listview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.example.custom_listview.databinding.ActivityMainBinding;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    private String[] name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        int[] imageIds;
        imageIds = new int[]{R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d, R.drawable.e,
                R.drawable.f, R.drawable.g, R.drawable.h, R.drawable.i};

        String[] name = {"Alienware", "Asus ROG", "MSI", "Razer Blade", "Gigabyte Aero", "Acer Predator", "Lenovo Legion", "HP Omen", "Dell G Series"};

        // Jumlah stok dengan format "Jumlah stok (n)"
        Random random = new Random();
        String[] lastMessage = new String[name.length];
        for (int i = 0; i < name.length; i++) {
            int stock = random.nextInt(10) + 1;
            lastMessage[i] = "Jumlah stok (" + stock + ")";
        }

        // Detail Item
        String[] lastmsgTime = {"Detail Item", "Detail Item", "Detail Item", "Detail Item", "Detail Item", "Detail Item", "Detail Item", "Detail Item", "Detail Item"};

        // Harga laptop gaming dalam mata uang Rupiah (Rp)
        String[] phoneNo = new String[name.length];
        for (int i = 0; i < name.length; i++) {
            int price = random.nextInt(11000000) + 10000000; // Harga antara 10 juta hingga 20 juta
            NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
            phoneNo[i] = formatRupiah.format(price);
        }

        // Lokasi barang laptopnya (Pekanbaru, Indonesia)
        String[] country = {"Pekanbaru, Indonesia", "Pekanbaru, Indonesia", "Pekanbaru, Indonesia",
                "Pekanbaru, Indonesia", "Pekanbaru, Indonesia", "Pekanbaru, Indonesia", "Pekanbaru, Indonesia", "Pekanbaru, Indonesia", "Pekanbaru, Indonesia"};

        // Memasukkan data ke dalam ArrayList<User>
        ArrayList<User> userArrayList = new ArrayList<>();
        for (int i = 0; i < name.length; i++) {
            User user = new User(name[i], lastMessage[i], lastmsgTime[i], phoneNo[i], country[i], imageIds[i]);
            userArrayList.add(user);
        }

        // Menggunakan ListAdapter untuk menampilkan data
        ListAdapter listAdapter = new ListAdapter(MainActivity.this, userArrayList);
        binding.listview.setAdapter(listAdapter);

        // Mengatur onClickListener untuk item di ListView
        binding.listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Mengirim data ke UserActivity saat item di ListView diklik
                Intent i = new Intent(MainActivity.this, UserActivity.class);
                i.putExtra("Nama Barang", name[position]);
                i.putExtra("Harga Barang", phoneNo[position]);
                i.putExtra("Lokasi", country[position]);
                i.putExtra("imageIds", imageIds[position]);
                startActivity(i);
            }
        });
    }
}
