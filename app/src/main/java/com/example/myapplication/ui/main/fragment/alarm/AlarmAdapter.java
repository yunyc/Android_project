package com.example.myapplication.ui.main.fragment.alarm;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.database.alarm.Alarm;

import java.util.List;

public class AlarmAdapter extends RecyclerView.Adapter<AlarmAdapter.ViewHolder> {

    private List<Alarm> mData;

    // 아이템 뷰를 저장하는 뷰홀더 클래스.
    public class ViewHolder extends RecyclerView.ViewHolder {
        ToggleButton alarmSwitch;
        TextView alarmTime;
        TextView alarmDay;
        TextView alarmTitle;
        Button alarmSetting;


        ViewHolder(View itemView) {
            super(itemView);

            // 뷰 객체에 대한 참조. (hold strong reference)
            alarmSwitch = itemView.findViewById(R.id.alarm_switch);
            alarmTime = itemView.findViewById(R.id.alarm_time);
            alarmDay = itemView.findViewById(R.id.alarm_day);
            alarmTitle = itemView.findViewById(R.id.memo_title);
            alarmSetting = itemView.findViewById(R.id.alarm_setting);
        }
    }

    // 생성자에서 데이터 리스트 객체를 전달받음.
    AlarmAdapter(List<Alarm> list) {
        mData = list;
    }

    // onCreateViewHolder() - 아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴.
    @Override
    public AlarmAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.item_alarm, parent, false);
        AlarmAdapter.ViewHolder vh = new AlarmAdapter.ViewHolder(view);

        return vh;
    }

    // onBindViewHolder() - position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시.
    @Override
    public void onBindViewHolder(AlarmAdapter.ViewHolder holder, int position) {
        String title = mData.get(position).getTitle();

        holder.alarmTitle.setText(title);
    }

    // getItemCount() - 전체 데이터 갯수 리턴.
    @Override
    public int getItemCount() {
        return mData.size();
    }
}