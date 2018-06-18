package com.xing.weijian.weather.db.domain;

import java.util.List;

/**
 * Created by Administrator on 2018/4/7.
 */

//    {
//        "HeWeather5": [
//        {
//            "basic":{
//            "city":"上海",
//                    "cnty":"中国",
//                    "id":"CN101020100",
//                    "lat":"31.23170662",
//                    "lon":"121.47264099",
//                    "update":{
//                        "loc":"2018-04-06 10:47",
//                        "utc":"2018-04-06 02:47"
//                    }
//            },
//            "daily_forecast": [
//            {
//                "astro":{
//                    "mr":"23:41",
//                    "ms":"09:33",
//                    "sr":"05:36",
//                    "ss":"18:16"
//                },
//                "cond":{
//                     "code_d":"101",
//                     "code_n":"100",
//                     "txt_d":"多云",
//                     "txt_n":"晴"
//                },
//                "date":"2018-04-06",
//                "hum":"74",
//                "pcpn":"0.4",
//                "pop":"96",
//                "pres":"1022",
//                "tmp":{
//                    "max":"15",
//                    "min":"8"
//                 },
//                "uv":"9",
//                "vis":"15",
//                "wind":{
//                    "deg":"297",
//                    "dir":"西北风",
//                    "sc":"4-5",
//                    "spd":"28"
//                }
//            },
//            {
//                "astro":{
//                    "mr":"02:45",
//                    "ms":"10:18",
//                    "sr":"05:35",
//                    "ss":"18:17"
//                },
//                "cond":{
//                        "code_d":"100",
//                        "code_n":"100",
//                        "txt_d":"晴",
//                        "txt_n":"晴"
//                },
//                "date":"2018-04-07",
//                "hum":"27",
//                "pcpn":"0.0",
//                "pop":"0",
//                "pres":"1026",
//                "tmp":{
//                        "max":"13",
//                        "min":"7"
//                },
//                "uv":"8",
//                 "vis":"20",
//                 "wind":{
//                        "deg":"301",
//                        "dir":"西北风",
//                        "sc":"4-5",
//                        "spd":"24"
//                 }
//            },
//            {
//                "astro":{
//                        "mr":"00:30",
//                        "ms":"11:07",
//                        "sr":"05:34",
//                        "ss":"18:17"
//                },
//                "cond":{
//                        "code_d":"100",
//                        "code_n":"100",
//                        "txt_d":"晴",
//                        "txt_n":"晴"
//                },
//                "date":"2018-04-08",
//                "hum":"38",
//                "pcpn":"0.0",
//                "pop":"0",
//                "pres":"1021",
//                "tmp":{
//                        "max":"19",
//                        "min":"11"
//                },
//                "uv":"7",
//                "vis":"20",
//                "wind":{
//                        "deg":"244",
//                        "dir":"西南风",
//                        "sc":"3-4",
//                        "spd":"15"
//                }
//            }],
//            "status":"ok"
//        }]
//    }


public class Weather {


    private List<HeWeather6Bean> HeWeather6;

    public List<HeWeather6Bean> getHeWeather6() {
        return HeWeather6;
    }

    public void setHeWeather6(List<HeWeather6Bean> HeWeather6) {
        this.HeWeather6 = HeWeather6;
    }

    public static class HeWeather6Bean {
        /**
         * basic : {"cid":"CN101020100","location":"上海","parent_city":"上海","admin_area":"上海","cnty":"中国","lat":"31.23170662","lon":"121.47264099","tz":"+8.00"}
         * update : {"loc":"2018-04-07 12:47","utc":"2018-04-07 04:47"}
         * status : ok
         * daily_forecast : [{"cond_code_d":"100","cond_code_n":"100","cond_txt_d":"晴","cond_txt_n":"晴","date":"2018-04-07","hum":"25","mr":"04:45","ms":"10:18","pcpn":"0.0","pop":"0","pres":"1027","sr":"05:35","ss":"18:17","tmp_max":"14","tmp_min":"7","uv_index":"8","vis":"20","wind_deg":"315","wind_dir":"西北风","wind_sc":"3-4","wind_spd":"15"},{"cond_code_d":"100","cond_code_n":"100","cond_txt_d":"晴","cond_txt_n":"晴","date":"2018-04-08","hum":"38","mr":"00:30","ms":"11:07","pcpn":"0.0","pop":"0","pres":"1022","sr":"05:34","ss":"18:17","tmp_max":"20","tmp_min":"12","uv_index":"7","vis":"20","wind_deg":"254","wind_dir":"西南风","wind_sc":"3-4","wind_spd":"24"},{"cond_code_d":"100","cond_code_n":"100","cond_txt_d":"晴","cond_txt_n":"晴","date":"2018-04-09","hum":"42","mr":"01:17","ms":"11:57","pcpn":"0.0","pop":"0","pres":"1018","sr":"05:33","ss":"18:18","tmp_max":"24","tmp_min":"15","uv_index":"8","vis":"20","wind_deg":"107","wind_dir":"东南风","wind_sc":"1-2","wind_spd":"8"}]
         */

        private BasicBean basic;
        private UpdateBean update;
        private String status;
        private List<DailyForecastBean> daily_forecast;

        public BasicBean getBasic() {
            return basic;
        }

        public void setBasic(BasicBean basic) {
            this.basic = basic;
        }

        public UpdateBean getUpdate() {
            return update;
        }

