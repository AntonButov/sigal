package pro.butovanton.sigal;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView)findViewById(R.id.my_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<satellite> satelittes = new ArrayList<satellite>();
        satelittes.add(new satellite(R.drawable.item_212, "Спутниковый интернет SenSat 2 Вт","Скорость до 45 мб/с Оптимальный вариант для северных широт и коллективного доступа."));
        satelittes.add(new satellite(R.drawable.tricolor532,"Триколор двойной GS B532M (Сибирь)","Комплект оборудования с цифровым приёмником GS B532M предназначен для просмотра спутниковых и онлайн каналов Триколор в формате стандартной и высокой (HD)(1) четкости. "));
        satadapter msatadapter = new satadapter(satelittes);
        recyclerView.setAdapter(msatadapter);
         // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();

    }

}
