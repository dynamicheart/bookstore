package com.dynamicheart.bookstore.core.model.customer;

import com.dynamicheart.bookstore.core.constants.SchemaConstant;
import com.dynamicheart.bookstore.core.model.generic.BookstoreEntity;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by dynamicheart on 5/3/2017.
 */

@Entity
@Table(name = "CUSTOMER", schema = SchemaConstant.BOOKSTORE_SHECMA)
public class Customer extends BookstoreEntity<Long, Customer>{

    private static final long serialVersionUID = 4887453298941565028L;

    @Id
    @Column(name = "CUSTOMER_ID", unique = true, nullable = false)
    @TableGenerator(name = "TABLE_GEN", table = "BS_SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT",
            pkColumnValue = "CUSTOMER_SEQ_NEXT_VAL")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GEN")
    private Long id;

    @Column(name="CUSTOMER_GENDER", length=1, nullable=true)
    @Enumerated(value = EnumType.STRING)
    private CustomerGender gender;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="CUSTOMER_DOB")
    private Date dateOfBirth;

    @Email
    @NotEmpty
    @Column(name="CUSTOMER_EMAIL_ADDRESS", length=96, nullable=false)
    private String emailAddress;

    @Column(name="CUSTOMER_NICK", length=96)
    private String nick;

    @Column(name="CUSTOMER_PASSWORD", length=60)
    private String password;

    public Customer() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CustomerGender getGender() {
        return gender;
    }

    public void setGender(CustomerGender gender) {
        this.gender = gender;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
