package br.com.gearsoft.doarsangue.services;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import br.com.gearsoft.doarsangue.domain.Solicitacao;

import static com.google.firebase.firestore.Query.Direction;

/**
 * Created by marcio on 10/5/17.
 */

public final class SolicitacaoService {

    private static final String collection_name = "solicitacoes";
    private static FirebaseFirestore db = FirebaseFirestore.getInstance();
    private static final String TAG = "SolicitacaoService";

    private SolicitacaoService() {
    }

    public static Query getSolicitacoes() {
        Calendar utc = Calendar.getInstance(TimeZone.getTimeZone("UTC"));

        return db.collection(SolicitacaoService.collection_name)
                .whereGreaterThan(Solicitacao.field_data_expiracao, Calendar.getInstance().getTime())
                .orderBy(Solicitacao.field_data_expiracao, Direction.DESCENDING)
                .orderBy(Solicitacao.field_urgente, Direction.ASCENDING);
    }

    public static void createSome() {
        Calendar calendar = Calendar.getInstance();
        Date now = calendar.getTime();

        calendar.add(Calendar.DATE, 7);
        Date expire = calendar.getTime();

        calendar.add(Calendar.DATE, -3);
        Date expireLess = calendar.getTime();

        Solicitacao sol = new Solicitacao();
        sol.setId("sol1");
        sol.setUrgente(true);
        sol.setNomeRecebedor("Recebedor Hardcode 1");
        sol.setDataExpiracao(expire);
        sol.setDataSolicitacao(now);

        Solicitacao sol2 = new Solicitacao();
        sol2.setId("sol2");
        sol2.setUrgente(false);
        sol2.setNomeRecebedor("Recebedor Não Urgente 2");
        sol2.setDataExpiracao(expireLess);
        sol2.setDataSolicitacao(now);

        Task<Void> task = db.collection(collection_name).document(sol.getId()).set(sol);
        Task<Void> task1 = db.collection(collection_name).document(sol2.getId()).set(sol2);

        task.addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.d(TAG, "DocumentSnapshot successfully written!");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w(TAG, "Error writing document", e);
            }
        });

        task1.addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.d(TAG, "DocumentSnapshot successfully written!");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w(TAG, "Error writing document", e);
            }
        });

    }

}
