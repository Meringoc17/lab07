package vn.edu.ueh.anhmai.lab_07;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

// Vẫn giữ nguyên cấu trúc kế thừa CountryViewHolder của thầy
public class MyAdapter extends RecyclerView.Adapter<CountryViewHolder> {

    // Nâng cấp: Đổi thành List chứa Article
    private List<Article> articleList;
    private LayoutInflater mInflater;
    private OnItemClickListener listener;

    // Interface để MainActivity có thể bắt được sự kiện click
    public interface OnItemClickListener {
        void onItemClick(Article article, int position);
    }

    // Cập nhật lại Constructor
    public MyAdapter(Context context, List<Article> list, OnItemClickListener listener) {
        this.mInflater = LayoutInflater.from(context);
        this.articleList = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CountryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Vẫn gọi đúng layout cũ đã được nâng cấp
        View view = mInflater.inflate(R.layout.country_layout, parent, false);
        return new CountryViewHolder(view, this);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryViewHolder holder, int position) {
        // Nâng cấp: Gắn dữ liệu của Article vào ViewHolder
        Article article = articleList.get(position);
        holder.tvTitle.setText(article.getTitle());
        holder.tvContent.setText(article.getContent());
        holder.tvViews.setText("Views: " + article.getView());
        holder.imgCover.setImageResource(article.getImg_cover());
    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }

    // Hàm này được gọi từ CountryViewHolder khi user click vào item
    public void onItemClicked(int position) {
        if (listener != null) {
            listener.onItemClick(articleList.get(position), position);
        }
    }
}