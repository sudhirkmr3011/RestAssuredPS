package com.hsbc.qe.api.util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ApiParseJson {
static int count=0;

    public static List<Object> Api_List_Elements = new ArrayList<Object>();

    
    
    public static void parseJson(JSONObject json, String key) throws JSONException {
//    	System.out.println(json);
//        Api_List_Elements = new ArrayList<Object>();
//        asdf = (String) json.get(key);
//        System.out.println("staring "+asdf);
//        Api_List_Elements.add(asdf);
        
        System.out.println(json.get(key));
//        System.out.println("dmndcvdvc"+Api_List_Elements);
//        count = count+1;
//    	System.out.println("dfef"+count);
//    	Api_List_Elements.add(count);
//    	 System.out.println("s"+Api_List_Elements);
    	 Api_List_Elements.add(json.get(key));
    }


    public static void getKey(JSONObject json, String key) throws JSONException {


        boolean exists = json.has(key);
        Iterator<?> keys;
        String nextKeys;

        if (!exists) {
            keys = json.keys();
            while (keys.hasNext()) {
                nextKeys = (String) keys.next();
//                System.out.println(nextKeys);
                try {
                    if (json.get(nextKeys) instanceof JSONObject) {
                        if(exists == false){
                            getKey(json.getJSONObject(nextKeys),key);
                        }
                    } else if (json.get(nextKeys) instanceof JSONArray) {
                        JSONArray jsonArray = json.getJSONArray(nextKeys);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            String jsonArrayString = jsonArray.get(i).toString();
                            JSONObject innerJson = new JSONObject(jsonArrayString);

                            if (exists == false) {
                                getKey(innerJson, key);
                            }
                        }

                    }

                } catch (Exception e) {
//                    e.printStackTrace();
                }
            }
        } else {
//        	count = count+1;
//        	System.out.println("dcd"+count);
            parseJson(json,key);
            

        }

    }

    public static void main(String[] args) throws JSONException {

    	

        String dd = "{\"kk\":[{\"product_id\":\"22427\",\n" + 
        		"\"product_from_price\":\"0.00\",\n" + 
        		"\"product_start_date\":\"2020-09-15T03:00:00+04:00\",\n" + 
        		"\"product_end_date\":\"2021-05-01T02:59:00+04:00\",\n" + 
        		"\"product_availability\":false,\n" + 
        		"\"product_admission_type\":\"TIME_OPEN\",\n" + 
        		"\"product_pickup_point\":\"NOT_SET\",\n" + 
        		"\"product_pickup_point_details\":[],\n" + 
        		"\"product_content\":{\"product_title\":\"Al Jahili Fort\",\n" + 
        		"\"product_short_description\":\"Al Jahili Fort, one of the largest forts in the United Arab Emirates, was built in the late 19th century by Sheikh Zayed bin Khalifa the First (r. 1855-1909), both as a symbol of power and as a royal summer residence. The original fort consisted of a square fortified enclosure and a distinctive four-storey circular tower.\",\n" + 
        		"\"product_long_description\":\"\\\"Al Jahili Fort, one of the largest forts in the United Arab Emirates, was built in the late 19th century by Sheikh Zayed bin Khalifa the First (r. 1855-1909), both as a symbol of power and as a royal summer residence. The original fort consisted of a square fortified enclosure and a distinctive four-storey circular tower.\\n\\nHistory\\n\\nAt the end of the 19th century, the Emirate of Abu Dhabi was ruled by Sheikh Zayed bin Khalifa, leader of the Bani Yas tribe and head of the Al Falah family. During summer, Abu Dhabi’s rulers left the intense humidity of the coast for the more temperate weather of Al Ain, with its low humidity, fertile land and fresh water. \\n\\nSheikh Zayed owned a farm in Al Ain, and he ordered construction of Al Jahili Fort to control the tribes who lived in the area. He also would use the fort as his summer residence. Work on the fort began in 1891 but it was not completed until 1898. \\n\\nWhen Sheikh Zayed died, his eldest son, Sheikh Khalifa bin Zayed died, chose not to become ruler of Abu Dhabi and instead remained in Al Ain. He lived in the fort with his family. Al Jahili’s history over the next 30 years is unclear, but at some point, the fort was abandoned and fell into disrepair. \\n\\nIn the early 1950s, British forces came to Al Ain and incorporated the fort into a larger enclosure that formed their regional headquarters. Barracks and other buildings were added to the compound. This military use of the tower continued until around 1970.\\n\\nIn 1985, early restoration work was done on the fort, while in 2007-2008, the fort underwent a major rehabilitation project. A visitor information centre, permanent exhibition space devoted to the explorer and photograph Wilfred Thesiger, and a temporary exhibition space were added.\\n\\nFort Structure\\n\\nThe original components of Al Jahili Fort include a square high-walled fort and a separate round tower composed of four consecutively rising and narrowing concentric tiers. Today, the fort has cylindrical towers at three of its corners, while in the fourth is the sheikh’s majlis, or reception area, where he entertained guests and conducted official business.\\\"\",\n" + 
        		"\"product_images\":[{\"image_type\":\"MAIN\",\n" + 
        		"\"image_url\":\"https://sandboxadmin.prioticket.com/tickets/1070294/NEW/480x270_img_1600090208_1600090799__Al-Jahili_Fort_Al_Ain.jpg\"},\n" + 
        		"{\"image_type\":\"BANNER\",\n" + 
        		"\"image_url\":\"https://sandboxadmin.prioticket.com/tickets/1070294/NEW/img_1600090208_1600090799__Al-Jahili_Fort_Al_Ain.jpg\"}]},\n" + 
        		"\"product_locations\":[{\"location_id\":\"2533241\",\n" + 
        		"\"location_label\":\"\",\n" + 
        		"\"location_name\":\"Sultan Bin Zayed\",\n" + 
        		"\"location_description\":\"\",\n" + 
        		"\"location_type\":\"VENUE\",\n" + 
        		"\"location_address\":{\"name\":\"Al Jahili Fort - Abu Dhabi - United Arab Emirates\",\n" + 
        		"\"street\":\"\",\n" + 
        		"\"city\":\"Al Ain\",\n" + 
        		"\"postal_code\":\"\",\n" + 
        		"\"region\":\"\",\n" + 
        		"\"country\":\"United Arab Emirates\",\n" + 
        		"\"country_code\":\"AE\",\n" + 
        		"\"place_id\":\"\",\n" + 
        		"\"latitude\":\"24.216167\",\n" + 
        		"\"longitude\":\"55.752722\",\n" + 
        		"\"notes\":\"\",\n" + 
        		"\"website\":\"\",\n" + 
        		"\"email\":\"\",\n" + 
        		"\"telephone\":\"\"},\n" + 
        		"\"location_pickup_point\":\"False\"}],\n" + 
        		"\"product_type_seasons\":[{\"product_type_season_start_date\":\"2020-09-15T03:00:00+04:00\",\n" + 
        		"\"product_type_season_end_date\":\"2021-05-01T02:59:00+04:00\",\n" + 
        		"\"product_type_season_details\":[{\"product_type_id\":\"121618\",\n" + 
        		"\"product_type\":\"PERSON\",\n" + 
        		"\"product_type_label\":\"All Age\",\n" + 
        		"\"product_type_description\":null,\n" + 
        		"\"product_type_class\":\"STANDARD\",\n" + 
        		"\"product_type_age_from\":1,\n" + 
        		"\"product_type_age_to\":99,\n" + 
        		"\"product_type_pax\":1,\n" + 
        		"\"product_type_capacity\":1,\n" + 
        		"\"product_type_price_type\":\"INDIVIDUAL\",\n" + 
        		"\"product_type_price_tax_id\":\"2\",\n" + 
        		"\"product_type_pricing\":{\"product_type_list_price\":\"0.00\",\n" + 
        		"\"product_type_discount\":\"0.00\",\n" + 
        		"\"product_type_sales_price\":\"0.00\",\n" + 
        		"\"product_type_resale_price\":\"0.00\"}}]}],\n" + 
        		"\"product_categories\":[\"4\"],\n" + 
        		"\"product_category_detail\":[{\"category_id\":\"4\",\n" + 
        		"\"category_name\":\"Museums\",\n" + 
        		"\"category_type\":\"\",\n" + 
        		"\"category_parent_id\":\"\"}],\n" + 
        		"\"product_highlights\":[{\"highlight_description\":\"Mubarak bin London Exhibition\"},\n" + 
        		"{\"highlight_description\":\"The Tower\"},\n" + 
        		"{\"highlight_description\":\"Temporary Exhibition\"},\n" + 
        		"{\"highlight_description\":\"Old Fort\"},\n" + 
        		"{\"highlight_description\":\"Visitors Center\"}],\n" + 
        		"\"product_content_languages\":[\"en\",\n" + 
        		"\"ar\"]},\n" + 
        		"{\"product_id\":\"22426\",\n" + 
        		"\"product_from_price\":\"0.00\",\n" + 
        		"\"product_start_date\":\"2020-09-08T03:00:00+04:00\",\n" + 
        		"\"product_end_date\":\"0001-01-01T00:00:00\",\n" + 
        		"\"product_availability\":false,\n" + 
        		"\"product_admission_type\":\"TIME_OPEN\",\n" + 
        		"\"product_pickup_point\":\"NOT_SET\",\n" + 
        		"\"product_pickup_point_details\":[],\n" + 
        		"\"product_content\":{\"product_title\":\"Al Ain Palace Museum\",\n" + 
        		"\"product_short_description\":\"Al Ain Palace was one of  Al Ain residences of the late Sheikh Zayed bin Sultan Al Nahyan, the founding father and first President of the United Arab Emirates. He lived there with his family until 1966 when he moved to Abu Dhabi as the new ruler of the emirate. The Palace provides a glimpse into the life of the ruling family and the culture of the local community before the Union in 1971.\",\n" + 
        		"\"product_long_description\":\"Al Ain Palace dates back to 1937 and was built in the traditional architectural style of Al Ain. Al Ain Palace is significant for its close association with the life and story of Sheikh Zayed bin Sultan Al Nahyan during his twenty years as Ruler’s Representative in Al Ain. It stands witness to the great transformations that Al Ain went through from the middle of the last century and contains elements of the city’s traditional architecture.\\nSheikh Zayed bin Sultan Al Nahyan ordered the restoration and rehabilitation of the palace in 1998 by a group of experts and architectural consultants from his own team. During the two-year restoration, a number of extensions were added to the original construction to prepare it to receive visitors. The building displays a number of architectural features common to historic buildings in the UAE. Among these features are the wide-open vents to help cool the building’s\\nmain rooms naturally. The Palace is surrounded with a high wall punctuated by the main entrance gate with a raised hollow hoist on its walls, topped by triangular openings known as mahazel alrami (throwing spindles). The roofing and flooring was made using almost all the parts of palm trees. Materials from palm trees were also used for making rugs, baskets, food covers (majabat), and palm leaf mats used in roofs (da’aoon), as well as for making doors and window frames (darayesh).\\nTeak was also used in the recent renovations of the building. All the furniture seen in the Palace today is of local and traditional design to simulate the interior and furnishings of the time when Sheikh Zayed bin Sultan Al Nahyan lived in it.\\nThe Palace was opened to the public in 2001 to give visitors the chance to explore and experience the character of life in the UAE in the past and to wander around the gardens and visit the rooms in which Sheikh Zayed bin Sultan Al Nahyan spent much of his time. It is a chance to learn about the life of the ruling family, the local culture of the people and the history of Abu Dhabi. After its renovation, a modern tent was added to the Palace in homage to the traditional style of tent in which Sheikh Zayed bin Sultan Al Nahyan used to receive guests. It echoes the desert life that he embraced with pride, and is a symbol representing the deep links to the UAE’s heritage and to Arab hospitality.\\nThe Palace was opened to the public in 2001 to give visitors the chance to explore and experience the character of life in the UAE in the past and to wander around the gardens and visit the rooms in which Sheikh Zayed bin Sultan Al Nahyan spent much of his time. It is a chance to learn about the life of the ruling family, the local culture of the people and the history of Abu Dhabi. After its renovation, a modern tent was added to the Palace in homage to the traditional style of tent in which Sheikh Zayed bin Sultan Al Nahyan used to receive guests. It echoes the desert life that he embraced with pride, and is a symbol representing the deep links to the UAE’s heritage and to Arab hospitality.\",\n" + 
        		"\"product_images\":[{\"image_type\":\"MAIN\",\n" + 
        		"\"image_url\":\"https://sandboxadmin.prioticket.com/tickets/1070294/NEW/480x270_img_1599550813_1599551275__Al_Ain_Palace_Museum_Al_Ain.jpg\"},\n" + 
        		"{\"image_type\":\"BANNER\",\n" + 
        		"\"image_url\":\"https://sandboxadmin.prioticket.com/tickets/1070294/NEW/img_1599550813_1599551275__Al_Ain_Palace_Museum_Al_Ain.jpg\"}]},\n" + 
        		"\"product_locations\":[{\"location_id\":\"2533240\",\n" + 
        		"\"location_label\":\"test\",\n" + 
        		"\"location_name\":\"Al Ain City\",\n" + 
        		"\"location_description\":\"test\",\n" + 
        		"\"location_type\":\"DESTINATION\",\n" + 
        		"\"location_address\":{\"name\":\"Al Ain Palace Museum - Hessa Bint Mohamed Street - Abu Dhabi - United Arab Emirates\",\n" + 
        		"\"street\":\"\",\n" + 
        		"\"city\":\"Al Ain\",\n" + 
        		"\"postal_code\":\"\",\n" + 
        		"\"region\":\"\",\n" + 
        		"\"country\":\"United Arab Emirates\",\n" + 
        		"\"country_code\":\"AE\",\n" + 
        		"\"place_id\":\"\",\n" + 
        		"\"latitude\":\"24.214971\",\n" + 
        		"\"longitude\":\"55.760992\",\n" + 
        		"\"notes\":\"\",\n" + 
        		"\"website\":\"\",\n" + 
        		"\"email\":\"\",\n" + 
        		"\"telephone\":\"\"},\n" + 
        		"\"location_pickup_point\":\"True\"}],\n" + 
        		"\"product_type_seasons\":[{\"product_type_season_start_date\":\"2020-09-08T03:00:00+04:00\",\n" + 
        		"\"product_type_season_end_date\":\"0001-01-01T00:00:00\",\n" + 
        		"\"product_type_season_details\":[{\"product_type_id\":\"121617\",\n" + 
        		"\"product_type\":\"PERSON\",\n" + 
        		"\"product_type_label\":\"All Age\",\n" + 
        		"\"product_type_description\":null,\n" + 
        		"\"product_type_class\":\"STANDARD\",\n" + 
        		"\"product_type_age_from\":1,\n" + 
        		"\"product_type_age_to\":99,\n" + 
        		"\"product_type_pax\":1,\n" + 
        		"\"product_type_capacity\":1,\n" + 
        		"\"product_type_price_type\":\"INDIVIDUAL\",\n" + 
        		"\"product_type_price_tax_id\":\"2\",\n" + 
        		"\"product_type_pricing\":{\"product_type_list_price\":\"0.00\",\n" + 
        		"\"product_type_discount\":\"0.00\",\n" + 
        		"\"product_type_sales_price\":\"0.00\",\n" + 
        		"\"product_type_resale_price\":\"0.00\"}}]}],\n" + 
        		"\"product_categories\":[\"4\"],\n" + 
        		"\"product_category_detail\":[{\"category_id\":\"4\",\n" + 
        		"\"category_name\":\"Museums\",\n" + 
        		"\"category_type\":\"\",\n" + 
        		"\"category_parent_id\":\"\"}],\n" + 
        		"\"product_highlights\":[{\"highlight_description\":\"\\n  Al Ain Palace was one of \\n  Al Ain residences of the late Sheikh Zayed bin Sultan Al Nahyan, the\\n  founding father and first President of the United Arab Emirates. \\n\"}],\n" + 
        		"\"product_content_languages\":[\"en\",\n" + 
        		"\"ar\"]},\n" + 
        		"{\"product_id\":\"22412\",\n" + 
        		"\"product_from_price\":\"50.00\",\n" + 
        		"\"product_start_date\":\"2020-08-28T03:00:00+04:00\",\n" + 
        		"\"product_end_date\":\"2020-11-01T02:59:00+04:00\",\n" + 
        		"\"product_availability\":true,\n" + 
        		"\"product_admission_type\":\"TIME_POINT\",\n" + 
        		"\"product_pickup_point\":\"NOT_SET\",\n" + 
        		"\"product_pickup_point_details\":[],\n" + 
        		"\"product_content\":{\"product_title\":\"Bait Al Oud - Cello Assessment\",\n" + 
        		"\"product_short_description\":\"\",\n" + 
        		"\"product_long_description\":\"\",\n" + 
        		"\"product_images\":[{\"image_type\":\"MAIN\",\n" + 
        		"\"image_url\":\"https://sandboxadmin.prioticket.com/tickets/1070292/NEW/480x270_img_1598097919_1598098209__Bait-Al-Oud-01-Hero.jpg\"},\n" + 
        		"{\"image_type\":\"BANNER\",\n" + 
        		"\"image_url\":\"https://sandboxadmin.prioticket.com/tickets/1070292/NEW/img_1598097919_1598098209__Bait-Al-Oud-01-Hero.jpg\"}]},\n" + 
        		"\"product_locations\":[{\"location_id\":\"2533211\",\n" + 
        		"\"location_label\":\"Check-in point label\",\n" + 
        		"\"location_name\":\"Bait Al Oud\",\n" + 
        		"\"location_description\":\"Check-in point description\",\n" + 
        		"\"location_type\":\"DESTINATION\",\n" + 
        		"\"location_address\":{\"name\":\"Bait Al Oud - بيت العود العربي - Abu Dhabi - United Arab Emirates\",\n" + 
        		"\"street\":\"\",\n" + 
        		"\"city\":\"Abu Dhabi\",\n" + 
        		"\"postal_code\":\"\",\n" + 
        		"\"region\":\"\",\n" + 
        		"\"country\":\"United Arab Emirates\",\n" + 
        		"\"country_code\":\"AE\",\n" + 
        		"\"place_id\":\"\",\n" + 
        		"\"latitude\":\"24.4735435\",\n" + 
        		"\"longitude\":\"54.385973\",\n" + 
        		"\"notes\":\"Address Additional Address Information\",\n" + 
        		"\"website\":\"\",\n" + 
        		"\"email\":\"\",\n" + 
        		"\"telephone\":\"\"},\n" + 
        		"\"location_pickup_point\":\"True\"}],\n" + 
        		"\"product_type_seasons\":[{\"product_type_season_start_date\":\"2020-08-28T03:00:00+04:00\",\n" + 
        		"\"product_type_season_end_date\":\"2020-11-01T02:59:00+04:00\",\n" + 
        		"\"product_type_season_details\":[{\"product_type_id\":\"121593\",\n" + 
        		"\"product_type\":\"ADULT\",\n" + 
        		"\"product_type_label\":\"Adult\",\n" + 
        		"\"product_type_description\":null,\n" + 
        		"\"product_type_class\":\"STANDARD\",\n" + 
        		"\"product_type_age_from\":16,\n" + 
        		"\"product_type_age_to\":60,\n" + 
        		"\"product_type_pax\":1,\n" + 
        		"\"product_type_capacity\":1,\n" + 
        		"\"product_type_price_type\":\"INDIVIDUAL\",\n" + 
        		"\"product_type_price_tax_id\":\"2\",\n" + 
        		"\"product_type_pricing\":{\"product_type_list_price\":\"50.00\",\n" + 
        		"\"product_type_discount\":\"0.00\",\n" + 
        		"\"product_type_sales_price\":\"50.00\",\n" + 
        		"\"product_type_resale_price\":\"0.00\"}},\n" + 
        		"{\"product_type_id\":\"121599\",\n" + 
        		"\"product_type\":\"CHILD\",\n" + 
        		"\"product_type_label\":\"Child\",\n" + 
        		"\"product_type_description\":null,\n" + 
        		"\"product_type_class\":\"STANDARD\",\n" + 
        		"\"product_type_age_from\":11,\n" + 
        		"\"product_type_age_to\":15,\n" + 
        		"\"product_type_pax\":1,\n" + 
        		"\"product_type_capacity\":1,\n" + 
        		"\"product_type_price_type\":\"INDIVIDUAL\",\n" + 
        		"\"product_type_price_tax_id\":\"2\",\n" + 
        		"\"product_type_pricing\":{\"product_type_list_price\":\"10.00\",\n" + 
        		"\"product_type_discount\":\"0.00\",\n" + 
        		"\"product_type_sales_price\":\"10.00\",\n" + 
        		"\"product_type_resale_price\":\"0.00\"}},\n" + 
        		"{\"product_type_id\":\"121598\",\n" + 
        		"\"product_type\":\"CHILD\",\n" + 
        		"\"product_type_label\":\"Child\",\n" + 
        		"\"product_type_description\":null,\n" + 
        		"\"product_type_class\":\"STANDARD\",\n" + 
        		"\"product_type_age_from\":5,\n" + 
        		"\"product_type_age_to\":10,\n" + 
        		"\"product_type_pax\":1,\n" + 
        		"\"product_type_capacity\":1,\n" + 
        		"\"product_type_price_type\":\"INDIVIDUAL\",\n" + 
        		"\"product_type_price_tax_id\":\"2\",\n" + 
        		"\"product_type_pricing\":{\"product_type_list_price\":\"5.00\",\n" + 
        		"\"product_type_discount\":\"0.00\",\n" + 
        		"\"product_type_sales_price\":\"5.00\",\n" + 
        		"\"product_type_resale_price\":\"0.00\"}}]}],\n" + 
        		"\"product_categories\":[\"109\"],\n" + 
        		"\"product_category_detail\":[{\"category_id\":\"109\",\n" + 
        		"\"category_name\":\"Courses\",\n" + 
        		"\"category_type\":\"\",\n" + 
        		"\"category_parent_id\":\"\"}],\n" + 
        		"\"product_highlights\":[{\"highlight_description\":\"Testing 1\"},\n" + 
        		"{\"highlight_description\":\"Trsting 2\"}],\n" + 
        		"\"product_content_languages\":[\"en\",\n" + 
        		"\"ar\"]},\n" + 
        		"{\"product_id\":\"22406\",\n" + 
        		"\"product_from_price\":\"260.00\",\n" + 
        		"\"product_start_date\":\"2020-09-01T03:00:00+04:00\",\n" + 
        		"\"product_end_date\":\"2021-03-01T02:59:00+04:00\",\n" + 
        		"\"product_availability\":true,\n" + 
        		"\"product_admission_type\":\"TIME_POINT\",\n" + 
        		"\"product_pickup_point\":\"NOT_SET\",\n" + 
        		"\"product_pickup_point_details\":[],\n" + 
        		"\"product_content\":{\"product_title\":\"Workshop\",\n" + 
        		"\"product_short_description\":\"Art workshop every Friday in 2020\",\n" + 
        		"\"product_long_description\":\"\",\n" + 
        		"\"product_images\":[{\"image_type\":\"MAIN\",\n" + 
        		"\"image_url\":\"https://sandboxadmin.prioticket.com/tickets/1070292/NEW/480x270_img_1597820864_1597821284__Deser camp.jpg\"},\n" + 
        		"{\"image_type\":\"BANNER\",\n" + 
        		"\"image_url\":\"https://sandboxadmin.prioticket.com/tickets/1070292/NEW/img_1597820864_1597821284__Deser camp.jpg\"}]},\n" + 
        		"\"product_locations\":[{\"location_id\":\"2533205\",\n" + 
        		"\"location_label\":\"info@qaralhosn.ae\",\n" + 
        		"\"location_name\":\"Workshop location\",\n" + 
        		"\"location_description\":\"+971 2 6976400\",\n" + 
        		"\"location_type\":\"VENUE\",\n" + 
        		"\"location_address\":{\"name\":\"Nation Towers - 1st Street - Abu Dhabi - United Arab Emirates, \",\n" + 
        		"\"street\":\"\",\n" + 
        		"\"city\":\"Abu Dhabi\",\n" + 
        		"\"postal_code\":\"1010\",\n" + 
        		"\"region\":\"\",\n" + 
        		"\"country\":\"United Arab Emirates\",\n" + 
        		"\"country_code\":\"AE\",\n" + 
        		"\"place_id\":\"\",\n" + 
        		"\"latitude\":\"24.4642615\",\n" + 
        		"\"longitude\":\"54.327422\",\n" + 
        		"\"notes\":\"https://qasralhosn.ae/\",\n" + 
        		"\"website\":\"https://qasralhosn.ae/\",\n" + 
        		"\"email\":\"\",\n" + 
        		"\"telephone\":\"\"},\n" + 
        		"\"location_pickup_point\":\"True\"}],\n" + 
        		"\"product_type_seasons\":[{\"product_type_season_start_date\":\"2020-09-01T03:00:00+04:00\",\n" + 
        		"\"product_type_season_end_date\":\"2021-01-01T02:59:00+04:00\",\n" + 
        		"\"product_type_season_details\":[{\"product_type_id\":\"121576\",\n" + 
        		"\"product_type\":\"ADULT\",\n" + 
        		"\"product_type_label\":\"Adult\",\n" + 
        		"\"product_type_description\":null,\n" + 
        		"\"product_type_class\":\"STANDARD\",\n" + 
        		"\"product_type_age_from\":18,\n" + 
        		"\"product_type_age_to\":99,\n" + 
        		"\"product_type_pax\":1,\n" + 
        		"\"product_type_capacity\":1,\n" + 
        		"\"product_type_price_type\":\"INDIVIDUAL\",\n" + 
        		"\"product_type_price_tax_id\":\"2\",\n" + 
        		"\"product_type_pricing\":{\"product_type_list_price\":\"260.00\",\n" + 
        		"\"product_type_discount\":\"0.00\",\n" + 
        		"\"product_type_sales_price\":\"260.00\",\n" + 
        		"\"product_type_resale_price\":\"0.00\"}}]},\n" + 
        		"{\"product_type_season_start_date\":\"2021-01-01T03:00:00+04:00\",\n" + 
        		"\"product_type_season_end_date\":\"2021-01-31T02:59:00+04:00\",\n" + 
        		"\"product_type_season_details\":[{\"product_type_id\":\"121600\",\n" + 
        		"\"product_type\":\"CHILD\",\n" + 
        		"\"product_type_label\":\"Child\",\n" + 
        		"\"product_type_description\":null,\n" + 
        		"\"product_type_class\":\"STANDARD\",\n" + 
        		"\"product_type_age_from\":1,\n" + 
        		"\"product_type_age_to\":15,\n" + 
        		"\"product_type_pax\":1,\n" + 
        		"\"product_type_capacity\":1,\n" + 
        		"\"product_type_price_type\":\"INDIVIDUAL\",\n" + 
        		"\"product_type_price_tax_id\":\"2\",\n" + 
        		"\"product_type_pricing\":{\"product_type_list_price\":\"50.00\",\n" + 
        		"\"product_type_discount\":\"0.00\",\n" + 
        		"\"product_type_sales_price\":\"50.00\",\n" + 
        		"\"product_type_resale_price\":\"0.00\"}}]},\n" + 
        		"{\"product_type_season_start_date\":\"2021-02-01T03:00:00+04:00\",\n" + 
        		"\"product_type_season_end_date\":\"2021-03-01T02:59:00+04:00\",\n" + 
        		"\"product_type_season_details\":[{\"product_type_id\":\"121601\",\n" + 
        		"\"product_type\":\"ADULT\",\n" + 
        		"\"product_type_label\":\"Adult\",\n" + 
        		"\"product_type_description\":null,\n" + 
        		"\"product_type_class\":\"STANDARD\",\n" + 
        		"\"product_type_age_from\":1,\n" + 
        		"\"product_type_age_to\":10,\n" + 
        		"\"product_type_pax\":1,\n" + 
        		"\"product_type_capacity\":1,\n" + 
        		"\"product_type_price_type\":\"INDIVIDUAL\",\n" + 
        		"\"product_type_price_tax_id\":\"2\",\n" + 
        		"\"product_type_pricing\":{\"product_type_list_price\":\"10.00\",\n" + 
        		"\"product_type_discount\":\"0.00\",\n" + 
        		"\"product_type_sales_price\":\"10.00\",\n" + 
        		"\"product_type_resale_price\":\"0.00\"}}]}],\n" + 
        		"\"product_categories\":[\"107\",\n" + 
        		"\"110\"],\n" + 
        		"\"product_category_detail\":[{\"category_id\":\"107\",\n" + 
        		"\"category_name\":\"Art Workshop\",\n" + 
        		"\"category_type\":\"\",\n" + 
        		"\"category_parent_id\":\"\"},\n" + 
        		"{\"category_id\":\"110\",\n" + 
        		"\"category_name\":\"Pottery\",\n" + 
        		"\"category_type\":\"\",\n" + 
        		"\"category_parent_id\":\"\"}],\n" + 
        		"\"product_highlights\":[],\n" + 
        		"\"product_content_languages\":[\"en\",\n" + 
        		"\"ar\"]},\n" + 
        		"{\"product_id\":\"22402\",\n" + 
        		"\"product_from_price\":\"60.00\",\n" + 
        		"\"product_start_date\":\"2020-08-17T03:00:00+04:00\",\n" + 
        		"\"product_end_date\":\"0001-01-01T00:00:00\",\n" + 
        		"\"product_availability\":true,\n" + 
        		"\"product_admission_type\":\"TIME_DATE\",\n" + 
        		"\"product_pickup_point\":\"NOT_SET\",\n" + 
        		"\"product_pickup_point_details\":[],\n" + 
        		"\"product_content\":{\"product_title\":\"General Admission\",\n" + 
        		"\"product_short_description\":\"General admission ticket QAH\",\n" + 
        		"\"product_long_description\":\"\",\n" + 
        		"\"product_images\":[{\"image_type\":\"MAIN\",\n" + 
        		"\"image_url\":\"https://sandboxadmin.prioticket.com/tickets/1070294/NEW/480x270_img_1597681304_1597681418__QAH 2.jpg\"},\n" + 
        		"{\"image_type\":\"BANNER\",\n" + 
        		"\"image_url\":\"https://sandboxadmin.prioticket.com/tickets/1070294/NEW/img_1597681304_1597681418__QAH 2.jpg\"}]},\n" + 
        		"\"product_locations\":[],\n" + 
        		"\"product_type_seasons\":[{\"product_type_season_start_date\":\"2020-08-17T03:00:00+04:00\",\n" + 
        		"\"product_type_season_end_date\":\"0001-01-01T00:00:00\",\n" + 
        		"\"product_type_season_details\":[{\"product_type_id\":\"121573\",\n" + 
        		"\"product_type\":\"ADULT\",\n" + 
        		"\"product_type_label\":\"Adult\",\n" + 
        		"\"product_type_description\":null,\n" + 
        		"\"product_type_class\":\"STANDARD\",\n" + 
        		"\"product_type_age_from\":13,\n" + 
        		"\"product_type_age_to\":99,\n" + 
        		"\"product_type_pax\":1,\n" + 
        		"\"product_type_capacity\":1,\n" + 
        		"\"product_type_price_type\":\"INDIVIDUAL\",\n" + 
        		"\"product_type_price_tax_id\":\"2\",\n" + 
        		"\"product_type_pricing\":{\"product_type_list_price\":\"60.00\",\n" + 
        		"\"product_type_discount\":\"0.00\",\n" + 
        		"\"product_type_sales_price\":\"60.00\",\n" + 
        		"\"product_type_resale_price\":\"0.00\"}},\n" + 
        		"{\"product_type_id\":\"121572\",\n" + 
        		"\"product_type\":\"CHILD\",\n" + 
        		"\"product_type_label\":\"Child\",\n" + 
        		"\"product_type_description\":null,\n" + 
        		"\"product_type_class\":\"STANDARD\",\n" + 
        		"\"product_type_age_from\":3,\n" + 
        		"\"product_type_age_to\":12,\n" + 
        		"\"product_type_pax\":1,\n" + 
        		"\"product_type_capacity\":1,\n" + 
        		"\"product_type_price_type\":\"INDIVIDUAL\",\n" + 
        		"\"product_type_price_tax_id\":\"2\",\n" + 
        		"\"product_type_pricing\":{\"product_type_list_price\":\"30.00\",\n" + 
        		"\"product_type_discount\":\"0.00\",\n" + 
        		"\"product_type_sales_price\":\"30.00\",\n" + 
        		"\"product_type_resale_price\":\"0.00\"}}]}],\n" + 
        		"\"product_categories\":[\"4\"],\n" + 
        		"\"product_category_detail\":[{\"category_id\":\"4\",\n" + 
        		"\"category_name\":\"Museums\",\n" + 
        		"\"category_type\":\"\",\n" + 
        		"\"category_parent_id\":\"\"}],\n" + 
        		"\"product_highlights\":[],\n" + 
        		"\"product_content_languages\":[\"en\",\n" + 
        		"\"ar\"]},\n" + 
        		"{\"product_id\":\"22401\",\n" + 
        		"\"product_from_price\":\"260.00\",\n" + 
        		"\"product_start_date\":\"2020-08-17T03:00:00+04:00\",\n" + 
        		"\"product_end_date\":\"2020-12-01T02:59:00+04:00\",\n" + 
        		"\"product_availability\":true,\n" + 
        		"\"product_admission_type\":\"TIME_POINT\",\n" + 
        		"\"product_pickup_point\":\"NOT_SET\",\n" + 
        		"\"product_pickup_point_details\":[],\n" + 
        		"\"product_content\":{\"product_title\":\"Workshop Art\",\n" + 
        		"\"product_short_description\":\"Art workshop in Qasr Al Hosn\",\n" + 
        		"\"product_long_description\":\"A historical landmark, experience Qasr Al Hosn and learn about the story of Abu Dhabi, from traditional pearling settlement to modern global metropolis.\\n\\nAdmission to House of Artisans, Cultural Foundation, and landscape is complimentary, excluding Qasr Al Hosn, special workshops and events. Tickets can be booked and purchased online or onsite, directly at Ticketing.\",\n" + 
        		"\"product_images\":[{\"image_type\":\"MAIN\",\n" + 
        		"\"image_url\":\"https://sandboxadmin.prioticket.com/tickets/1070294/NEW/480x270_img_1597676936_1597677150__QAH.jpg\"},\n" + 
        		"{\"image_type\":\"BANNER\",\n" + 
        		"\"image_url\":\"https://sandboxadmin.prioticket.com/tickets/1070294/NEW/img_1597676936_1597677150__QAH.jpg\"}]},\n" + 
        		"\"product_locations\":[{\"location_id\":\"2533200\",\n" + 
        		"\"location_label\":\"\",\n" + 
        		"\"location_name\":\"Qasr Al Hosn\",\n" + 
        		"\"location_description\":\"\",\n" + 
        		"\"location_type\":\"VENUE\",\n" + 
        		"\"location_address\":{\"name\":\"Qasr Al Hosn - Rashid Bin Saeed Al Maktoum St(2nd St) - Abu Dhabi - United Arab Emirates, \",\n" + 
        		"\"street\":\"\",\n" + 
        		"\"city\":\"Abu Dhabi\",\n" + 
        		"\"postal_code\":\"\",\n" + 
        		"\"region\":\"\",\n" + 
        		"\"country\":\"United Arab Emirates\",\n" + 
        		"\"country_code\":\"AE\",\n" + 
        		"\"place_id\":\"\",\n" + 
        		"\"latitude\":\"24.482237\",\n" + 
        		"\"longitude\":\"54.354723\",\n" + 
        		"\"notes\":\"\",\n" + 
        		"\"website\":\"\",\n" + 
        		"\"email\":\"\",\n" + 
        		"\"telephone\":\"\"},\n" + 
        		"\"location_pickup_point\":\"False\"}],\n" + 
        		"\"product_type_seasons\":[{\"product_type_season_start_date\":\"2020-08-17T03:00:00+04:00\",\n" + 
        		"\"product_type_season_end_date\":\"2020-12-01T02:59:00+04:00\",\n" + 
        		"\"product_type_season_details\":[{\"product_type_id\":\"121571\",\n" + 
        		"\"product_type\":\"ADULT\",\n" + 
        		"\"product_type_label\":\"Adult\",\n" + 
        		"\"product_type_description\":null,\n" + 
        		"\"product_type_class\":\"STANDARD\",\n" + 
        		"\"product_type_age_from\":18,\n" + 
        		"\"product_type_age_to\":99,\n" + 
        		"\"product_type_pax\":1,\n" + 
        		"\"product_type_capacity\":1,\n" + 
        		"\"product_type_price_type\":\"INDIVIDUAL\",\n" + 
        		"\"product_type_price_tax_id\":\"2\",\n" + 
        		"\"product_type_pricing\":{\"product_type_list_price\":\"260.00\",\n" + 
        		"\"product_type_discount\":\"0.00\",\n" + 
        		"\"product_type_sales_price\":\"260.00\",\n" + 
        		"\"product_type_resale_price\":\"0.00\"}}]}],\n" + 
        		"\"product_categories\":[\"107\"],\n" + 
        		"\"product_category_detail\":[{\"category_id\":\"107\",\n" + 
        		"\"category_name\":\"Art Workshop\",\n" + 
        		"\"category_type\":\"\",\n" + 
        		"\"category_parent_id\":\"\"}],\n" + 
        		"\"product_highlights\":[],\n" + 
        		"\"product_content_languages\":[\"en\",\n" + 
        		"\"ar\"]}]}";

JSONObject dddd = new JSONObject(dd);

        getKey(dddd,"country");
//        System.out.println("sudhir"+count);
        
        System.out.println("list value "+ Api_List_Elements);
       



    }
}
