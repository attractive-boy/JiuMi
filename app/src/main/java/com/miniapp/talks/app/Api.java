/*
 * Copyright 2017 JessYan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.miniapp.talks.app;

/**
 * ================================================
 * 存放一些与 API 有关的东西,如请求地址,请求码等
 * <p>
 * Created by JessYan on 08/05/2016 11:25
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * ================================================
 */
public interface Api {

    /**
     * 0://线上环境
     * 1://测试环境
     */


    public static boolean IS_DEBUG = true;

    //todo 域名
    public static String APP_DOMAIN = "http://b.juchuyuncang.com/api/";//测试

    //todo 融云key
    public static String RONG_YUN_KEY ="ik1qhw09il9pp";//融云key
    //todo 替换声网
    public static String AGORA_KEY = "3b78278bd66a46bfacd50ec55d96fa5f";//声网key



        String CHANNEL = "guanfang";



    String RequestSuccess = "0";
}
