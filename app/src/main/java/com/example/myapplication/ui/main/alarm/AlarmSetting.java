package com.example.myapplication.ui.main.alarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import java.util.Calendar;

public class AlarmSetting {

    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;
    private Calendar calendar;
    private Context context;

    public AlarmSetting(Context context) {
        this.alarmManager = (AlarmManager) context.getSystemService(context.ALARM_SERVICE);
        this.context = context;
    }

    public void setCalendar(int hour, int minute) {
        Calendar cal = Calendar.getInstance();

        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, minute);
        cal.set(Calendar.SECOND, 0);

        this.calendar = cal;
    }

    /* 알람 시작 */
    public void start(int id) {

        // 현재시간보다 이전이면
        if (calendar.before(Calendar.getInstance())) {
            // 다음날로 설정
            calendar.add(Calendar.DATE, 1);
        }

        // Receiver 설정
        Intent intent = new Intent(context, AlarmReceiver.class);
        // state 값이 on 이면 알람시작, off 이면 중지
        intent.putExtra("state", "on");

        pendingIntent = PendingIntent.getBroadcast(context, id, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        // 알람 설정
        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);


    }

    /* 알람 중지 */
    public void stop(int id) {

        // 알람 중지 Broadcast
        Intent intent = new Intent(context, AlarmReceiver.class);
        intent.putExtra("state","off");

        pendingIntent = PendingIntent.getBroadcast(context, id, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        // 알람 취소
        alarmManager.cancel(pendingIntent);

        context.sendBroadcast(intent);

    }
}
