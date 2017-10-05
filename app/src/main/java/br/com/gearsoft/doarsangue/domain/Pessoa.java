package br.com.gearsoft.doarsangue.domain;

import java.sql.Date;
import java.util.List;

import br.com.gearsoft.doarsangue.domain.enums.FatorRH;
import br.com.gearsoft.doarsangue.domain.enums.TipoSanguineo;

/**
 * Created by marcio.arrosi on 17/07/2017.
 */

public class Pessoa {

    private FatorRH fatorRH;
    private TipoSanguineo tipoSanguineo;
    private String nome;
    private String primeiroNome;
    private String ultimoNome;
    private Date ultimaDoacao;
    private List<Doacao> historicoDoacoes;



}
