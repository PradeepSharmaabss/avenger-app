package app.test.avenger.com.avengerapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import app.test.avenger.com.avengerapp.R;
import app.test.avenger.com.avengerapp.model.AvengerModel;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class AvengerDetailActivity extends AppCompatActivity {

    @BindView(R.id.ivAvenger)
    ImageView ivAvenger;
    @BindView(R.id.rbAvengerRating)
    RatingBar rbAvengerRating;
    @BindView(R.id.tbToolbar)
    Toolbar tbToolbar;
    @BindView(R.id.tvTbTitle)
    TextView tvTbTitle;
    private Unbinder mBinder;
    private AvengerModel mAvengerModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avenger_detail);
        mBinder = ButterKnife.bind(this);
        setToolbar();
        getIntentData();
        setImage();
        setRating();
        setTitle();
        ratingChagneListner();
    }

    private void setToolbar() {
        setSupportActionBar(tbToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    private void setImage() {
        ivAvenger.setImageResource(mAvengerModel.getAvengerImage());
    }

    private void setRating() {
        rbAvengerRating.setRating(mAvengerModel.getAvengerRating());
    }

    private void setTitle() {
        tvTbTitle.setText(mAvengerModel.getAvengerName());
    }

    private void getIntentData() {
        mAvengerModel = (AvengerModel) getIntent().getSerializableExtra("data");
    }

    private void ratingChagneListner() {
        rbAvengerRating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                mAvengerModel.setAvengerRating((int) rating);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            setResult();
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != mBinder)
            mBinder.unbind();
    }

    private void setResult() {
        Intent intent = new Intent();
        intent.putExtra("data", mAvengerModel);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        setResult();
    }
}
