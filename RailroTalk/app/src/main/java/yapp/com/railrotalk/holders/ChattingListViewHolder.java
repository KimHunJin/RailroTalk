package yapp.com.railrotalk.holders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import yapp.com.railrotalk.R;
import yapp.com.railrotalk.dto.ChattingListData;

/**
 * Created by HunJin on 2017-02-25.
 */

public class ChattingListViewHolder extends GenericViewHolder {

    private ImageView mImageRoom;
    private TextView mTxtRoomName;
    private View mView;

    public ChattingListViewHolder(View itemView) {
        super(itemView);
        this.mView = itemView;
        this.mImageRoom = (ImageView)mView.findViewById(R.id.img_chatting_main_item_list);
        this.mTxtRoomName = (TextView)mView.findViewById(R.id.txt_chatting_main_item_list);
    }

    @Override
    public void setDataOnView(int position, List<ChattingListData> items) {
        ChattingListData item = items.get(position);
        mTxtRoomName.setText(item.getmRoomName());

    }
}
