<<<<<<< HEAD:src/main/java/com/fag/lucasmartins/arquitetura_software/infrastructure/adapters/in/messaging/services/PessoaService.java
package com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.in.messaging.services;

import org.springframework.stereotype.Service;
=======
package com.fag.lucasmartins.arquitetura_software.application.services;
>>>>>>> 0ca4be6e81a4a218adffd67f0b9c1d6bca84636e:src/main/java/com/fag/lucasmartins/arquitetura_software/application/services/PessoaService.java

import com.fag.lucasmartins.arquitetura_software.application.ports.in.service.PessoaServicePort;
import com.fag.lucasmartins.arquitetura_software.application.ports.out.persistence.PessoaRepositoryPort;
import com.fag.lucasmartins.arquitetura_software.core.domain.bo.PessoaBO;
<<<<<<< HEAD:src/main/java/com/fag/lucasmartins/arquitetura_software/infrastructure/adapters/in/messaging/services/PessoaService.java
=======
import org.springframework.stereotype.Service;
>>>>>>> 0ca4be6e81a4a218adffd67f0b9c1d6bca84636e:src/main/java/com/fag/lucasmartins/arquitetura_software/application/services/PessoaService.java

@Service
public class PessoaService implements PessoaServicePort {

    private final PessoaRepositoryPort pessoaRepositoryPort;

    public PessoaService(PessoaRepositoryPort pessoaRepositoryPort) {
        this.pessoaRepositoryPort = pessoaRepositoryPort;
    }

    @Override
    public PessoaBO salvar(PessoaBO pessoaBO) {
        pessoaBO.validarCamposObrigatorios();
        pessoaBO.validarMaioridade();
        pessoaBO.validarEmail();
        pessoaBO.validarTelefone();
        pessoaBO.validarCpf();
        return pessoaRepositoryPort.salvar(pessoaBO);
    }
}
