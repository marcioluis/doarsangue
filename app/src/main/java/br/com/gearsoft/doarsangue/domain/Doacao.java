package br.com.gearsoft.doarsangue.domain;


import java.util.Date;

import br.com.gearsoft.doarsangue.domain.enums.FatorRH;
import br.com.gearsoft.doarsangue.domain.enums.TipoSanguineo;

/**
 * Created by marcio.arrosi on 17/07/2017.
 */

public final class Doacao {

    private String id;
    private String localDoacao;
    private String cidadeDoacao;
    private Date dataDoacao;
    private FatorRH fatorRH;
    private TipoSanguineo tipoSanguineo;
    private Pessoa doador;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLocalDoacao() {
        return localDoacao;
    }

    public void setLocalDoacao(String localDoacao) {
        this.localDoacao = localDoacao;
    }

    public String getCidadeDoacao() {
        return cidadeDoacao;
    }

    public void setCidadeDoacao(String cidadeDoacao) {
        this.cidadeDoacao = cidadeDoacao;
    }

    public Date getDataDoacao() {
        return dataDoacao;
    }

    public void setDataDoacao(Date dataDoacao) {
        this.dataDoacao = dataDoacao;
    }

    public FatorRH getFatorRH() {
        return fatorRH;
    }

    public void setFatorRH(FatorRH fatorRH) {
        this.fatorRH = fatorRH;
    }

    public TipoSanguineo getTipoSanguineo() {
        return tipoSanguineo;
    }

    public void setTipoSanguineo(TipoSanguineo tipoSanguineo) {
        this.tipoSanguineo = tipoSanguineo;
    }

    public Pessoa getDoador() {
        return doador;
    }

    public void setDoador(Pessoa doador) {
        this.doador = doador;
    }
}
