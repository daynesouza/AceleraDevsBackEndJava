package store.games.api.domain.relatorios;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.Date;

public record DataJson(
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="dd/MM/yyyy")
        LocalDate date
) {
}
