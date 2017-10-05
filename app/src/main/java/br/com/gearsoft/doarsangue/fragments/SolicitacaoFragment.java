package br.com.gearsoft.doarsangue.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.firestore.DocumentSnapshot;

import br.com.gearsoft.doarsangue.R;
import br.com.gearsoft.doarsangue.adapter.SolicitacaoAdapter;
import br.com.gearsoft.doarsangue.services.SolicitacaoService;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnSolicitacaoFragmentInteractionListener}
 * interface.
 */
public class SolicitacaoFragment extends Fragment implements SolicitacaoAdapter.OnSolicitacaoSelectedListener {

    @Override
    public void onSolicitacaoSelected(DocumentSnapshot solicitacao) {
        Log.d("SolicitacaoFragment", "********** onSolicitacaoSelected ************");
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnSolicitacaoFragmentInteractionListener {
        void onClickSolicitacaoItem();
    }

    private OnSolicitacaoFragmentInteractionListener mListener;
    private SolicitacaoAdapter mAdapter;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public SolicitacaoFragment() {
    }

    public static SolicitacaoFragment newInstance() {
        return new SolicitacaoFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_solicitacao_list, container, false);

        // Set the mAdapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;

            LinearLayoutManager layoutManager = new LinearLayoutManager(context);

            mAdapter = new SolicitacaoAdapter(SolicitacaoService.getSolicitacoes(), this);

            recyclerView.addItemDecoration(new DividerItemDecoration(context, layoutManager.getOrientation()));
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(mAdapter);
        }
        return view;
    }

    // Start listening for Firestore updates
    @Override
    public void onStart() {
        super.onStart();
        if(mAdapter != null)
            mAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        if(mAdapter != null)
            mAdapter.stopListening();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnSolicitacaoFragmentInteractionListener) {
            mListener = (OnSolicitacaoFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnSolicitacaoFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

}
