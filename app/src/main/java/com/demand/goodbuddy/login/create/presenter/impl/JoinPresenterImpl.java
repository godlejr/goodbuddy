package com.demand.goodbuddy.login.create.presenter.impl;

import com.demand.goodbuddy.login.create.interactor.JoinInteractor;
import com.demand.goodbuddy.login.create.interactor.impl.JoinInteractorImpl;
import com.demand.goodbuddy.login.create.presenter.JoinPresenter;
import com.demand.goodbuddy.login.create.view.JoinView;
import com.demand.goodbuddy.util.APIErrorUtil;

/**
 * Created by ㅇㅇ on 2017-11-17.
 */

public class JoinPresenterImpl implements JoinPresenter {
    private JoinInteractor joinInteractor;
    private JoinView joinView;

    public JoinPresenterImpl(JoinView joinView) {
        this.joinInteractor = new JoinInteractorImpl(this);
        this.joinView = joinView;
    }

    @Override
    public void init() {
        joinInteractor.setMainController();
        joinView.init();

        joinView.setToolbar();
        joinView.showToolbarTitle("회원가입");
    }

    @Override
    public void showUserBirth(String birth) {
        joinView.showUserBirth(birth);
    }

    @Override
    public void onClickBirth() {
        joinView.showDatePicker();
    }

    @Override
    public void onClickEmailCheck(String email) {
        if(email.length() > 0) {
            joinInteractor.getEmailCheck(email);
        } else {
            joinView.showMessage("이메일을 입력해주세요.");
        }
    }

    @Override
    public void onClickJoin(String email, String password, String passwordConfirm, String name, String birth, int gender) {
        if(email == null || email.length() == 0 || email.equals("")) {
            joinView.showMessage("이메일을 입력해주세요.");
            return;
        }

        if(password == null || password.length() == 0 || password.equals("")) {
            joinView.showMessage("비밀번호를 입력해주세요.");
            return;
        }

        if(passwordConfirm == null || passwordConfirm.length() == 0 || passwordConfirm.equals("")) {
            joinView.showMessage("비밀번호를 확인해주세요.");
            return;
        }

        if(name == null || name.length() == 0 || name.equals("")) {
            joinView.showMessage("이름을 입력해주세요.");
            return;
        }

        if(birth == null || birth.length() == 0 || birth.equals("")) {
            joinView.showMessage("생년월일을 입력해주세요.");
            return;
        }

        if(!password.equals(passwordConfirm)){
            joinView.showMessage("비밀번호가 일치하지 않습니다.");
            return;
        }

        if(password.length() < 6 || password.length() > 20){
            joinView.showMessage("비밀번호는 6~20자로 입력해주세요.");
            return;
        }

        if(!joinInteractor.getEmailValidation(email)){
            joinView.showMessage("올바른 이메일 형식이 아닙니다.");
            joinInteractor.setEmailChecked(false);
            return;
        }

        if(!joinInteractor.isEmailChecked()){
            joinView.showMessage("이메일 중복확인을 해주세요.");
            return;
        }

        if(gender == -1){
            joinView.showMessage("성별을 선택 해주세요.");
            return;
        }

        joinInteractor.setJoin(email, password, name, birth, gender+1);

    }

    @Override
    public void onNetworkError(APIErrorUtil apiErrorUtil) {
        if (apiErrorUtil == null) {
            joinView.showMessage("네트워크 불안정합니다. 다시 시도하세요.");
        } else {
            joinView.showMessage(apiErrorUtil.message());
        }
    }

    @Override
    public void onSuccessGetEmailCheck(Boolean check) {
        if(!check){
            joinView.showMessage("사용 가능한 이메일입니다.");
            joinInteractor.setEmailChecked(true);
        } else {
            joinView.showMessage("이미 가입된 이메일입니다.");
            joinInteractor.setEmailChecked(false);
        }
    }

    @Override
    public void onSuccessSetJoin() {
        joinView.showMessage("회원가입이 완료되었습니다.");
        joinView.navigateToLoginActivity();
    }

    @Override
    public void onClickBack() {
        joinView.navigateToBack();
    }
}
