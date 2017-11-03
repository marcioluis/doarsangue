package br.com.gearsoft.doarsangue.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.gearsoft.doarsangue.R;
import br.com.gearsoft.doarsangue.adapters.DoacaoAdapter;
import br.com.gearsoft.doarsangue.domain.Doacao;
import br.com.gearsoft.doarsangue.services.DoacaoService;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnDoacaoInteractionListener}
 * interface.
 */
public class DoacaoFragment extends Fragment {

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
    public interface OnDoacaoInteractionListener {
        void onClickDoacaoItem(Doacao item);
    }

    private OnDoacaoInteractionListener mListener;
    private DoacaoAdapter mAdapter;
    private DoacaoService mDoacaoService;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public DoacaoFragment() {
    }

    public static DoacaoFragment newInstance() {
        DoacaoFragment fragment = new DoacaoFragment();
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnDoacaoInteractionListener) {
            mListener = (OnDoacaoInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement " + OnDoacaoInteractionListener.class.getCanonicalName());
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_doacao_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {

            mAdapter = new DoacaoAdapter(mListener);
            mDoacaoService = DoacaoService.getInstance(mAdapter, getActivity());

            Context context = view.getContext();
            LinearLayoutManager layoutManager = new LinearLayoutManager(context);

            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.addItemDecoration(new DividerItemDecoration(context, layoutManager.getOrientation() ));
            recyclerView.setAdapter(mAdapter);
        }
        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
}
