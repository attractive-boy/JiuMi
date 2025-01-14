package com.miniapp.talks.service;

import com.miniapp.talks.bean.ActiveListBean;
import com.miniapp.talks.bean.AdminUser;
import com.miniapp.talks.bean.AgreeCpResult;
import com.miniapp.talks.bean.AgreementBean;
import com.miniapp.talks.bean.AliInfor;
import com.miniapp.talks.bean.AllCommentBean;
import com.miniapp.talks.bean.BXShuoMingTextBean;
import com.miniapp.talks.bean.BannerBean;
import com.miniapp.talks.bean.BaoXiangBean;
import com.miniapp.talks.bean.BaseBean;
import com.miniapp.talks.bean.BindBean;
import com.miniapp.talks.bean.BoxExchangeBean;
import com.miniapp.talks.bean.BoxOpenRecordBean;
import com.miniapp.talks.bean.CPDetailsBean;
import com.miniapp.talks.bean.CashHis;
import com.miniapp.talks.bean.CategorRoomBean;
import com.miniapp.talks.bean.ChaMoneyBean;
import com.miniapp.talks.bean.CodeBean;
import com.miniapp.talks.bean.CollectionRoomListBean;
import com.miniapp.talks.bean.CommentBean;
import com.miniapp.talks.bean.ConstellationBean;
import com.miniapp.talks.bean.DengJiBean;
import com.miniapp.talks.bean.DynamicDetailsBean;
import com.miniapp.talks.bean.DynamicSearchBean;
import com.miniapp.talks.bean.EmojiBean;
import com.miniapp.talks.bean.EnterRoom;
import com.miniapp.talks.bean.FollowBean;
import com.miniapp.talks.bean.GifBean;
import com.miniapp.talks.bean.GiftListBean;
import com.miniapp.talks.bean.GoodsList;
import com.miniapp.talks.bean.GuanFangLianXiBean;
import com.miniapp.talks.bean.IncomeBean;
import com.miniapp.talks.bean.JinSheng;
import com.miniapp.talks.bean.Login;
import com.miniapp.talks.bean.LookCommentBean;
import com.miniapp.talks.bean.MeYiDuiBean;
import com.miniapp.talks.bean.MessageYoBean;
import com.miniapp.talks.bean.Microphone;
import com.miniapp.talks.bean.MiniOfficBean;
import com.miniapp.talks.bean.MoneyBean;
import com.miniapp.talks.bean.MusicYinxiao;
import com.miniapp.talks.bean.MyPackBean;
import com.miniapp.talks.bean.MyPersonalCebterBean;
import com.miniapp.talks.bean.OffiMessageBean;
import com.miniapp.talks.bean.OnlineUser;
import com.miniapp.talks.bean.OpenBoxBean;
import com.miniapp.talks.bean.OtherBean;
import com.miniapp.talks.bean.OtherUser;
import com.miniapp.talks.bean.PayBean;
import com.miniapp.talks.bean.Rank;
import com.miniapp.talks.bean.RecommenRoomBean;
import com.miniapp.talks.bean.RecommendLabelBean;
import com.miniapp.talks.bean.RecommendedDynamicBean;
import com.miniapp.talks.bean.Register;
import com.miniapp.talks.bean.ReportBean;
import com.miniapp.talks.bean.RoomBg;
import com.miniapp.talks.bean.RoomRankBean;
import com.miniapp.talks.bean.RoomType;
import com.miniapp.talks.bean.RoomUsersBean;
import com.miniapp.talks.bean.Search;
import com.miniapp.talks.bean.SearchAdmin;
import com.miniapp.talks.bean.SearchHis;
import com.miniapp.talks.bean.SearchLabelBean;
import com.miniapp.talks.bean.SendGemResult;
import com.miniapp.talks.bean.SendGiftResult;
import com.miniapp.talks.bean.StartLoftBean;
import com.miniapp.talks.bean.TopicBean;
import com.miniapp.talks.bean.TopicDynamicBean;
import com.miniapp.talks.bean.UnreadBean;
import com.miniapp.talks.bean.UpVideoResult;
import com.miniapp.talks.bean.UserBean;
import com.miniapp.talks.bean.UserFriend;
import com.miniapp.talks.bean.VipBean;
import com.miniapp.talks.bean.VipCenterBean;
import com.miniapp.talks.bean.WaitList;
import com.miniapp.talks.bean.Winner;
import com.miniapp.talks.bean.Wxmodel;
import com.miniapp.talks.bean.XuYaoMiZuanBean;
import com.miniapp.talks.bean.Yinxiao;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.QueryMap;

import static com.miniapp.talks.app.Api.APP_DOMAIN;


/**
 * 接口
 */
public interface CommonService {

    /**
     * 注册
     */
    @POST("registered")
    @FormUrlEncoded
    Observable<Register> register(@Field("phone") String phone,
                                  @Field("sex") String sex,
                                  @Field("birthday") String birthday,
                                  @Field("pass") String pass,
                                  @Field("nickname") String nickname,
                                  @Field("headimgurl") String headimgurl,
                                  @Field("system") String system,
                                  @Field("channel") String channel,
                                  @Field("code") String code);