        public void setUpdate(UpdateBean update) {
            this.update = update;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public List<DailyForecastBean> getDaily_forecast() {
            return daily_forecast;
        }

        public void setDaily_forecast(List<DailyForecastBean> daily_forecast) {
            this.daily_forecast = daily_forecast;
        }

        public static class BasicBean {
            /**
             * cid : CN101020100
             * location : 上海
             * parent_city : 上海
             * admin_area : 上海
             * cnty : 中国
             * lat : 31.23170662
             * lon : 121.47264099
             * tz : +8.00
             */

            private String cid;
            private String location;
            private String parent_city;
            private String admin_area;
            private String cnty;
            private String lat;
            private String lon;
            private String tz;

            public String getCid() {
                return cid;
            }

            public void setCid(String cid) {
                this.cid = cid;
            }

            public String getLocation() {
                return location;
            }

            public void setLocation(String location) {
                this.location = location;
            }

            public String getParent_city() {
                return parent_city;
            }

            public void setParent_city(String parent_city) {
                this.parent_city = parent_city;
            }

            public String getAdmin_area() {
                return admin_area;
            }

            public void setAdmin_area(String admin_area) {
                this.admin_area = admin_area;
            }

            public String getCnty() {
                return cnty;
            }

            public void setCnty(String cnty) {
                this.cnty = cnty;
            }

            public String getLat() {
                return lat;
            }

            public void setLat(String lat) {
                this.lat = lat;
            }

            public String getLon() {
                return lon;
            }

            public void setLon(String lon) {
                this.lon = lon;
            }

            public String getTz() {
                return tz;
            }

            public void setTz(String tz) {
                this.tz = tz;
            }
        }

        public static class UpdateBean {
            /**
             * loc : 2018-04-07 12:47
             * utc : 2018-04-07 04:47
             */

            private String loc;
            private String utc;

            public String getLoc() {
                return loc;
            }

            public void setLoc(String loc) {
                this.loc = loc;
            }

            public String getUtc() {
                return utc;
            }

            public void setUtc(String utc) {
                this.utc = utc;
            }
        }

        public static class DailyForecastBean {
            /**
             * cond_code_d : 100
             * cond_code_n : 100
             * cond_txt_d : 晴
             * cond_txt_n : 晴
             * date : 2018-04-07
             * hum : 25
             * mr : 04:45
             * ms : 10:18
             * pcpn : 0.0
             * pop : 0
             * pres : 1027
             * sr : 05:35
             * ss : 18:17
             * tmp_max : 14
             * tmp_min : 7
             * uv_index : 8
             * vis : 20
             * wind_deg : 315
             * wind_dir : 西北风
             * wind_sc : 3-4
             * wind_spd : 15
             */

            private String cond_code_d;
            private String cond_code_n;
            private String cond_txt_d;
            private String cond_txt_n;
            private String date;
            private String hum;
            private String mr;
            private String ms;
            private String pcpn;
            private String pop;
            private String pres;
            private String sr;
            private String ss;
            private String tmp_max;
            private String tmp_min;
            private String uv_index;
            private String vis;
            private String wind_deg;
            private String wind_dir;
            private String wind_sc;
            private String wind_spd;

            public String getCond_code_d() {
                return cond_code_d;
            }

            public void setCond_code_d(String cond_code_d) {
                this.cond_code_d = cond_code_d;
            }

            public String getCond_code_n() {
                return cond_code_n;
            }

            public void setCond_code_n(String cond_code_n) {
                this.cond_code_n = cond_code_n;
            }

            public String getCond_txt_d() {
                return cond_txt_d;
            }

            public void setCond_txt_d(String cond_txt_d) {
                this.cond_txt_d = cond_txt_d;
            }

            public String getCond_txt_n() {
                return cond_txt_n;
            }

            public void setCond_txt_n(String cond_txt_n) {
                this.cond_txt_n = cond_txt_n;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getHum() {
                return hum;
            }

            public void setHum(String hum) {
                this.hum = hum;
            }

            public String getMr() {
                return mr;
            }

            public void setMr(String mr) {
                this.mr = mr;
            }

            public String getMs() {
                return ms;
            }

            public void setMs(String ms) {
                this.ms = ms;
            }

            public String getPcpn() {
                return pcpn;
            }

            public void setPcpn(String pcpn) {
                this.pcpn = pcpn;
            }

            public String getPop() {
                return pop;
            }

            public void setPop(String pop) {
                this.pop = pop;
            }

            public String getPres() {
                return pres;
            }

            public void setPres(String pres) {
                this.pres = pres;
            }

            public String getSr() {
                return sr;
            }

            public void setSr(String sr) {
                this.sr = sr;
            }

            public String getSs() {
                return ss;
            }

            public void setSs(String ss) {
                this.ss = ss;
            }

            public String getTmp_max() {
                return tmp_max;
            }

            public void setTmp_max(String tmp_max) {
                this.tmp_max = tmp_max;
            }

            public String getTmp_min() {
                return tmp_min;
            }

            public void setTmp_min(String tmp_min) {
                this.tmp_min = tmp_min;
            }

            public String getUv_index() {
                return uv_index;
            }

            public void setUv_index(String uv_index) {
                this.uv_index = uv_index;
            }

            public String getVis() {
                return vis;
            }

            public void setVis(String vis) {
                this.vis = vis;
            }

            public String getWind_deg() {
                return wind_deg;
            }

            public void setWind_deg(String wind_deg) {
                this.wind_deg = wind_deg;
            }

            public String getWind_dir() {
                return wind_dir;
            }

            public void setWind_dir(String wind_dir) {
                this.wind_dir = wind_dir;
            }

            public String getWind_sc() {
                return wind_sc;
            }

            public void setWind_sc(String wind_sc) {
                this.wind_sc = wind_sc;
            }

            public String getWind_spd() {
                return wind_spd;
            }

            public void setWind_spd(String wind_spd) {
                this.wind_spd = wind_spd;
            }
        }
    }
}
