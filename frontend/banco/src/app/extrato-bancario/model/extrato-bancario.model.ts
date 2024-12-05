import { Transacao } from "src/app/transacoes/model/transacao.model";

export interface ExtratoBancario{
  dataInicial: Date;
  dataFinal: Date;
  saldoDoPeriodo: number;
  saldoAtual: number;
  transacoes: Transacao[];
}
