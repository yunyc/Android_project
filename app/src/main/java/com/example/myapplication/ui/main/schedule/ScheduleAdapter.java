package com.example.myapplication.ui.main.schedule;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.database.schedule.Schedule;
import com.example.myapplication.ui.Layout;

import java.util.ArrayList;
import java.util.List;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ViewHolder> {

    private List<Schedule> mData;
    private Context context;
    private ArrayList<String> dateList = new ArrayList<String>();


    // 아이템 뷰를 저장하는 뷰홀더 클래스.
    public class ViewHolder extends RecyclerView.ViewHolder  {

        LinearLayout linearLayout;

        ViewHolder(View itemView) {
            super(itemView);

            linearLayout = itemView.findViewById(R.id.schedule_item);


            // 뷰 객체에 대한 참조. (hold strong reference)


            Log.d("ddd", "ViewHolder");
        }

    }

    // 생성자에서 데이터 리스트 객체를 전달받음.
    ScheduleAdapter(List<Schedule> list, Context context) {
        mData = list;
        this.context = context;
    }



    // onCreateViewHolder() - 아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴.
    @Override
    public ScheduleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.item_schedule, parent, false);

        ScheduleAdapter.ViewHolder vh = new ScheduleAdapter.ViewHolder(view);

        Log.d("ddd", "onCreateViewHolder");

        return vh;
    }

    // onBindViewHolder() - position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시.
    @Override
    public void onBindViewHolder(ScheduleAdapter.ViewHolder holder, int position) {
        Schedule schedule = mData.get(position);
        String date = schedule.getStartDate();

        if (!dateList.contains(date)) {
            LinearLayout dateView = new Layout(context, R.layout.item_schedule_date);
            TextView dateText = (TextView) dateView.findViewById(R.id.schedule_date);
            dateText.setText(date);

            holder.linearLayout.addView(dateView);
            dateList.add(date);
        }
        LinearLayout content = new Layout(context, R.layout.item_schedule_content);

        TextView scheduleTitle = (TextView) content.findViewById(R.id.memo_title);
        scheduleTitle.setText(schedule.getTitle());

        scheduleTitle = (TextView) content.findViewById(R.id.schedule_content);
        scheduleTitle.setText(schedule.getStartDate() + "~" + schedule.getEndDate());

        holder.linearLayout.addView(content);

    }

    // getItemCount() - 전체 데이터 갯수 리턴.
    @Override
    public int getItemCount() {
        return mData.size();
    }




}