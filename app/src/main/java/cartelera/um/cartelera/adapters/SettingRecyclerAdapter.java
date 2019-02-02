package cartelera.um.cartelera.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cartelera.um.cartelera.R;
import cartelera.um.cartelera.entities.Setting;
import cartelera.um.cartelera.listeners.AdapterClickListener;


/**
 * Created by cyazky on 11/19/17.
 */

public class SettingRecyclerAdapter extends RecyclerView.Adapter<SettingRecyclerAdapter.CustomViewHolder> {
    private List<Setting> settingDataSet;
    private Context mContext;
    private AdapterClickListener clickListener;

    public SettingRecyclerAdapter(Context context, List<Setting> dataSet, AdapterClickListener clickListener) {
        this.settingDataSet = dataSet;
        this.mContext = context;
        this.clickListener = clickListener;
    }

    @Override
    public SettingRecyclerAdapter.CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.setting_row_layout, null);
        final SettingRecyclerAdapter.CustomViewHolder viewHolder = new SettingRecyclerAdapter.CustomViewHolder(view);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onItemClick(v, viewHolder.getAdapterPosition());
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(SettingRecyclerAdapter.CustomViewHolder customViewHolder, int i) {
        Setting setting = settingDataSet.get(i);

        Resources res = mContext.getResources();
        customViewHolder.title.setText(setting.getTitle());
        customViewHolder.icon.setImageDrawable(res.getDrawable(setting.getIcon()));
    }

    @Override
    public int getItemCount() {
        return (null != settingDataSet ? settingDataSet.size() : 0);
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        protected ImageView icon;
        protected TextView title;

        public CustomViewHolder(View view) {
            super(view);
            this.title =  view.findViewById(R.id.title);
            this.icon = view.findViewById(R.id.icon);
        }
    }
}