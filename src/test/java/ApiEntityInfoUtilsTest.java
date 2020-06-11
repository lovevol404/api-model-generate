import org.junit.Test;

import java.util.Set;

public class ApiEntityInfoUtilsTest {

    public static class TestApiBody{
        private String test;
    }

    @Test
    public void fromClass() throws Exception {
        ApiEntityInfoUtils apiEntityInfoUtils = new ApiEntityInfoUtils(TestApiBody.class);
        ApiEntityInfo apiEntityInfo = apiEntityInfoUtils.fromClass();
        assert TestApiBody.class.getName().equals(apiEntityInfo.getType());
    }

}