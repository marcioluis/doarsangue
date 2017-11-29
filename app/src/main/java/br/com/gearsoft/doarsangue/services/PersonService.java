package br.com.gearsoft.doarsangue.services;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import br.com.gearsoft.doarsangue.domain.Person;

/**
 * Created by marcio on 11/3/17.
 */
public final class PersonService {

    /**
     *
     */
    public interface PersonServiceListener {
        void onSearchPerson(Person pessoa);
    }

    private static final String collection_name = "pessoas";
    private static final String TAG = PersonService.class.getSimpleName();
    private static PersonService mPessoaService;
    private PersonServiceListener mListener;
    private Activity mActivity;
    private FirebaseFirestore db;
    private boolean isConfigured;

    //construtor
    private PersonService() {
        db = FirebaseFirestore.getInstance();
    }

    public static PersonService getInstance(final PersonServiceListener listener, final Activity activity) {
        if (mPessoaService == null) {
            mPessoaService = new PersonService();
        }
        mPessoaService.mListener = listener;
        mPessoaService.mActivity = activity;
        mPessoaService.isConfigured = true;
        return mPessoaService;
    }

    public void searchPersonByUser(final FirebaseUser user) {
        db.collection(collection_name)
                .document(user.getUid())
                .get()
                .addOnCompleteListener(mActivity, new OnCompleteListener<DocumentSnapshot>() {

                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                        if (task.isSuccessful()) {
                            Person person;

                            if (task.getResult().exists()) {
                                person = task.getResult().toObject(Person.class);
                            } else {
                                person = new Person();
                                fillProfile(person, user);
                            }
                            mListener.onSearchPerson(person);

                        } else {
                            Log.e(TAG, "Erro ao buscar uma pessoa com id: " + user, task.getException());
                        }
                    }
                });
    }

    public void savePersonForUser(final Person person, final FirebaseUser user) {
        db.collection(collection_name)
                .document(user.getUid())
                .set(person)
                .addOnFailureListener(mActivity, new OnFailureListener() {

                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e(TAG, "Erro ao salvar pessoa", e);
                    }
                });
    }

    private void fillProfile(final Person person, final FirebaseUser user) {
        person.setEmail(user.getEmail());
        person.setNome(user.getDisplayName());
        person.setFoto(user.getPhotoUrl().toString());
        person.setTelefone(user.getPhoneNumber());
    }
}
