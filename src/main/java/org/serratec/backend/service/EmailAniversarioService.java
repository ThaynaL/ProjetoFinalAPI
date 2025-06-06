package org.serratec.backend.service;
import org.serratec.backend.config.MailConfig;
import org.serratec.backend.entity.Cliente;
import org.serratec.backend.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;


@Service
public class EmailAniversarioService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EmailService emailService;

    @Scheduled(cron = "0 0 12 1 * *")
    public void enviarEmailAniversario() {
        int mesAtual = LocalDate.now().getMonthValue();
        List<Cliente> clientesAniversariantes = clienteRepository.findByMesAniversario(mesAtual);

        for (Cliente cliente : clientesAniversariantes) {
            String assuntoEmail = "Parabéns! do(a) " + cliente.getNome();
            String corpoEmail = String.format("Olá " + cliente.getNome() + ", hoje é seu aniversário e quem ganha o presente somos nós," +
                    "ganhe até 30% de desconto em produtos selecionados, USANDO o CUPOM: FELIZANIVERSARIO !");
            emailService.sendEmail(cliente.getEmail(), assuntoEmail, corpoEmail);
        }
    }

}
