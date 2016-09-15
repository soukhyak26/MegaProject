package com.affaince.subscription.common.scheduling;

/**
 * Created by mandar on 13-09-2016.
 */
//Field   mandatory Allowed Sp.Chars
//Seconds       YES	0-59	, - * /
//Minutes       YES 0-59,-*/
//Hours         YES 0-23,-*/
//Day of month  YES 1-31,-*?/L W
//Month         YES 1-12or JAN-DEC,-*/
//Day of week   YES 1-7or SUN-SAT,-*?/L #
//Year          NO empty,1970-2099,-*/
public class CronExpressionBuilder {
    private String startDate;
    private String time;
    private boolean recurring;
    private boolean SUN;
    private boolean MON;
    private boolean TUE;
    private boolean WED;
    private boolean THU;
    private boolean FRI;
    private boolean SAT;

    private boolean JAN;
    private boolean FEB;
    private boolean MAR;
    private boolean APR;
    private boolean MAY;
    private boolean JUN;
    private boolean JUL;
    private boolean AUG;
    private boolean SEP;
    private boolean OCT;
    private boolean NOV;
    private boolean DEC;
    private int[] daysOfMonth;
    private int[] years;

    public static void main(String[] args) {
        CronExpressionBuilder pCron = new CronExpressionBuilder();
        //pCron.setTime("11:00 AM");
        pCron.setTime("17:30");
        //pCron.setMON(true);
        //pCron.setSUN(true);
        //pCron.setJAN(true);
        //pCron.setMAR(true);
        //pCron.setYears(new int[]{2016});
        pCron.setRecurring(true);
        pCron.setStartDate("12-05-2011");
        System.out.println(pCron.buildCronExpression());
    }

    public int[] getYears() {
        return years;
    }

    public void setYears(int[] years) {
        this.years = years;
    }

    public String buildCronExpression() {
        final String hour = buildHoursString();
        final String minutes = buildMinutesString();
        if (isRecurring()) {
            final String daysOfXString = buildDayString();
            //final String monthString = buildMonthString();
            final String yearString = buildYearsString();
            //Day of month and day of week are mutually exclusive
            //In case both are provided then dayOfMonth will take precedence over dayOfWeek
            StringBuilder cronExp = new StringBuilder();
            cronExp.append("0");
            if (!"".equals(minutes)) {
                cronExp.append(" ");
                cronExp.append(minutes);
            }
            if (!"".equals(hour)) {
                cronExp.append(" ");
                cronExp.append(hour);
            }
            if (!"".equals(daysOfXString)) {
                cronExp.append(" ");
                cronExp.append(daysOfXString);
            }
            if (!"".equals(yearString)) {
                cronExp.append(" ");
                cronExp.append(yearString);
            }
            //final String cronExp = "0 " + minutes + " " + hour + " " + daysOfXString + " " + yearString;
            return cronExp.toString();
        } else {
            final String cronExp = buildStartDateString();
            return cronExp;
        }
    }

    private String buildStartDateString() {
        String startDate = getStartDate();
        String[] dateArray = startDate.split("\\-");
        String day = dateArray[0];
        String hour = buildHoursString();
        String minutes = buildMinutesString();
        String cronExp = "";
        if (day.charAt(0) == '0') {
            day = day.substring(1);
        }
        String month = dateArray[1];
        if (month.charAt(0) == '0') {
            month = month.substring(1);
        }
        String year = dateArray[2];
        cronExp = "0 " + minutes + " " + hour + " " + day + " " + month
                + " ? " + year;
        return cronExp;
    }

    private String buildHoursString() {
        String time = getTime();
        String[] time1 = time.split("\\:");
        String[] time2 = null;
        if (time1[1].contains("\\ ")) {
            time2 = time1[1].split("\\ ");
        } else {
            time2 = new String[1];
            time2[0] = "";
        }
        String hour = "";
        if (time2.length > 1 && time2[1].equalsIgnoreCase("PM")) {
            Integer hourInt = Integer.parseInt(time1[0]) + 12;
            if (hourInt == 24) {
                hourInt = 0;
            }
            hour = hourInt.toString();
        } else {
            hour = time1[0];
        }
        return hour;
    }

