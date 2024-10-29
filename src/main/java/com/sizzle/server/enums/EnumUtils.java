package com.sizzle.server.enums;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EnumUtils {
    public static <E extends Enum<E> & IEnumText> Map<String, String> enumToMap(
            Class<E> enumClass) {
        return Arrays.stream(enumClass.getEnumConstants()).collect(Collectors.toMap(Enum::name,
                IEnumText::getText, (e1, e2) -> e1, LinkedHashMap::new));
    }

    public static <E extends Enum<E>> Stream<E> enumToStream(Class<E> enumClass) {
        return Arrays.stream(enumClass.getEnumConstants());
    }

    public static <T extends HasType> List<String> getStringTypes(List<T> typeList) {
        if (typeList == null) {
            return null;
        }
        return typeList.stream().map(type -> type.getType().getText()).collect(Collectors.toList());
    }

    public static <E extends Enum<E> & IEnumText> String getTextOrDefault(E enumValue,
            E defaultValue) {
        return Objects.requireNonNullElse(enumValue, defaultValue).getText();
    }
}
