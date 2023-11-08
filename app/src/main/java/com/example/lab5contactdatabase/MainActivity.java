package com.example.lab5contactdatabase;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity {
    EditText nameTxt, dobTxt, emailTxt;
    Button saveBtn, viewBtn;
    Spinner avatarSpinner;
    // Sample avatar images - You might have different images in your resources
    Integer[] avatarImages = {R.drawable.mark, R.drawable.renjun, R.drawable.jeno, R.drawable.haechan,
                                R.drawable.jaemin, R.drawable.chenle, R.drawable.jisung};
    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nameTxt = (EditText) findViewById(R.id.edtName);
        dobTxt = (EditText) findViewById(R.id.edtDOB);
        emailTxt = (EditText) findViewById(R.id.edtEmail);
        avatarSpinner = findViewById(R.id.avatarSpinner);
        saveBtn = (Button) findViewById(R.id.saveButton);
        viewBtn = (Button) findViewById(R.id.viewButton);
        databaseHelper = new DatabaseHelper(this);
        if (avatarSpinner != null) {
            // Create an adapter for the spinner using the avatar images
            ImageArrayAdapter adapter = new ImageArrayAdapter(this, R.layout.custom_spinner_item, avatarImages);
            avatarSpinner.setAdapter(adapter);
            setupSaveButton();
            setupViewButton();
        }
        avatarSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Get the selected avatar resource ID
                int selectedAvatarResourceId = (int) avatarSpinner.getItemAtPosition(avatarSpinner.getSelectedItemPosition());
                // Store this selectedAvatarResourceId along with other contact details when saving the contact
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }
    private void setupSaveButton() {
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveDetails();
            }
        });
    }
    private void setupViewButton() {
        viewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDetailsActivity();
            }
        });
    }
    private void saveDetails(){
        String name = nameTxt.getText().toString();
        String dob = dobTxt.getText().toString();
        String email = emailTxt.getText().toString();
        int selectedAvatarResourceId = (int) avatarSpinner.getSelectedItem(); // Get the selected avatar resource ID
        databaseHelper.insertDetails(name, dob, email, selectedAvatarResourceId);
        Toast t = Toast.makeText(MainActivity.this, "New contact has been added",Toast.LENGTH_SHORT);
        t.show();
    }
    private void openDetailsActivity() {
        Intent intentToView = new Intent(MainActivity.this, DetailsActivity.class);
        startActivity(intentToView);
    }
}