    /**
     * 登录
     */
    @POST(APP_DOMAIN + "login")
    @FormUrlEncoded
    Observable<Login> login(@Field("phone") String phone,
                            @Field("pass") String pass);

    /**
     * 验证码
     */
    @POST(APP_DOMAIN + "verification")
    @FormUrlEncoded
    Observable<CodeBean> verification(
            @Field("phone") String phone,
            @Field("type") String type
    );

    /**
     * 校验验证码
     */
    @POST(APP_DOMAIN + "is_verification")
    @FormUrlEncoded
    Observable<Register> is_verification(@Field("phone") String phone, @Field("code") String rand);

    /**
     * 获取房间推荐位的房间
     */
    @POST(APP_DOMAIN + "room_recommend_room")
    @FormUrlEncoded
    Observable<RecommenRoomBean> getrecommendroom(@Field("categories") int categories,
                                                  @Field("page") int page);

    /**
     * 获取房间推荐位类别
     */
    @POST(APP_DOMAIN + "room_recommend_categories")
    Observable<CategorRoomBean> room_categories();

    /**
     * 获取热门房间
     */
    @POST(APP_DOMAIN + "is_popular")
    Observable<RecommenRoomBean> is_popular();

    /**
     * 获取密聊推荐房间
     */
    @POST(APP_DOMAIN + "secret_chat")
    Observable<RecommenRoomBean> secret_chat();

    /**
     * 微星阁
     */
    @POST(APP_DOMAIN + "star_loft")
    @FormUrlEncoded
    Observable<StartLoftBean> star_loft(@Field("sex") int sex);

    /**
     * 轮播图
     */
    @POST(APP_DOMAIN + "carousel")
    @FormUrlEncoded
    Observable<BannerBean> carousel(@Field("xx") String xx);

    /**
     * 创建编辑房间
     */
    @POST(APP_DOMAIN + "edit_room")
    @FormUrlEncoded
    Observable<Register> create_or_edit_room(
            @Field("room_pass") String room_pass,
            @Field("room_name") String room_name,
            @Field("room_type") String room_type,
            @Field("room_intro") String room_intro,

            @Field("room_background") String room_background,
            @Field("uid") String uid,
            @Field("cover") String room_cover
    );

    /**
     * 获取房间类型
     */
    @POST(APP_DOMAIN + "room_type")
    @FormUrlEncoded
    Observable<RoomType> room_type(@Field("xx") String xx);

    /**
     * 获取房间背景图*
     */
    @POST(APP_DOMAIN + "room_background")
    @FormUrlEncoded
    Observable<RoomBg> room_background(@Field("xx") String xx);

    /**
     * 获取礼物列表*
     */
    @POST(APP_DOMAIN + "gift_list")
    @FormUrlEncoded
    Observable<GiftListBean> gift_list(@Field("user_id") String user_id);

    /**
     * 进入房间*
     */
    @POST(APP_DOMAIN + "enter_room")
    @FormUrlEncoded
    Observable<EnterRoom> enter_room(
            @Field("uid") String uid,
            @Field("room_pass") String room_pass,
            @Field("user_id") String user_id
    );

    /**
     * 获取麦的信息
     */
    @POST(APP_DOMAIN + "microphone_status")
    @FormUrlEncoded
    Observable<Microphone> microphone_status(
            @Field("uid") String uid);

    /**
     * 上麦
     */
    @POST(APP_DOMAIN + "up_microphone")
    @FormUrlEncoded
    Observable<UpVideoResult> up_microphone(
            @Field("uid") String uid, @Field("user_id") String user_id, @Field("position") String position);

    /**
     * 下麦
     */
    @POST(APP_DOMAIN + "go_microphone")
    @FormUrlEncoded
    Observable<BaseBean> go_microphone(
            @Field("uid") String uid, @Field("user_id") String user_id);

    /**
     * 上传音乐*
     */
    @POST(APP_DOMAIN + "upload_music")
    @FormUrlEncoded
    Observable<BaseBean> upload_music(
            @Field("singer") String singer,
            @Field("music") String music,
            @Field("music_name") String music_name,
            @Field("upload_name") String upload_name);

    /**
     * 动态详情*
     */
    @POST(APP_DOMAIN + "dynamic_details")
    @FormUrlEncoded
    Observable<DynamicDetailsBean> dynamic_details(
            @Field("user_id") String user_id,
            @Field("sort") String sort,
            @Field("page") String page,
            @Field("id") String id);

    /**
     * 发布动态*
     */
    @POST(APP_DOMAIN + "send_dynamic")
    @Multipart
    Observable<BaseBean> send_dynamic(
            @Part("user_id") int user_id,
            @QueryMap Map<String, Object> op,
            @Part MultipartBody.Part audioFile,
            @Part MultipartBody.Part videoFile,
            @Part MultipartBody.Part img1File,
            @Part MultipartBody.Part img2File,
            @Part MultipartBody.Part img3File,
            @Part MultipartBody.Part img4File,
            @Part MultipartBody.Part img5File,
            @Part MultipartBody.Part img6File);