    private String buildMinutesString() {
        String time = getTime();
        String[] time1 = null;
        String[] time2 = null;
        if (time.contains(":")) {
            time1 = time.split("\\:");
        }
        if (null != time1 && time1.length > 1) {
            time2 = time1[1].split("\\ ");
            return time2[0];
        }
        return "";
    }

    private String buildDayString() {
        StringBuilder sb = new StringBuilder(800);
        boolean moreConditions = false;
        final int[] daysOfMonth = getDaysOfMonth();
        //if DayOfMonth is specified then it would take priority over day name;
        //only single day permonth is allowed for this version.. in order to get mul
        if (null != daysOfMonth && daysOfMonth.length > 0) {
            for (int i = 0; i < daysOfMonth.length; i++) {
                sb.append(daysOfMonth[i]);
                if (i != daysOfMonth.length - 1) {
                    sb.append(",");
                }
            }
            sb.append(" ");
            sb.append(buildMonthString());
            sb.append(" ");
            //DaysOfWeek are not required.
            sb.append("?");
            return sb.toString();
        }
        if (isSUN()) {
            sb.append("SUN");
            moreConditions = true;
        }
        if (isMON()) {
            if (moreConditions) {
                sb.append(",");
            }
            sb.append("MON");
            moreConditions = true;
        }
        if (isTUE()) {
            if (moreConditions) {
                sb.append(",");
            }

            sb.append("TUE");
            moreConditions = true;
        }
        if (isWED()) {
            if (moreConditions) {
                sb.append(",");
            }

            sb.append("WED");
            moreConditions = true;
        }
        if (isTHU()) {
            if (moreConditions) {
                sb.append(",");
            }
            sb.append("THU");
            moreConditions = true;
        }
        if (isFRI()) {
            if (moreConditions) {
                sb.append(",");
            }
            sb.append("FRI");
            moreConditions = true;
        }
        if (isSAT()) {
            if (moreConditions) {
                sb.append(",");
            }
            sb.append("SAT");
            moreConditions = true;
        }

        if (sb.toString().equals("")) {
            sb.append("*");
            sb.append(" ");
            sb.append("*");
            sb.append(" ");
            sb.append("?");
        }
        return sb.toString();
    }

    private String buildMonthString() {
        boolean moreConditions = false;
        StringBuilder sb = new StringBuilder(800);
        if (isJAN()) {
            sb.append("JAN");
            moreConditions = true;
        }
        if (isFEB()) {
            if (moreConditions) {
                sb.append(",");
            }
            sb.append("FEB");
            moreConditions = true;
        }
        if (isMAR()) {
            if (moreConditions) {
                sb.append(",");
            }

            sb.append("MAR");
            moreConditions = true;
        }
        if (isAPR()) {
            if (moreConditions) {
                sb.append(",");
            }

            sb.append("APR");
            moreConditions = true;
        }
        if (isMAY()) {
            if (moreConditions) {
                sb.append(",");
            }
            sb.append("MAY");
            moreConditions = true;
        }
        if (isJUN()) {
            if (moreConditions) {
                sb.append(",");
            }
            sb.append("JUN");
            moreConditions = true;
        }
        if (isJUL()) {
            if (moreConditions) {
                sb.append(",");
            }
            sb.append("JUL");
            moreConditions = true;
        }
        if (isAUG()) {
            if (moreConditions) {
                sb.append(",");
            }
            sb.append("AUG");
            moreConditions = true;
        }
        if (isSEP()) {
            if (moreConditions) {
                sb.append(",");
            }
            sb.append("SEP");
            moreConditions = true;
        }
        if (isOCT()) {
            if (moreConditions) {
                sb.append(",");
            }
            sb.append("OCT");
            moreConditions = true;
        }
        if (isNOV()) {
            if (moreConditions) {
                sb.append(",");
            }
            sb.append("NOV");
            moreConditions = true;
        }
        if (isDEC()) {
            if (moreConditions) {
                sb.append(",");
            }
            sb.append("DEC");
            moreConditions = true;
        }
        if (sb.toString().equals("")) {
            sb.append("*");
        }
        return sb.toString();
    }

