export interface Cliente {
  id?: number;
  nome: string;
  rg: string;
  cpf: string;
  endereco: string;
  profissao: string;
  login: string;
  senha: string;
  entidadeEmpregadora?: string; // Campo opcional
  rendimento?: number; // Campo opcional
}
