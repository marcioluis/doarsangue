package br.com.gearsoft.doarsangue.adapter;

import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.Query;

import br.com.gearsoft.doarsangue.R;
import br.com.gearsoft.doarsangue.domain.Solicitacao;
import butterknife.BindView;
import butterknife.ButterKnife;

public class SolicitacaoAdapter extends FirestoreAdapter<SolicitacaoAdapter.ViewHolder> {

    public interface OnSolicitacaoSelectedListener{
        void onSolicitacaoSelected(DocumentSnapshot solicitacao);
    }

    private OnSolicitacaoSelectedListener mSolicitacaoSelectedListener;

    public SolicitacaoAdapter(Query query, OnSolicitacaoSelectedListener mSolicitacaoSelectedListener) {
        super(query);
        this.mSolicitacaoSelectedListener = mSolicitacaoSelectedListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new ViewHolder(inflater.inflate(R.layout.fragment_solicitacao, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.bind(getSnapshot(position), mSolicitacaoSelectedListener);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.nomeRecebedor)
        TextView mIdView;

        @BindView(R.id.localDoacao)
        TextView mContentView;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void bind(final DocumentSnapshot snapshot, final OnSolicitacaoSelectedListener listener) {
            final Solicitacao solicitacao = snapshot.toObject(Solicitacao.class);
            Resources resources = itemView.getResources();

            mIdView.setText(solicitacao.getNomeRecebedor());
            mContentView.setText(solicitacao.getNomeRecebedor());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        listener.onSolicitacaoSelected(snapshot);
                    }
                }
            });
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
