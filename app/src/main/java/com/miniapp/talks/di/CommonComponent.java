package com.miniapp.talks.di;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;
import com.miniapp.talks.activity.AgreementActivity;
import com.miniapp.talks.activity.ChargeActivity;
import com.miniapp.talks.activity.EditGaoActivity;
import com.miniapp.talks.activity.HelpActivity;
import com.miniapp.talks.activity.MainActivity;
import com.miniapp.talks.activity.OnlineUserActivity;
import com.miniapp.talks.activity.SearchDynamicActivity;
import com.miniapp.talks.activity.SearchHisActivity;
import com.miniapp.talks.activity.SearchResultActivity;
import com.miniapp.talks.activity.SearchRoomActivity;
import com.miniapp.talks.activity.SearchUserActivity;
import com.miniapp.talks.activity.SetActivity;
import com.miniapp.talks.activity.dynamic.CommentDetailsActivity;
import com.miniapp.talks.activity.dynamic.DynamicDetailsActivity;
import com.miniapp.talks.activity.dynamic.DynamicNewsActivity;
import com.miniapp.talks.activity.dynamic.HeatTopicActivity;
import com.miniapp.talks.activity.dynamic.MeZanActivity;
import com.miniapp.talks.activity.dynamic.SocialReleaseActivity;
import com.miniapp.talks.activity.dynamic.TopicTrendsActivity;
import com.miniapp.talks.activity.login.BingPhoneActivity;
import com.miniapp.talks.activity.login.ForgetPsActivity;
import com.miniapp.talks.activity.login.LoginActivity;
import com.miniapp.talks.activity.login.ModifyPsActivity;
import com.miniapp.talks.activity.login.RegisterActivity;
import com.miniapp.talks.activity.login.SexActivity;
import com.miniapp.talks.activity.login.UploadActivity;
import com.miniapp.talks.activity.message.MessageActivity;
import com.miniapp.talks.activity.mine.BingCancelActivity;
import com.miniapp.talks.activity.my.BlackListActivity;
import com.miniapp.talks.activity.my.CPActivity;
import com.miniapp.talks.activity.my.GradeCenterActivity;
import com.miniapp.talks.activity.my.MemberCoreActivity;
import com.miniapp.talks.activity.my.ModifyDataActivity;
import com.miniapp.talks.activity.my.MyFollowActivity;
import com.miniapp.talks.activity.my.MyPackageActivity;
import com.miniapp.talks.activity.my.MyPersonalCenterActivity;
import com.miniapp.talks.activity.message.MessageOfficeActivity;
import com.miniapp.talks.activity.message.MessageSetActivity;
import com.miniapp.talks.activity.message.ReportActivity;
import com.miniapp.talks.activity.mine.CashMoneyActivity;
import com.miniapp.talks.activity.mine.MoneyActivity;
import com.miniapp.talks.activity.mine.MyProfitActivity;
import com.miniapp.talks.activity.mine.RealNameActivity;
import com.miniapp.talks.activity.mine.WebRealNameActivity;
import com.miniapp.talks.activity.my.BlackListActivity;
import com.miniapp.talks.activity.my.ModifyDataActivity;
import com.miniapp.talks.activity.my.MyPersonalCenterActivity;
import com.miniapp.talks.activity.room.AdminHomeActivity;
import com.miniapp.talks.activity.room.CollectionRoomListActivity;
import com.miniapp.talks.activity.room.MusicActivity;
import com.miniapp.talks.activity.room.MyMusciActivity;
import com.miniapp.talks.activity.room.RankActivity;
import com.miniapp.talks.activity.room.RoomSettingActivity;
import com.miniapp.talks.activity.room.SetAdminActivity;
import com.miniapp.talks.adapter.SearchUserAdapter;
import com.miniapp.talks.fragment.BeiBaoHeadKuangFragment;
import com.miniapp.talks.fragment.BeiBaoIntoSEFragment;
import com.miniapp.talks.fragment.BeiBaoTalkKuangFragment;
import com.miniapp.talks.fragment.BeiBaoTalkapertureFragment;
import com.miniapp.talks.fragment.CPAFragment;
import com.miniapp.talks.fragment.CPBFragment;
import com.miniapp.talks.fragment.CPCFragment;
import com.miniapp.talks.fragment.CardFragment;
import com.miniapp.talks.fragment.CashHisFragment;
import com.miniapp.talks.fragment.CommFragment;
import com.miniapp.talks.fragment.DressUpFragment;
import com.miniapp.talks.fragment.EmojiFragment;
import com.miniapp.talks.fragment.FollowDynamicFragment;
import com.miniapp.talks.fragment.GemFragment;
import com.miniapp.talks.fragment.GemstoneFragment;
import com.miniapp.talks.fragment.MainCenterFragment;
import com.miniapp.talks.fragment.MainCommunityFragment;
import com.miniapp.talks.fragment.MainFindFragment;
import com.miniapp.talks.fragment.MainHomeFragment;
import com.miniapp.talks.fragment.MainMessageFragment;
import com.miniapp.talks.fragment.MessageFansFragment;
import com.miniapp.talks.fragment.MessageFragment;
import com.miniapp.talks.fragment.MessageFriendFragment;
import com.miniapp.talks.fragment.MyConcernFragment;
import com.miniapp.talks.fragment.MyDongTaiFragment;
import com.miniapp.talks.fragment.MyGiftFragment;
import com.miniapp.talks.fragment.MyMusicFragment;
import com.miniapp.talks.fragment.NetMuscicFragment;
import com.miniapp.talks.fragment.NewestDynamicFragment;
import com.miniapp.talks.fragment.PresentFragment;
import com.miniapp.talks.fragment.RankFragment;
import com.miniapp.talks.fragment.RecomFragment;
import com.miniapp.talks.fragment.RecomHomeFragment;
import com.miniapp.talks.fragment.RoomRankFragment;
import com.miniapp.talks.fragment.TopicFragment;

