package br.com.projetoecommerce.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.projetoecommerce.enuns.StatusEmail;

@Entity
@Table(name = "tb_email")
public class EmailModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String owner_Ref;
    private String email_From;
    private String email_To;
    private String subject;
    @Column(columnDefinition = "TEXT")
    private String text;

    private LocalDateTime send_Data_Email;
    private StatusEmail status_Email;
 
    public EmailModel() {
    	
    }

	public Long getid() {
		return id;
	}

	public void setid(Long id) {
		this.id = id;
	}

	public String getOwner_Ref() {
		return owner_Ref;
	}

	public void setOwner_Ref(String owner_Ref) {
		this.owner_Ref = owner_Ref;
	}

	public String getEmail_From() {
		return email_From;
	}

	public void setEmail_From(String email_From) {
		this.email_From = email_From;
	}

	public String getEmail_To() {
		return email_To;
	}

	public void setEmail_To(String email_To) {
		this.email_To = email_To;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public LocalDateTime getSend_Data_Email() {
		return send_Data_Email;
	}

	public void setSend_Data_Email(LocalDateTime send_Data_Email) {
		this.send_Data_Email = send_Data_Email;
	}

	public StatusEmail getStatus_Email() {
		return status_Email;
	}

	public void setStatus_Email(StatusEmail status_Email) {
		this.status_Email = status_Email;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmailModel other = (EmailModel) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "EmailModel [id=" + id + ", owner_Ref=" + owner_Ref + ", email_From=" + email_From
				+ ", email_To=" + email_To + ", subject=" + subject + ", text=" + text + ", send_Data_Email="
				+ send_Data_Email + ", status_Email=" + status_Email + "]";
	}

    
    
    
}
