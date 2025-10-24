package emanuelesanna.w3d5exam.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import emanuelesanna.w3d5exam.enums.Role;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@JsonIgnoreProperties({"password", "authorities", "enabled", "accountNonLocked", "accountNonExpired", "credentialsNonExpired", "eventi", "prenotazioni"})
public class Utente implements UserDetails {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID utenteId;
    private String username;
    private String nome;
    private String cognome;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    @OneToMany(mappedBy = "utente")
    private List<Evento> eventi = new ArrayList<>();
    @OneToMany(mappedBy = "utente")
    private List<Prenotazione> prenotazioni = new ArrayList<>();

    public Utente(String username, String cognome, String nome, String email, String password) {
        this.username = username;
        this.cognome = cognome;
        this.nome = nome;
        this.email = email;
        this.password = password;
        this.role = Role.UTENTE_NORMALE;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.role.name()));
    }

    @Override
    public String getUsername() {
        return this.username;
    }
}
