
export interface Cliente{
  clienteId: string;
  nome: string;
  idade: number;
  email: string;
  password: string;
  contaRegistroDto: {
    agencia: string
  };
}
