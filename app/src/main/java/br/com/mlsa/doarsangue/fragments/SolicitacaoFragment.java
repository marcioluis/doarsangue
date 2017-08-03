package br.com.mlsa.doarsangue.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.mlsa.doarsangue.R;
import br.com.mlsa.doarsangue.fragments.dummysol.DummySolicitacao;
import br.com.mlsa.doarsangue.fragments.dummysol.DummySolicitacao.DummySolicitacaoItem;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnSolicitacaoFragmentInteractionListener}
 * interface.
 */
public class SolicitacaoFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnSolicitacaoFragmentInteractionListener mListener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public SolicitacaoFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static SolicitacaoFragment newInstance(int columnCount) {
        SolicitacaoFragment fragment = new SolicitacaoFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_solicitacao_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;

            LinearLayoutManager layoutManager = new LinearLayoutManager(context);

            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(new SolicitacaoRecyclerViewAdapter(DummySolicitacao.ITEMS, mListener));
            recyclerView.addItemDecoration(new DividerItemDecoration(context, layoutManager.getOrientation()));
        }
        return view;
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
        // TODO: Update argument type and name
        void onClickSolicitacaoItem(DummySolicitacaoItem item);
    }
}
