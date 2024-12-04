import { Conta } from "src/app/cadastro/model/conta.model";

export interface Transacao{
  transacaoId: string;
  dataTransacao: Date;
  tipoTransacao: string;
  valor: number;
  descricao: string;
  conta: Conta;
}
