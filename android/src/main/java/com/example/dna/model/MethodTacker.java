package com.example.dna.model;

import com.example.dna.util.GsonUtils;
import com.example.dna.exception.ArgsException;

import java.util.ArrayList;
import java.util.List;


/**
 * Author: Zhiqing.Zhang
 * FileName: MethodTacker
 * Description:
 */

public class MethodTacker {
    MethodInfo info;

    public MethodTacker(MethodInfo info) {
        this.info = info;
    }

    public List<Object> getArgs(List<ParameterInfo> args) throws ArgsException {
        if (info == null) {
            return null;
        }
        List<Class<?>> reflectArgs = info.getArgs();
        if (reflectArgs == null || args == null) {
            return null;
        }
        if (reflectArgs.size() != args.size()) {
            throw new ArgsException("Args size error");
        }
        List<Object> argsElements = new ArrayList<>();
        for (int i = 0; i < reflectArgs.size(); i++) {
            argsElements.add(isString(reflectArgs.get(i)) ? args.get(i).getContent() : GsonUtils.fromJson(args.get(i).getContent(), reflectArgs.get(i)));
        }

        return argsElements;
    }

    public static boolean isString(Class clz) {
        if (clz.getName().equals(String.class.getName())) {
            return true;
        }
        return false;
    }

}
