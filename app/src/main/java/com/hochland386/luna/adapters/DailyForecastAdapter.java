package com.hochland386.luna.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hochland386.luna.R;
import com.hochland386.luna.model.DailyWeather;
import com.hochland386.luna.model.Forecast;
import com.hochland386.luna.utils.DateFormatUtils;

/**
 * Created by vitaly on 10/31/15.
 */
public class DailyForecastAdapter extends BaseAdapter {

//    Members
    private Context mContext;
    private Forecast mForecast;

//    Constructor
    public DailyForecastAdapter(Context context, Forecast forecast) {
        mContext = context;
        mForecast = forecast;
    }

    //    Implementing BaseAdapter interface
    @Override
    public int getCount() {
        return mForecast.getForecastCount();
    }

    @Override
    public Object getItem(int position) {
        return mForecast.getDailyWithIndex(position);
    }

    @Override
    public long getItemId(int position) {
        return 0; // we aren't going to use this. Tag items for easy reference
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

//        Init convertView
        if (convertView == null) {
//            brand new
            convertView = LayoutInflater.from(mContext).inflate(R.layout.daily_list_item, null);
            holder = new ViewHolder();
            holder.dailyDateTv = (TextView) convertView.findViewById(R.id.dailyDateTv);
            holder.dailyTemperatureTv = (TextView) convertView.findViewById(R.id.dailyTemperatureTv);
            holder.dailySummaryIconIv = (ImageView) convertView.findViewById(R.id.dailySummaryIconIv);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

//        Setting the data
        DailyWeather dailyWeather = mForecast.getDailyWithIndex(position);
//        Convert timestamp to human-friendly date and set it
        DateFormatUtils dateFormatUtils = DateFormatUtils.getInstance();
        String formattedDate = dateFormatUtils.getFormattedDate(dailyWeather.getTimeStamp());
        holder.dailyDateTv.setText(formattedDate);
//        Setting temperature
        holder.dailyTemperatureTv.setText(String.format("%s\u00B0", dailyWeather.getTemperature()));
//        Setting dailySummaryIconIv
        switch (dailyWeather.getConditionGroup()) {
            case "Drizzle":
                holder.dailySummaryIconIv.setImageResource(R.drawable.rain);
                break;
            case "Rain":
                holder.dailySummaryIconIv.setImageResource(R.drawable.rain);
                break;
            case "Snow":
                holder.dailySummaryIconIv.setImageResource(R.drawable.snow);
                break;
            case "Atmosphere":
                holder.dailySummaryIconIv.setImageResource(R.drawable.fog);
                break;
            case "Clear":
                holder.dailySummaryIconIv.setImageResource(R.drawable.clear_day);
                break;
            case "Clouds":
                holder.dailySummaryIconIv.setImageResource(R.drawable.cloudy);
                break;
            default:
                holder.dailySummaryIconIv.setImageResource(R.drawable.clear_day);
                break;
        }

        return convertView;
    }

//    ViewHolder static class
    private static class ViewHolder {
        TextView dailyDateTv;
        TextView dailyTemperatureTv;
        ImageView dailySummaryIconIv;
    }
}
