import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.Set;

public class ApiEntityInfoUtilsTest {

    @Test
    public void fromClass() throws Exception {
        ApiEntityInfoUtils apiEntityInfoUtils = new ApiEntityInfoUtils(TestApiBody.class);
        ApiEntityInfo apiEntityInfo = apiEntityInfoUtils.fromClass();
        assert TestApiBody.class.getName().equals(apiEntityInfo.getType());
        System.out.println(JSON.toJSONString(apiEntityInfo));
    }

}