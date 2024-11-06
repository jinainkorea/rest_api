package com.example.demo.Global.RsData;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RsData<T> {
    private T data;
    private String resultCode;
    private String msg;
    public static <T> RsData<T> of(String resultCode, String msg, T data) {
        return new RsData<>(data, resultCode, msg);
    }
    public static <T> RsData<T> of(String resultCode, String msg) {
        return of(resultCode, msg, null);
    }
    @JsonIgnore
    public boolean isSuccess() {
        return resultCode.startsWith("200");
    }
    @JsonIgnore
    public boolean isFail() {
        return !isSuccess();
    }
}
