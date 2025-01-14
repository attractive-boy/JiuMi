package com.miniapp.talks.bean;

/**
 * 作者:sgm
 * 描述:
 */
public class BindBean {

    /**
     * code : 1
     * message : 请求成功!
     * data : {"sign":"apiname=com.alipay.account.auth&app_id=2018121162530291&app_name=mc&auth_type=AUTHACCOUNT&biz _type=openservice&method=alipay.open.auth.sdk.code.get&pid=2088331823814007&product_id=APP_FAST_LOGIN&scope=ku aijie&sign_type=RSA2&target_id=559adc4e95f302308fe6ba71f25a3a3a&sign=nm%2FtoK8aCgiiH1EQX9ZF0rLXgt7QFfJt0Oldz%2 BFsimTx%2BIsp4mSO7hvbT5II%2B52KYGmr3lPULdlYZOxNjVzC8mdsXsVIXHviUrdlRkn6f1X7ZVybikbxSWn2xP4h5mdV2MCAFvaIqWY65NN G%2BL6stskkA65LK3dKiP81kV1%2FQHr5fJyKtG6PAZuhU0fReKmROG4s27%2Bqr72zz9T85o1LP7%2FFkDPwJEvzU219NBGL7cG2X9Z4YzPiC hBxmMCflJhzEl261y9ymPPgyuGzgpekOy%2Bq3LP%2BjD4H1%2BkMsTgCQ6J9b%2BpK5m9IFzbdrCoYOUz0sBI1%2B1MvHYkTZxaATGL8FQ%3D %3D"}
     */

    private int code;
    private String message;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * sign : apiname=com.alipay.account.auth&app_id=2018121162530291&app_name=mc&auth_type=AUTHACCOUNT&biz _type=openservice&method=alipay.open.auth.sdk.code.get&pid=2088331823814007&product_id=APP_FAST_LOGIN&scope=ku aijie&sign_type=RSA2&target_id=559adc4e95f302308fe6ba71f25a3a3a&sign=nm%2FtoK8aCgiiH1EQX9ZF0rLXgt7QFfJt0Oldz%2 BFsimTx%2BIsp4mSO7hvbT5II%2B52KYGmr3lPULdlYZOxNjVzC8mdsXsVIXHviUrdlRkn6f1X7ZVybikbxSWn2xP4h5mdV2MCAFvaIqWY65NN G%2BL6stskkA65LK3dKiP81kV1%2FQHr5fJyKtG6PAZuhU0fReKmROG4s27%2Bqr72zz9T85o1LP7%2FFkDPwJEvzU219NBGL7cG2X9Z4YzPiC hBxmMCflJhzEl261y9ymPPgyuGzgpekOy%2Bq3LP%2BjD4H1%2BkMsTgCQ6J9b%2BpK5m9IFzbdrCoYOUz0sBI1%2B1MvHYkTZxaATGL8FQ%3D %3D
         */

        private String sign;

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }
    }
}
