
export interface Cliente{
  clienteId: string;
  nome: string;
  idade: number;
  email: string;
  contaRegistroDto: {
    agencia: string
  };
}
