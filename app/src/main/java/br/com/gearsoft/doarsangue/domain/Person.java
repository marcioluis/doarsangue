package br.com.gearsoft.doarsangue.domain;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.net.Uri;

import com.google.firebase.firestore.Exclude;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.gearsoft.doarsangue.BR;
import br.com.gearsoft.doarsangue.domain.enums.FatorRH;
import br.com.gearsoft.doarsangue.domain.enums.TipoSanguineo;

/**
 * Created by marcio.arrosi on 17/07/2017.
 */

public class Person extends BaseObservable {

    private FatorRH fatorRH;
    private TipoSanguineo tipoSanguineo;
    private Date ultimaDoacao;
    private String email;
    private String nome;
    private String foto;
    private String telefone;
    @Exclude
    private List<Doacao> historicoDoacoes = new ArrayList<>();

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

    @Bindable
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        notifyPropertyChanged(BR.email);
    }

    @Bindable
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
        notifyPropertyChanged(BR.nome);
    }

    @Bindable
    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
        notifyPropertyChanged(BR.foto);
    }

    @Bindable
    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
        notifyPropertyChanged(BR.telefone);
    }

}
