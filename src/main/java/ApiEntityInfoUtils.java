

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

public class ApiEntityInfoUtils {

    private static final Map<String, Object> SIMPLE_CLASS_VALUE = new HashMap<String, Object>() {{
        put(String.class.getName(), "string");
        put(Date.class.getName(), new Date());
        put(Short.class.getName(), 0);
        put(Float.class.getName(), 0);
        put(Character.class.getName(), 0);
        put(Integer.class.getName(), 0);
        put(Boolean.class.getName(), false);
        put(Double.class.getName(), 0);
        put(Long.class.getName(), 0);
        put("int", 0);
        put("char", 0);
        put("short", 0);
        put("double", 0);
        put("float", 0);
        put("long", 0);
        put("boolean", false);
        put("byte", 0);
    }};

    private Class<?> target;
    private Queue<Class<?>> ref = new LinkedList<>();
    private Set<String> doneRef = new HashSet<>();
    private List<ApiEntityInfo> apiEntityInfosRefs = new ArrayList<>();

    public ApiEntityInfoUtils(Class<?> target){
        this.target = target;
    }

    public ApiEntityInfoUtils(Object target){
        this.target = target.getClass();
    }

    public ApiEntityInfo fromClass() throws Exception {
        ApiEntityInfo apiEntityInfo = fromClass(target);
        apiEntityInfo.setRefs(apiEntityInfosRefs);
        return apiEntityInfo;
    }

    public ApiEntityInfo fromClass(Class<?> t) throws Exception{
        ApiEntityInfo apiEntityInfo = new ApiEntityInfo();
        List<FieldInfo> fieldInfos = new ArrayList<>();
        Field[] fields = t.getDeclaredFields();
        for (Field field : fields) {
            FieldInfo fieldInfo = new FieldInfo();
            fieldInfo.setName(field.getName());
            fieldInfo.setType(field.getType().getSimpleName());
            Type fieldType = field.getGenericType();
            fieldInfo.setExampleValue(exampleValue(fieldType));
            ApiInfo annotation = field.getAnnotation(ApiInfo.class);
            if (!Objects.isNull(annotation)) {
                fieldInfo.setDesc(annotation.value());
            }
            fieldInfos.add(fieldInfo);
        }
        apiEntityInfo.setFieldInfos(fieldInfos);
        apiEntityInfo.setType(t.getName());
        doneRef.add(target.getName());

        while (!ref.isEmpty()){
            Class<?> r = ref.poll();
            if (r==null || doneRef.contains(r.getName())){
                continue;
            }
            apiEntityInfosRefs.add(fromClass(r));
        }
        return apiEntityInfo;
    }

    private Object exampleValue(Type t) throws Exception {
        if (isSimpleClass(t)) {
            return simpleClassExample(t);
        } else if (isEnum(t)) {
            return enumExample(t);
        } else if (isArray(t)) {
            return arrayExample(t);
        } else if (isListOrSet(t)) {
            return listOrSetExample(t);
        } else if (isMap(t)) {
            return mapExample(t);
        }
        ref.add(Class.forName(t.getTypeName()));
        return "ref#" + Class.forName(t.getTypeName()).getName();
    }

    private boolean isSimpleClass(Type t) {
        return SIMPLE_CLASS_VALUE.containsKey(t.getTypeName());
    }

    private Object simpleClassExample(Type t) {
        return SIMPLE_CLASS_VALUE.get(t.getTypeName());
    }

    private boolean isEnum(Type t) throws Exception {
        return Class.forName(ignoreGeneric(t.getTypeName())).isEnum();
    }

    private List<EnumInfo> enumExample(Type t) throws Exception {
        List<EnumInfo> enumInfos = new ArrayList<>();
        Class c = Class.forName(ignoreGeneric(t.getTypeName()));
        Object[] enumConstants = c.getEnumConstants();
        for (Object value : enumConstants) {
            EnumInfo enumInfo = new EnumInfo();
            String name = ((Enum<?>) value).name();
            ApiInfo annotation = c.getField(name).getAnnotation(ApiInfo.class);
            enumInfo.setValue(value);
            if (Objects.nonNull(annotation)) {
                enumInfo.setDesc(annotation.value());
            }
            enumInfos.add(enumInfo);
        }
        return enumInfos;
    }

    private boolean isArray(Type t) {
        return t.getClass().isArray();
    }

    private List<Object> arrayExample(Type t) throws Exception {
        List<Object> examples = new ArrayList<>();
        Class<?> componentType = t.getClass().getComponentType();
        examples.add(exampleValue(componentType));
        return examples;
    }

    private boolean isListOrSet(Type t) throws Exception {
        String typeName = ignoreGeneric(t.getTypeName());
        return typeName.equals(List.class.getName()) || typeName.equals(Set.class.getName());
    }

    private List<Object> listOrSetExample(Type t) throws Exception {
        ParameterizedType pt = (ParameterizedType) t;
        Type[] ts = pt.getActualTypeArguments();
        List<Object> examples = new ArrayList<>();
        examples.add(exampleValue(ts[0]));
        return examples;
    }

    private boolean isMap(Type t) throws Exception {
        return ignoreGeneric(t.getTypeName()).equals(Map.class.getName());
    }

    private List<Object> mapExample(Type t) throws Exception {
        ParameterizedType pt = (ParameterizedType) t;
        Type[] ts = pt.getActualTypeArguments();
        List<Object> examples = new ArrayList<>();
        Map<Object, Object> map = new HashMap<>();
        map.put(exampleValue(ts[0]), exampleValue(ts[1]));
        examples.add(map);
        return examples;
    }

    private String ignoreGeneric(String typeName) {
        int index = typeName.indexOf("<");
        if (index >= 0) {
            return typeName.substring(0, index);
        }
        return typeName;
    }
}
