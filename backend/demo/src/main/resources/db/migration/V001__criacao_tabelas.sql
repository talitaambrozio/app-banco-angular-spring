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
                           "email" varchar(100) NOT NULL UNIQUE,
                           "conta_id" UUID NOT NULL UNIQUE,
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