    /**
     * 发评论*
     */
    @POST(APP_DOMAIN + "dynamic_comment")
    @FormUrlEncoded
    Observable<BaseBean> dynamic_comment(
            @Field("b_dynamic_id") int b_dynamic_id,
            @Field("user_id") int user_id,
            @Field("content") String content);

    /**
     * 收藏动态*
     */
    @POST(APP_DOMAIN + "dynamic_collection")
    @FormUrlEncoded
    Observable<BaseBean> dynamic_collection(
            @Field("b_dynamic_id") int b_dynamic_id,
            @Field("user_id") int user_id);

    /**
     * 社区动态搜索*
     */
    @POST(APP_DOMAIN + "dynamic_search")
    @FormUrlEncoded
    Observable<DynamicSearchBean> dynamic_search(
            @Field("search") int search);

    /**
     * 获取推荐动态*
     */
    @POST(APP_DOMAIN + "dynamicTjList")
    @FormUrlEncoded
    Observable<RecommendedDynamicBean> recommended_dynamic(
            @Field("user_id") String userId,
            @Field("page") String page);

    /**
     * 获取最新动态*
     */
    @POST(APP_DOMAIN + "dynamicNewList")
    @FormUrlEncoded
    Observable<RecommendedDynamicBean> new_dynamic(
            @Field("user_id") String user_id,
            @Field("page") String page);

    /**
     * 获取热门话题*
     */
    @POST(APP_DOMAIN + "topic")
    @FormUrlEncoded
    Observable<TopicBean> topic(@Field("type") String type);

    /**
     * 获取话题内动态*
     */
    @POST(APP_DOMAIN + "topic_dynamic")
    @FormUrlEncoded
    Observable<TopicDynamicBean> topic_dynamic(
            @Field("tags") String tags,
            @Field("user_id") String user_id,
            @Field("page") String page,
            @Field("type") String type);

    /**
     * 赞动态*
     */
    @POST(APP_DOMAIN + "dynamics_hand")
    @FormUrlEncoded
    Observable<BaseBean> dynamic_praise(
            @Field("user_id") String user_id,
            @Field("target_id") String target_id,
            @Field("type") String type,
            @Field("hand") String hand);


    /**
     * 赞评论*
     */
    @POST(APP_DOMAIN + "comment_praise")
    @FormUrlEncoded
    Observable<BaseBean> comment_praise(
            @Field("b_dynamic_id") int b_dynamic_id,
            @Field("user_id") int user_id);


    /**
     * 获取置顶位置的房间
     */
    @POST(APP_DOMAIN + "is_top")
    @FormUrlEncoded
    Observable<RecommenRoomBean> is_top(@Field("xs") String xx);

    /**
     * 获取置顶位置的房间
     */
    @POST(APP_DOMAIN + "is_pass")
    @FormUrlEncoded
    Observable<BaseBean> is_pass(@Field("uid") String uid);

    /**
     * 锁麦
     */
    @POST(APP_DOMAIN + "shut_microphone")
    @FormUrlEncoded
    Observable<BaseBean> shut_microphone(@Field("uid") String uid, @Field("position") int position);

    /**
     * 开放麦
     */
    @POST(APP_DOMAIN + "open_microphone")
    @FormUrlEncoded
    Observable<BaseBean> open_microphone(@Field("uid") String uid,
                                         @Field("position") int position);

    /**
     * 获取其他用户信息
     */
    @POST(APP_DOMAIN + "get_other_user")
    @FormUrlEncoded
    Observable<OtherUser> get_other_user(
            @Field("uid") String uid,
            @Field("user_id") String user_id,
            @Field("my_id") String my_id);

    /**
     * 管理员闭麦 暂时无用
     */
    @POST(APP_DOMAIN + "shut_sound")
    @FormUrlEncoded
    Observable<BaseBean> shut_sound(@Field("uid") String uid, @Field("position") int position);

    /**
     * 加入禁声
     */
    @POST(APP_DOMAIN + "is_sound")
    @FormUrlEncoded
    Observable<JinSheng> is_sound(@Field("uid") String uid, @Field("user_id") String is_sound);

    /**
     * 取消禁声
     */
    @POST(APP_DOMAIN + "remove_sound")
    @FormUrlEncoded
    Observable<JinSheng> remove_sound(@Field("uid") String uid, @Field("user_id") String sound_id);

    /**
     * 加入禁声
     */
    @POST(APP_DOMAIN + "is_black")
    @FormUrlEncoded
    Observable<JinSheng> is_black(@Field("uid") String uid, @Field("user_id") String black_id);

    /**
     * 收藏
     */
    @POST(APP_DOMAIN + "room_mykeep")
    @FormUrlEncoded
    Observable<BaseBean> room_mykeep(@Field("uid") String uid, @Field("user_id") String user_id);


    /**
     * 取消收藏
     */
    @POST(APP_DOMAIN + "remove_mykeep")
    @FormUrlEncoded
    Observable<BaseBean> remove_mykeep(@Field("uid") String uid, @Field("user_id") String user_id);

    /**
     * 排行榜
     */
    @POST(APP_DOMAIN + "ranking")
    @FormUrlEncoded
    Observable<Rank> leaderboard(
            @Field("class") String type,
            @Field("type") String date,
            @Field("user_id") String user_id);

