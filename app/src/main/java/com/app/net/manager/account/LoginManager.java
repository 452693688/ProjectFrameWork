package com.app.net.manager.account;

import com.app.net.common.NetSource;
import com.app.net.common.RequestBack;
import com.app.net.common.AbstractManager;
import com.app.net.req.LoginBeanReq;
import com.app.net.res.ResultObject;
import com.app.net.res.SysUser;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2016/9/7.
 */
public class LoginManager extends AbstractManager {


    public LoginManager(RequestBack requestBack) {
        super(requestBack);

    }

    private LoginBeanReq loginBeanReq;

    public void setData(String account, String password) {
        if (loginBeanReq == null) {
            loginBeanReq = new LoginBeanReq();
        }
        loginBeanReq.setUserMobile(account);
        loginBeanReq.setUserPassword(password);
    }

    public void doRequest() {
        ApiAccount service = NetSource.getRetrofit().create(ApiAccount.class);
        Call<ResultObject<SysUser>> call = service.loginIn(loginBeanReq);
        call.enqueue(new Callback<ResultObject<SysUser>>() {
            @Override
            public void onResponse(Call<ResultObject<SysUser>> call, Response<ResultObject<SysUser>> response) {
                ResultObject<SysUser> body = response.body();
            }

            @Override
            public void onFailure(Call<ResultObject<SysUser>> call, Throwable t) {
            }
        });

    }

    public void request() {
        ApiAccount service = NetSource.getRetrofit().create(ApiAccount.class);
        Call<ResultObject<SysUser>> call = service.loginIn(loginBeanReq);
        call.enqueue(new DataManagerListener<ResultObject<SysUser>>() {
            @Override
            public Object getObject(Response<ResultObject<SysUser>> response) {
                ResultObject<SysUser> body = response.body();
                SysUser obj = body.getObj();
                return obj;
            }
        });

    }
}
