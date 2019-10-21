/**
 * Created by canbay on 21.10.2019.
 */
public enum ProcessType {
    ENCRYPTION("Encryption",1),DECRYPTION("Decryption",2);

    private final String key;
    private final Integer value;

    ProcessType(String key, Integer value){
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public Integer getValue() {
        return value;
    }
}
