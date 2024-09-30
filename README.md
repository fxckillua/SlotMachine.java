### README - Projeto Máquina de Caça-Níqueis com RMI e SSL/TLS

# Máquina de Caça-Níqueis com RMI e Criptografia SSL/TLS 🎰🔒

Este é um projeto de uma aplicação distribuída que simula uma Máquina de Caça-Níqueis. A comunicação entre o servidor e os clientes é feita através de Java RMI (Remote Method Invocation) e é protegida utilizando SSL/TLS para garantir a segurança das transações. Cada cliente conecta-se ao servidor central, realiza apostas e recebe os resultados de forma segura e independente.

## Funcionalidades 📋

- **Conexão segura**: O projeto utiliza criptografia SSL/TLS para garantir a segurança das comunicações entre clientes e servidor.
- **Registro de clientes**: Cada cliente recebe um identificador único (UUID) e é registrado no servidor.
- **Apostas**: Os clientes podem realizar apostas, e o resultado é processado pelo servidor.
- **Saldo do cliente**: O servidor mantém o saldo de cada cliente e atualiza de acordo com as apostas realizadas.
- **Histórico de jogadas**: O servidor armazena um histórico das últimas 10 jogadas de cada cliente.
- **Interface gráfica**: Interface gráfica (GUI) implementada com Java Swing para facilitar a interação do usuário.
- **Comunicação com o servidor**: Utiliza Java RMI para a comunicação remota entre a aplicação cliente e o servidor.

## Requisitos do Sistema 🛠️

- **Java Development Kit (JDK) 8** ou superior.
- **NetBeans IDE** ou outra IDE de sua preferência para desenvolvimento Java.
- **Certificado SSL/TLS**: Para a comunicação segura, é necessário gerar certificados e armazená-los em um keystore.

## Estrutura do Projeto 📂

O projeto está dividido em três pacotes principais:

1. **SlotMachineServer**: Contém a implementação do servidor, que gerencia as conexões dos clientes, processa as apostas e mantém o saldo e histórico das jogadas.
2. **SlotMachineClient**: Contém a implementação do cliente, com uma interface gráfica para o usuário interagir com a Máquina de Caça-Níqueis.
3. **SlotMachineService**: Contém a interface remota que define os métodos que o cliente pode invocar no servidor via RMI.

## Configuração e Execução 🚀

### Passos para configurar e executar o projeto:

1. **Gerar o Keystore**: Gere um keystore SSL para o servidor usando o comando abaixo no terminal:

    ```
    keytool -genkey -alias servidor -keyalg RSA -keystore servidor.keystore -storepass senha123
    ```

2. **Exportar o Certificado**: Exporte o certificado do servidor para permitir que os clientes confiem na comunicação.

    ```
    keytool -export -alias servidor -keystore servidor.keystore -file servidor.cer -storepass senha123
    ```

3. **Importar o Certificado no Cliente**: Importe o certificado para o truststore do cliente:

    ```
    keytool -import -alias servidor -file servidor.cer -keystore truststore.jks -storepass senha123
    ```

4. **Configurar os arquivos do keystore**: Copie os arquivos `servidor.keystore` e `truststore.jks` para a pasta raiz do projeto.

5. **Executar o Servidor**: Execute a classe `SlotMachineServer.java`. O servidor estará aguardando conexões de clientes de forma segura.

6. **Executar o Cliente**: Execute a classe `SlotMachineClient.java` para iniciar a interface da Máquina de Caça-Níqueis e conectar-se ao servidor.

## Tecnologias Utilizadas 🛠️

- **Java RMI**: Para comunicação remota entre servidor e clientes.
- **Java Swing**: Para a construção da interface gráfica do cliente.
- **SSL/TLS**: Para criptografia da comunicação usando keystore e truststore.

## Contribuição 🤝

1. Faça um fork do repositório.
2. Crie uma nova branch: `git checkout -b minha-nova-feature`.
3. Faça as alterações desejadas e realize commit: `git commit -m 'Minha nova feature'`.
4. Faça push para a branch: `git push origin minha-nova-feature`.
5. Envie um Pull Request para análise.

## Licença 📄

Este projeto é distribuído sob a licença MIT. Consulte o arquivo `LICENSE` para obter mais informações.

## Contato 📧

Em caso de dúvidas ou sugestões, entre em contato:

- **Nome:** [Seu Nome]
- **E-mail:** [Seu E-mail]

Sinta-se à vontade para contribuir e melhorar este projeto!

---

### Divirta-se e boa sorte! 🎰🍀
