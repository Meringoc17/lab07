package vn.edu.ueh.anhmai.lab_07;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class CountryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private MyAdapter mAdapter;
    public TextView tvTitle, tvContent, tvViews;
    public ImageView imgCover;

    public CountryViewHolder(@NonNull View item, MyAdapter adapter) {
        super(item);
        this.mAdapter = adapter;


        this.tvTitle = item.findViewById(R.id.tvTitle);
        this.tvContent = item.findViewById(R.id.tvContent);
        this.tvViews = item.findViewById(R.id.tvViews);
        this.imgCover = item.findViewById(R.id.imgCover);

        item.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        mAdapter.onItemClicked(getAdapterPosition());
    }
}