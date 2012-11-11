package beans;
/**
 * InvoiceInfo class
 * 
 * @author Team 7
 */
public class InvoiceInfo {
    private int invoiceId;
    private String invoiceDate;
    private float invoiceAmount;
    
    public InvoiceInfo() {
        // DO NOTHING
    }
    /**
     * @return the invoiceId
     */
    public final int getInvoiceId() {
        return invoiceId;
    }
    /**
     * @param invoiceId the invoiceId to set
     */
    public final void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }
    /**
     * @return the invoiceDate
     */
    public final String getInvoiceDate() {
        return invoiceDate;
    }
    /**
     * @param invoiceDate the invoiceDate to set
     */
    public final void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }
    /**
     * @return the invoiceAmount
     */
    public final float getInvoiceAmount() {
        return invoiceAmount;
    }
    /**
     * @param invoiceAmount the invoiceAmount to set
     */
    public final void setInvoiceAmount(float invoiceAmount) {
        this.invoiceAmount = invoiceAmount;
    }
}