    /**
     * 设置管理员
     */
    @POST(APP_DOMAIN + "is_admin")
    @FormUrlEncoded
    Observable<BaseBean> is_admin(
            @Field("uid") String uid,
            @Field("admin_id") String admin_id);

    /**
     * 取消管理员
     */
    @POST(APP_DOMAIN + "remove_admin")
    @FormUrlEncoded
    Observable<BaseBean> remove_admin(
            @Field("uid") String uid,
            @Field("admin_id") String admin_id);

    /**
     * 退出房间
     */
    @POST(APP_DOMAIN + "quit_room")
    @FormUrlEncoded
    Observable<BaseBean> quit_room(
            @Field("uid") String uid,
            @Field("user_id") String user_id);

    /**
     * 添加关注
     */
    @POST(APP_DOMAIN + "follow")
    @FormUrlEncoded
    Observable<BaseBean> follow(
            @Field("user_id") String uid,
            @Field("followed_user_id") String followed_user_id);

    /**
     * 取消关注
     */
    @POST(APP_DOMAIN + "cancel_follow")
    @FormUrlEncoded
    Observable<BaseBean> cancel_follow(
            @Field("user_id") String uid,
            @Field("followed_user_id") String followed_user_id);

    /**
     * 游客管理员
     */
    @POST(APP_DOMAIN + "getRoomUsers")
    @FormUrlEncoded
    Observable<AdminUser> getRoomUsers(
            @Field("uid") String uid);

    /**
     * 搜索用户
     */
    @POST(APP_DOMAIN + "search_user")
    @FormUrlEncoded
    Observable<SearchAdmin> search_user(
            @Field("uid") String uid,
            @Field("keywords") String keywords);


    /**
     * 好友列表is_black
     */
    @POST(APP_DOMAIN + "friend_list")
    @FormUrlEncoded
    Observable<BaseBean> friend_list(
            @Field("user_id") String user_id);

    /**
     * 判断是否禁言
     */
    @POST(APP_DOMAIN + "not_speak_status")
    @FormUrlEncoded
    Observable<BaseBean> not_speak_status(
            @Field("uid") String uid,
            @Field("user_id") String user_id);

    /**
     * 音效
     */
    @POST(APP_DOMAIN + "now_music")
    @FormUrlEncoded
    Observable<MusicYinxiao> get_sound(@Field("id") String id, @Field("user_id") String user_id);


    /**
     * 音乐列表
     */
    @POST(APP_DOMAIN + "local_musics")
    @FormUrlEncoded
    Observable<Yinxiao> get_music(
            @Field("keywords") String keywords,
            @Field("page") String page,
            @Field("user_id") String user_id
    );

    /**
     * 表情
     */
    @POST(APP_DOMAIN + "emoji_list")
    @FormUrlEncoded
    Observable<EmojiBean> emoji_list(
            @Field("xx") String xx);

    /**
     * 动态表情
     */
    @POST(APP_DOMAIN + "get_emoji")
    @FormUrlEncoded
    Observable<GifBean> get_emoji(
            @Field("id") String id);

    /**
     * 获取关注动态
     */

    @POST(APP_DOMAIN + "dynamicFollowList")
    @FormUrlEncoded
    Observable<RecommendedDynamicBean> get_GZ_dynamic(@Field("user_id") String user_id,
                                                      @Field("page") String page);

    /**
     * 我点赞,收藏,转发,评论关注过的动态
     * type 1点赞  2收藏   3转发  4评论   5关注 6目标用户
     */
    @POST(APP_DOMAIN + "merge_dynamic")
    @FormUrlEncoded
    Observable<MeYiDuiBean> getMeYiDui(@Field("user_id") String user_id,
                                       @Field("my_id") String my_id,
                                       @Field("type") String type,
                                       @Field("page") String page);

    /**
     * 检查未读消息
     * user_id 本人id
     */
    @POST(APP_DOMAIN + "unreadMessage")
    @FormUrlEncoded
    Observable<UnreadBean> getUnreadMessage(@Field("user_id") String user_id);

    /**
     * 消息页面
     * user_id 本人ID
     */
    @POST(APP_DOMAIN + "message")
    @FormUrlEncoded
    Observable<MessageYoBean> getMessageYo(@Field("user_id") String user_id);

    /**
     * 查看评论
     * user_id 本人ID
     * hf_uid 评论人ID
     * id 评论ID
     */
    @POST(APP_DOMAIN + "lookComments")
    @FormUrlEncoded
    Observable<LookCommentBean> getLookComment(@Field("user_id") String user_id,
                                               @Field("hf_uid") String hf_uid,
                                               @Field("id") String id);

    /**
     * 查看某条动态下的所有评论
     * id 动态Id
     * user_id 本人ID
     */
    @POST(APP_DOMAIN + "allComment")
    @FormUrlEncoded
    Observable<AllCommentBean> getAllComment(@Field("id") String id,
                                             @Field("user_id") String user_id,
                                             @Field("page") String page);

    /**
     * 评论以及回复评论
     * id 动态的ID
     * pid 回复别人的评论或者评论别人的评论  如果不是就传0
     * user_id 自己的ID
     * content 评论的内容
     */
    @POST(APP_DOMAIN + "dynamic_comment")
    @FormUrlEncoded
    Observable<CommentBean> setComment(@Field("id") String id,
                                       @Field("pid") String pid,
                                       @Field("user_id") String user_id,
                                       @Field("content") String content);

