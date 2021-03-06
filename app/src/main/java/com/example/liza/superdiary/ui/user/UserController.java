package com.example.liza.superdiary.ui.user;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.bluelinelabs.conductor.RouterTransaction;
import com.bluelinelabs.conductor.changehandler.FadeChangeHandler;
import com.bluelinelabs.conductor.changehandler.HorizontalChangeHandler;
import com.example.liza.superdiary.R;
import com.example.liza.superdiary.ui.list.ListController;
import com.example.liza.superdiary.ui.main.MoxyController;
import com.example.liza.superdiary.ui.start.StartController;

import static com.example.liza.superdiary.ui.list.ListController.LIST_CONTROLLER;
import static com.example.liza.superdiary.ui.list.ListController.NOTES;
import static com.example.liza.superdiary.ui.list.ListController.NOTIFICATIONS;
import static com.example.liza.superdiary.ui.list.ListController.TASKS;
import static com.example.liza.superdiary.ui.main.MainActivity.ANIM_LENGTH;

/**
 * Created by User on 15.05.2017.
 */

public class UserController extends MoxyController implements UserView {

    @InjectPresenter
    public UserPresenter userPresenter;

    @Override
    protected View inflateView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.controller_user, container, false);
    }

    @Override
    protected void onViewBound(@NonNull View view) {
        super.onViewBound(view);
        view.findViewById(R.id.buttonNotes).setOnClickListener(view1 -> showListController(NOTES));
        view.findViewById(R.id.buttonNotifications).setOnClickListener(view1 -> showListController(NOTIFICATIONS));
        view.findViewById(R.id.buttonTasks).setOnClickListener(view1 -> showListController(TASKS));
        view.findViewById(R.id.buttonLogout).setOnClickListener(view1 -> userPresenter.logout());
    }

    private void showListController(int type) {
        getRouter().pushController(RouterTransaction.with(new ListController(type))
                .tag(LIST_CONTROLLER)
                .pushChangeHandler(new FadeChangeHandler(ANIM_LENGTH))
                .popChangeHandler(new FadeChangeHandler(ANIM_LENGTH)));
    }

    @Override
    public void showStartController() {
        getRouter().setRoot(RouterTransaction.with(new StartController())
                .tag("start")
                .pushChangeHandler(new HorizontalChangeHandler(ANIM_LENGTH))
                .popChangeHandler(new HorizontalChangeHandler(ANIM_LENGTH)));
    }
}
