package app.test.avenger.com.avengerapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.mylibrary.TestingActivity;

import java.util.ArrayList;
import java.util.List;

import app.test.avenger.com.avengerapp.R;
import app.test.avenger.com.avengerapp.model.AvengerModel;
import app.test.avenger.com.avengerapp.ui.adapter.AvengerAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class LandingActivity extends AppCompatActivity {

    @BindView(R.id.rvAvengerList)
    RecyclerView rvAvengerList;
    private Unbinder mUIBinder;
    private AvengerAdapter mAvengerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        mUIBinder = ButterKnife.bind(this);
        init();
//        startActivity(TestingActivity.getCallingIntent(this));
        Intent intent = new Intent(this, TestingActivity.class);
        startActivityForResult(intent, 300);
    }

    private void init() {
        rvAvengerList.setLayoutManager(new LinearLayoutManager(this));
        rvAvengerList.addItemDecoration(new DividerItemDecoration(this, RecyclerView.VERTICAL));
        rvAvengerList.setAdapter(getAdapter());
        mAvengerAdapter.update(getDummyData());
    }

    private AvengerAdapter getAdapter() {
        if (mAvengerAdapter == null) {
            mAvengerAdapter = new AvengerAdapter(this);
        }

        return mAvengerAdapter;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != mUIBinder)
            mUIBinder.unbind();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100 && resultCode == RESULT_OK) {
            if (data != null) {
                AvengerModel model = (AvengerModel) data.getSerializableExtra("data");
                mAvengerAdapter.update(model.getListPosition(), model);
            }
        }else{
            Toast.makeText(getApplicationContext(),"return",Toast.LENGTH_LONG).show();
        }

    }

    public List<AvengerModel> getDummyData() {
        return new ArrayList<AvengerModel>() {{
            add(new AvengerModel(R.drawable.icon_holder_hulk, "Hulk"));
        }};
    }
}