    /**
     * 发送礼物
     */
    @POST(APP_DOMAIN + "gift_queue")
    @FormUrlEncoded
    Observable<SendGemResult> gift_queue(
            @Field("id") String id,
            @Field("uid") String uid,
            @Field("user_id") String user_id,
            @Field("fromUid") String fromUid,
            @Field("num") String num
    );

    /**
     * 排麦
     */
    @POST(APP_DOMAIN + "addWaid")
    @FormUrlEncoded
    Observable<WaitList> addWaid(
            @Field("uid") String uid,
            @Field("user_id") String user_id
    );

    /**
     * 排麦列表
     */
    @POST(APP_DOMAIN + "waitList")
    @FormUrlEncoded
    Observable<WaitList> waitList(
            @Field("uid") String uid,
            @Field("user_id") String user_id
    );

    /**
     * 发送礼物,全服
     */
    @POST(APP_DOMAIN + "Giftqueue/gift_queue_six")
    @FormUrlEncoded
    Observable<SendGiftResult> gift_queue_six(
            @Field("id") String id,
            @Field("uid") String uid,
            @Field("fromUid") String fromUid,
            @Field("num") String num
    );


    /**
     * 去除排麦
     */
    @POST(APP_DOMAIN + "delWait")
    @FormUrlEncoded
    Observable<BaseBean> delWait(
            @Field("user_id") String user_id
    );

    /**
     * 充值列表
     */
    @POST(APP_DOMAIN + "androidGoodsList")
    @FormUrlEncoded
    Observable<GoodsList> goodsList(
            @Field("user_id") String user_id
    );

    /**
     * 好友,关注,粉丝
     */
    @POST(APP_DOMAIN + "userFriend")
    @FormUrlEncoded
    Observable<UserFriend> userFriend(
            @Field("user_id") String user_id,
            @Field("type") String type,
            @Field("page") String page
    );

    /**
     * 用户个人信息
     */
    @POST(APP_DOMAIN + "get_user_info")
    @FormUrlEncoded
    Observable<UserBean> get_user_info(
            @Field("user_id") String user_id
    );

    /**
     * 支付宝，微信
     */
    @POST(APP_DOMAIN + "rechargePay")//1支付宝2微信
    @FormUrlEncoded
    Observable<PayBean> rechargePay(
            @Field("user_id") String user_id,
            @Field("goods_id") String goods_id,
            @Field("type") String type
    );

    /**
     * 微信
     */
    @POST(APP_DOMAIN + "rechargePay")//1支付宝2微信
    @FormUrlEncoded
    Observable<Wxmodel> rechargeWxPay(
            @Field("user_id") String user_id,
            @Field("goods_id") String goods_id,
            @Field("type") String type
    );

    /**
     * 获取标签
     */
    @POST(APP_DOMAIN + "get_talk_labels")
    @FormUrlEncoded
    Observable<RecommendLabelBean> getLabel(@Field("xx") String xx);

    /**
     * 搜索标签
     * keywords 关键词
     */
    @POST(APP_DOMAIN + "search_labels")
    @FormUrlEncoded
    Observable<SearchLabelBean> getSouSuoLabel(@Field("keywords") String keywords);

    /**
     * 加入黑名单
     */
    @POST(APP_DOMAIN + "pull_black")
    @FormUrlEncoded
    Observable<BaseBean> pull_black(@Field("user_id") String user_id,
                                    @Field("from_uid") String from_uid);

    /**
     * 移除黑名单
     */
    @POST(APP_DOMAIN + "cancel_black")
    @FormUrlEncoded
    Observable<BaseBean> cancel_black(@Field("user_id") String user_id,
                                      @Field("from_uid") String from_uid);

    /**
     * 黑名单列表
     */
    @POST(APP_DOMAIN + "blackList")
    @FormUrlEncoded
    Observable<UserFriend> blackList(@Field("user_id") String user_id,
                                     @Field("page") String page);

    /**
     * 举报类型
     */
    @POST(APP_DOMAIN + "report")
    @FormUrlEncoded
    Observable<ReportBean> report(@Field("xx") String xx);

    /**
     * 举报
     */
    @POST(APP_DOMAIN + "send_report")
    @FormUrlEncoded
    Observable<BaseBean> send_report(
            @Field("user_id") String user_id,
            @Field("img") String img,
            @Field("type") String type,
            @Field("target") String target,
            @Field("report_type") String report_type
    );

    /**
     * 获取官方联系方式
     */
    @POST(APP_DOMAIN + "official")
    @FormUrlEncoded
    Observable<GuanFangLianXiBean> guanfang(@Field("xx") String xx);

    /**
     * 官方消息
     */
    @POST(APP_DOMAIN + "official_message")
    @FormUrlEncoded
    Observable<OffiMessageBean> official_message(
            @Field("user_id") String user_id
    );

    /**
     * 清空官方消息
     */
    @POST(APP_DOMAIN + "clear_message")
    @FormUrlEncoded
    Observable<BaseBean> clear_message(
            @Field("user_id") String user_id
    );

