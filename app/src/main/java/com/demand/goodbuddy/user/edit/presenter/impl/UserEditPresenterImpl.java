package com.demand.goodbuddy.user.edit.presenter.impl;

import com.demand.goodbuddy.dto.User;
import com.demand.goodbuddy.flag.UserFlag;
import com.demand.goodbuddy.user.edit.interactor.UserEditInteractor;
import com.demand.goodbuddy.user.edit.interactor.impl.UserEditInteractorImpl;
import com.demand.goodbuddy.user.edit.presenter.UserEditPresenter;
import com.demand.goodbuddy.user.edit.view.UserEditView;
import com.demand.goodbuddy.util.APIErrorUtil;

/**
 * Created by ㅇㅇ on 2017-11-17.
 */

public class UserEditPresenterImpl implements UserEditPresenter {
    private UserEditInteractor userEditInteractor;
    private UserEditView userEditView;

    public UserEditPresenterImpl(UserEditView userEditView) {
        this.userEditInteractor = new UserEditInteractorImpl(this);
        this.userEditView = userEditView;
    }

    @Override
    public void init(User user) {
        userEditInteractor.setUser(user);
        userEditView.init();

        String accessToken = user.getAccessToken();
        userEditInteractor.setUserController(accessToken);
        userEditInteractor.setMainController(accessToken);

        userEditView.showUserName(user.getName());
        userEditView.showUserBirth(user.getBirth());

        int gender = user.getGender();
        if(gender == UserFlag.MAN){
            userEditView.setRadioButtonCheckForMan();
        }
        if(gender == UserFlag.FEMALE){
            userEditView.setRadioButtonCheckForFemale();
        }

        userEditView.setToolbar();
        userEditView.showToolbarTitle("프로필 설정");
    }

    @Override
    public void onClickEditProfile(String name, String birth, int gender) {
        User user= userEditInteractor.getUser();
        int userId= user.getId();

        if (name == null || name.length() == 0 || name.equals("")) {
            userEditView.showMessage("이름을 입력해주세요.");
            return;
        }

        if (birth == null || birth.length() == 0 || birth.equals("")) {
            userEditView.showMessage("생년월일을 입력해주세요.");
            return;
        }

        if (gender == -1) {
            userEditView.showMessage("성별을 선택 해주세요.");
            return;
        }

        userEditInteractor.updateUser(userId, name, birth, gender + 1);
    }

    @Override
    public void onClickEditPassword(String password, String passwordConfirm) {
        User user= userEditInteractor.getUser();
        int userId= user.getId();

        if (password == null || password.length() == 0 || password.equals("")) {
            userEditView.showMessage("비밀번호를 입력해주세요.");
            return;
        }

        if (passwordConfirm == null || passwordConfirm.length() == 0 || passwordConfirm.equals("")) {
            userEditView.showMessage("비밀번호를 확인해주세요.");
            return;
        }

        if (!password.equals(passwordConfirm)) {
            userEditView.showMessage("비밀번호가 일치하지 않습니다.");
            return;
        }

        if (password.length() < 6 || password.length() > 20) {
            userEditView.showMessage("비밀번호는 6~20자로 입력해주세요.");
            return;
        }

        userEditInteractor.updatePassword(userId, password);
    }

    @Override
    public void showUserBirth(String message) {
        userEditView.showUserBirth(message);
    }

    @Override
    public void onNetworkError(APIErrorUtil apiErrorUtil) {
        if (apiErrorUtil == null) {
            userEditView.showMessage("네트워크 불안정합니다. 다시 시도하세요.");
        } else {
            userEditView.showMessage(apiErrorUtil.message());
        }
    }

    @Override
    public void onClickBack() {
        userEditView.navigateToBack();
    }

    @Override
    public void onSuccessUpdateUser(User user) {
        User prevUser= userEditInteractor.getUser();
        user.setAccessToken(prevUser.getAccessToken());
        userEditInteractor.setUser(user);
        userEditView.setUser(user);

        String accessToken = user.getAccessToken();
        userEditInteractor.setMainController(accessToken);
        userEditInteractor.setMainController(accessToken);

        userEditView.showMessage("프로필이 변경되었습니다.");
    }

    @Override
    public void onSuccessUpdatePassword() {
        userEditView.showMessage("비밀번호가 변경되었습니다.");
    }

    @Override
    public void onClickBirth() {
        userEditView.showDatePicker();
    }
}
