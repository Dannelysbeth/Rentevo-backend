package dannelysbeth.rentevo.postgres.rentevo_backend_postgres.api;

import dannelysbeth.rentevo.postgres.rentevo_backend_postgres.model.Country;
import dannelysbeth.rentevo.postgres.rentevo_backend_postgres.service.definition.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/country")
public class CountryController {

    private final CountryService countryService;

    @GetMapping
    public ResponseEntity<Set<Country>> findAll(@RequestParam(required = false) String startsWith,
                                                @RequestParam(required = false) String code) {
        return ResponseEntity.ok()
                .body(countryService.findAll(startsWith, code));

    }

}
