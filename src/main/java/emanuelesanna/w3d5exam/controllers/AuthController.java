package emanuelesanna.w3d5exam.controllers;

import emanuelesanna.w3d5exam.entities.Utente;
import emanuelesanna.w3d5exam.exceptions.ValidationException;
import emanuelesanna.w3d5exam.payload.LoginDTO;
import emanuelesanna.w3d5exam.payload.LoginResponseDTO;
import emanuelesanna.w3d5exam.payload.NewUtenteDTO;
import emanuelesanna.w3d5exam.services.AuthService;
import emanuelesanna.w3d5exam.services.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @Autowired
    private UtenteService utenteService;

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody LoginDTO body) {
        return new LoginResponseDTO(authService.checkCredentialsAndGenerateToken(body));
    }

    // 2 POST http://localhost:3001/ (+ payload) 201 CREATED
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public Utente createUtente(@RequestBody @Validated NewUtenteDTO payload, BindingResult validationResult) {
        if (validationResult.hasErrors()) {

            throw new ValidationException(validationResult.getFieldErrors()
                    .stream().map(fieldError -> fieldError.getDefaultMessage()).toList());
        }
        return this.utenteService.saveUtente(payload);
    }
}
