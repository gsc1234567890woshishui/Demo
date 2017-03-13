package com.example.administrator.myplayerdemo.forms;

import android.app.Activity;
import android.provider.ContactsContract;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.example.administrator.myplayerdemo.R;

import eu.inmite.android.lib.validations.form.FormValidator;
import eu.inmite.android.lib.validations.form.annotations.NotEmpty;
import eu.inmite.android.lib.validations.form.annotations.RegExp;
import eu.inmite.android.lib.validations.form.callback.SimpleErrorPopupCallback;

import static eu.inmite.android.lib.validations.form.annotations.RegExp.EMAIL;

/**
 * Created by Administrator on 2017/3/13 0013.
 */

public class FormAct extends BaseAct{
    @NotEmpty(messageId = R.string.name, order = 1)
    private EditText ed;
    @RegExp(value = EMAIL,messageId = R.string.email,order = 2)
    private EditText emal;
    /**
     * 业务逻辑处理
     */
    @Override
    protected void doBusy() {
      ed=findviews(R.id.et);
        emal=findviews(R.id.et_emal);
        emal.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                FormValidator.validate(FormAct.this, new SimpleErrorPopupCallback(FormAct.this));
            }
        });
        FormValidator.validate(this, new SimpleErrorPopupCallback(this));
    }
    /**
     * @return
     * 加载布局
     */
    @Override
    protected int getLayoutId() {
        return R.layout.form;
    }
}
