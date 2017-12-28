package com.qxrj.net.Director;

import android.support.v4.util.ArrayMap;

/**
 * Created by Administrator on 2016/6/7.
 */
public class Dictionary {
    private ArrayMap<String, String>                        map = new ArrayMap<>();
    private String                                          TAGLOG = "Dictionary";

    public Dictionary() {}

    public void add(String key, Object object) {

        if (object instanceof Integer) {
            map.put(key, String.valueOf(((Integer) object).intValue()));
        } else if (object instanceof String) {
            map.put(key, (String)object);
        } else if (object instanceof Double) {
            map.put(key, String.valueOf(((Double) object).doubleValue()));
        } else if (object instanceof Float) {
            map.put(key, String.valueOf(((Float) object).floatValue()));
        } else if (object instanceof Long) {
            map.put(key, String.valueOf(((Long) object).longValue()));
        } else if (object instanceof Boolean) {
            map.put(key, String.valueOf(((Boolean) object).booleanValue()));
        } else if (object instanceof Short) {
            map.put(key, String.valueOf(((Short) object).shortValue()));
        } else if (object instanceof Byte) {
            map.put(key, String.valueOf(((Byte) object).byteValue()));
        }
    }

    public void setInt(String key, int object) {
        map.remove(key);
        map.put(key, String.valueOf(object));
    }
    public int getInt(String key) {
        String value = map.get(key);
        if (value == null || value.length() == 0) {
            log(key+" == int null");
            return 0;
        }
        return Integer.valueOf(value);
    }

    public void setString(String key, String value) {
        map.remove(key);
        map.put(key, value);
    }
    public String getString(String key) {
        String value = map.get(key);
        if (value == null || value.length() == 0) {
            log(key+" == String null");
            return "";
        }
        return value;
    }

    public void setBoolean(String key, boolean value) {
        map.remove(key);
        map.put(key, String.valueOf(value));
    }
    public boolean getBoolean(String key) {
        String value = map.get(key);
        if (value == null || value.length() == 0) {
            log(key+" == boolean null");
            return false;
        }
        return Boolean.valueOf(value).booleanValue();
    }

    public void setLong(String key, long value) {
        map.remove(key);
        map.put(key, String.valueOf(value));
    }
    public long getLong(String key) {
        String value = map.get(key);
        if (value == null || value.length() == 0) {
            log(key+" == long null");
            return 0;
        }
        return Long.parseLong(value);
    }

    public void setShort(String key, short value) {
        map.remove(key);
        map.put(key, String.valueOf(value));
    }
    public short getShort(String key) {
        String value = map.get(key);
        if (value == null || value.length() == 0) {
            log(key+" == short null");
            return 0;
        }
        return Short.parseShort(value);
    }

    public void setFloat(String key, float value) {
        map.remove(key);
        map.put(key, String.valueOf(value));
    }
    public float getFloat(String key) {
        String value = map.get(key);
        if (value == null || value.length() == 0) {
            log(key+" == float null");
            return 0;
        }
        //return Float.valueOf(value).floatValue();
        return Float.parseFloat(value);
    }

    public void setDouble(String key, double value) {
        map.remove(key);
        map.put(key, String.valueOf(value));
    }
    public double getDouble(String key) {
        String value = map.get(key);
        if (value == null || value.length() == 0) {
            log(key+" double null");
            return 0;
        }
        return Double.parseDouble(value);
    }

    public void setByte(String key, Byte value) {
        map.remove(key);
        map.put(key, String.valueOf(value));
    }
    public Byte getByte(String key) {
        String value = map.get(key);
        if (value == null || value.length() == 0) {
            log(key+" == byte null");
            return null;
        }
        return Byte.parseByte(value);
    }

    public String toString() {
        return map.toString();
    }

    public int size() {
        return map.size();
    }

    public String getKey(int i) {
        return map.keyAt(i);
    }

    public void clear() {
        map.clear();
    }

    private void log(String value) {
        //Log.e(TAGLOG, value);
    }
}
