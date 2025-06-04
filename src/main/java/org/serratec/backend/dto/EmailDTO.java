package org.serratec.backend.dto;

public class EmailDTO {
    private String para;
    private String assunto;
    private String corpo;

    public EmailDTO(){

    }

    public EmailDTO(String para, String assunto, String corpo) {
        this.para = para;
        this.assunto = assunto;
        this.corpo = corpo;
    }

    public String getPara() {
        return para;
    }

    public void setPara(String para) {
        this.para = para;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public String getCorpo() {
        return corpo;
    }

    public void setCorpo(String corpo) {
        this.corpo = corpo;
    }
}
