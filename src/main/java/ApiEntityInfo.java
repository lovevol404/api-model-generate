

import java.util.List;

public class ApiEntityInfo {
    private String type;
    private List<FieldInfo> fieldInfos;
    private List<ApiEntityInfo> refs;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<FieldInfo> getFieldInfos() {
        return fieldInfos;
    }

    public void setFieldInfos(List<FieldInfo> fieldInfos) {
        this.fieldInfos = fieldInfos;
    }

    public List<ApiEntityInfo> getRefs() {
        return refs;
    }

    public void setRefs(List<ApiEntityInfo> refs) {
        this.refs = refs;
    }
}