    private String buildYearsString() {
        int[] years = getYears();
        StringBuilder sb = new StringBuilder(800);
        if (null != years && years.length > 0) {
            for (int i = 0; i < years.length; i++) {
                sb.append(years[i]);
                if (i != years.length - 1) {
                    sb.append(",");
                }
            }
        } else {
            sb.append("");
        }
        return sb.toString();
    }

    /**
     * @return the startDate
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * The date set should be of the format (MM-DD-YYYY for example 25-04-2011)
     *
     * @param startDate the startDate to set
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    /**
     * @return the time
     */
    public String getTime() {
        return time;
    }

    /**
     * The time set should be of the format (HH:MM AM/PM for example 12:15 PM)
     *
     * @param time the time to set
     */
    public void setTime(String time) {
        this.time = time;
    }

    public boolean isRecurring() {
        return recurring;
    }

    public void setRecurring(boolean recurring) {
        this.recurring = recurring;
    }

    public boolean isSUN() {
        return SUN;
    }

    public void setSUN(boolean sUN) {
        SUN = sUN;
    }

    public boolean isMON() {
        return MON;
    }

    /**
     * @param mON the mON to set
     */
    public void setMON(boolean mON) {
        MON = mON;
    }

    public boolean isTUE() {
        return TUE;
    }

    public void setTUE(boolean tUE) {
        TUE = tUE;
    }

    public boolean isWED() {
        return WED;
    }

    public void setWED(boolean wED) {
        WED = wED;
    }

    public boolean isTHU() {
        return THU;
    }

    public void setTHU(boolean tHU) {
        THU = tHU;
    }

    public boolean isFRI() {
        return FRI;
    }

    public void setFRI(boolean fRI) {
        FRI = fRI;
    }

    public boolean isSAT() {
        return SAT;
    }

    public void setSAT(boolean sAT) {
        SAT = sAT;
    }

    public boolean isJAN() {
        return JAN;
    }

    public void setJAN(boolean JAN) {
        this.JAN = JAN;
    }

    public boolean isFEB() {
        return FEB;
    }

    public void setFEB(boolean FEB) {
        this.FEB = FEB;
    }

    public boolean isMAR() {
        return MAR;
    }

    public void setMAR(boolean MAR) {
        this.MAR = MAR;
    }

    public boolean isAPR() {
        return APR;
    }

    public void setAPR(boolean APR) {
        this.APR = APR;
    }

    public boolean isMAY() {
        return MAY;
    }

    public void setMAY(boolean MAY) {
        this.MAY = MAY;
    }

    public boolean isJUN() {
        return JUN;
    }

    public void setJUN(boolean JUN) {
        this.JUN = JUN;
    }

    public boolean isJUL() {
        return JUL;
    }

    public void setJUL(boolean JUL) {
        this.JUL = JUL;
    }

    public boolean isAUG() {
        return AUG;
    }

    public void setAUG(boolean AUG) {
        this.AUG = AUG;
    }

    public boolean isSEP() {
        return SEP;
    }

    public void setSEP(boolean SEP) {
        this.SEP = SEP;
    }

    public boolean isOCT() {
        return OCT;
    }

    public void setOCT(boolean OCT) {
        this.OCT = OCT;
    }

    public boolean isNOV() {
        return NOV;
    }

    public void setNOV(boolean NOV) {
        this.NOV = NOV;
    }

    public boolean isDEC() {
        return DEC;
    }

    public void setDEC(boolean DEC) {
        this.DEC = DEC;
    }

    public int[] getDaysOfMonth() {
        return daysOfMonth;
    }

    public void setDaysOfMonth(int[] daysOfMonth) {
        this.daysOfMonth = daysOfMonth;
    }

    public void setDayOfMonth(int[] daysOfMonth) {
        this.daysOfMonth = daysOfMonth;
    }

    public int hashCode() {
        return this.buildCronExpression().hashCode();
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof CronExpressionBuilder) {
            if (((CronExpressionBuilder) obj).buildCronExpression()
                    .equalsIgnoreCase(this.buildCronExpression())) {
                return true;
            }
        } else {
            return false;
        }
        return false;

    }

}
