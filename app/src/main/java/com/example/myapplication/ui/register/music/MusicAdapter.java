package com.example.myapplication.ui.register.music;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.List;

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.ViewHolder> {

    private List<Music> mData;
    private int selected;
    private String selectedTitle;


    // 아이템 뷰를 저장하는 뷰홀더 클래스.
    public class ViewHolder extends RecyclerView.ViewHolder  {
        RadioButton radioButton;

        ViewHolder(View itemView) {
            super(itemView);

            // 뷰 객체에 대한 참조. (hold strong reference)
            radioButton = itemView.findViewById(R.id.music_radio);

            Log.d("ddd", "ViewHolder");
        }

    }

    // 생성자에서 데이터 리스트 객체를 전달받음.
    MusicAdapter(List<Music> list) {
        mData = list;
    }

    // onCreateViewHolder() - 아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴.
    @Override
    public MusicAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.item_music, parent, false);
        MusicAdapter.ViewHolder vh = new MusicAdapter.ViewHolder(view);

        Log.d("ddd", "onCreateViewHolder");

        return vh;
    }

    // onBindViewHolder() - position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시.
    @Override
    public void onBindViewHolder(MusicAdapter.ViewHolder holder, int position) {
        String text = mData.get(position).getMusicTitle();
        holder.radioButton.setText(text);
        holder.radioButton.setChecked(false);
        holder.radioButton.setOnClickListener(v -> {
            selected = position + 1;
            notifyDataSetChanged();
        });

        if (selected - 1 == position) {
            holder.radioButton.setChecked(true);
            selectedTitle = mData.get(position).getMusicTitle();
        }

    }

    // getItemCount() - 전체 데이터 갯수 리턴.
    @Override
    public int getItemCount() {
        return mData.size();
    }

    public String getSelectedTitle() {
        return selectedTitle;
    }




}