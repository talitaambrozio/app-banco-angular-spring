CREATE TABLE "endereco" (
                           "endereco_id" UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                           "logradouro" varchar(80) NOT NULL,
                           "bairro" varchar(80) NOT NULL,
                           "numero" integer NOT NULL,
                           "cidade" varchar(60) NOT NULL,
                           "cep" varchar(15) NOT NULL,
                           "estado" varchar(2) NOT NULL,
                           "pais" varchar(60) NOT NULL
);

CREATE TABLE "conta" (
                           "conta_id" UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                           "numero_conta" varchar(10) NOT NULL UNIQUE,
                           "digito_conta" varchar(2) NOT NULL,
                           "agencia" varchar(40) NOT NULL,
                           "data_abertura" timestamptz NOT NULL DEFAULT (now())
);

CREATE TABLE "cliente" (
                           "cliente_id" UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                           "nome" varchar(80) UNIQUE NOT NULL,
                           "idade" int NOT NULL,
                           "endereco_id" UUID NOT NULL,
                           "email" varchar(100) NOT NULL UNIQUE,
                           "conta_id" UUID NOT NULL UNIQUE,
                           CONSTRAINT fk_endereco FOREIGN KEY ("endereco_id") REFERENCES "endereco" ("endereco_id") ON DELETE CASCADE,
                           CONSTRAINT fk_conta FOREIGN KEY ("conta_id") REFERENCES "conta" ("conta_id") ON DELETE CASCADE
);



CREATE TABLE "transacao" (
                           "transacao_id" UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                           "data_transacao" timestamptz NOT NULL DEFAULT now(),
                           "tipo_transacao" char(1) NOT NULL,
                           "valor" decimal(10,2) NOT NULL,
                           "descricao" VARCHAR(255) NOT NULL,
                           "conta_id" UUID NOT NULL,
                           CONSTRAINT fk_conta FOREIGN KEY ("conta_id") REFERENCES "conta" ("conta_id")
);