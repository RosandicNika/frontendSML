package hr.fer.ruazosa.sharemylocation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
//u ovoj aktivnosti trebam iz baze uzimat postojece grupe svog usera i
//prikazat ih u list viewu-adapter

public class HomepageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(hr.fer.ruazosa.sharemylocation.R.layout.activity_homepage);

        Intent intent = getIntent();
    }

    public void createGroupAct(View v){
        Intent intent = new Intent(HomepageActivity.this, CreateGroupActivity.class);

        startActivity(intent);
    }

}
