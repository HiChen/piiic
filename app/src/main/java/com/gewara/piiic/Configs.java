package com.gewara.piiic;

public final class Configs
{
    public static final boolean DEBUG =false;
    public static final String SHARED="Gewara";
    public static <T> String makeTag(T paramT)
    {
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = String.valueOf(paramT);
        return String.format("Piiic-%s", arrayOfObject);
    }

}