package br.com.gearsoft.doarsangue.services;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import br.com.gearsoft.doarsangue.domain.Solicitacao;

/**
 * Created by marcio on 10/5/17.
 */

public final class SolicitacaoService {

    private boolean isConfigured;

    /**
     * listeners das acoes de interacao com o firestore
     */
    public interface OnSolicitacaoListener{
        void onSolCompleted(List<Solicitacao> solicitacao);
    }

    private static final String collection_name = "solicitacoes";
    private static final String TAG = "SolicitacaoService";
    private static SolicitacaoService solicitacaoService;
    private static FirebaseFirestore db = FirebaseFirestore.getInstance();

    private OnSolicitacaoListener mSolListener;
    private Activity mActivity;
    private ArrayList<Solicitacao> sols = new ArrayList<Solicitacao>();

    private SolicitacaoService() {
    }

    public static SolicitacaoService getInstance(RecyclerView.Adapter adapter, Activity activity) {
        if (solicitacaoService == null) {
            solicitacaoService = new SolicitacaoService();
        }

        solicitacaoService.mActivity = activity;
        if (adapter instanceof OnSolicitacaoListener) {
            solicitacaoService.mSolListener = (OnSolicitacaoListener) adapter;
            solicitacaoService.isConfigured = true;
        } else {
            solicitacaoService.isConfigured = false;
            throw new RuntimeException(adapter.toString() + " must implement OnSolicitacaoListener");
        }

        return solicitacaoService;
    }

    public static SolicitacaoService getInstance(){
        if(solicitacaoService == null || !solicitacaoService.isConfigured)
            throw new RuntimeException("Serviço não está configurado");
        return solicitacaoService;
    }

    public void getSolicitacoes() {
        Calendar utc = Calendar.getInstance(TimeZone.getTimeZone("UTC"));

        if (!sols.isEmpty()) {
            mSolListener.onSolCompleted(sols);
            return;
        }

//        todas as urgentes e depois todas as não urgentes; ambas não expiradas
        db.collection(SolicitacaoService.collection_name)
                .get()
                .addOnCompleteListener(this.mActivity, new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot doc : task.getResult()) {
                                Solicitacao solicitacao = doc.toObject(Solicitacao.class);
                                sols.add(solicitacao);
                            }
                            mSolListener.onSolCompleted(sols);
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
    }

    public static void createSome() {
        Calendar calendar = Calendar.getInstance();
        Date now = calendar.getTime();

        calendar.add(Calendar.DATE, 7);
        Date expire = calendar.getTime();

        calendar.add(Calendar.DATE, -3);
        Date expireLess = calendar.getTime();

        Solicitacao sol = new Solicitacao();
        sol.setId("sol");
        sol.setUrgente(true);
        sol.setNomeRecebedor("Recebedor Hardcode 1 Urgente");
        sol.setDataExpiracao(expire);
        sol.setDataSolicitacao(now);

        Solicitacao sol2 = new Solicitacao();
        sol2.setId("sol2");
        sol2.setUrgente(false);
        sol2.setNomeRecebedor("Recebedor Não Urgente 2");
        sol2.setDataExpiracao(expireLess);
        sol2.setDataSolicitacao(now);

        Solicitacao sol1 = new Solicitacao();
        sol1.setId("sol1");
        sol1.setUrgente(false);
        sol1.setNomeRecebedor("Recebedor Hardcode 1 Não Urgente");
        sol1.setDataExpiracao(expire);
        sol1.setDataSolicitacao(now);

        Task<Void> task = db.collection(collection_name).document(sol.getId()).set(sol);
        Task<Void> task2 = db.collection(collection_name).document(sol2.getId()).set(sol2);
        Task<Void> task1 = db.collection(collection_name).document(sol.getId()).set(sol1);


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

        task2.addOnSuccessListener(new OnSuccessListener<Void>() {
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
