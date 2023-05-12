/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package wpsSocietyBank.Agent;

import java.io.Serializable;

/**
 * Simple Loan Table without extras
 */
public class LoanTable implements Serializable {
    
    private String peasantFamily;
    private Integer loanGranted;
    private Integer maxTerm;
    private Integer paidTerm;
    private BankMessageType loanType;
    
    LoanTable(String peasantFamily, Integer loanGranted, Integer maxTerm, Integer paidTerm, BankMessageType loanType){
        this.peasantFamily = peasantFamily;
        this.loanGranted = loanGranted;
        this.maxTerm = maxTerm;
        this.paidTerm = paidTerm;
        this.loanType = loanType;
    }

    public BankMessageType getLoanType() {
        return loanType;
    }

    public void setLoanType(BankMessageType loanType) {
        this.loanType = loanType;
    }
    
    public int MoneyToPay(){
        if (maxTerm > 0){
            return loanGranted/maxTerm;
        }
        return 0;
    }

    public String getPeasantFamilyAlias() {
        return peasantFamily;
    }

    public void setPeasantFamilyAlias(String peasantFamilyAlias) {
        this.peasantFamily = peasantFamilyAlias;
    }

    public Integer getLoanGranted() {
        return loanGranted;
    }

    public void setLoanGranted(Integer loanGranted) {
        this.loanGranted = loanGranted;
    }

    public Integer getMaxTerm() {
        return maxTerm;
    }

    public void setMaxTerm(Integer maxTerm) {
        this.maxTerm = maxTerm;
    }

    public Integer getPaidTerm() {
        return paidTerm;
    }

    public void setPaidTerm(Integer paidTerm) {
        this.paidTerm = paidTerm;
    }
    public void increasePaidTerm() {
        this.paidTerm++;
    }
    
}