    /**
     * mini官方
     */
    @POST(APP_DOMAIN + "mini_official")
    @FormUrlEncoded
    Observable<MiniOfficBean> mini_official(
            @Field("user_id") String user_id
    );

    /**
     * <<<<<<< HEAD
     * 我的钱包
     */
    @POST(APP_DOMAIN + "my_store")
    @FormUrlEncoded
    Observable<MoneyBean> my_store(
            @Field("user_id") String user_id
    );

    /**
     * 提现记录
     */
    @POST(APP_DOMAIN + "tixian_log")
    @FormUrlEncoded
    Observable<CashHis> tixian_log(
            @Field("user_id") String user_id
    );

    /**
     * 兑换记录
     */
    @POST(APP_DOMAIN + "exchange_log")
    @FormUrlEncoded
    Observable<CashHis> exchange_log(
            @Field("user_id") String user_id,
            @Field("page") String page
    );

    /**
     * 兑换米砖
     */
    @POST(APP_DOMAIN + "exchange")
    @FormUrlEncoded
    Observable<BaseBean> exchange(
            @Field("user_id") String user_id,
            @Field("money") String money
    );

    /**
     * 登录授权
     */
    @POST(APP_DOMAIN + "ali_oauth_code")
    @FormUrlEncoded
    Observable<BindBean> ali_oauth_code(
            @Field("xx") String xx
    );

    /**
     * 获取自己的个人信息以及其他人的个人信息
     * user_id 自己的ID
     * from_uid 别人的ID(看的是自己的信息的时候不传这个值）
     */
    @POST(APP_DOMAIN + "user_home_page")
    @FormUrlEncoded
    Observable<MyPersonalCebterBean> getPersonalCabter(@Field("user_id") String user_id,
                                                       @Field("from_uid") String from_uid);

    /**
     * <<<<<<< HEAD
     * 支付宝信息
     */
    @POST(APP_DOMAIN + "ali_oauth_token")
    @FormUrlEncoded
    Observable<AliInfor> ali_oauth_token(
            @Field("auth_code") String auth_code,
            @Field("user_id") String user_id
    );

    /**
     * 提现
     */
    @POST(APP_DOMAIN + "tixian")
    @FormUrlEncoded
    Observable<BaseBean> tixian(
            @Field("user_id") String user_id,
            @Field("money") String money
    );

    /**
     * 人脸识别
     */
    @POST(APP_DOMAIN + "sfrz_start")
    @FormUrlEncoded
    Observable<PayBean> sfrz_start(
            @Field("user_id") String user_id,
            @Field("name") String name,
            @Field("idno") String idno
    );

    /**
     * 人脸查询
     */
    @POST(APP_DOMAIN + "sfrz_query")
    @FormUrlEncoded
    Observable<BaseBean> sfrz_query(
            @Field("user_id") String user_id
    );

    /*
     * 忘记密码
     * phone 手机号
     * code 验证码
     * pass 密码
     */
    @POST(APP_DOMAIN + "forget_pwd")
    @FormUrlEncoded
    Observable<CommentBean> ForGetPWD(@Field("phone") String phone,
                                      @Field("code") String code,
                                      @Field("pass") String pass);

    /**
     * 获取星座
     * birthday 生日
     */
    @POST(APP_DOMAIN + "getConstellation")
    @FormUrlEncoded
    Observable<ConstellationBean> getConstellation(@Field("birthday") String birthday);

    /**
     * 修改用户信息
     * img 头像
     * nickname 昵称
     * sex 性别
     * birthday 生日
     * constellation 星座
     * city 城市
     */
    @POST(APP_DOMAIN + "edit_user_info")
    @FormUrlEncoded
    Observable<OtherBean> setUserInfo(@Field("id") String id,
                                      @Field("img") String img,
                                      @Field("nickname") String nickname,
                                      @Field("sex") String sex,
                                      @Field("birthday") String birthday,
                                      @Field("constellation") String constellation,
                                      @Field("city") String city);

    /**
     * 协议
     */
    @POST(APP_DOMAIN + "one_page")
    @FormUrlEncoded
    Observable<AgreementBean> getAgreement(@Field("type") String type);

    /**
     * 会员中心
     */
    @POST(APP_DOMAIN + "vip_center")
    @FormUrlEncoded
    Observable<VipCenterBean> getVipCenter(@Field("user_id") String user_id);

    /**
     * 等级中心
     */
    @POST(APP_DOMAIN + "level_center")
    @FormUrlEncoded
    Observable<DengJiBean> getLevelCenter(@Field("user_id") String user_id);


    /**
     * 收益
     */
    @POST(APP_DOMAIN + "user_income")
    @FormUrlEncoded
    Observable<IncomeBean> user_income(
            @Field("user_id") String user_id
    );

    /**
     * 搜索记录
     */
    @POST(APP_DOMAIN + "searhList")
    @FormUrlEncoded
    Observable<SearchHis> searhList(
            @Field("user_id") String user_id
    );

    /**
     * 清空记录
     */
    @POST(APP_DOMAIN + "cleanSarhList")
    @FormUrlEncoded
    Observable<BaseBean> cleanSarhList(
            @Field("user_id") String user_id
    );

