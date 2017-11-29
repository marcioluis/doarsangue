package br.com.gearsoft.doarsangue.domain;

import android.support.annotation.NonNull;

import java.util.Date;

import br.com.gearsoft.doarsangue.domain.enums.FatorRH;
import br.com.gearsoft.doarsangue.domain.enums.TipoSanguineo;

/**
 * Created by marcio.arrosi on 17/07/2017.
 */
public final class Solicitacao implements Comparable<Solicitacao>{

    public static final String field_data_expiracao = "dataExpiracao";
    public static final String field_urgente = "urgente";
    public static final String field_data_solicitacao = "dataSolicitacao";

    private String id;
    private boolean urgente;
    private String localDoacao;
    private String cidadeDoacao;
    private String nomeRecebedor;
    private FatorRH fatorRH;
    private TipoSanguineo tipoSanguineo;
    private String observacao;
    private Person solicitante;
    private Date dataSolicitacao;
    private Date dataExpiracao;
    private int qtdDoadores;
    private int qtdVisualizacoes;

    public Solicitacao() {
    }

    public int getQtdDoadores() {
        return qtdDoadores;
    }

    public void setQtdDoadores(int qtdDoadores) {
        this.qtdDoadores = qtdDoadores;
    }

    public int getQtdVisualizacoes() {
        return qtdVisualizacoes;
    }

    public void setQtdVisualizacoes(int qtdVisualizacoes) {
        this.qtdVisualizacoes = qtdVisualizacoes;
    }

    public Date getDataExpiracao() {
        return dataExpiracao;
    }

    public void setDataExpiracao(Date dataExpiracao) {
        this.dataExpiracao = dataExpiracao;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isUrgente() {
        return urgente;
    }

    public void setUrgente(boolean urgente) {
        this.urgente = urgente;
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

    public String getNomeRecebedor() {
        return nomeRecebedor;
    }

    public void setNomeRecebedor(String nomeRecebedor) {
        this.nomeRecebedor = nomeRecebedor;
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

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Person getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(Person solicitante) {
        this.solicitante = solicitante;
    }

    public Date getDataSolicitacao() {
        return dataSolicitacao;
    }

    public void setDataSolicitacao(Date dataSolicitacao) {
        this.dataSolicitacao = dataSolicitacao;
    }

    @Override
    public int compareTo(@NonNull Solicitacao o) {
        int result = Boolean.compare(urgente, o.urgente) *-1;// inverte a ordem
        if (result != 0) {
            return result;
        }
        result = dataSolicitacao.compareTo(o.dataSolicitacao);
        if (result != 0) {
            return result;
        }
        return 0;
    }
}
/**
 * O presidente da Companhia, xxxxxxxxxxx, solicita doadores de sangue, de qualquer tipo para sua neta recém-nascida e que está internada na UTI Neonatal do Hospital Moinhos de Vento, xxxxxxxxx.
 * Onde doar? Serviço de Hemoterapia do Hospital Moinhos de Vento de Porto Alegre (rua Ramiro Barcelos, 910 - térreo).
 * Horário para doação: De segunda a sexta-feira, das 8h às 18h30.
 * Informações pelos telefones: 33146960- 33146928 - 33143072
 */
