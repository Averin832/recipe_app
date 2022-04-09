package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
    @NamedQueries({
        @NamedQuery(
                name = "getAllUsers",
                query = "select u from User as u order by u.id DESC"
                ),
        @NamedQuery(
                name = "getUsersCount",
                query = "select count(u) from User as u"
                ),
        @NamedQuery(
                name = "countRegisteredByMail",
                query = "select count(u) from User as u where u.mail = :mail"
                ),
        @NamedQuery(
                name = "getByMailAndPass",
                query = "select u from User as u where u.mail = :mail and u.password = :password"
                )

    })

    @Table(name = "users")

    public class User {

        @Id
        @Column(name = "id")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;


        @Column(name = "name", length = 12, nullable = false)
        private String name;

        @Column(name = "mail", nullable = false, unique = true)
        private String mail;

        @Column(name = "password", length = 64, nullable = false)
        private String password;

        //@Column(name = "admin_flag", nullable = false)
        //private Integer adminFlag;


        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getMail() {
            return mail;
        }

        public void setMail(String mail) {
            this.mail = mail;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
/*
        public Integer getAdminFlag() {
            return adminFlag;
        }

        public void setAdminFlag(Integer adminFlag) {
            this.adminFlag = adminFlag;
        }
*/
}
