package com.huateng.xhcp.service.upload;

import lombok.Getter;

/**
 * Created by sam.pan on 2017/3/27.
 */
public enum UploadType {
    SUCCESS(0, "上传成功"),
    NO_FILE(1, "请选择文件"),
    CANNOT_WIRTE(2, "上传的目录没有写权限"),
    NO_FILE_TYPE(3, "上传的目录找不到"),
    EXCEED_SIZE(4, "上传文件大小超过限制"),
    ERROR_EXT(5, "上传文件扩展名是不允许的扩展名"),
    OTHER_ERR(6, "其他错误"),
    SAVE_DATA_ERR(7, "保存数据失败"),
    NO_UPLOAD_DIR(8, "上传的目录不存在");

    private final int value;
    private final @Getter String reasonPhrase;

    UploadType(int value, String reasonPhrase) {
        this.value = value;
        this.reasonPhrase = reasonPhrase;
    }

    /**
     * 返回状态值
     */
    public int value() {
        return this.value;
    }

    /**
     * 返回状态值的字符串
     */
    @Override
    public String toString() {
        return Integer.toString(value);
    }

    /**
     * 根据对应的状态值返回枚举类型
     * @param statusCode 要返回枚举类型的状态值
     * @return 返回对应状态值的枚举类型
     * @throws IllegalArgumentException 如果没有找到对应的状态值，即抛出异常
     */
    public static UploadType valueOf(int statusCode) {
        for (UploadType status : values()) {
            if (status.value == statusCode) {
                return status;
            }
        }
        throw new IllegalArgumentException("No matching constant for [" + statusCode + "]");
    }
}
