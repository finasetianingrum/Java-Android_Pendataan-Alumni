package pnj.remedial.finasetianingrum;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import pnj.remedial.finasetianingrum.fragment.FragmentBerita;
import pnj.remedial.finasetianingrum.fragment.FragmentHome;
import pnj.remedial.finasetianingrum.fragment.FragmentProfile;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomMenu;
    FragmentHome fragmentHome = new FragmentHome();
    FragmentBerita fragmentBerita = new FragmentBerita();
    FragmentProfile fragmentProfile = new FragmentProfile();
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomMenu = findViewById(R.id.BottomMenu);
        sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.containerHost, fragmentHome);
        fragmentTransaction.commit();

        bottomMenu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentTransaction fragmentTransaction2 = getSupportFragmentManager().beginTransaction();
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        fragmentTransaction2.replace(R.id.containerHost, fragmentHome);
                        break;

                    case R.id.navigation_dashboard:
                        fragmentTransaction2.replace(R.id.containerHost, fragmentBerita);
                        break;

                    case R.id.navigation_notifications:
                        fragmentTransaction2.replace(R.id.containerHost, fragmentProfile);
                        break;
                }
                fragmentTransaction2.commit();
                return true;
            }

        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.actionTambah) {
            Intent intent = new Intent( this, TambahDataActivity.class);
            startActivity(intent);

        }else if(item.getItemId() == R.id.actionList) {
            Intent intent = new Intent( this, ListDataAlumni.class);
            startActivity(intent);

        }else {

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.commit();

            Intent intent = new Intent( this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
        return true;
    }
}