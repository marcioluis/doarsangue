package br.com.gearsoft.doarsangue.fragments;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import br.com.gearsoft.doarsangue.R;
import br.com.gearsoft.doarsangue.databinding.FragmentPerfilBinding;
import br.com.gearsoft.doarsangue.domain.Person;
import br.com.gearsoft.doarsangue.services.PersonService;
import br.com.gearsoft.doarsangue.services.PersonService.PersonServiceListener;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnPerfilInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PerfilFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

public class PerfilFragment extends Fragment implements PersonServiceListener {

    @Override
    public void onSearchPerson(final Person person) {
        mPerson.setNome(person.getNome());
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnPerfilInteractionListener {

    }

    private OnPerfilInteractionListener mListener;
    private FirebaseUser mUser;
    private Person mPerson;
    private PersonService mPersonService;

    // Required empty public constructor
    public PerfilFragment() {
    }

    public static PerfilFragment newInstance() {
        return new PerfilFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mUser = FirebaseAuth.getInstance().getCurrentUser();
        mPerson = new Person();
        mPersonService = PersonService.getInstance(this, super.getActivity());
        mPersonService.searchPersonByUser(mUser);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentPerfilBinding dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_perfil, container, false);

        dataBinding.setFragment(this);
        dataBinding.setPerson(mPerson);

        return dataBinding.getRoot();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnPerfilInteractionListener) {
            mListener = (OnPerfilInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement " + OnPerfilInteractionListener.class.getSimpleName());
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        mPersonService.savePersonForUser(mPerson, mUser);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
        mPersonService = null;
    }

    public void onPrintPressed(View view) {
        Log.d("PERFIL", mPerson.getNome());
    }
}
