import { Transacao } from "src/app/transacoes/model/transacao.model";

export interface ExtratoBancario{
  dataInicial: Date;
  dataFinal: Date;
  saldo: number;
  transacoes: Transacao[];
}
