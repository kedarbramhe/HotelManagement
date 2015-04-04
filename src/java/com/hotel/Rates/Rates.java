package com.vedisoft.Rates;
/**
 *
 * @author vedisoft
 */
public class Rates {

    int rateId;
    int rcategoryId;
    String fromDate;
    String toDate;
    float rate;
    float tax;

    public Rates() {
    }

    public Rates(int rateId, int rcategoryId, String fromDate, String toDate, float rate, float tax) {
        this.rateId = rateId;
        this.rcategoryId = rcategoryId;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.rate = rate;
        this.tax = tax;
    }

    public int getRateId() {
        return rateId;
    }

    public void setRateId(int rateId) {
        this.rateId = rateId;
    }

    public int getRcategoryId() {
        return rcategoryId;
    }

    public void setRcategoryId(int rcategoryId) {
        this.rcategoryId = rcategoryId;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public float getTax() {
        return tax;
    }

    public void setTax(float tax) {
        this.tax = tax;
    }
}
/*
Vedisoft Software and Education Services Pvt. Ltd.<br/>
275, Zone II, M.P.Nagar,
Bhopal.
Ph: 0755-4076207,208<br/>
Email: contact@vedisoft.com<br/>
Web: www.vedisoft.com<br/>
Courses : Java, .NET, PHP, C/C++, Web Designing
Certifications : OCJP, OCP, CCNA
Major and Minor Training and Projects
 */
