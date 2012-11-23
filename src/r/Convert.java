package r;

import r.data.*;

public class Convert {

    public static double string2double(String v) {
        if (v.equals("NA")) {
            return RDouble.NA;
        }
        // FIXME use R rules
        try {
            return Double.parseDouble(v);
        } catch (NumberFormatException e) {
            return RDouble.NA;
        }
    }

    public static String double2string(double d) {
        // TODO check what's generated by this method
        if (RDouble.RDoubleUtils.isNA(d)) {
            return "NA";
        }
        // FIXME use R rules
        return Double.toString(d);
    }

    public static int string2logical(String b) {
        if (b.equals("TRUE") || b.equals("T")) {
            return 1;
        } else if (b.equals("FALSE") || b.equals("F")) {
            return 0;
        } else {
            return RLogical.NA;
        }
    }

    public static String logical2string(int i) {
        switch (i) {
            case 0:
                return "FALSE";
            case RLogical.NA:
                return "NA";
            default:
                return "TRUE";
        }
    }

    public static String int2string(int i) {
        if (i == RInt.NA) {
            return "NA";
        }
        // FIXME use R rules
        return Integer.toString(i) + "L";
    }

    public static int string2int(String v) {
        if (v.equals("NA")) {
            return RInt.NA;
        }
        // FIXME use R rules
        try {
            return Integer.parseInt(v);
        } catch (NumberFormatException e) {
            return RInt.NA;
        }
    }

    public static int double2int(double d) {
        if (RDouble.RDoubleUtils.isNA(d)) {
            return RInt.NA;
        }
        return (int) d; // FIXME: NA when double too big to fit + warning
    }

    public static double logical2double(int l) {
        return  l == RLogical.NA ? RDouble.NA : l;
    }

    public static double int2double(int i) {
        return  i == RInt.NA ? RDouble.NA : i;
    }

    public static int double2logical(double d) {
        if (RDouble.RDoubleUtils.isNA(d)) {
            return RLogical.NA;
        }
        return d != 0 ? RLogical.TRUE : RLogical.FALSE;
    }

    public static int int2logical(int i) {
        if (i == RInt.NA) {
            return RLogical.NA;
        }
        return i != 0 ? RLogical.TRUE : RLogical.FALSE;
    }

    public static int logical2int(int l) {
        return l;
    }

    public static int scalar2int(RAny v) { // FIXME: rewrite to scalar impl types if we have reliable scalarization, or remove
        if (v instanceof RInt) {
            return ((RInt) v).getInt(0);
        }
        if (v instanceof RDouble) {
            return double2int(((RDouble) v).getDouble(0));
        }
        if (v instanceof RLogical) {
            return logical2int(((RLogical) v).getLogical(0));
        }
        Utils.nyi("unsupported type");
        return -1;
    }

    public static double scalar2double(RAny v) { // FIXME: rewrite to scalar impl types if we have reliable scalarization, or remove
        if (v instanceof RInt) {
            return double2int(((RInt) v).getInt(0));
        }
        if (v instanceof RDouble) {
            return ((RDouble) v).getDouble(0);
        }
        if (v instanceof RLogical) {
            return logical2double(((RLogical) v).getLogical(0));
        }
        Utils.nyi("unsupported type");
        return -1;
    }
}
