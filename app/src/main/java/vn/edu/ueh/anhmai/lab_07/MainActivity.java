package vn.edu.ueh.anhmai.lab_07;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    Button btLoad;
    List<Article> articleList = new ArrayList<>();
    MyAdapter myAdapter;


    private final ActivityResultLauncher<Intent> detailActivityLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    Article updatedArticle = result.getData().getParcelableExtra("updated_article");
                    int position = result.getData().getIntExtra("article_position", -1);

                    if (position != -1 && updatedArticle != null) {
                        articleList.set(position, updatedArticle);
                        myAdapter.notifyItemChanged(position);//
                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerView = findViewById(R.id.recyclerView);
        btLoad = findViewById(R.id.btLoad);


        String bai1_content = "Hôm nay, ngày 21/09/2026, thời tiết tại TP.HCM tiếp tục có mưa rào vào chiều tối. " +
                "Người dân khi tham gia giao thông cần chú ý mang theo áo mưa và hạn chế di chuyển qua các tuyến đường " +
                "thường xuyên ngập nước như Nguyễn Hữu Cảnh, Huỳnh Tấn Phát. Bên cạnh đó, tình hình triều cường " +
                "đang có xu hướng dâng cao trong những ngày tới, các địa phương ven sông cần có biện pháp ứng phó kịp thời. " +
                "Đài khí tượng thủy văn khu vực Nam Bộ cũng cảnh báo nguy cơ xảy ra lốc xoáy và gió giật mạnh...";

        articleList.add(new Article("Bản tin Thời tiết số 1", bai1_content, R.drawable.img_thoisu, 0));


        String bai2_content = "Đại học Kinh tế TPHCM (UEH) vừa chính thức khánh thành thêm cơ sở mới hiện đại " +
                "nhằm phục vụ nhu cầu học tập và nghiên cứu của sinh viên. Cơ sở mới được trang bị hệ thống phòng máy tính " +
                "cấu hình cao, thư viện thông minh với hàng ngàn đầu sách điện tử, cùng các khu vực tự học không gian mở. " +
                "Đặc biệt, cơ sở này áp dụng 100% công nghệ xanh, sử dụng năng lượng mặt trời và hệ thống thu gom nước mưa...";

        articleList.add(new Article("Khám phá cơ sở mới UEH", bai2_content, R.drawable.img_ueh, 0));
        articleList.add(new Article("Học lập trình Android", "Cách sử dụng RecyclerView và Parcelable trong Java...", R.drawable.img_hocandroid, 0));

        btLoad.setOnClickListener(v -> {

            myAdapter = new MyAdapter(MainActivity.this, articleList, (article, position) -> {
                Intent intent = new Intent(MainActivity.this, ArticleDetailActivity.class);
                intent.putExtra("article_data", article);
                intent.putExtra("article_position", position);

                detailActivityLauncher.launch(intent);//
            });

            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(myAdapter);
        });
    }
}