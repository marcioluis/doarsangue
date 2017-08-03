package br.com.mlsa.doarsangue.fragments;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.com.mlsa.doarsangue.R;
import br.com.mlsa.doarsangue.fragments.DoacaoFragment.OnDoacaoFragmentInteractionListener;
import br.com.mlsa.doarsangue.fragments.dummydoa.DummyDoacao.DummyDoacaoItem;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyDoacaoItem} and makes a call to the
 * specified {@link OnDoacaoFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class DoacaoRecyclerViewAdapter extends RecyclerView.Adapter<DoacaoRecyclerViewAdapter.ViewHolder> {

    private final List<DummyDoacaoItem> mValues;
    private final OnDoacaoFragmentInteractionListener mListener;

    public DoacaoRecyclerViewAdapter(List<DummyDoacaoItem> items, OnDoacaoFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_doacao, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).id);
        holder.mContentView.setText(mValues.get(position).content);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onClickDoacaoItem(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
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

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
