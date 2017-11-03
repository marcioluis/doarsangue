package br.com.gearsoft.doarsangue.adapters;

import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.gearsoft.doarsangue.R;
import br.com.gearsoft.doarsangue.domain.Doacao;
import br.com.gearsoft.doarsangue.fragments.DoacaoFragment.OnDoacaoInteractionListener;
import br.com.gearsoft.doarsangue.services.DoacaoService.OnDoacaoServiceListener;
import butterknife.BindView;
import butterknife.ButterKnife;

public class DoacaoAdapter extends RecyclerView.Adapter<DoacaoAdapter.DoacaoViewHolder> implements OnDoacaoServiceListener{

    private final OnDoacaoInteractionListener mListener;
    private List<Doacao> doacoes = new ArrayList<>();

    public DoacaoAdapter(OnDoacaoInteractionListener mListener) {
        this.mListener = mListener;
    }

    @Override
    public DoacaoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new DoacaoViewHolder(inflater.inflate(R.layout.fragment_doacao, parent, false));
    }

    @Override
    public void onBindViewHolder(final DoacaoViewHolder holder, int position) {
        holder.bind(doacoes.get(position), mListener);
    }

    @Override
    public int getItemCount() {
        return doacoes.size();
    }

    static class DoacaoViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.nomeRecebedor)
        TextView mIdView;
        @BindView(R.id.localDoacao)
        TextView mContentView;

        public DoacaoViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void bind(final Doacao doacao, final OnDoacaoInteractionListener listener) {
            Resources resources = itemView.getResources();

            mIdView.setText(doacao.getLocalDoacao());
            mContentView.setText(doacao.getDataDoacao().toString());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onClickDoacaoItem(doacao);
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
