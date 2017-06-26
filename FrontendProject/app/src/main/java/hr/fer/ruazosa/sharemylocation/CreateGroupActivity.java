package hr.fer.ruazosa.sharemylocation;

/**
 * Created by Nika on 6/25/2017.
 */

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static hr.fer.ruazosa.sharemylocation.MyFCM.finalToken;

public class CreateGroupActivity extends AppCompatActivity {
    String userID;
    Button btnCreateGroup;
    EditText editGroupName;
    EditText editAdminName;
    private int REQUEST_CAMERA = 0, SELECT_FILE = 1;
    private ImageView userIcon;
    private TextView userIconLabel;
    private String userChoosenTask;
    private String iconString; //tu dodamo odma default string koji će bit ak on niš ne stavi


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_group);

        Intent intent = getIntent();
        userID=intent.getStringExtra("userID");


        btnCreateGroup = (Button) findViewById(R.id.btnCreateGroup);
        editGroupName = (EditText) findViewById(R.id.editGroupName);
        editAdminName=(EditText) findViewById(R.id.editAdminName) ;



        userIcon = (ImageView) findViewById(hr.fer.ruazosa.sharemylocation.R.id.photoadd);
        userIcon.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                addPhoto();
            }
        });
        userIconLabel = (TextView) findViewById(hr.fer.ruazosa.sharemylocation.R.id.iconLabel);

        btnCreateGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptCreate();
            }
        });


    }

    private void attemptCreate() {

        editGroupName.setError(null);
        editAdminName.setError(null);

        boolean cancel = false;
        View focusView = null;



        String adminName = editAdminName.getText().toString();
        String groupName = editGroupName.getText().toString();


        if (TextUtils.isEmpty(iconString)) {
            userIconLabel.setError(getString(R.string.error_field_required));
            focusView = userIcon;

            cancel = true;
        }

        if (TextUtils.isEmpty(adminName)) {
            editAdminName.setError(getString(R.string.error_field_required));
            focusView = editAdminName;
            cancel = true;
        }
        if (TextUtils.isEmpty(groupName)) {
            editGroupName.setError(getString(R.string.error_field_required));
            focusView = editGroupName;
            cancel = true;
        }




        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        }
        else{
            Group group= new Group();
            group.setName(groupName);
            group.setAdmin(adminName);
            group.setNoOfMembers(1);
            group.setIcon(iconString);




            // Retrofit begin
            RestServiceClient restServiceClient = RestServiceClient.retrofit.create(RestServiceClient.class);
            Call<Group> call = restServiceClient.createGroup(group);
            call.enqueue(new Callback<Group>() {
                @Override
                public void onResponse(Call<Group> call, Response<Group> response) {
                    Group groupData = response.body();
                    if (groupData.getErrorMessage() == null) {
                        Toast.makeText(getApplicationContext(), "Registered successfully ", Toast.LENGTH_SHORT).show();

                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Registration failed", Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onFailure(Call<Group> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Registration failed", Toast.LENGTH_SHORT).show();
                }
            } );
        }
    }



    private void addPhoto() {
        final CharSequence[] items = {"Take Photo", "Choose from Gallery",
                "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(CreateGroupActivity.this);
        builder.setTitle("Choose a method: ");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                boolean result = CameraUtility.checkPermission(CreateGroupActivity.this);

                if (items[item].equals("Take Photo")) {
                    userChoosenTask = "Take Photo";
                    if (result)
                        cameraIntent();

                } else if (items[item].equals("Choose from Gallery")) {
                    userChoosenTask = "Choose from Gallery";
                    if (result)
                        galleryIntent();

                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }





    private void galleryIntent() {
        Intent intent = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        intent.setType("image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("scale", true);
        intent.putExtra("outputX", 256);
        intent.putExtra("outputY", 256);
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, 1);
    }

    private void cameraIntent() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_CAMERA);
    }
}
