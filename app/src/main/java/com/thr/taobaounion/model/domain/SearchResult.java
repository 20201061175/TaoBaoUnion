package com.thr.taobaounion.model.domain;

import java.util.ArrayList;
import java.util.List;

public class SearchResult {

    private boolean success;
    private int code;
    private String message;
    private DataBean data;

    public List<DataBean.TbkDgMaterialOptionalResponseBean.ResultListBean.MapDataBean> getList() {
        try {
            return getData().getTbk_dg_material_optional_response().getResult_list().getMap_data();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    @Override
    public String toString() {
        return "SearchResult{" +
                ", data=" + data +
                '}';
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

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

        private TbkDgMaterialOptionalResponseBean tbk_dg_material_optional_response;

        @Override
        public String toString() {
            return "DataBean{" +
                    "tbk_dg_material_optional_response=" + tbk_dg_material_optional_response +
                    '}';
        }

        public TbkDgMaterialOptionalResponseBean getTbk_dg_material_optional_response() {
            return tbk_dg_material_optional_response;
        }

        public void setTbk_dg_material_optional_response(TbkDgMaterialOptionalResponseBean tbk_dg_material_optional_response) {
            this.tbk_dg_material_optional_response = tbk_dg_material_optional_response;
        }

        public static class TbkDgMaterialOptionalResponseBean {

            private ResultListBean result_list;
            private int total_results;
            private String request_id;

            @Override
            public String toString() {
                return "TbkDgMaterialOptionalResponseBean{" +
                        "result_list=" + result_list +
                        ", total_results=" + total_results +
                        ", request_id='" + request_id + '\'' +
                        '}';
            }

            public ResultListBean getResult_list() {
                return result_list;
            }

            public void setResult_list(ResultListBean result_list) {
                this.result_list = result_list;
            }

            public int getTotal_results() {
                return total_results;
            }

            public void setTotal_results(int total_results) {
                this.total_results = total_results;
            }

            public String getRequest_id() {
                return request_id;
            }

            public void setRequest_id(String request_id) {
                this.request_id = request_id;
            }

            public static class ResultListBean {
                private List<MapDataBean> map_data;

                @Override
                public String toString() {
                    return "ResultListBean{" +
                            "map_data=" + map_data +
                            '}';
                }

                public List<MapDataBean> getMap_data() {
                    return map_data;
                }

                public void setMap_data(List<MapDataBean> map_data) {
                    this.map_data = map_data;
                }

                public static class MapDataBean implements IBaseInfo{

                    private int category_id;
                    private String category_name;
                    private String commission_rate;
                    private String commission_type;
                    private double coupon_amount;
                    private String coupon_end_time;
                    private String coupon_id;
                    private String coupon_info;
                    private int coupon_remain_count;
                    private String coupon_share_url;
                    private String coupon_start_fee;
                    private String coupon_start_time;
                    private int coupon_total_count;
                    private String include_dxjh;
                    private String include_mkt;
                    private String info_dxjh;
                    private String item_description;
                    private String item_id;
                    private String item_url;
                    private int level_one_category_id;
                    private String level_one_category_name;
                    private String nick;
                    private String num_iid;
                    private String pict_url;
                    private String presale_deposit;
                    private int presale_end_time;
                    private int presale_start_time;
                    private int presale_tail_end_time;
                    private int presale_tail_start_time;
                    private String provcity;
                    private String real_post_fee;
                    private String reserve_price;
                    private long seller_id;
                    private int shop_dsr;
                    private String shop_title;
                    private String short_title;
                    private SmallImagesBean small_images;
                    private String superior_brand;
                    private String title;
                    private String tk_total_commi;
                    private String tk_total_sales;
                    private String url;
                    private int user_type;
                    private int volume;
                    private String white_image;
                    private String x_id;
                    private double zk_final_price;

                    @Override
                    public String toString() {
                        return "MapDataBean{" +
                                "title='" + title + '\'' +
                                '}';
                    }

                    public int getCategory_id() {
                        return category_id;
                    }

                    public void setCategory_id(int category_id) {
                        this.category_id = category_id;
                    }

                    public String getCategory_name() {
                        return category_name;
                    }

                    public void setCategory_name(String category_name) {
                        this.category_name = category_name;
                    }

                    public String getCommission_rate() {
                        return commission_rate;
                    }

                    public void setCommission_rate(String commission_rate) {
                        this.commission_rate = commission_rate;
                    }

                    public String getCommission_type() {
                        return commission_type;
                    }

                    public void setCommission_type(String commission_type) {
                        this.commission_type = commission_type;
                    }

                    public double getCoupon_amount() {
                        return coupon_amount;
                    }

                    public void setCoupon_amount(double coupon_amount) {
                        this.coupon_amount = coupon_amount;
                    }

                    public String getCoupon_end_time() {
                        return coupon_end_time;
                    }

                    public void setCoupon_end_time(String coupon_end_time) {
                        this.coupon_end_time = coupon_end_time;
                    }

                    public String getCoupon_id() {
                        return coupon_id;
                    }

                    public void setCoupon_id(String coupon_id) {
                        this.coupon_id = coupon_id;
                    }

                    public String getCoupon_info() {
                        return coupon_info;
                    }

                    public void setCoupon_info(String coupon_info) {
                        this.coupon_info = coupon_info;
                    }

                    public int getCoupon_remain_count() {
                        return coupon_remain_count;
                    }

                    public void setCoupon_remain_count(int coupon_remain_count) {
                        this.coupon_remain_count = coupon_remain_count;
                    }

                    public String getCoupon_share_url() {
                        return coupon_share_url;
                    }

                    public void setCoupon_share_url(String coupon_share_url) {
                        this.coupon_share_url = coupon_share_url;
                    }

                    public String getCoupon_start_fee() {
                        return coupon_start_fee;
                    }

                    public void setCoupon_start_fee(String coupon_start_fee) {
                        this.coupon_start_fee = coupon_start_fee;
                    }

                    public String getCoupon_start_time() {
                        return coupon_start_time;
                    }

                    public void setCoupon_start_time(String coupon_start_time) {
                        this.coupon_start_time = coupon_start_time;
                    }

                    public int getCoupon_total_count() {
                        return coupon_total_count;
                    }

                    public void setCoupon_total_count(int coupon_total_count) {
                        this.coupon_total_count = coupon_total_count;
                    }

                    public String getInclude_dxjh() {
                        return include_dxjh;
                    }

                    public void setInclude_dxjh(String include_dxjh) {
                        this.include_dxjh = include_dxjh;
                    }

                    public String getInclude_mkt() {
                        return include_mkt;
                    }

                    public void setInclude_mkt(String include_mkt) {
                        this.include_mkt = include_mkt;
                    }

                    public String getInfo_dxjh() {
                        return info_dxjh;
                    }

                    public void setInfo_dxjh(String info_dxjh) {
                        this.info_dxjh = info_dxjh;
                    }

                    public String getItem_description() {
                        return item_description;
                    }

                    public void setItem_description(String item_description) {
                        this.item_description = item_description;
                    }

                    public String getItem_id() {
                        return item_id;
                    }

                    public void setItem_id(String item_id) {
                        this.item_id = item_id;
                    }

                    public String getItem_url() {
                        return item_url;
                    }

                    public void setItem_url(String item_url) {
                        this.item_url = item_url;
                    }

                    public int getLevel_one_category_id() {
                        return level_one_category_id;
                    }

                    public void setLevel_one_category_id(int level_one_category_id) {
                        this.level_one_category_id = level_one_category_id;
                    }

                    public String getLevel_one_category_name() {
                        return level_one_category_name;
                    }

                    public void setLevel_one_category_name(String level_one_category_name) {
                        this.level_one_category_name = level_one_category_name;
                    }

                    public String getNick() {
                        return nick;
                    }

                    public void setNick(String nick) {
                        this.nick = nick;
                    }

                    public String getNum_iid() {
                        return num_iid;
                    }

                    public void setNum_iid(String num_iid) {
                        this.num_iid = num_iid;
                    }

                    public String getPict_url() {
                        return pict_url;
                    }

                    public void setPict_url(String pict_url) {
                        this.pict_url = pict_url;
                    }

                    public String getPresale_deposit() {
                        return presale_deposit;
                    }

                    public void setPresale_deposit(String presale_deposit) {
                        this.presale_deposit = presale_deposit;
                    }

                    public int getPresale_end_time() {
                        return presale_end_time;
                    }

                    public void setPresale_end_time(int presale_end_time) {
                        this.presale_end_time = presale_end_time;
                    }

                    public int getPresale_start_time() {
                        return presale_start_time;
                    }

                    public void setPresale_start_time(int presale_start_time) {
                        this.presale_start_time = presale_start_time;
                    }

                    public int getPresale_tail_end_time() {
                        return presale_tail_end_time;
                    }

                    public void setPresale_tail_end_time(int presale_tail_end_time) {
                        this.presale_tail_end_time = presale_tail_end_time;
                    }

                    public int getPresale_tail_start_time() {
                        return presale_tail_start_time;
                    }

                    public void setPresale_tail_start_time(int presale_tail_start_time) {
                        this.presale_tail_start_time = presale_tail_start_time;
                    }

                    public String getProvcity() {
                        return provcity;
                    }

                    public void setProvcity(String provcity) {
                        this.provcity = provcity;
                    }

                    public String getReal_post_fee() {
                        return real_post_fee;
                    }

                    public void setReal_post_fee(String real_post_fee) {
                        this.real_post_fee = real_post_fee;
                    }

                    public String getReserve_price() {
                        return reserve_price;
                    }

                    public void setReserve_price(String reserve_price) {
                        this.reserve_price = reserve_price;
                    }

                    public long getSeller_id() {
                        return seller_id;
                    }

                    public void setSeller_id(long seller_id) {
                        this.seller_id = seller_id;
                    }

                    public int getShop_dsr() {
                        return shop_dsr;
                    }

                    public void setShop_dsr(int shop_dsr) {
                        this.shop_dsr = shop_dsr;
                    }

                    public String getShop_title() {
                        return shop_title;
                    }

                    public void setShop_title(String shop_title) {
                        this.shop_title = shop_title;
                    }

                    public String getShort_title() {
                        return short_title;
                    }

                    public void setShort_title(String short_title) {
                        this.short_title = short_title;
                    }

                    public SmallImagesBean getSmall_images() {
                        return small_images;
                    }

                    public void setSmall_images(SmallImagesBean small_images) {
                        this.small_images = small_images;
                    }

                    public String getSuperior_brand() {
                        return superior_brand;
                    }

                    public void setSuperior_brand(String superior_brand) {
                        this.superior_brand = superior_brand;
                    }

                    @Override
                    public String getCover() {
                        return pict_url;
                    }

                    public String getTitle() {
                        return title;
                    }

                    public void setTitle(String title) {
                        this.title = title;
                    }

                    public String getTk_total_commi() {
                        return tk_total_commi;
                    }

                    public void setTk_total_commi(String tk_total_commi) {
                        this.tk_total_commi = tk_total_commi;
                    }

                    public String getTk_total_sales() {
                        return tk_total_sales;
                    }

                    public void setTk_total_sales(String tk_total_sales) {
                        this.tk_total_sales = tk_total_sales;
                    }

                    public String getUrl() {
                        return url;
                    }

                    public void setUrl(String url) {
                        this.url = url;
                    }

                    public int getUser_type() {
                        return user_type;
                    }

                    public void setUser_type(int user_type) {
                        this.user_type = user_type;
                    }

                    public int getVolume() {
                        return volume;
                    }

                    public void setVolume(int volume) {
                        this.volume = volume;
                    }

                    public String getWhite_image() {
                        return white_image;
                    }

                    public void setWhite_image(String white_image) {
                        this.white_image = white_image;
                    }

                    public String getX_id() {
                        return x_id;
                    }

                    public void setX_id(String x_id) {
                        this.x_id = x_id;
                    }

                    public double getZk_final_price() {
                        return zk_final_price;
                    }

                    public void setZk_final_price(double zk_final_price) {
                        this.zk_final_price = zk_final_price;
                    }

                    public static class SmallImagesBean {
                        private List<String> string;

                        public List<String> getString() {
                            return string;
                        }

                        public void setString(List<String> string) {
                            this.string = string;
                        }
                    }
                }
            }
        }
    }
}
