package com.example.bluechip.models;

public class debtor_ledger_model {
    String debtorName;
    int debtor_id;

    public debtor_ledger_model(String debtorName) {
        this.debtorName = debtorName;
    }

    public String getDebtorName() {
        return debtorName;
    }

    public void setDebtorName(String debtorName) {
        this.debtorName = debtorName;
    }

    public int getDebtor_id() {
        return debtor_id;
    }

    public void setDebtor_id(int debtor_id) {
        this.debtor_id = debtor_id;
    }
}
