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
 * Copyright 2015 Vitaly Sulimov <quarry386@fastmail.com>
 * <p/>
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 * <p/>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * <p/>
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301, USA.
 */
public class DailyForecastAdapter extends BaseAdapter {

    private Context mContext;
    private Forecast mForecast;

    public DailyForecastAdapter(Context context, Forecast forecast) {
        mContext = context;
        mForecast = forecast;
    }

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
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {

            convertView = LayoutInflater.from(mContext).inflate(R.layout.daily_list_item, null);
            holder = new ViewHolder();
            holder.dailyDateTv = (TextView) convertView.findViewById(R.id.dailyDateTv);
            holder.dailyTemperatureTv = (TextView) convertView.findViewById(R.id.dailyTemperatureTv);
            holder.dailySummaryIconIv = (ImageView) convertView.findViewById(R.id.dailySummaryIconIv);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        DailyWeather dailyWeather = mForecast.getDailyWithIndex(position);

        DateFormatUtils dateFormatUtils = DateFormatUtils.getInstance();
        String formattedDate = dateFormatUtils.getFormattedDate(dailyWeather.getTimeStamp());
        holder.dailyDateTv.setText(formattedDate);

        holder.dailyTemperatureTv.setText(String.format("%s\u00B0", dailyWeather.getTemperature()));

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

    private static class ViewHolder {
        TextView dailyDateTv;
        TextView dailyTemperatureTv;
        ImageView dailySummaryIconIv;
    }
}