    /**
     * 搜索
     */
    @POST(APP_DOMAIN + "merge_search")
    @FormUrlEncoded
    Observable<Search> merge_search(
            @Field("user_id") String user_id,
            @Field("keywords") String keywords
    );

    /**
     * 搜索全部
     */
    @POST(APP_DOMAIN + "search_all")
    @FormUrlEncoded
    Observable<UserFriend> search_all(
            @Field("user_id") String user_id,
            @Field("keywords") String keywords,
            @Field("type") String type,
            @Field("page") String page
    );

    /**
     * 搜索全部
     */
    @POST(APP_DOMAIN + "search_all")
    @FormUrlEncoded
    Observable<RecommenRoomBean> search_all_room(
            @Field("user_id") String user_id,
            @Field("keywords") String keywords,
            @Field("type") String type,
            @Field("page") String page
    );

    /**
     * 搜索全部
     */
    @POST(APP_DOMAIN + "search_all")
    @FormUrlEncoded
    Observable<RecommendedDynamicBean> search_all_dyni(
            @Field("user_id") String user_id,
            @Field("keywords") String keywords,
            @Field("type") String type,
            @Field("page") String page
    );

//////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * 获取房间所有用户
     */
    @POST(APP_DOMAIN + "get_room_users")
    @FormUrlEncoded
    Observable<RoomUsersBean> getRoomUsers(@Field("uid") String user_id,
                                           @Field("user_id") String target_id);

    /**
     * 发送宝石
     */
    @POST(APP_DOMAIN + "send_baoshi")
    @FormUrlEncoded
    Observable<SendGemResult> send_baoshi(@Field("id") String id,
                                          @Field("uid") String uid,
                                          @Field("token") String token,
                                          @Field("fromUid") String fromUid,
                                          @Field("num") String num
    );

    /**
     * 发送爆音卡
     */
    @POST(APP_DOMAIN + "send_byk")
    @FormUrlEncoded
    Observable<SendGemResult> send_byk(
            @Field("uid") String uid,
            @Field("token") String token,
            @Field("fromUid") String fromUid,
            @Field("num") String num);

    /**
     * 同意，拒绝CP，
     */
    @POST(APP_DOMAIN + "handle_cp")
    @FormUrlEncoded
    Observable<AgreeCpResult> handle_cp(
            @Field("token") String token,
            @Field("user_id") String user_id,
            @Field("type") String type);

    /**
     * 删除CP，测试用，测试后会删掉此接口
     */
    @POST(APP_DOMAIN + "delete_cp")
    @FormUrlEncoded
    Observable<BaseBean> delete_cp(
            @Field("token") String token
    );

    //////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * 收藏的房间列表
     */
    @POST(APP_DOMAIN + "get_mykeep")
    @FormUrlEncoded
    Observable<CollectionRoomListBean> getCollectionRoomList(@Field("user_id") String user_id);

    /**
     * 第三方注册
     */
    @POST(APP_DOMAIN + "OtherRegister")
    @FormUrlEncoded
    Observable<Login> registerOther(@Field("type") String type,
                                    @Field("openid") String openid,
                                    @Field("phone") String phone,
                                    @Field("sex") String sex,
                                    @Field("birthday") String birthday,
                                    @Field("nickname") String nickname,
                                    @Field("headimgurl") String headimgurl,
                                    @Field("pass") String pass,
                                    @Field("system") String system,
                                    @Field("channel") String channel,
                                    @Field("code") String code);

    /**
     * 三方登录
     */
    @POST(APP_DOMAIN + "OtherLogin")
    @FormUrlEncoded
    Observable<OtherBean> logOther(@Field("openid") String openid,
                                   @Field("type") String type);

    /**
     * 删除动态
     */
    @POST(APP_DOMAIN + "del_community")
    @FormUrlEncoded
    Observable<CommentBean> delCommunity(@Field("user_id") String user_id,
                                         @Field("id") String id);

    /**
     * 用户反馈
     */
    @POST(APP_DOMAIN + "feedback")
    @FormUrlEncoded
    Observable<BaseBean> feedBack(@Field("user_id") String user_id,
                                  @Field("content") String content,
                                  @Field("img") String img);

    /**
     * 社区活动
     */
    @POST(APP_DOMAIN + "activeList")
    @FormUrlEncoded
    Observable<ActiveListBean> activeList(@Field("xx") String xx);

    /**
     * 获取vip等级
     */
    @POST(APP_DOMAIN + "get_user_vip")
    @FormUrlEncoded
    Observable<VipBean> get_user_vip(@Field("uid") String user_id, @Field("token") String token);

    /**
     * 获取vip等级
     */
    @POST(APP_DOMAIN + "user/fjzxnum")
    @FormUrlEncoded
    Observable<OnlineUser> getOnlineUser(@Field("roomid") int roomId, @Field("token") String token);

    /**
     * 分享动态
     */
    @POST(APP_DOMAIN + "share_dynamic")
    @FormUrlEncoded
    Observable<CommentBean> fenxiang(@Field("user_id") String user_id,
                                     @Field("target_id") String target_id,
                                     @Field("hand") String hand);

