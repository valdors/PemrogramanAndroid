package com.t.pemrogramanandro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class FirebaseActivity extends AppCompatActivity {

    private FirebaseFirestore firebaseFirestoreDb;
    private EditText noMhs;
    private EditText namaMhs;
    private EditText phoneMhs;
    private Button buttonSimpan;
    private Button buttonHapus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase);

        noMhs = findViewById(R.id.noMhs);
        namaMhs = findViewById(R.id.namaMhs);
        phoneMhs = findViewById(R.id.phoneMhs);
        buttonSimpan = findViewById(R.id.simpanButton);
        buttonHapus = findViewById(R.id.hapusButton);
        firebaseFirestoreDb = FirebaseFirestore.getInstance();

        buttonSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //sanity check
                if (!noMhs.getText().toString().isEmpty() && !namaMhs.getText().toString().isEmpty()) {
                    tambahMahasiswa();
                } else {
                    Toast.makeText(FirebaseActivity.this, "No dan Nama Mhs tidak boleh kosong",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        getDataMahasiswa();

        buttonHapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteDataMahasiswa();
            }
        });

    }

    private void tambahMahasiswa() {

        Mahasiswa mhs = new Mahasiswa(noMhs.getText().toString(),
                namaMhs.getText().toString(),
                phoneMhs.getText().toString());

        firebaseFirestoreDb.collection("DaftarMhs").document().set(mhs)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(FirebaseActivity.this, "Mahasiswa berhasil didaftarkan",
                                Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(FirebaseActivity.this, "ERROR" + e.toString(),
                                Toast.LENGTH_SHORT).show();
                        Log.d("TAG", e.toString());
                    }
                });
    }

    private void getDataMahasiswa() {
        DocumentReference docRef = firebaseFirestoreDb.collection("DaftarMhs").document("mhs1");
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Mahasiswa mhs = document.toObject(Mahasiswa.class);
                        noMhs.setText(mhs.getNim());
                        namaMhs.setText(mhs.getNama());
                        phoneMhs.setText(mhs.getPhone());
                    } else {
                        Toast.makeText(FirebaseActivity.this, "Document tidak ditemukan",
                                Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(FirebaseActivity.this, "Document error : " + task.getException(),
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void deleteDataMahasiswa() {
        firebaseFirestoreDb.collection("DaftarMhs").document("mhs1")
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        noMhs.setText("");
                        namaMhs.setText("");
                        phoneMhs.setText("");
                        Toast.makeText(FirebaseActivity.this, "Mahasiswa berhasil dihapus",
                                Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(FirebaseActivity.this, "Error deleting document: " + e.getMessage(),
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }


}