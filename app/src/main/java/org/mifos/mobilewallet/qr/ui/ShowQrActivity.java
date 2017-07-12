package org.mifos.mobilewallet.qr.ui;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import org.mifos.mobilewallet.R;
import org.mifos.mobilewallet.auth.AuthContract;
import org.mifos.mobilewallet.auth.presenter.AddAccountPresenter;
import org.mifos.mobilewallet.auth.ui.AddAccountActivity;
import org.mifos.mobilewallet.core.BaseActivity;
import org.mifos.mobilewallet.qr.QrContract;
import org.mifos.mobilewallet.qr.presenter.ShowQrPresenter;
import org.mifos.mobilewallet.utils.Constants;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by naman on 8/7/17.
 */

public class ShowQrActivity extends BaseActivity implements QrContract.ShowQrView {

    @Inject
    ShowQrPresenter mPresenter;

    QrContract.ShowQrPresenter mShowQrPresenter;

    @BindView(R.id.iv_qr_code)
    ImageView ivQrCode;

    @BindView(R.id.tv_qr_data)
    TextView tvQrData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivityComponent().inject(this);

        setContentView(R.layout.activity_show_qr);

        ButterKnife.bind(ShowQrActivity.this);

        setToolbarTitle("QR code");
        showBackButton();
        mPresenter.attachView(this);

        String qrData = getIntent().getStringExtra(Constants.QR_DATA);

        mShowQrPresenter.generateQr(qrData);
        tvQrData.setText(qrData);

    }

    @Override
    public void setPresenter(QrContract.ShowQrPresenter presenter) {
        this.mShowQrPresenter = presenter;
    }

    @Override
    public void showGeneratedQr(Bitmap bitmap) {
        ivQrCode.setImageBitmap(bitmap);

    }
}
