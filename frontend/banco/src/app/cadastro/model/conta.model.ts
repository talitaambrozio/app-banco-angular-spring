import { Transacao } from "src/app/transacoes/model/transacao.model";

export interface Conta{
  contaId: string;
  numeroConta: string;
  digitoConta: string;
  agencia: string;
  dataAbertura: Date;
  transacoes: Transacao[];
}