    /**
     * 踢出房间
     */
    @POST(APP_DOMAIN + "out_room")
    @FormUrlEncoded
    Observable<BaseBean> out_room(@Field("uid") String uid,
                                  @Field("user_id") String user_id);

    /**
     * 接触阿里绑定
     */
    @POST(APP_DOMAIN + "UntyingAli")
    @FormUrlEncoded
    Observable<BaseBean> UntyingAli(
            @Field("user_id") String user_id);

    /**
     * 房间信息
     */
    @POST(APP_DOMAIN + "getRoomInfo")
    @FormUrlEncoded
    Observable<EnterRoom> getRoomInfo(
            @Field("uid") String uid);

    /**
     * 兑换查询
     */
    @POST(APP_DOMAIN + "exchange_check")
    @FormUrlEncoded
    Observable<ChaMoneyBean> getMoney(@Field("money") String money);

    /**
     * 是否关注
     */
    @POST(APP_DOMAIN + "is_follow")
    @FormUrlEncoded
    Observable<FollowBean> is_follow(@Field("user_id") String user_id, @Field("from_uid") String from_uid);

    /**
     * 我的背包
     */
    @POST(APP_DOMAIN + "my_pack")
    @FormUrlEncoded
    Observable<MyPackBean> my_pack(@Field("type") String type);

    /**
     * 装扮
     */
    @POST(APP_DOMAIN + "dress_up")
    @FormUrlEncoded
    Observable<CommentBean> dress_up(@Field("id") String id,
                                     @Field("type") String type);

    /**
     * CP详情
     * id 本cp的id
     */
    @POST(APP_DOMAIN + "cp_desc")
    @FormUrlEncoded
    Observable<CPDetailsBean> cp_desc(@Field("id") String id);

    /**
     * 解除CP
     * id CP的id
     */
    @POST(APP_DOMAIN + "remove_cp")
    @FormUrlEncoded
    Observable<CommentBean> remove_cp(@Field("id") String id);

    /**
     * 宝箱信息
     */
    @POST(APP_DOMAIN + "getBoxInfo")
    @FormUrlEncoded
    Observable<BaoXiangBean> getBoxInfo(@Field("xx") String xx);

    /**
     * 开奖（开启宝箱）
     */
    @POST(APP_DOMAIN + "getAwardList")
    @FormUrlEncoded
    Observable<OpenBoxBean> getAwardList(@Field("keysNum") int keysNum);

    /**
     * 购买钥匙
     */
    @POST(APP_DOMAIN + "actionBuyKeys")
    @FormUrlEncoded
    Observable<CommentBean> actionBuyKeys(@Field("keysNum") String keysNum);

    /**
     * 查看购买的钥匙需要多少米钻
     */
    @POST(APP_DOMAIN + "getMizuanNum")
    @FormUrlEncoded
    Observable<XuYaoMiZuanBean> getMizuanNum(@Field("keysNum") String keysNum);

    /**
     * 获取积分可兑换的礼物
     */
    @POST(APP_DOMAIN + "getAwardWaresList")
    @FormUrlEncoded
    Observable<BoxExchangeBean> getAwardWaresList(@Field("xx") String xx);

    /**
     * 积分兑换礼物
     */
    @POST(APP_DOMAIN + "actionAwardExchange")
    @FormUrlEncoded
    Observable<CommentBean> actionAwardExchange(@Field("waresId") String waresId);

    /**
     * 获取中奖纪录
     */
    @POST(APP_DOMAIN + "getAwardRecordList")
    @FormUrlEncoded
    Observable<BoxOpenRecordBean> getAwardRecordList(@Field("page") int page);

    /**
     * 宝箱文字说明
     */
    @POST(APP_DOMAIN + "getRewardInfo")
    @FormUrlEncoded
    Observable<BXShuoMingTextBean> getRewardInfo(@Field("xx") String xx);

    /**
     * 开启cp位
     */
    @POST(APP_DOMAIN + "open_cp_card")
    @FormUrlEncoded
    Observable<CommentBean> openCPCard(@Field("xx") String xx);

    /**
     * 兑换米钻卡
     */
    @POST(APP_DOMAIN + "exchange_mizuan_card")
    @FormUrlEncoded
    Observable<CommentBean> exchangeMizuanCard(@Field("id") String id);

    /**
     * 房间内排行榜
     * 1贡献榜2房间榜 class
     * 1日榜2周榜3月榜 type
     */
    @POST(APP_DOMAIN + "room_ranking")
    @FormUrlEncoded
    Observable<RoomRankBean> room_ranking(@Field("uid") String uid,
                                          @Field("class") String mclass,
                                          @Field("type") String type);

    /**
     * 房间内星锐榜
     * uid房间id
     * 1日榜2周榜3月榜 type
     */
    @POST(APP_DOMAIN + "user/room_rankingc")
    @FormUrlEncoded
    Observable<RoomRankBean> room_rankingc(@Field("uid") String uid,
                                          @Field("type") String type);

    /**
     * 中奖信息
     */
    @POST(APP_DOMAIN + "demo/qfbb")
    @FormUrlEncoded
    Observable<Winner> winners(@Field("xx") String xx);
}
