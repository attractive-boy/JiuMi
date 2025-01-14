package com.miniapp.talks.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewDynamicBean {


    /**
     * code : 1
     * message : 获取成功
     * data : {"current_page":1,"data":[{"id":5,"video":"http://59.110.169.251/upload/video/123.mp4","content":"哈哈哈哈","comment_num":3,"awesome":1,"share":0,"created_at":"2019-06-13 12:34:32","nickname":"田中华(Mythe)","image_url":{"0":"http://59.110.169.251/upload/dynamic_image\\/9c685d32c46bfdaebfe2f35a10f0eff0.jpg","1":"http://59.110.169.251/upload/dynamic_image\\/9c685d32c46bfdaebfe2f35a10f0eff0.jpg"},"tags_name":{"0":"美食","1":"旅游"}},{"id":6,"video":"http://59.110.169.251/upload/","content":"123123123","comment_num":0,"awesome":0,"share":0,"created_at":"2019-06-06 12:52:33","nickname":null,"image_url":{"0":"http://59.110.169.251/upload/1020649296&fm=15&gp=0.jpg","1":"http://59.110.169.251/upload/1020649296&fm=15&gp=0.jpg"},"tags_name":{"0":"美食"}}],"first_page_url":"http://59.110.169.251/api/new_dynamic?page=1","from":1,"last_page":1,"last_page_url":"http://59.110.169.251/api/new_dynamic?page=1","next_page_url":null,"path":"http://59.110.169.251/api/new_dynamic","per_page":15,"prev_page_url":null,"to":2,"total":2}
     */

    private int code;
    private String message;
    private DataBeanX data;

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

    public DataBeanX getData() {
        return data;
    }

    public void setData(DataBeanX data) {
        this.data = data;
    }

    public static class DataBeanX {
        /**
         * current_page : 1
         * data : [{"id":5,"video":"http://59.110.169.251/upload/video/123.mp4","content":"哈哈哈哈","comment_num":3,"awesome":1,"share":0,"created_at":"2019-06-13 12:34:32","nickname":"田中华(Mythe)","image_url":{"0":"http://59.110.169.251/upload/dynamic_image\\/9c685d32c46bfdaebfe2f35a10f0eff0.jpg","1":"http://59.110.169.251/upload/dynamic_image\\/9c685d32c46bfdaebfe2f35a10f0eff0.jpg"},"tags_name":{"0":"美食","1":"旅游"}},{"id":6,"video":"http://59.110.169.251/upload/","content":"123123123","comment_num":0,"awesome":0,"share":0,"created_at":"2019-06-06 12:52:33","nickname":null,"image_url":{"0":"http://59.110.169.251/upload/1020649296&fm=15&gp=0.jpg","1":"http://59.110.169.251/upload/1020649296&fm=15&gp=0.jpg"},"tags_name":{"0":"美食"}}]
         * first_page_url : http://59.110.169.251/api/new_dynamic?page=1
         * from : 1
         * last_page : 1
         * last_page_url : http://59.110.169.251/api/new_dynamic?page=1
         * next_page_url : null
         * path : http://59.110.169.251/api/new_dynamic
         * per_page : 15
         * prev_page_url : null
         * to : 2
         * total : 2
         */

        private int current_page;
        private String first_page_url;
        private int from;
        private int last_page;
        private String last_page_url;
        private Object next_page_url;
        private String path;
        private int per_page;
        private Object prev_page_url;
        private int to;
        private int total;
        private List<DataBean> data;

        public int getCurrent_page() {
            return current_page;
        }

        public void setCurrent_page(int current_page) {
            this.current_page = current_page;
        }

        public String getFirst_page_url() {
            return first_page_url;
        }

        public void setFirst_page_url(String first_page_url) {
            this.first_page_url = first_page_url;
        }

        public int getFrom() {
            return from;
        }

        public void setFrom(int from) {
            this.from = from;
        }

        public int getLast_page() {
            return last_page;
        }

        public void setLast_page(int last_page) {
            this.last_page = last_page;
        }

        public String getLast_page_url() {
            return last_page_url;
        }

        public void setLast_page_url(String last_page_url) {
            this.last_page_url = last_page_url;
        }

        public Object getNext_page_url() {
            return next_page_url;
        }

        public void setNext_page_url(Object next_page_url) {
            this.next_page_url = next_page_url;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public int getPer_page() {
            return per_page;
        }

        public void setPer_page(int per_page) {
            this.per_page = per_page;
        }

        public Object getPrev_page_url() {
            return prev_page_url;
        }

        public void setPrev_page_url(Object prev_page_url) {
            this.prev_page_url = prev_page_url;
        }

        public int getTo() {
            return to;
        }

        public void setTo(int to) {
            this.to = to;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * id : 5
             * video : http://59.110.169.251/upload/video/123.mp4
             * content : 哈哈哈哈
             * comment_num : 3
             * awesome : 1
             * share : 0
             * created_at : 2019-06-13 12:34:32
             * nickname : 田中华(Mythe)
             * image_url : {"0":"http://59.110.169.251/upload/dynamic_image\\/9c685d32c46bfdaebfe2f35a10f0eff0.jpg","1":"http://59.110.169.251/upload/dynamic_image\\/9c685d32c46bfdaebfe2f35a10f0eff0.jpg"}
             * tags_name : {"0":"美食","1":"旅游"}
             */

            private int id;
            private String video;
            private String content;
            private int comment_num;
            private int awesome;
            private int share;
            private String created_at;
            private String nickname;
            private ImageUrlBean image_url;
            private TagsNameBean tags_name;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getVideo() {
                return video;
            }

            public void setVideo(String video) {
                this.video = video;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public int getComment_num() {
                return comment_num;
            }

            public void setComment_num(int comment_num) {
                this.comment_num = comment_num;
            }

            public int getAwesome() {
                return awesome;
            }

            public void setAwesome(int awesome) {
                this.awesome = awesome;
            }

            public int getShare() {
                return share;
            }

            public void setShare(int share) {
                this.share = share;
            }

            public String getCreated_at() {
                return created_at;
            }

            public void setCreated_at(String created_at) {
                this.created_at = created_at;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public ImageUrlBean getImage_url() {
                return image_url;
            }

            public void setImage_url(ImageUrlBean image_url) {
                this.image_url = image_url;
            }

            public TagsNameBean getTags_name() {
                return tags_name;
            }

            public void setTags_name(TagsNameBean tags_name) {
                this.tags_name = tags_name;
            }

            public static class ImageUrlBean {
                /**
                 * 0 : http://59.110.169.251/upload/dynamic_image\/9c685d32c46bfdaebfe2f35a10f0eff0.jpg
                 * 1 : http://59.110.169.251/upload/dynamic_image\/9c685d32c46bfdaebfe2f35a10f0eff0.jpg
                 */

                @SerializedName("0")
                private String _$0;
                @SerializedName("1")
                private String _$1;

                public String get_$0() {
                    return _$0;
                }

                public void set_$0(String _$0) {
                    this._$0 = _$0;
                }

                public String get_$1() {
                    return _$1;
                }

                public void set_$1(String _$1) {
                    this._$1 = _$1;
                }
            }

            public static class TagsNameBean {
                /**
                 * 0 : 美食
                 * 1 : 旅游
                 */

                @SerializedName("0")
                private String _$0;
                @SerializedName("1")
                private String _$1;

                public String get_$0() {
                    return _$0;
                }

                public void set_$0(String _$0) {
                    this._$0 = _$0;
                }

                public String get_$1() {
                    return _$1;
                }

                public void set_$1(String _$1) {
                    this._$1 = _$1;
                }
            }
        }
    }
}
