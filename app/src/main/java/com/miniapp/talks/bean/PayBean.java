package com.miniapp.talks.bean;

/**
 * 作者:sgm
 * 描述:
 */
public class PayBean {

    /**
     * code : 1
     * message : 请求成功!
     * data : alipay_sdk=alipay-sdk-php-20180705&app_id=2018121162530291&biz_content=%7B%22body%22%3A%22%E8%B4%AD%E4%B9%B0%E5%92%AA%E9%92%BB%22%2C%22subject%22%3A%22%E8%B4%AD%E4%B9%B0%E5%92%AA%E9%92%BB%22%2C%22out_trade_no%22%3A%22MN20190729182812261603%22%2C%22timeout_express%22%3A%2230m%22%2C%22total_amount%22%3A68%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%7D&charset=UTF-8&format=json&method=alipay.trade.app.pay¬ify_url=http%3A%2F%2F47.92.85.75%2FAliPayNotify&sign_type=RSA2×tamp=2019-07-29+18%3A28%3A12&version=1.0&sign=u8bVcxGiK9D1yQYlner%2BORRbaI5Jqy5Ts0ZEOLpIVPL9JgjLIYDB%2BgJUip%2BpOmiyf9MlqjOWorDfhvEl6t6wh0e76AlQdeHl8wn9khjzU2oQGSLUohtU9s9NJZjoYBV3omK9%2FRQGZZohCquRuhFab0Y8hNXlR3sKGqNWu75KdPqc5tGQVeo9Ghm%2BgpYBL%2BDeNFlQvyMLQePSbwuu0mSHVi9ModbYsUHY0piYnbPiSsFbWnqRsOsk2ZQ05Moc2ibtZtx4S3WC4ZzAEcbsokrjpXozEvTtNnjAI045GgUKzM0HUq4Flj6J23Pe1le3OC%2BsyduwfFStCbPJqoBW5hm%2B8w%3D%3D
     */

    private int code;
    private String message;
    private String data;

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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
