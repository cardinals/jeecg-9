package org.jeecgframework.test.demo;

import org.jeecgframework.web.cgform.util.SignatureUtil;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 云强 on 2017/11/17.
 */
public class TestInterface {


    public static void main(String[] args) {

        String url = "http://localhost:8180/jeecg/cgFormDataController.do?updateFormInfo";
//        String url = "http://117.48.215.66:8280/jeecg/cgFormDataController.do?updateFormInfo";

        String id = "b4e3c877-58d7-4e33-90ea-7b0b777c4c6f";
        String data = "{\"attachment\":\"\",\"bpmStatus\":\"1\",\"content\":\"1112\",\"finishRemark\":\"55\",\"finishTime\":\"2017-11-15\",\"id\":\"b4e3c877-58d7-4e33-90ea-7b0b777c4c6f\",\"level\":\"1\",\"programmer\":\"77\",\"proposeDate\":\"2017-11-17\",\"propose_man\":\"8888\",\"title\":\"111\",\"updateBy\":\"luke\",\"updateDate\":\"2017-11-17 03:43:10\",\"updateName\":\"luke\"}";
        String key = "26F72780372E84B6CFAED6F7B19139CC47B1912B6CAED753";

        String tableName = "work_demand";
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("tableName", tableName);
        paramMap.put("id", id);
        paramMap.put("data", data);
        paramMap.put("method", "updateFormInfo");
        String sign = SignatureUtil.sign(paramMap, key);// 生成签名
        Map<String, String> maps = new HashMap<String, String>();
        maps.put("tableName", tableName);
        maps.put("id", id);
        maps.put("data", data);
        System.out.println(sign);
        maps.put("sign", sign);

        System.out.println("begin");
        try {
            System.out.println( Jsoup.connect(url).data(maps).get().body().text().toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("end");

//        Key = id, Value = b4e3c877-58d7-4e33-90ea-7b0b777c4c6f
//        11-17 15:43:10.369 24387-25936/com.lyq.myapp I/System.out: Key = tableName, Value = work_demand
//        11-17 15:43:10.369 24387-25936/com.lyq.myapp I/System.out: Key = sign, Value = 9EA050B22B9EEF5AB8BE4839AD9BF23B
//        11-17 15:43:10.369 24387-25936/com.lyq.myapp I/System.out: Key = data, Value = {"attachment":"","bpmStatus":"1","content":"111了月","finishRemark":"阿狸","finishTime":"2017-11-15","id":"b4e3c877-58d7-4e33-90ea-7b0b777c4c6f","level":"1","programmer":"李","proposeDate":"2017-11-17","proposeMan":"阿里","title":"111","updateBy":"luke","updateDate":"2017-11-17 03:43:10","updateName":"luke"}
//        11-17 15:43:10.609 24387-25936/com.lyq.myapp I/System.out:

    }
}
