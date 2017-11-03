package br.com.gearsoft.doarsangue.services;

import android.app.Activity;

import com.google.firebase.firestore.FirebaseFirestore;

/**
 * Created by marcio on 11/3/17.
 */

public final class PessoaService {

    /**
     *
     */
    public interface PessoaServiceListener{
    }

    private static PessoaService mPessoaService;
    private PessoaServiceListener mListener;
    private Activity mActivity;
    private FirebaseFirestore db;
    private boolean isConfigured;

    private PessoaService() {
        db = FirebaseFirestore.getInstance();
    }

    public static PessoaService getInstance(PessoaServiceListener listener, Activity activity) {
        if (mPessoaService == null) {
            mPessoaService = new PessoaService();
        }
        mPessoaService.mListener = listener;
        mPessoaService.mActivity = activity;
        mPessoaService.isConfigured = true;
        return mPessoaService;
    }
}
