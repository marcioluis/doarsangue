package br.com.gearsoft.doarsangue.services;

import android.app.Activity;

import com.google.firebase.firestore.FirebaseFirestore;

/**
 * Created by marcio on 10/25/17.
 */

public final class DoacaoService {

    /**
     * Listener das interações com o Firestore
     */
    public interface OnDoacaoServiceListener{
    }

    private static DoacaoService doacaoService;

    private OnDoacaoServiceListener mListener;
    private Activity mActivity;
    private boolean isConfigured;
    private FirebaseFirestore db;

    private DoacaoService() {
        db = FirebaseFirestore.getInstance();
    }

    public static DoacaoService getInstance(OnDoacaoServiceListener listener, Activity activity) {
        if (doacaoService == null) {
            doacaoService = new DoacaoService();
        }
        doacaoService.mActivity = activity;
        doacaoService.mListener = listener;
        doacaoService.isConfigured = true;
        return doacaoService;
    }

    public static DoacaoService getInstance() {
        if(doacaoService == null || !doacaoService.isConfigured)
            throw new RuntimeException("Serviço não está configurado");
        return doacaoService;
    }
}
