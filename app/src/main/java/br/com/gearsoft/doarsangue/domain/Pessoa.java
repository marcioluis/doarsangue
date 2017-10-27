package br.com.gearsoft.doarsangue.domain;

import com.google.firebase.firestore.Exclude;

import java.util.Date;
import java.util.List;

import br.com.gearsoft.doarsangue.domain.enums.FatorRH;
import br.com.gearsoft.doarsangue.domain.enums.TipoSanguineo;

/**
 * Created by marcio.arrosi on 17/07/2017.
 */

public class Pessoa {

    private FatorRH fatorRH;
    private TipoSanguineo tipoSanguineo;
    private String primeiroNome;
    private String segundoNome;
    private String ultimoNome;
    private Date ultimaDoacao;
    @Exclude
    private List<Doacao> historicoDoacoes;

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

    public String getPrimeiroNome() {
        return primeiroNome;
    }

    public void setPrimeiroNome(String primeiroNome) {
        this.primeiroNome = primeiroNome;
    }

    public String getSegundoNome() {
        return segundoNome;
    }

    public void setSegundoNome(String segundoNome) {
        this.segundoNome = segundoNome;
    }

    public String getUltimoNome() {
        return ultimoNome;
    }

    public void setUltimoNome(String ultimoNome) {
        this.ultimoNome = ultimoNome;
    }

    public Date getUltimaDoacao() {
        return ultimaDoacao;
    }

    public void setUltimaDoacao(Date ultimaDoacao) {
        this.ultimaDoacao = ultimaDoacao;
    }

    public List<Doacao> getHistoricoDoacoes() {
        return historicoDoacoes;
    }

    public void setHistoricoDoacoes(List<Doacao> historicoDoacoes) {
        this.historicoDoacoes = historicoDoacoes;
    }
}
