package id.co.ledconsulting.automationcompetences;

import android.nfc.Tag;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class OptionalQuestionActivity extends AppCompatActivity {
    private static final String DEFAULT_COLLECTION = "question";
    private static final String PROJECT_ID = "competences-led";
    private static final String DOCUMENT_EMPLOYEE ="employee";
    private static final String DOCUMENT_PENDING_QUESTIONS="pending";
    private static final String DOCUMENT_SUBMIT_QUESTIONS="submit";
    private static final String DOCUMENT_HISTORY_ANSWER="history";
    private static final String TAG="OPTIONAL_QUESTION_ACTIVITY";
    private FirebaseFirestore mFirestore;
    //membuat variabel baru sesuai tipe
    TextView mtvSkor, mtvSoal;
    RadioGroup mrgPilihanJawaban;
    RadioButton mrbPilihanJawaban1, mrbPilihanJawaban2, mrbPilihanJawaban3, mrbPilihanJawaban4, mrbPilihanJawaban5 ;
    Button mbtnSubmit;
    int skor=0;
    int arr; //untuk menampung nilai panjang array
    int x;   //menunjukkan konten sekarang
    String jawaban; //menampung jawaban benar
    String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.optional_question);

        //menyambungkan antara variabel KuisPilihanGanda.java dengan id activity_kuis_pilihan_ganda
        // mtvSkor = (TextView) findViewById(R.id.tvSkor);
        mtvSoal = (TextView) findViewById(R.id.tvSoal);
        mrgPilihanJawaban = (RadioGroup) findViewById(R.id.rgPilihanJawaban);
        mrbPilihanJawaban1 = (RadioButton) findViewById(R.id.rbPilihanJawaban1);
        mrbPilihanJawaban2 = (RadioButton) findViewById(R.id.rbPilihanJawaban2);
        mrbPilihanJawaban3 = (RadioButton) findViewById(R.id.rbPilihanJawaban3);
        mrbPilihanJawaban4 = (RadioButton) findViewById(R.id.rbPilihanJawaban4);
        mrbPilihanJawaban5 = (RadioButton) findViewById(R.id.rbPilihanJawaban5);
        mbtnSubmit = (Button) findViewById(R.id.btnSubmit);

        //set konten
        //mtvSkor.setText(""+skor);
        setContent(username);
        mbtnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //aksinya disini
                //aksinya adalah ketika button tersebut diklik maka
                //akan mengecek jawaban benar atau salah
                //kemudian konten akan berubah (next konten)
                cekJawaban();
            }
        });
    }
    private void cekJawaban(){

        if(mrbPilihanJawaban1.isChecked()){ //jika radio button 1 diklik
            //jika text yang tertulis di radio button tsb = nilai dari var jawaban
            if(mrbPilihanJawaban1.getText().toString().equals(jawaban)){
                skor = skor + 10;
                // mtvSkor.setText(""+skor);	//mtvSkor diset nilainya = var skor
                Toast.makeText(this, "Jawaban Benar", Toast.LENGTH_SHORT).show(); //keluar notifikasi "Jawaban Benar"
//                setKonten(); //update next konten
                submitQuestion();
                historyAnswer();
            }else{
                // mtvSkor.setText(""+skor);
                Toast.makeText(this, "Jawaban Salah", Toast.LENGTH_SHORT).show();
//                setKonten();
                historyAnswer();
            }
        }else if(mrbPilihanJawaban2.isChecked()){
            //jika text yang tertulis di radio button tsb = nilai dari var jawaban
            if(mrbPilihanJawaban2.getText().toString().equals(jawaban)){
                skor = skor + 10;
                // mtvSkor.setText(""+skor);	//mtvSkor diset nilainya = var skor
                Toast.makeText(this, "Jawaban Benar", Toast.LENGTH_SHORT).show(); //keluar notifikasi "Jawaban Benar"
//                setKonten(); //update next konten
                submitQuestion();
                historyAnswer();
            }else{
                // mtvSkor.setText(""+skor);
                Toast.makeText(this, "Jawaban Salah", Toast.LENGTH_SHORT).show();
//                setKonten();
                historyAnswer();
            }
        }else if(mrbPilihanJawaban3.isChecked()){
            //jika text yang tertulis di radio button tsb = nilai dari var jawaban
            if(mrbPilihanJawaban3.getText().toString().equals(jawaban)){
                skor = skor + 10;
                //  mtvSkor.setText(""+skor);	//mtvSkor diset nilainya = var skor
                Toast.makeText(this, "Jawaban Benar", Toast.LENGTH_SHORT).show(); //keluar notifikasi "Jawaban Benar"
//                setKonten(); //update next konten
                submitQuestion();
                historyAnswer();
            }else{
                // mtvSkor.setText(""+skor);
                Toast.makeText(this, "Jawaban Salah", Toast.LENGTH_SHORT).show();
//                setKonten();
                historyAnswer();
            }
        }
        else if(mrbPilihanJawaban4.isChecked()){
            //jika text yang tertulis di radio button tsb = nilai dari var jawaban
            if(mrbPilihanJawaban4.getText().toString().equals(jawaban)){
                skor = skor + 10;
                //  mtvSkor.setText(""+skor);	//mtvSkor diset nilainya = var skor
                Toast.makeText(this, "Jawaban Benar", Toast.LENGTH_SHORT).show(); //keluar notifikasi "Jawaban Benar"
//                setKonten(); //update next konten
                submitQuestion();
                historyAnswer();
            }else{
                // mtvSkor.setText(""+skor);
                Toast.makeText(this, "Jawaban Salah", Toast.LENGTH_SHORT).show();
//                setKonten();
                historyAnswer();
            }
        }
        else if(mrbPilihanJawaban5.isChecked()){
            //jika text yang tertulis di radio button tsb = nilai dari var jawaban
            if(mrbPilihanJawaban5.getText().toString().equals(jawaban)){
                skor = skor + 10;
                // mtvSkor.setText(""+skor);	//mtvSkor diset nilainya = var skor
                Toast.makeText(this, "Jawaban Benar", Toast.LENGTH_SHORT).show(); //keluar notifikasi "Jawaban Benar"
//                setKonten(); //update next konten
                submitQuestion();
                historyAnswer();
            }else{
                // mtvSkor.setText(""+skor);
                Toast.makeText(this, "Jawaban Salah", Toast.LENGTH_SHORT).show();
//                setKonten();
                historyAnswer();
            }
        }else{
            Toast.makeText(this, "Silahkan pilih jawaban dulu!", Toast.LENGTH_SHORT).show();
        }
    }
    private void setContent(String username){
        PendingQuestion pending = this.customObjects();
        if(username.equals(pending.getUserName())){
            mtvSoal.setText(pending.getQuestions());
            mrbPilihanJawaban1.setText(pending.getAnswer1());
            mrbPilihanJawaban2.setText(pending.getAnswer2());
            if(pending.getAnswer3().equals(null)){
                mrbPilihanJawaban3.setEnabled(false);
            }else {
                mrbPilihanJawaban3.setText(pending.getAnswer3());
            }if(pending.getAnswer4().equals(null)){
                mrbPilihanJawaban4.setEnabled(false);
            }else {
                mrbPilihanJawaban4.setText(pending.getAnswer4());
            }if(pending.getAnswer5().equals(null)){
                mrbPilihanJawaban5.setEnabled(false);
            }else {
                mrbPilihanJawaban5.setText(pending.getAnswer5());
            }
            jawaban = pending.getCorrectAnswer();
        }
        else{
            mtvSoal.setText("Sorry there is no question for you");
            mrbPilihanJawaban1.setEnabled(false);
            mrbPilihanJawaban2.setEnabled(false);
            mrbPilihanJawaban3.setEnabled(false);
            mrbPilihanJawaban4.setEnabled(false);
            mrbPilihanJawaban5.setEnabled(false);
        }

    }
    public PendingQuestion customObjects() {
        // [START custom_objects]
        final PendingQuestion pend = null;
        DocumentReference docRef = mFirestore.collection(DEFAULT_COLLECTION).document(DOCUMENT_PENDING_QUESTIONS);
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                PendingQuestion pendingQuestion = documentSnapshot.toObject(PendingQuestion.class);
                pend.setAnswer1(pendingQuestion.getAnswer1());
                pend.setAnswer2(pendingQuestion.getAnswer2());
                pend.setAnswer3(pendingQuestion.getAnswer3());
                pend.setAnswer4(pendingQuestion.getAnswer4());
                pend.setAnswer5(pendingQuestion.getAnswer4());
                pend.setCompetency(pendingQuestion.getCompetency());
                pend.setCorrectAnswer(pendingQuestion.getCorrectAnswer());
                pend.setLevel(pendingQuestion.getLevel());
                pend.setQuestions(pendingQuestion.getQuestions());
                pend.setUserName(pendingQuestion.getUserName());
                pend.setQuestionType(pendingQuestion.getQuestionType());
            }
        });
        return  pend;
        // [END custom_objects]
    }
    private void submitQuestion(){
        String random = null;
        Map<String, Object> map = new HashMap<>();
        map.put("text", random);

        mFirestore.collection(DEFAULT_COLLECTION)
                .document(DOCUMENT_SUBMIT_QUESTIONS)
                .set(map)
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Log.d(TAG, "write:onComplete");
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "write:onComplete:failed", task.getException());
                        }
                    }
                });
    }
    private void historyAnswer(){
        String random = null;
        Map<String, Object> map = new HashMap<>();
        map.put("text", random);

        mFirestore.collection(DEFAULT_COLLECTION)
                .document(DOCUMENT_HISTORY_ANSWER)
                .set(map)
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Log.d(TAG, "write:onComplete");
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "write:onComplete:failed", task.getException());
                        }
                    }
                });
    }

}
