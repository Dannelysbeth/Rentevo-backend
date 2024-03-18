package dannelysbeth.rentevo.postgres.rentevo_backend_postgres.api;

import dannelysbeth.rentevo.postgres.rentevo_backend_postgres.model.Country;
import dannelysbeth.rentevo.postgres.rentevo_backend_postgres.service.definition.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@CrossOrigin
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

    @PostMapping
    public ResponseEntity<String> importCountries(@RequestBody Set<Country> countries) {
        countryService.importCountries(countries);
        return ResponseEntity.ok()
                .body("Countries imported successfully");

    }

}
