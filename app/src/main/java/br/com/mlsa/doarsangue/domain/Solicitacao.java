package br.com.mlsa.doarsangue.domain;

import java.util.Date;
import java.util.List;

import br.com.mlsa.doarsangue.domain.enums.FatorRH;
import br.com.mlsa.doarsangue.domain.enums.TipoSanguineo;

/**
 * Created by marcio.arrosi on 17/07/2017.
 */

public final class Solicitacao {

    private boolean urgente;
    private String nomeRecebedor;
    private String nomeLocal;
    private String cidadeDoacao;
    private FatorRH fatorRH;
    private TipoSanguineo tipoSanguineo;
    private List<Pessoa> doadores;
    private Pessoa solicitante;
    private Date dataSolicitacao;


}
