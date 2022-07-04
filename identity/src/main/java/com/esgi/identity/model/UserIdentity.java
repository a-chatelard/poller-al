package com.esgi.identity.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author mplayout
 */
@Entity
public class UserIdentity {
    /**
     * The user id.
     */
    @GeneratedValue(generator = "seq_gen_user")
    @GenericGenerator(
            name = "seq_gen_user",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "seq_user"),
                    @Parameter(name = "initial_value", value = "0"), @Parameter(name = "increment_size", value = "1")
            })
    @Id
    private long id;

    /**
     * The user name.
     */

    private String login;

    /**
     * The user last name.
     */
    private String lastName;

    public UserIdentity(String login, String lastName) {
        this.login = login;
        this.lastName = lastName;
    }

    public UserIdentity() {

    }

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param newId the id to set
     */
    public void setId(final long newId) {
        this.id = newId;
    }

    /**
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * @param newLogin the login to set
     */
    public void setLogin(final String newLogin) {
        this.login = newLogin;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param newLastName the lastName to set
     */
    public void setLastName(final String newLastName) {
        this.lastName = newLastName;
    }

}
