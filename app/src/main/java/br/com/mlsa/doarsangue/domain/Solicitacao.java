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
/**
 O presidente da Companhia, xxxxxxxxxxx, solicita doadores de sangue, de qualquer tipo para sua neta recém-nascida e que está internada na UTI Neonatal do Hospital Moinhos de Vento, xxxxxxxxx.
 Onde doar? Serviço de Hemoterapia do Hospital Moinhos de Vento de Porto Alegre (rua Ramiro Barcelos, 910 - térreo).
 Horário para doação: De segunda a sexta-feira, das 8h às 18h30.
 Informações pelos telefones: 33146960- 33146928 - 33143072
 */
