package yapp.com.railrotalk.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import yapp.com.railrotalk.dto.ChattingListData;

/**
 * Created by HunJin on 2017-02-25.
 */

public abstract class GenericViewHolder extends RecyclerView.ViewHolder {

    public GenericViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void setDataOnView(int position, List<ChattingListData> items);
}