import dagger.Component;

//import com.miniapp.talks.activity.ChargeActivity;

/**
 * 必须依赖arms包，dagger才会生效
 */
@ActivityScope
@Component(modules = CommonModule.class, dependencies = AppComponent.class)
public interface CommonComponent {
    //------Activity--------

    void inject(MainActivity activity);

    void inject(LoginActivity activity);

    void inject(RegisterActivity activity);

    void inject(ForgetPsActivity activity);

    void inject(SexActivity activity);

    void inject(UploadActivity activity);

    void inject(RoomSettingActivity activity);

    void inject(AdminHomeActivity activity);

    void inject(SetActivity activity);

    void inject(EditGaoActivity activity);

    void inject(RankActivity activity);

    void inject(SocialReleaseActivity socialReleaseActivity);

    void inject(SetAdminActivity activity);

    void inject(MusicActivity activity);

    void inject(MyMusciActivity activity);

    void inject(HeatTopicActivity activity);

    void inject(TopicTrendsActivity topicTrendsActivity);

    void inject(DynamicDetailsActivity dynamicDetailsActivity);

    void inject(ChargeActivity dynamicDetailsActivity);

    void inject(DynamicNewsActivity dynamicNewsActivity);

    void inject(MeZanActivity meZanActivity);

    void inject(CommentDetailsActivity commentDetailsActivity);

    void inject(MessageActivity messageActivity);

    void inject(MessageSetActivity messageActivity);

    void inject(ReportActivity messageActivity);

    void inject(MessageOfficeActivity messageActivity);

    void inject(MoneyActivity messageActivity);

    void inject(MyPersonalCenterActivity myPersonalCenterActivity);

    void inject(CashMoneyActivity myPersonalCenterActivity);

    void inject(RealNameActivity myPersonalCenterActivity);

    void inject(WebRealNameActivity myPersonalCenterActivity);

    void inject(ModifyDataActivity modifyDataActivity);

    void inject(ModifyPsActivity modifyPsActivity);

    void inject(BlackListActivity blackListActivity);

    void inject(AgreementActivity agreementActivity);

    void inject(MyProfitActivity agreementActivity);

    void inject(MyFollowActivity myFollowActivity);

    void inject(MemberCoreActivity memberCoreActivity);

    void inject(GradeCenterActivity gradeCenterActivity);

    void inject(SearchHisActivity gradeCenterActivity);

    void inject(SearchResultActivity gradeCenterActivity);

    void inject(SearchUserActivity gradeCenterActivity);

    void inject(SearchRoomActivity gradeCenterActivity);

    void inject(SearchDynamicActivity gradeCenterActivity);

    void inject(BingCancelActivity bingCancelActivity);

    void inject(CollectionRoomListActivity collectionRoomListActivity);

    void inject(BingPhoneActivity bingPhoneActivity);

    void inject(HelpActivity helpActivity);

    void inject(MyPackageActivity myPackageActivity);

    void inject(CPActivity cpActivity);

    //------Fragment--------

    void inject(MainHomeFragment mainHomeFragment);

    void inject(MainCommunityFragment mainHomeFragment);

    void inject(MainMessageFragment mainHomeFragment);

    void inject(MainFindFragment mainHomeFragment);

    void inject(MainCenterFragment mainHomeFragment);

    void inject(RecomFragment recomFragment);

    void inject(RankFragment recomFragment);

    void inject(CommFragment commFragment);

    void inject(MyMusicFragment commFragment);

    void inject(NetMuscicFragment commFragment);

    void inject(TopicFragment topicFragment);

    void inject(EmojiFragment topicFragment);

    void inject(NewestDynamicFragment newestDynamicFragment);

    void inject(FollowDynamicFragment followDynamicFragment);

    void inject(MessageFragment followDynamicFragment);

    void inject(MessageFansFragment followDynamicFragment);

    void inject(MessageFriendFragment followDynamicFragment);

    void inject(CashHisFragment followDynamicFragment);

    void inject(MyGiftFragment myGiftFragment);

    void inject(MyDongTaiFragment myDongTaiFragment);

    void inject(MyConcernFragment myConcernFragment);

    void inject(GemstoneFragment mGemstoneFragment);

    void inject(DressUpFragment mDressUpFragment);

    void inject(GemFragment gemFragment);

    void inject(PresentFragment presentFragment);

    void inject(CardFragment cardFragment);

    void inject(BeiBaoHeadKuangFragment beiBaoHeadKuangFragment);

    void inject(BeiBaoTalkKuangFragment beiBaoTalkKuangFragment);

    void inject(BeiBaoIntoSEFragment beiBaoIntoSEFragment);

    void inject(BeiBaoTalkapertureFragment beiBaoTalkapertureFragment);

    void inject(CPAFragment cpaFragment);

    void inject(CPBFragment cpbFragment);

    void inject(CPCFragment cpcFragment);

    void inject(RecomHomeFragment recomFragment);

    void inject(RoomRankFragment roomRankFragment);


    void inject(OnlineUserActivity onlineUserActivity);
}
