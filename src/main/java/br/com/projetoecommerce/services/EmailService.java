package br.com.projetoecommerce.services;

import java.time.LocalDateTime;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import br.com.projetoecommerce.entities.EmailModel;
import br.com.projetoecommerce.entities.User;
import br.com.projetoecommerce.enuns.StatusEmail;
import br.com.projetoecommerce.repositories.EmailRepository;

@Service
public class EmailService {
	@Autowired
	EmailRepository repository;

	@Autowired
	private JavaMailSender emailSender;

	@SuppressWarnings("finally")
	public EmailModel sendEmail(EmailModel emailModel) {
		emailModel.setSend_Data_Email(LocalDateTime.now());
		try {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setFrom(emailModel.getEmail_From());
			message.setTo(emailModel.getEmail_To());
			message.setSubject(emailModel.getSubject());
			message.setText(emailModel.getText());

			emailSender.send(message);

			emailModel.setStatus_Email(StatusEmail.SENT);

		} catch (MailException e) {
			emailModel.setStatus_Email(StatusEmail.ERROR);

		} finally {
			return repository.save(emailModel);
		}
	}

	public Page<EmailModel> findAll(Pageable pageable) {
		return repository.findAll(pageable);
	}

	public void sendMailAuto(User obj) {

		EmailModel emailModel = new EmailModel();
		emailModel.setSend_Data_Email(LocalDateTime.now());

		emailModel.setOwner_Ref("WCecommerce");
		emailModel.setEmail_From("project64.alunos@gmail.com");
		emailModel.setEmail_To(obj.getEmail());
		emailModel.setSubject("Wcecommerce Cadastro Online");
		emailModel.setText(
				"Olá " + obj.getName() + "! \n\n" + "Seu email foi cadastrado com sucesso em nossa plataforma\n\n"
						+ "Fique atento(a) as novas ofertas sobre nossos produtos\n\n"
						+ "A Wcecommerce agradece pela sua preferencia!\n");

		try {
			MimeMessage mail = emailSender.createMimeMessage();

			MimeMessageHelper helper = new MimeMessageHelper(mail);
			helper.setTo(obj.getEmail());
			helper.setSubject("Wcecommerce Cadastro Online");
			helper.setText("<h3>Olá " + obj.getName() + "!<h3> \n\n"
					+ "<p>Seu email foi cadastrado com sucesso em nossa plataforma</p>"
					+ "<p>Fique atento(a) as novas ofertas sobre nossos produtos</p>"
					+ "<p>A Wcecommerce agradece pela sua preferencia!</p>", true);

			emailSender.send(mail);

			emailModel.setStatus_Email(StatusEmail.SENT);

		} catch (Exception e) {
			e.printStackTrace();
			emailModel.setStatus_Email(StatusEmail.ERROR);
		} finally {
			repository.save(emailModel);
		}
	}
}
