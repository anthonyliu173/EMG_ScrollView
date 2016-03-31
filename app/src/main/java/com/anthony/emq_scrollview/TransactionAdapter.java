package com.anthony.emq_scrollview;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.anthony.emq_scrollview.Objects.Transaction;

/**
 * Created by anthonyliu on 2016/3/30.
 */
public class TransactionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{

    private Context context;
    private int layoutId = R.layout.card_transaction;

    // The minimum amount of items to have below your current scroll position before loading more.
    private int visibleThreshold = 6;
    private int lastVisibleItem, totalItemCount;
    private boolean isLoading;
    private OnLoadMoreListener onLoadMoreListener;

    public TransactionAdapter(Context context, RecyclerView recyclerView)
    {
        this.context = context;

        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                totalItemCount = linearLayoutManager.getItemCount();
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                if (!isLoading && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                    // End has been reached
                    // Do something
                    if (onLoadMoreListener != null) {
                        onLoadMoreListener.onLoadMore();
                    }
                    isLoading = true;
                }
            }
        });

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i)
    {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(layoutId, viewGroup, false);
        CustomViewHolder viewHolder = new CustomViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder view, int position)
    {
        Transaction transaction = DataHandler.getInstance().getTransactionList().get(position);

        ((CustomViewHolder)view).txtSender.setText(transaction.getSender().getSenderName());
        ((CustomViewHolder)view).txtNote.setText(transaction.getSender().getNote());
        ((CustomViewHolder)view).txtRecipient.setText(transaction.getDestination().getRecipient());
        ((CustomViewHolder)view).txtAmount.setText(String.valueOf(transaction.getDestination().getAmount()));
        ((CustomViewHolder)view).txtCurrency.setText(transaction.getDestination().getCurrency());

    }

    @Override
    public int getItemCount()
    {
        return DataHandler.getInstance().getTransactionList().size();
    }

    /**
     * loaded handles adapter update when transactions have been loaded.
     * */
    public void loaded(){
        this.notifyDataSetChanged();
        isLoading = false;
    }

    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        this.onLoadMoreListener = onLoadMoreListener;
    }

    public interface OnLoadMoreListener {
        void onLoadMore();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {

        protected TextView txtSender;
        protected TextView txtNote;
        protected TextView txtRecipient;
        protected TextView txtAmount;
        protected TextView txtCurrency;


        public CustomViewHolder(View view)
        {
            super(view);

            this.txtSender = (TextView)view.findViewById(R.id.txtSender);
            this.txtNote = (TextView)view.findViewById(R.id.txtNote);
            this.txtRecipient = (TextView)view.findViewById(R.id.txtRecipient);
            this.txtAmount = (TextView)view.findViewById(R.id.txtAmount);
            this.txtCurrency = (TextView)view.findViewById(R.id.txtCurrency);

        }

        @Override
        public void onClick(View view)
        {

        }
    }

}