package vn.edu.ueh.anhmai.lab_07;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ArticleDetailActivity extends AppCompatActivity {

    private Article article;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_detail);

        ImageView imgCover = findViewById(R.id.imgDetailCover);
        TextView tvTitle = findViewById(R.id.tvDetailTitle);
        TextView tvViews = findViewById(R.id.tvDetailViews);
        TextView tvContent = findViewById(R.id.tvDetailContent);


        if (getIntent() != null) {
            article = getIntent().getParcelableExtra("article_data");
            position = getIntent().getIntExtra("article_position", -1);

            if (article != null) {
                article.setView(article.getView() + 1);//
                imgCover.setImageResource(article.getImg_cover());
                tvTitle.setText(article.getTitle());
                tvViews.setText("Views: " + article.getView());
                tvContent.setText(article.getContent());
            }
        }


        Button btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> {
            returnDataAndClose();
        });


        getOnBackPressedDispatcher().addCallback(this, new androidx.activity.OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                returnDataAndClose();
            }
        });
    }


    private void returnDataAndClose() {
        Intent returnIntent = new Intent();
        returnIntent.putExtra("updated_article", article);
        returnIntent.putExtra("article_position", position);
        setResult(RESULT_OK, returnIntent);
        finish();
    }
}