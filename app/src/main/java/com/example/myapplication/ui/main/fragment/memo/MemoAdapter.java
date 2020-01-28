package com.example.myapplication.ui.main.fragment.memo;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.database.memo.Memo;
import com.example.myapplication.ui.register.activity.RegisterActivity;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class MemoAdapter extends RecyclerView.Adapter<MemoAdapter.ViewHolder> {

    private List<Memo> mData;
    private Activity activity;

    private View.OnClickListener mListener = null;

    public void setOnItemClickListener(View.OnClickListener listener) {
        this.mListener = listener;
    }


    // 아이템 뷰를 저장하는 뷰홀더 클래스.
    public class ViewHolder extends RecyclerView.ViewHolder  {
        TextView memoTitle;
        TextView memoContent;
        TextView memoPriority;
        TextView memoDate;
        Button button;

        ViewHolder(View itemView) {
            super(itemView);

            // 뷰 객체에 대한 참조. (hold strong reference)
            memoTitle = itemView.findViewById(R.id.memo_title);
            memoContent = itemView.findViewById(R.id.memo_content);
            memoPriority = itemView.findViewById(R.id.memo_priority);
            memoDate = itemView.findViewById(R.id.memo_date);
            button = itemView.findViewById(R.id.memo_setting);

            Log.d("ddd", "ViewHolder");
        }

    }

    // 생성자에서 데이터 리스트 객체를 전달받음.
    MemoAdapter(List<Memo> list, Context context) {
        mData = list;
        this.activity = activity;
    }

    // onCreateViewHolder() - 아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴.
    @Override
    public MemoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.item_memo, parent, false);
        MemoAdapter.ViewHolder vh = new MemoAdapter.ViewHolder(view);

        Log.d("ddd", "onCreateViewHolder");

        return vh;
    }

    // onBindViewHolder() - position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시.
    @Override
    public void onBindViewHolder(MemoAdapter.ViewHolder holder, int position) {
        String title = mData.get(position).getTitle();
        String content = mData.get(position).getContent();
        String priority = mData.get(position).getPriority().toString();
        String date = mData.get(position).getInsertDate();
        int id = mData.get(position).getId();

        holder.memoTitle.setText(title);
        holder.memoContent.setText(content);
        holder.memoPriority.setText(priority);
        holder.memoDate.setText(date + " 작성");
        holder.button.setTag(id);
        holder.button.setOnClickListener(mListener);



    }

    // getItemCount() - 전체 데이터 갯수 리턴.
    @Override
    public int getItemCount() {
        return mData.size();
    }



}