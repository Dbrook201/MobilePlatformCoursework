// Matric Number: S1828977

package org.me.gcu.trafficscotland.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.me.gcu.trafficscotland.R;
import org.me.gcu.trafficscotland.classes.RssItem;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecycleHolder> {

    private ArrayList<RssItem> ArrayList;

    public RecyclerAdapter(ArrayList<RssItem> xml_list) {

        this.ArrayList = xml_list;
    }

    @NonNull
    @Override
    public RecycleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_adapter_layout, parent, false);
        RecycleHolder holderView = new RecycleHolder(view);
        return holderView;
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleHolder holder, int position) {

        RssItem currentItem = ArrayList.get(position);
        holder.title.setText(currentItem.getTitle());
        holder.desc.setText(currentItem.getDescription());
        SimpleDateFormat sdf = new SimpleDateFormat("M/dd/yyy");
        holder.pubDate.setText(sdf.format(currentItem.getPublishDate()));

    }

    @Override
    public int getItemCount() {
        return ArrayList.size();
    }

    public class RecycleHolder extends RecyclerView.ViewHolder {

        public TextView title;
        public TextView desc;
        public TextView pubDate;

        public RecycleHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            desc = itemView.findViewById(R.id.desc);
            pubDate = itemView.findViewById(R.id.pubDate);
        }
    }
}
