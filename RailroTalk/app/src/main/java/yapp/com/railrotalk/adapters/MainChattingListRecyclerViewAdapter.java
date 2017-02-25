package yapp.com.railrotalk.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import yapp.com.railrotalk.R;
import yapp.com.railrotalk.dto.ChattingListData;
import yapp.com.railrotalk.holders.ChattingListViewHolder;
import yapp.com.railrotalk.holders.GenericViewHolder;

/**
 * Created by HunJin on 2017-02-25.
 */

public class MainChattingListRecyclerViewAdapter extends RecyclerView.Adapter<GenericViewHolder> {

    private View mView;
    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private List<ChattingListData> items;

    public MainChattingListRecyclerViewAdapter(Context context) {
        super();
        this.mContext = context;
        items = new ArrayList<>();
    }

    @Override
    public GenericViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        mLayoutInflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mView = mLayoutInflater.inflate(R.layout.item_main_chatting_list, viewGroup, false);
        return new ChattingListViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(GenericViewHolder holder, int position) {
        holder.setDataOnView(position, items);

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addData(ChattingListData item) {
        items.add(item);
        notifyDataSetChanged();
    }

    public void clear() {
        items.clear();;
        notifyDataSetChanged();
    }
}
