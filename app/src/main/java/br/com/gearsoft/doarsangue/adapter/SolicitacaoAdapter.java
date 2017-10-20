package br.com.gearsoft.doarsangue.adapter;

import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.gearsoft.doarsangue.R;
import br.com.gearsoft.doarsangue.domain.Solicitacao;
import br.com.gearsoft.doarsangue.fragments.SolicitacaoFragment.OnSolicitacaoFragmentInteractionListener;
import br.com.gearsoft.doarsangue.services.SolicitacaoService;
import butterknife.BindView;
import butterknife.ButterKnife;

public class SolicitacaoAdapter extends RecyclerView.Adapter<SolicitacaoAdapter.SolicitacaoViewHolder> implements SolicitacaoService.OnSolicitacaoListener {

    private OnSolicitacaoFragmentInteractionListener mListener;
    private List<Solicitacao> mSolicitacoes = new ArrayList<>();

    public SolicitacaoAdapter(OnSolicitacaoFragmentInteractionListener mListener) {
        this.mListener = mListener;
    }

    public SolicitacaoAdapter(List<Solicitacao> solicitacoes, OnSolicitacaoFragmentInteractionListener listener) {
        this.mListener = listener;
        this.mSolicitacoes = solicitacoes;
    }

    @Override
    public SolicitacaoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new SolicitacaoViewHolder(inflater.inflate(R.layout.fragment_solicitacao, parent, false));
    }

    @Override
    public void onBindViewHolder(final SolicitacaoViewHolder holder, int position) {
        holder.bind(this.mSolicitacoes.get(position), this.mListener);
    }

    @Override
    public int getItemCount() {
        return this.mSolicitacoes.size();
    }

    @Override
    public void onSolCompleted(List<Solicitacao> solicitacao) {
        this.mSolicitacoes = solicitacao;
        this.notifyDataSetChanged();
    }

    static class SolicitacaoViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.nomeRecebedor)
        TextView mIdView;

        @BindView(R.id.localDoacao)
        TextView mContentView;

        public SolicitacaoViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void bind(final Solicitacao solicitacao, final OnSolicitacaoFragmentInteractionListener listener) {
            Resources resources = itemView.getResources();

            mIdView.setText(solicitacao.getNomeRecebedor());
            mContentView.setText(solicitacao.getNomeRecebedor());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        listener.onClickSolicitacaoItem(solicitacao);
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
