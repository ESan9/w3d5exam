package emanuelesanna.w3d5exam.payload;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record NewEventoDTO(
        @NotBlank(message = "Il titolo è obbligatorio.")
        @Size(min = 2, max = 50, message = "Il titolo deve avere tra 2 e 50 caratteri.")
        String titolo,

        @NotBlank(message = "La descrizione è obbligatoria.")
        @Size(min = 5, max = 255, message = "La descrizione deve avere tra 5 e 255 caratteri.")
        String descrizione,

        @NotNull(message = "La data è obbligatoria.")
        @FutureOrPresent(message = "La data dell'evento non può essere nel passato.")
        LocalDate data,

        @NotBlank(message = "Il luogo è obbligatorio.")
        @Size(min = 2, max = 100, message = "Il luogo deve avere tra 2 e 100 caratteri.")
        String luogo,

        @NotNull(message = "Il numero di posti è obbligatorio.")
        @Min(value = 1, message = "Ci deve essere almeno un posto disponibile.")
        int numeroPostiDisponibili) {
}