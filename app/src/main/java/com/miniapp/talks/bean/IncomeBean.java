package com.miniapp.talks.bean;

/**
 * 作者:sgm
 * 描述:
 */
public class IncomeBean {

    /**
     * code : 1
     * message : 请求成功!
     * data : {"gift_income":{"yue":"100.00","day_sum":0,"week_sum":0,"mon_sum":0,"last_mon_sum":0,"is_leader":1},"room_income":{"yue":"200.42","day_sum":0,"week_sum":"200.42","mon_sum":"200.42","last_mon_sum":0}}
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
         * gift_income : {"yue":"100.00","day_sum":0,"week_sum":0,"mon_sum":0,"last_mon_sum":0,"is_leader":1}
         * room_income : {"yue":"200.42","day_sum":0,"week_sum":"200.42","mon_sum":"200.42","last_mon_sum":0}
         */

        private GiftIncomeBean gift_income;
        private RoomIncomeBean room_income;

        public GiftIncomeBean getGift_income() {
            return gift_income;
        }

        public void setGift_income(GiftIncomeBean gift_income) {
            this.gift_income = gift_income;
        }

        public RoomIncomeBean getRoom_income() {
            return room_income;
        }

        public void setRoom_income(RoomIncomeBean room_income) {
            this.room_income = room_income;
        }

        public static class GiftIncomeBean {
            /**
             * yue : 100.00
             * day_sum : 0
             * week_sum : 0
             * mon_sum : 0
             * last_mon_sum : 0
             * is_leader : 1
             */

            private String yue;
            private String day_sum;
            private String week_sum;
            private String mon_sum;
            private String last_mon_sum;
            private int is_leader;

            public String getYue() {
                return yue;
            }

            public void setYue(String yue) {
                this.yue = yue;
            }

            public String getDay_sum() {
                return day_sum;
            }

            public void setDay_sum(String day_sum) {
                this.day_sum = day_sum;
            }

            public String getWeek_sum() {
                return week_sum;
            }

            public void setWeek_sum(String week_sum) {
                this.week_sum = week_sum;
            }

            public String getMon_sum() {
                return mon_sum;
            }

            public void setMon_sum(String mon_sum) {
                this.mon_sum = mon_sum;
            }

            public String getLast_mon_sum() {
                return last_mon_sum;
            }

            public void setLast_mon_sum(String last_mon_sum) {
                this.last_mon_sum = last_mon_sum;
            }

            public int getIs_leader() {
                return is_leader;
            }

            public void setIs_leader(int is_leader) {
                this.is_leader = is_leader;
            }
        }

        public static class RoomIncomeBean {
            /**
             * yue : 200.42
             * day_sum : 0
             * week_sum : 200.42
             * mon_sum : 200.42
             * last_mon_sum : 0
             */

            private String yue;
            private String day_sum;
            private String week_sum;
            private String mon_sum;
            private String last_mon_sum;

            public String getYue() {
                return yue;
            }

            public void setYue(String yue) {
                this.yue = yue;
            }

            public String getDay_sum() {
                return day_sum;
            }

            public void setDay_sum(String day_sum) {
                this.day_sum = day_sum;
            }

            public String getWeek_sum() {
                return week_sum;
            }

            public void setWeek_sum(String week_sum) {
                this.week_sum = week_sum;
            }

            public String getMon_sum() {
                return mon_sum;
            }

            public void setMon_sum(String mon_sum) {
                this.mon_sum = mon_sum;
            }

            public String getLast_mon_sum() {
                return last_mon_sum;
            }

            public void setLast_mon_sum(String last_mon_sum) {
                this.last_mon_sum = last_mon_sum;
            }
        }
    }
}
