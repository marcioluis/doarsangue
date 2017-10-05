package br.com.gearsoft.doarsangue.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.Query;

import br.com.gearsoft.doarsangue.R;
import br.com.gearsoft.doarsangue.domain.Solicitacao;
import br.com.gearsoft.doarsangue.fragments.DoacaoFragment.OnDoacaoFragmentInteractionListener;
import br.com.gearsoft.doarsangue.fragments.dummydoa.DummyDoacao.DummyDoacaoItem;

public class DoacaoAdapter extends FirestoreAdapter<DoacaoAdapter.ViewHolder> {

    public interface OnDoacaoSelectedListener{
        void onDoacaoSelected(DocumentSnapshot doacao);
    }

    private OnDoacaoSelectedListener mDoacaoSelectedListener;

    public DoacaoAdapter(Query query, OnDoacaoSelectedListener mDoacaoSelectedListener) {
        super(query);
        this.mDoacaoSelectedListener = mDoacaoSelectedListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new ViewHolder(inflater.inflate(R.layout.fragment_doacao, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.bind(getSnapshot(position), mDoacaoSelectedListener);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public DummyDoacaoItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.nomeRecebedor);
            mContentView = (TextView) view.findViewById(R.id.localDoacao);
        }

        public void bind(final DocumentSnapshot snapshot, final OnDoacaoSelectedListener listener){
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
